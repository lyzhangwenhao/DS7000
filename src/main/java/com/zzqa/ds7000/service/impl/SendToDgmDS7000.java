package com.zzqa.ds7000.service.impl;

import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.dau_cfg.*;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Upgrade_file_info;
import com.zzqa.ds7000.service.interfaces.ISendToDgm;
import com.zzqa.ds7000.util.FormatTransfer;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SendToDgmDS7000
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/12 13:52
 */
@Service
public class SendToDgmDS7000 implements ISendToDgm {
    public static ThreadLocal<Integer> update_status = new ThreadLocal<>();
    public static ThreadLocal<Integer> upgrade_status = new ThreadLocal<>();
//    private int config_status = 0;

    /**
     * 发送时间
     *
     * @param response ServletResponse
     * @param head7000 包头
     */
    @Override
    public void sendTime(ServletResponse response, Head7000 head7000) {
        if (head7000 == null){  //防止出现空指针
            head7000 = new Head7000();
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            int i = FormatTransfer.getUtcTime();
            dos.writeInt(i);
            byte[] time_data = baos.toByteArray();

            dos.flush();
            dos.close();
            baos.flush();
            baos.close();
            conn(response, time_data, head7000, head7000.getAppDataNum());
        } catch (IOException e) {
            head7000.setErrorCode(1999);    //返回错误代码
            conn(response, null, head7000,0);
        }
    }

    /**
     * 通信
     *
     * @param response ServletResponse
     * @param data     数据
     * @param head7000     包头
     * @param appDataNum  重复次数
     * @return  返回结果
     */
    @Override
    public boolean conn(ServletResponse response, byte[] data, Head7000 head7000, int appDataNum) {
        boolean bool = true;
        try {
            int dataLen = 0;
            if (data != null) {
                dataLen = data.length;
            }
            OutputStream os = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] head = Head7000.creatHead(head7000);
            baos.write(head);
            if (dataLen != 0) {
                if (appDataNum > 0){
                    //重复appDataNum个，如果为1则为不重复
                    for (int i=0; i<appDataNum; i++){
                        baos.write(data);
                    }
                }
            }
            byte[] b = baos.toByteArray();
            baos.flush();
            baos.close();
            // 写出
            os.write(b);
            os.flush();
            os.close();
        } catch (IOException e) {
            // 基础发送方法错误
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(),e);
            bool = false;
        }
        return bool;
    }

    /**
     * 向采集器发送配置信息等
     *
     * @param response ServletResponse
     * @param head7000 请求头
     */
    @Override
    public void sendDgmTask(ServletResponse response, Head7000 head7000) {
        byte[] data = null;
        if (head7000 == null){
            head7000 = new Head7000();
            head7000.setErrorCode(1000);
            head7000.setProtocolID(255);
            conn(response, null, head7000, 0);
        }
        head7000.setErrorCode(0);
        try {
            //服务器更新了采集器配置
            if (update_status.get() == 1){
                head7000.setProtocolID(3);
                data = getDgmConfig(head7000, response);
                conn(response, data, head7000, 1);
            }
            if (upgrade_status.get() == 1){
                head7000.setProtocolID(8);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                //TODO 从CMC获取升级内容
                List<Upgrade_file_info> upgradeList = new ArrayList<>();
                if (upgradeList == null || upgradeList.size() == 0){
                    head7000.setProtocolID(255);
                    conn(response, null, head7000, 0);
                    return;
                }
                head7000.setAppDataNum(upgradeList.size());
                for (Upgrade_file_info upgrade_file_info:upgradeList){
                    dos.writeInt(upgrade_file_info.getDwStructLen());
                    dos.writeShort(upgrade_file_info.getFileTotal());
                    dos.writeShort(upgrade_file_info.getSquenceNo());
                    dos.writeShort(upgrade_file_info.getFileNameLen());
                    dos.write(upgrade_file_info.getFile_name());
                    dos.writeShort(upgrade_file_info.getDestDirLen());
                    dos.write(upgrade_file_info.getDest_dir());
                    dos.writeShort(upgrade_file_info.getFile_ver());
                    dos.writeByte(upgrade_file_info.getFile_groupId());
                    dos.writeByte(upgrade_file_info.getIf_exec());
                    dos.writeByte(upgrade_file_info.getIf_forced());
                    dos.writeByte(upgrade_file_info.getIf_restarted());
                    dos.writeByte(upgrade_file_info.getIf_crc());
                    dos.writeInt(upgrade_file_info.getDwFileCrc());
                    dos.writeInt(upgrade_file_info.getFile_len());
                    dos.write(upgrade_file_info.getFileStream());
                }
                data = baos.toByteArray();
                dos.flush();
                dos.close();
                baos.flush();
                baos.close();
                //发送数据
                boolean conn = conn(response, data, head7000, head7000.getAppDataNum());
                //下面不能加外面的条件，否则这个如果一直执行失败后面的if中都无法执行
//                if (conn){
                    upgrade_status.set(0);
//                }

            }
        } catch (Exception e){
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(),e);
            head7000.setErrorCode(1999);
            conn(response, null, head7000, 0);
        }
    }

    /**
     * 向采集器发送配置
     *
     * @param response ServletResponse
     * @param head7000 请求头
     */
    @Override
    public void sendDgmConfig(ServletResponse response, Head7000 head7000) {
        if (head7000 == null || head7000.getMaindata() == null) {
            head7000 = new Head7000();
            head7000.setProtocolID(4);
            head7000.setErrorCode(1000);
            return;
        }
        InputStream in = null;
        DataInputStream dis = null;
        try {
            byte[] maindata = head7000.getMaindata();
            if (maindata == null){
                head7000 = new Head7000();
                head7000.setProtocolID(4);
                head7000.setErrorCode(1000);
                return;
            }

            in = new ByteArrayInputStream(maindata);
            dis = new DataInputStream(in);
            long nodeId = dis.readLong();
            //关流
            dis.close();
            in.close();
            //获取采集器设置
            byte[] data = getDgmConfig(head7000,response);

            conn(response,data,head7000,1 );
        } catch (Exception e) {
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
            head7000.setErrorCode(1999);
            conn(response, null, head7000, 0);
        }
    }

    private byte[] getDgmConfig(Head7000 head7000,ServletResponse response){
        byte[] data = null;
        if (head7000 == null){
            return data;
        }
        int protocolID = head7000.getProtocolID();
        InputStream in = null;
        DataInputStream dis = null;
        //TODO 根据条件向CMC获取采集器设置
        DAU_ALL_CFG dau_all_cfg = new DAU_ALL_CFG();
        //根据下面情况分别取数据
        switch (protocolID){
            case 3:
                //TODO 向CMC获取DGM设置
//                dau_all_cfg =
                break;
            case 4:
                try {
                    in = new ByteArrayInputStream(head7000.getMaindata());
                    dis = new DataInputStream(in);
                    long nodeId = dis.readLong();
                    //TODO 获取设置
//                    dau_all_cfg =
                } catch (IOException e){
                    DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
                    head7000.setErrorCode(1999);
                    conn(response, null, head7000, 0);
                }

                break;
            default:
                break;
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            dos.writeInt(dau_all_cfg.getDwStructLen());

            //振动通道信息
            dos.writeInt(dau_all_cfg.getVib_num());

            DAU_CHL_VIB_CFG vib_chl = dau_all_cfg.getVib_chl();
            vib_chl = vib_chl == null ? new DAU_CHL_VIB_CFG() : vib_chl;
            dos.writeInt(vib_chl.getDwStructLen());

            C_DAU_CHL_CFG dau_chl = vib_chl.getDau_chl();
            C_DAU_CHL_VIB_CFG dau_vib_chl = vib_chl.getDau_vib_chl();
            writeDauChl2Dos(dos,dau_chl);
            dau_vib_chl = dau_vib_chl == null ? new C_DAU_CHL_VIB_CFG() : dau_vib_chl;

            dos.writeInt(dau_vib_chl.getId_dauchl());
            dos.writeShort(dau_vib_chl.getChl_no());
            dos.writeByte(dau_vib_chl.getSensor_type());
            dos.write(FormatTransfer.stringToBytes(dau_vib_chl.getEu(),20));
            dos.write(FormatTransfer.stringToBytes(dau_vib_chl.getSensor_model(), 50));
            dos.write(FormatTransfer.stringToBytes(dau_vib_chl.getSensor_vendor(), 50));
            dos.writeFloat(dau_vib_chl.getInstall_angle());
            dos.writeByte(dau_vib_chl.getCurrent_feed());
            dos.writeFloat(dau_vib_chl.getScale());
            dos.writeFloat(dau_vib_chl.getZero_shift());
            dos.writeFloat(dau_vib_chl.getAc_corr());
            dos.writeFloat(dau_vib_chl.getDc_corr());
            dos.writeFloat(dau_vib_chl.getAc_intg_corr());
            dos.writeByte(dau_vib_chl.getInput_vol_gear());
            dos.writeByte(dau_vib_chl.getCable_check());
            dos.writeFloat(dau_vib_chl.getV_min());
            dos.writeFloat(dau_vib_chl.getV_max());
            dos.writeFloat(dau_vib_chl.getGraphXMin());
            dos.writeFloat(dau_vib_chl.getGraphXMax());
            dos.writeFloat(dau_vib_chl.getGraphYMin());
            dos.writeFloat(dau_vib_chl.getGraphYMax());

            //过程量通道信息
            dos.writeInt(dau_all_cfg.getPro_num());
            DAU_CHL_PRO_CFG pro_chl = dau_all_cfg.getPro_chl();
            pro_chl = pro_chl == null ? new DAU_CHL_PRO_CFG() : pro_chl;
            dos.writeInt(pro_chl.getDwStructLen());

            dau_chl = pro_chl.getDau_chl();
            writeDauChl2Dos(dos, dau_chl);

            C_DAU_CHL_PRO_CFG dau_pro_chl = pro_chl.getDau_pro_chl();
            dau_pro_chl = dau_pro_chl == null ? new C_DAU_CHL_PRO_CFG() : dau_pro_chl;
            dos.writeInt(dau_pro_chl.getId_dauchl());
            dos.writeShort(dau_pro_chl.getChl_no());
            dos.writeByte(dau_pro_chl.getSensor_type());
            dos.write(FormatTransfer.stringToBytes(dau_pro_chl.getEu(), 20));
            dos.write(FormatTransfer.stringToBytes(dau_pro_chl.getSensor_model(), 50));
            dos.write(FormatTransfer.stringToBytes(dau_pro_chl.getSensor_vendor(), 50));
            dos.writeFloat(dau_pro_chl.getInstall_angle());
            dos.writeByte(dau_pro_chl.getCurrent_feed());
            dos.writeFloat(dau_pro_chl.getScale());
            dos.writeFloat(dau_pro_chl.getZero_shift());
            dos.writeFloat(dau_pro_chl.getCorr());
            dos.writeByte(dau_pro_chl.getCable_check());
            dos.writeFloat(dau_pro_chl.getV_min());
            dos.writeFloat(dau_pro_chl.getV_max());
            dos.writeFloat(dau_pro_chl.getGraphXMin());
            dos.writeFloat(dau_pro_chl.getGraphXMax());
            dos.writeFloat(dau_pro_chl.getGraphYMin());
            dos.writeFloat(dau_pro_chl.getGraphYMax());

            //数字量通道信息
            dos.writeInt(dau_all_cfg.getDgt_num());
            DAU_CHL_DGT_CFG dgt_chl = dau_all_cfg.getDgt_chl();
            dgt_chl =  dgt_chl == null ? new DAU_CHL_DGT_CFG() : dgt_chl;
            dos.writeInt(dgt_chl.getDwStructLen());

            dau_chl = dgt_chl.getDau_chl();
            writeDauChl2Dos(dos, dau_chl);

            C_DAU_CHL_DGT_CFG dau_dgt_chl = dgt_chl.getDau_dgt_chl();
            dau_dgt_chl = dau_dgt_chl == null ? new C_DAU_CHL_DGT_CFG() : dau_dgt_chl;
            dos.writeInt(dau_dgt_chl.getId_dauchl());
            dos.writeShort(dau_dgt_chl.getChl_no());
            dos.writeByte(dau_dgt_chl.getSensor_type());
            dos.write(FormatTransfer.stringToBytes(dau_dgt_chl.getEu(), 20));
            dos.write(FormatTransfer.stringToBytes(dau_dgt_chl.getSensor_model(), 50));
            dos.write(FormatTransfer.stringToBytes(dau_dgt_chl.getSensor_vendor(), 50));
            dos.writeFloat(dau_dgt_chl.getInstall_angle());
            dos.writeByte(dau_dgt_chl.getVol_trigger());
            dos.writeByte(dau_dgt_chl.getVol_type());
            dos.writeByte(dau_dgt_chl.getVol_gear());
            dos.writeByte(dau_dgt_chl.getPolarity());
            dos.writeByte(dau_dgt_chl.getTrigger_type());
            dos.writeByte(dau_dgt_chl.getCable_check());
            dos.writeFloat(dau_dgt_chl.getV_min());
            dos.writeFloat(dau_dgt_chl.getV_max());
            dos.writeFloat(dau_dgt_chl.getAc_corr());
            dos.writeFloat(dau_dgt_chl.getDc_corr());

            //串口通道信息
            dos.writeInt(dau_all_cfg.getCom_num());
            DAU_CHL_COM_CFG com_chl = dau_all_cfg.getCom_chl();
            com_chl = com_chl == null ? new DAU_CHL_COM_CFG() : com_chl;
            dos.writeInt(com_chl.getDwStructLen());

            dau_chl = com_chl.getDau_chl();
            writeDauChl2Dos(dos, dau_chl);

            C_DAU_CHL_COM_CFG dau_com_chl = com_chl.getDau_com_chl();
            dau_com_chl = dau_com_chl == null ? new C_DAU_CHL_COM_CFG() : dau_com_chl;
            dos.writeInt(dau_com_chl.getId_dauchl());
            dos.writeShort(dau_com_chl.getChl_no());
            dos.writeByte(dau_com_chl.getChl_type());
            dos.writeByte(dau_com_chl.getMaster_slave());
            if (dau_com_chl.getMaster_slave() == 1) {
                dos.writeByte(dau_com_chl.getSlaveid());
            }
            dos.writeByte(dau_com_chl.getCommu_mode());
            dos.writeInt(dau_com_chl.getBaudrate());
            dos.writeByte(dau_com_chl.getParity());
            dos.writeByte(dau_com_chl.getDatabit());
            dos.writeByte(dau_com_chl.getStopbit());
            dos.writeByte(dau_com_chl.getCrc());

            //转速测点信息
            dos.writeInt(dau_all_cfg.getSpeed_tag_num());
            TAG_SPEED_CFG speed_tag = dau_all_cfg.getSpeed_tag();
            speed_tag = speed_tag == null ? new TAG_SPEED_CFG() : speed_tag;
            dos.writeInt(speed_tag.getDwStructLen());

            C_SYS_NODE_CFG node = speed_tag.getNode();
            C_TAG_COMMON tag_comm = speed_tag.getTag_comm();
            writeTag2Dos(dos,node,tag_comm);

            C_TAG_SPEED_CFG c_tag_speed_cfg = speed_tag.getSpeed_tag();
            c_tag_speed_cfg = c_tag_speed_cfg == null ? new C_TAG_SPEED_CFG() : c_tag_speed_cfg;
            dos.writeLong(c_tag_speed_cfg.getId_node());
            dos.writeInt(c_tag_speed_cfg.getId_dauchl());
            dos.writeInt(c_tag_speed_cfg.getId_speed());
            dos.writeInt(c_tag_speed_cfg.getId_process());
            dos.writeInt(c_tag_speed_cfg.getId_digital());
            dos.writeFloat(c_tag_speed_cfg.getPulse_rev());
            dos.writeByte(c_tag_speed_cfg.getEv_act_range_type0());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_min0());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_max0());
            dos.writeByte(c_tag_speed_cfg.getEv_act_range_type1());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_min1());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_max1());
            dos.writeByte(c_tag_speed_cfg.getHh_act());
            dos.writeFloat(c_tag_speed_cfg.getHh_level());
            dos.writeByte(c_tag_speed_cfg.getH_act());
            dos.writeFloat(c_tag_speed_cfg.getH_level());
            dos.writeByte(c_tag_speed_cfg.getLl_act());
            dos.writeFloat(c_tag_speed_cfg.getLl_level());
            dos.writeByte(c_tag_speed_cfg.getL_act());
            dos.writeFloat(c_tag_speed_cfg.getL_level());
            dos.writeByte(c_tag_speed_cfg.getAlarm_enter());
            dos.writeByte(c_tag_speed_cfg.getAlarm_level());
            dos.writeByte(c_tag_speed_cfg.getSpeed_wave());
            dos.writeShort(c_tag_speed_cfg.getStored_intvl());
            dos.writeShort(c_tag_speed_cfg.getDelta_percent());
            dos.writeFloat(c_tag_speed_cfg.getDelta_level());
            dos.writeByte(c_tag_speed_cfg.getDiretion());
            dos.writeShort(c_tag_speed_cfg.getMolecule());
            dos.writeShort(c_tag_speed_cfg.getDenominator());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_delta0());
            dos.writeFloat(c_tag_speed_cfg.getEv_act_range_delta1());
            dos.writeFloat(c_tag_speed_cfg.getRange_h());
            dos.writeFloat(c_tag_speed_cfg.getRange_l());

            //数字量测点
            dos.writeInt(dau_all_cfg.getDgt_tag_num());
            TAG_DGT_CFG dgt_tag = dau_all_cfg.getDgt_tag();
            dgt_tag = dgt_tag == null ? new TAG_DGT_CFG() : dgt_tag;
            dos.writeInt(dgt_tag.getDwStructLen());

            node = dgt_tag.getNode_cfg();
            tag_comm = dgt_tag.getTag_comm();
            writeTag2Dos(dos,node,tag_comm);

            C_TAG_DGT_CFG digital_tag = dgt_tag.getDigital_tag();
            digital_tag = digital_tag == null ? new C_TAG_DGT_CFG() : digital_tag;
            dos.writeLong(digital_tag.getId_node());
            dos.writeInt(digital_tag.getId_dauchl());
            dos.writeByte(digital_tag.getAlarm_trigg());
            dos.writeByte(digital_tag.getAlarm_enter());
            dos.writeByte(digital_tag.getAlarm_leave());
            dos.writeShort(digital_tag.getStored_intvl());
            dos.writeByte(digital_tag.getHh_act());
            dos.writeFloat(digital_tag.getHh_level());
            dos.writeByte(digital_tag.getH_act());
            dos.writeFloat(digital_tag.getH_level());
            dos.writeByte(digital_tag.getLl_act());
            dos.writeFloat(digital_tag.getLl_level());
            dos.writeByte(digital_tag.getL_act());
            dos.writeFloat(digital_tag.getL_level());
            dos.writeFloat(digital_tag.getRange_h());
            dos.writeFloat(digital_tag.getRange_l());

            //振动测点
            dos.writeInt(dau_all_cfg.getVib_tag_num());
            TAG_VIB_CFG vib_tag = dau_all_cfg.getVib_tag();
            vib_tag =  vib_tag == null ? new TAG_VIB_CFG() : vib_tag;
            dos.writeInt(vib_tag.getDwStructLen());

            node = vib_tag.getNode_cfg();
            tag_comm = vib_tag.getTag_comm();
            writeTag2Dos(dos, node, tag_comm);

            C_TAG_VIB_CFG c_tag_vib_cfg = vib_tag.getVib_tag();
            c_tag_vib_cfg = c_tag_vib_cfg == null ? new C_TAG_VIB_CFG() : c_tag_vib_cfg;
            dos.writeLong(c_tag_vib_cfg.getId_node());
            dos.writeByte(c_tag_vib_cfg.getChl_num());
            dos.writeInt(c_tag_vib_cfg.getId_dauchl1());
            dos.writeInt(c_tag_vib_cfg.getId_dauchl2());
            dos.writeInt(c_tag_vib_cfg.getId_dauchl3());
            dos.writeInt(c_tag_vib_cfg.getId_plain());
            dos.writeLong(c_tag_vib_cfg.getId_speed());
            dos.writeInt(c_tag_vib_cfg.getId_process());
            dos.writeInt(c_tag_vib_cfg.getId_digital());
            dos.writeByte(c_tag_vib_cfg.getVib_type());
            dos.writeByte(c_tag_vib_cfg.getDiretion());
            dos.writeFloat(c_tag_vib_cfg.getRef_gap_vol());
            dos.writeByte(c_tag_vib_cfg.getWave_window());
            dos.writeShort(c_tag_vib_cfg.getWave_fft_lines());
            dos.writeShort(c_tag_vib_cfg.getWave_run_cycle());
            dos.writeByte(c_tag_vib_cfg.getWave_env_filter());
            dos.writeFloat(c_tag_vib_cfg.getWave_freq_range());
            dos.writeFloat(c_tag_vib_cfg.getWave_freq_cutoff());
            dos.writeByte(c_tag_vib_cfg.getWave_avg_type());
            dos.writeShort(c_tag_vib_cfg.getWave_avg_no());
            dos.writeShort(c_tag_vib_cfg.getEv_lines());
            dos.writeByte(c_tag_vib_cfg.getEv_act_range_type0());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_min0());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_max0());
            dos.writeByte(c_tag_vib_cfg.getEv_act_range_type1());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_min1());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_max1());
            dos.writeByte(c_tag_vib_cfg.getWave_act_range_type0());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_min0());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_max0());
            dos.writeByte(c_tag_vib_cfg.getWave_act_range_type1());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_min1());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_max1());
            dos.writeShort(c_tag_vib_cfg.getEv_stored_intvl());
            dos.writeShort(c_tag_vib_cfg.getEv_realtime_intvl());
            dos.writeShort(c_tag_vib_cfg.getEv_realtime_intvl());
            dos.writeShort(c_tag_vib_cfg.getWave_stored_intvl());
            dos.writeShort(c_tag_vib_cfg.getWave_realtime_intvl());
            dos.writeByte(c_tag_vib_cfg.getAlarm_enter());
            dos.writeByte(c_tag_vib_cfg.getAlarm_leave());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type1());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name1(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point1());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range1());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert1());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm1());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act1());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type2());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name2(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point2());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range2());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert2());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm2());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act2());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type3());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name3(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point3());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range3());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert3());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm3());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act3());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type4());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name4(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point4());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range4());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert4());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm4());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act4());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type5());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name5(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point5());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range5());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert5());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm5());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act5());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_type6());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getFreq_bd_name6(), 20));
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_point6());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_range6());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alert6());
            dos.writeFloat(c_tag_vib_cfg.getFreq_bd_alarm6());
            dos.writeByte(c_tag_vib_cfg.getFreq_bd_ld_act6());
            dos.writeByte(c_tag_vib_cfg.getAllover_type());
            dos.write(FormatTransfer.stringToBytes(c_tag_vib_cfg.getAllover_name(), 20));
            dos.writeFloat(c_tag_vib_cfg.getAllover_s());
            dos.writeFloat(c_tag_vib_cfg.getAllover_e());
            dos.writeFloat(c_tag_vib_cfg.getAllover_alert());
            dos.writeFloat(c_tag_vib_cfg.getAllover_alarm());
            dos.writeShort(c_tag_vib_cfg.getAllover_delta_percent());
            dos.writeFloat(c_tag_vib_cfg.getAllover_delta_level());
            dos.writeByte(c_tag_vib_cfg.getAllover_ld_act());
            dos.writeByte(c_tag_vib_cfg.getLd_type());
            dos.writeFloat(c_tag_vib_cfg.getLd0());
            dos.writeFloat(c_tag_vib_cfg.getLd1());
            dos.writeFloat(c_tag_vib_cfg.getLd2());
            dos.writeFloat(c_tag_vib_cfg.getLd3());
            dos.writeFloat(c_tag_vib_cfg.getLd4());
            dos.writeFloat(c_tag_vib_cfg.getLd5());
            dos.writeFloat(c_tag_vib_cfg.getLd6());
            dos.writeFloat(c_tag_vib_cfg.getLd7());
            dos.writeFloat(c_tag_vib_cfg.getLd8());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent1());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent2());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent3());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent4());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent5());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent6());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent7());
            dos.writeFloat(c_tag_vib_cfg.getLd_percent8());
            dos.writeByte(c_tag_vib_cfg.getWave_mode());
            if (c_tag_vib_cfg.getWave_mode() == 0 || c_tag_vib_cfg.getWave_mode() ==2) {
                dos.writeFloat(c_tag_vib_cfg.getFft_thld());
                dos.writeShort(c_tag_vib_cfg.getFft_max_xf());
            }
            dos.writeFloat(c_tag_vib_cfg.getLf_cursor());
            dos.writeFloat(c_tag_vib_cfg.getHf_cursor());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_delta0());
            dos.writeFloat(c_tag_vib_cfg.getEv_act_range_delta1());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_delta0());
            dos.writeFloat(c_tag_vib_cfg.getWave_act_range_delta1());
            dos.writeFloat(c_tag_vib_cfg.getRange_h());
            dos.writeFloat(c_tag_vib_cfg.getRange_l());
            dos.writeByte(c_tag_vib_cfg.getBearingType());
            dos.writeFloat(c_tag_vib_cfg.getNormalSpeed());

            //过程量测点
            dos.writeInt(dau_all_cfg.getPro_tag_num());
            TAG_PRO_CFG pro_tag = dau_all_cfg.getPro_tag();
            pro_tag = pro_tag == null ? new TAG_PRO_CFG() : pro_tag;
            node = pro_tag.getNode_cfg();
            tag_comm = pro_tag.getTag_comm();
            writeTag2Dos(dos, node, tag_comm);

            C_TAG_PRO_CFG c_tag_pro_cfg = pro_tag.getPro_tag();
            c_tag_pro_cfg = c_tag_pro_cfg == null ? new C_TAG_PRO_CFG() : c_tag_pro_cfg;
            dos.writeLong(c_tag_pro_cfg.getId_node());
            dos.writeInt(c_tag_pro_cfg.getId_dauchl());
            dos.writeLong(c_tag_pro_cfg.getId_speed());
            dos.writeInt(c_tag_pro_cfg.getId_process());
            dos.writeInt(c_tag_pro_cfg.getId_digital());
            dos.writeByte(c_tag_pro_cfg.getEv_act_range_type0());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_min0());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_max0());
            dos.writeByte(c_tag_pro_cfg.getEv_act_range_type1());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_min1());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_max1());
            dos.writeByte(c_tag_pro_cfg.getHh_act());
            dos.writeFloat(c_tag_pro_cfg.getHh_level());
            dos.writeByte(c_tag_pro_cfg.getH_act());
            dos.writeFloat(c_tag_pro_cfg.getH_level());
            dos.writeByte(c_tag_pro_cfg.getLl_act());
            dos.writeFloat(c_tag_pro_cfg.getLl_level());
            dos.writeByte(c_tag_pro_cfg.getL_act());
            dos.writeFloat(c_tag_pro_cfg.getL_level());
            dos.writeByte(c_tag_pro_cfg.getAlarm_enter());
            dos.writeByte(c_tag_pro_cfg.getAlarm_leave());
            dos.writeShort(c_tag_pro_cfg.getStored_intvl());
            dos.writeShort(c_tag_pro_cfg.getDelta_percent());
            dos.writeFloat(c_tag_pro_cfg.getDelta_level());
            dos.writeFloat(c_tag_pro_cfg.getScale());
            dos.writeByte(c_tag_pro_cfg.getMes_range_act());
            dos.writeFloat(c_tag_pro_cfg.getMes_range_min());
            dos.writeFloat(c_tag_pro_cfg.getMes_range_max());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_delta0());
            dos.writeFloat(c_tag_pro_cfg.getEv_act_range_delta1());
            dos.writeFloat(c_tag_pro_cfg.getRange_h());
            dos.writeFloat(c_tag_pro_cfg.getRange_l());

            //Modbus测点数据
            dos.writeInt(dau_all_cfg.getMdb_tag_num());
            TAG_MDB_CFG mdb_tag = dau_all_cfg.getMdb_tag();
            mdb_tag = mdb_tag == null ? new TAG_MDB_CFG() : mdb_tag;
            dos.writeInt(mdb_tag.getDwStructLen());
            node = mdb_tag.getNode_cfg();
            tag_comm = mdb_tag.getTag_comm();
            writeTag2Dos(dos, node, tag_comm);

            C_TAG_MDB_CFG modbus_tag = mdb_tag.getModbus_tag();
            modbus_tag = modbus_tag == null ? new C_TAG_MDB_CFG() : modbus_tag;
            dos.writeLong(modbus_tag.getId_node());
            dos.writeInt(modbus_tag.getChl_no());
            dos.writeInt(modbus_tag.getData_address());
            dos.writeByte(modbus_tag.getData_mode());
            dos.writeByte(modbus_tag.getSlave_id());
            dos.writeByte(modbus_tag.getTag_type());
            dos.writeShort(modbus_tag.getStored_intvl());
            dos.writeShort(modbus_tag.getDelta_percent());
            dos.writeFloat(modbus_tag.getDelta_level());
            dos.writeFloat(modbus_tag.getInput_value_1());
            dos.writeFloat(modbus_tag.getInput_value_2());
            dos.writeFloat(modbus_tag.getRef_value_1());
            dos.writeFloat(modbus_tag.getRef_value_2());
            dos.writeFloat(modbus_tag.getScale());
            dos.writeByte(modbus_tag.getAlarm_trigg());
            dos.writeByte(modbus_tag.getHh_act());
            dos.writeFloat(modbus_tag.getHh_level());
            dos.writeByte(modbus_tag.getH_act());
            dos.writeFloat(modbus_tag.getH_level());
            dos.writeByte(modbus_tag.getLl_act());
            dos.writeFloat(modbus_tag.getLl_level());
            dos.writeByte(modbus_tag.getL_act());
            dos.writeFloat(modbus_tag.getL_level());
            dos.writeByte(modbus_tag.getAlarm_enter());
            dos.writeByte(modbus_tag.getAlarm_leave());
            dos.writeFloat(modbus_tag.getEv_act_range_delta0());
            dos.writeFloat(modbus_tag.getEv_act_range_delta1());
            dos.writeInt(modbus_tag.getEu_len());
            dos.write(FormatTransfer.stringToBytes(modbus_tag.getEu(), modbus_tag.getEu_len()));
            dos.writeByte(modbus_tag.getComDataType());
            dos.writeFloat(modbus_tag.getRange_h());
            dos.writeFloat(modbus_tag.getRange_l());
            if (modbus_tag.getTag_type() == 1 || modbus_tag.getTag_type() == 2){
                dos.writeInt(modbus_tag.getUnit0_len());
                dos.write(FormatTransfer.stringToBytes(modbus_tag.getUnit0(), modbus_tag.getUnit0_len()));
                dos.writeInt(modbus_tag.getUnit1_len());
                dos.write(FormatTransfer.stringToBytes(modbus_tag.getUnit1(), modbus_tag.getUnit1_len()));
                if (modbus_tag.getTag_type() == 2){
                    dos.writeFloat(modbus_tag.getSwPara0());
                    dos.writeFloat(modbus_tag.getSwPara1());
                }
            }

            //测量组数据
            dos.writeInt(dau_all_cfg.getRun_group_num());
            RUN_GROUP_CFG run_group = dau_all_cfg.getRun_group();
            run_group = run_group == null ? new RUN_GROUP_CFG() : run_group;
            dos.writeInt(run_group.getDwStructLen());
            C_RUN_GROUP c_run_group = run_group.getRun_group();
            c_run_group = c_run_group == null ? new C_RUN_GROUP() : c_run_group;
            dos.writeInt(c_run_group.getId_rungroup());
            dos.write(FormatTransfer.stringToBytes(c_run_group.getName(), 50));
            dos.writeShort(c_run_group.getId_dau());
            dos.writeByte(c_run_group.getFreq_type());
            dos.writeLong(c_run_group.getId_speed());
            dos.writeLong(c_run_group.getId_process());
            dos.writeLong(c_run_group.getId_digital());
            dos.writeShort(c_run_group.getWave_fft_lines());
            if (c_run_group.getFreq_type() == 1) {
                dos.writeFloat(c_run_group.getWave_freq_range());
            }
            if (c_run_group.getFreq_type() == 0) {
                dos.writeShort(c_run_group.getWave_run_cycles());
                dos.writeShort(c_run_group.getWave_susd_cycles());
            }
            dos.writeShort(c_run_group.getDelta_speed());
            dos.writeShort(c_run_group.getSusd_stored_intvl());
            if (c_run_group.getFreq_type() == 0) {
                dos.writeByte(c_run_group.getSu_sample_mode());
                dos.writeByte(c_run_group.getSd_sample_mode());
                dos.writeFloat(c_run_group.getRun_speed());
                dos.writeFloat(c_run_group.getSpeed_swing());
                dos.writeFloat(c_run_group.getMin_speed());
                dos.writeFloat(c_run_group.getMax_speed());
            }
            dos.writeByte(c_run_group.getEv_act_range_type0());
            dos.writeFloat(c_run_group.getEv_act_range_min0());
            dos.writeFloat(c_run_group.getEv_act_range_max0());
            dos.writeByte(c_run_group.getEv_act_range_type1());
            dos.writeFloat(c_run_group.getEv_act_range_min1());
            dos.writeFloat(c_run_group.getEv_act_range_max1());
            dos.writeByte(c_run_group.getWave_act_range_type0());
            dos.writeFloat(c_run_group.getWave_act_range_min0());
            dos.writeFloat(c_run_group.getWave_act_range_max0());
            dos.writeByte(c_run_group.getWave_act_range_type1());
            dos.writeFloat(c_run_group.getWave_act_range_min1());
            dos.writeFloat(c_run_group.getWave_act_range_max1());
            dos.writeShort(c_run_group.getEv_stored_intvl());
            dos.writeShort(c_run_group.getEv_realtime_intvl());
            dos.writeShort(c_run_group.getWave_stored_intvl());
            dos.writeShort(c_run_group.getWave_realtime_intvl());
            if (c_run_group.getFreq_type() == 0) {
                dos.writeByte(c_run_group.getBlack_box_triger());
                dos.writeShort(c_run_group.getBlack_box_intvl());
            }
            dos.writeShort(c_run_group.getEv_lines());
            dos.writeByte(c_run_group.getWave_avg_type());
            dos.writeShort(c_run_group.getWave_avg_no());
            dos.writeFloat(c_run_group.getEv_act_range_delta0());
            dos.writeFloat(c_run_group.getEv_act_range_delta1());
            dos.writeFloat(c_run_group.getWave_act_range_delta0());
            dos.writeFloat(c_run_group.getWave_act_range_delta1());
            dos.writeLong(c_run_group.getId_node());

            //2.0新增的网口通道
            dos.writeInt(dau_all_cfg.getWlan_chl_num());
            DAU_CHL_WLAN_CFG wlan_chl = dau_all_cfg.getWlan_chl();
            dos.writeInt(wlan_chl.getDwStructLen());
            dau_chl = wlan_chl.getDau_chl();
            writeDauChl2Dos(dos, dau_chl);
            C_DAU_CHL_WLAN_CFG dau_wlan_chl = wlan_chl.getDau_wlan_chl();
            dos.writeInt(dau_wlan_chl.getId_dauchl());
            dos.writeShort(dau_wlan_chl.getChl_no());
            dos.writeByte(dau_wlan_chl.getMaster_slave());
            dos.writeByte(dau_wlan_chl.getSlave_id());
            if (dau_wlan_chl.getMaster_slave() == 0) {
                dos.writeInt(dau_wlan_chl.getPoll_time());
                dos.write(FormatTransfer.stringToBytes(dau_wlan_chl.getSlave_ip(), 20));
            }
            dos.writeInt(dau_wlan_chl.getPort());

            //2.0新增的Modbus转速通道
            dos.writeInt(dau_all_cfg.getMdb_speed_tag_num());
            TAG_MDB_SPEED_CFG mdb_speed_tag = dau_all_cfg.getMdb_speed_tag();
            mdb_speed_tag = mdb_speed_tag == null ? new TAG_MDB_SPEED_CFG() : mdb_speed_tag;
            dos.writeInt(mdb_speed_tag.getDwStructLen());
            node = mdb_speed_tag.getNode_cfg();
            tag_comm = mdb_speed_tag.getTag_comm();
            writeTag2Dos(dos, node, tag_comm);

            C_TAG_MDB_SPEED_CFG c_tag_mdb_speed_cfg = mdb_speed_tag.getMdb_speed_tag();
            c_tag_mdb_speed_cfg = c_tag_mdb_speed_cfg == null ? new C_TAG_MDB_SPEED_CFG() : c_tag_mdb_speed_cfg;
            dos.writeLong(c_tag_mdb_speed_cfg.getId_node());
            dos.writeInt(c_tag_mdb_speed_cfg.getId_dauchl());
            dos.writeByte(c_tag_mdb_speed_cfg.getHh_act());
            dos.writeFloat(c_tag_mdb_speed_cfg.getHh_level());
            dos.writeByte(c_tag_mdb_speed_cfg.getH_act());
            dos.writeFloat(c_tag_mdb_speed_cfg.getH_level());
            dos.writeByte(c_tag_mdb_speed_cfg.getLl_act());
            dos.writeFloat(c_tag_mdb_speed_cfg.getLl_level());
            dos.writeByte(c_tag_mdb_speed_cfg.getL_act());
            dos.writeFloat(c_tag_mdb_speed_cfg.getL_level());
            dos.writeByte(c_tag_mdb_speed_cfg.getAlarm_enter());
            dos.writeByte(c_tag_mdb_speed_cfg.getAlarm_leave());
            dos.writeShort(c_tag_mdb_speed_cfg.getStored_intvl());
            dos.writeShort(c_tag_mdb_speed_cfg.getDelta_percent());
            dos.writeFloat(c_tag_mdb_speed_cfg.getDelta_level());
            dos.writeByte(c_tag_mdb_speed_cfg.getDiretion());
            dos.writeShort(c_tag_mdb_speed_cfg.getSpeed_fz());
            dos.writeShort(c_tag_mdb_speed_cfg.getSpeed_fm());
            dos.writeFloat(c_tag_mdb_speed_cfg.getRange_h());
            dos.writeFloat(c_tag_mdb_speed_cfg.getRange_l());
            dos.writeByte(c_tag_mdb_speed_cfg.getFuntion_code1());
            dos.writeInt(c_tag_mdb_speed_cfg.  getPlc_address1());
            dos.writeByte(c_tag_mdb_speed_cfg.getMdb_datatype1());
            dos.writeByte(c_tag_mdb_speed_cfg.getFuntion_code2());
            dos.writeInt(c_tag_mdb_speed_cfg.getPlc_address2());
            dos.writeByte(c_tag_mdb_speed_cfg.getMdb_datatype2());
            dos.writeByte(c_tag_mdb_speed_cfg.getFuntion_code3());
            dos.writeInt(c_tag_mdb_speed_cfg.getPlc_address3());
            dos.writeByte(c_tag_mdb_speed_cfg.getMdb_datatype3());




            dos.flush();
            baos.flush();
            data = baos.toByteArray();
            dos.close();
            baos.close();
        } catch (Exception e){
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
            head7000.setErrorCode(1999);
            conn(response, null, head7000, 0);
        }
        return data;
    }

    private void writeDauChl2Dos(DataOutputStream dos, C_DAU_CHL_CFG dau_chl) throws IOException{
        dau_chl = dau_chl == null ? new C_DAU_CHL_CFG() : dau_chl;
        dos.writeInt(dau_chl.getId_dauchl());
        dos.writeShort(dau_chl.getId_dau());
        dos.write(FormatTransfer.stringToBytes(dau_chl.getName(),50));
        dos.writeByte(dau_chl.getEnable());
        dos.writeByte(dau_chl.getChl_type());
    }
    private void writeTag2Dos(DataOutputStream dos, C_SYS_NODE_CFG node, C_TAG_COMMON tag_comm) throws IOException{
        node = node == null ? new C_SYS_NODE_CFG() : node;
        tag_comm = tag_comm == null ? new C_TAG_COMMON() : tag_comm;
        //node
        dos.writeLong(node.getId_node());
        dos.writeLong(node.getId_parent());
        dos.writeByte(node.getType_node());
        dos.writeByte(node.getEnable());
        dos.write(FormatTransfer.stringToBytes(node.getName(), 100));
        dos.writeInt(node.getSq_no());
        dos.write(FormatTransfer.stringToBytes(node.getComment(), 100));
        dos.writeInt(node.getStringLen());
        dos.write(FormatTransfer.stringToBytes(node.getUrl_cfg(), node.getStringLen()));
        //tag_comm
        dos.writeLong(tag_comm.getId_node());
        dos.writeInt(tag_comm.getId_rungroup());
        dos.writeShort(tag_comm.getId_dau());
        dos.write(FormatTransfer.stringToBytes(tag_comm.getEu(), 20));
        dos.writeByte(tag_comm.getEu_type());
        dos.writeByte(tag_comm.getAlarmBlocking());
    }

}
