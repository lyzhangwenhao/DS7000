package com.zzqa.ds7000.service.impl;

import com.alibaba.fastjson.JSON;
import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.dau_cfg.*;
import com.zzqa.ds7000.pojo.*;
import com.zzqa.ds7000.service.interfaces.ISaveData;
import com.zzqa.ds7000.util.FormatTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SaveData
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/13 15:50
 */
@Service
public class SaveData implements ISaveData {
    @Autowired
    private DAU_ALL_CFG dau_all_cfg;
    /**
     * 保存数据
     *
     * @param head7000 采集器传过来的数据
     */
    @Override
    public int saveData(Head7000 head7000) {
        if (head7000 == null || head7000.getMaindata() == null) {
            return 1000;
        }
        InputStream in = null;
        DataInputStream dis = null;
        try {
            byte[] maindata = head7000.getMaindata();
            if (maindata == null){
                return 1000;
            }
            int appDataNum = head7000.getAppDataNum();  //monitor_data重复个数
            int protocolID = head7000.getProtocolID();
            in = new ByteArrayInputStream(maindata);
            dis = new DataInputStream(in);

            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("head", head7000);
            List<Map<String,Object>> dataList = new ArrayList<>();
            switch (protocolID) {
                case 1:
                    //重复appDataNum个
                    for (int i=0; i<appDataNum; i++){
                        Map<String,Object> monitor_data = new HashMap<>();
                        dataList.add(monitor_data);
                        monitor_data.put("dwStructLen", dis.readInt());     //字节长度
                        //monitor_data_head
                        Monitor_data_head monitor_data_head = new Monitor_data_head();
                        monitor_data.put("monitor_data_head", monitor_data_head);

                        monitor_data_head.setDwStructLen(dis.readInt());
                        monitor_data_head.setDataTime(dis.readLong());
                        monitor_data_head.setSpeedStutas(dis.readByte());
                        monitor_data_head.setDataType(dis.readShort());
                        monitor_data_head.setSamples(dis.readShort());
                        monitor_data_head.setCycles(dis.readShort());
                        monitor_data_head.setVibNum(dis.readShort());
                        monitor_data_head.setProcNum(dis.readShort());
                        monitor_data_head.setWaveSpeedNum(dis.readShort());
                        float[] speed = new float[monitor_data_head.getWaveSpeedNum()];
                        for (int j=0; j<monitor_data_head.getWaveSpeedNum(); j++){
                            speed[j] = dis.readFloat();
                        }
                        monitor_data_head.setSpeed(speed);
                        monitor_data_head.setPorcessValue(dis.readFloat());
                        monitor_data_head.setDigitalValue(dis.readFloat());

                        //vib_data
                        for (int k=0; k<monitor_data_head.getVibNum(); k++){
                            Vib_data vib_data = new Vib_data();
                            monitor_data.put("vib_data", vib_data);

                            vib_data.setDwStructLen(dis.readInt());
                            vib_data.setId(dis.readLong());

                            vib_data.setAlarmStutas(dis.readInt());
                            vib_data.setVoltGap(dis.readFloat());
                            vib_data.setAMP_pp(dis.readFloat());
                            vib_data.setAMP_p(dis.readFloat());
                            vib_data.setRms(dis.readFloat());
                            vib_data.setAmp1x(dis.readFloat());
                            vib_data.setPhase1x(dis.readFloat());
                            vib_data.setAmp2x(dis.readFloat());
                            vib_data.setPhase2x(dis.readFloat());
                            vib_data.setAmpHx(dis.readFloat());
                            vib_data.setAmpOpt1(dis.readFloat());
                            vib_data.setAmpOpt2(dis.readFloat());
                            vib_data.setAmpOpt3(dis.readFloat());
                            vib_data.setAmpOpt4(dis.readFloat());
                            vib_data.setAmpOpt5(dis.readFloat());
                            vib_data.setAmpOpt6(dis.readFloat());
                            vib_data.setAmpRT(dis.readFloat());
                            vib_data.setAmpRTn1X(dis.readFloat());
                            vib_data.setAmpMaxVect(dis.readFloat());
                            vib_data.setDataType(dis.readShort());

                            vib_data.setAppendWave(dis.readByte());
                            if (vib_data.getAppendWave() == 1){
                                Vib_wave_data vib_wave_data = new Vib_wave_data();
                                vib_data.setWave(vib_wave_data);
                                readWave(dis, vib_wave_data, head7000);
                            }
                        }
                        //process_data
                        for (int l=0; l<monitor_data_head.getProcNum(); l++){
                            Procss_data procss_data = new Procss_data();
                            monitor_data.put("procss_data", procss_data);

                            procss_data.setDwStructLen(dis.readInt());
                            procss_data.setId(dis.readLong());

                            procss_data.setTagType(dis.readByte());
                            procss_data.setAlarmStatus(dis.readByte());
                            procss_data.setValue(dis.readByte());
                            procss_data.setDataType(dis.readShort());
                        }
                    }
//                    dataMap.put("monitor_data", dataList);
                    break;
                case 6:
                    //重复appDataNum个
                    for (int i=0; i<appDataNum; i++){
                        Map<String,Object> dau_log_code = new HashMap<>();
                        dataList.add(dau_log_code);

                        dau_log_code.put("dwStructLen", dis.readInt());
                        dau_log_code.put("dataTime", dis.readLong());
                        dau_log_code.put("logCode", dis.readShort());
                    }
//                    dataMap.put("dau_log_code", dataList);
                    break;
                case 7:
                    //重复appDataNum个
                    for (int i=0; i<appDataNum; i++){
                        Map<String,Object> dau_susd_index = new HashMap<>();
                        dataList.add(dau_susd_index);

                        dau_susd_index.put("dwStructLen", dis.readInt());
                        dau_susd_index.put("id_speed", dis.readLong());
                        dau_susd_index.put("start_dataTime", dis.readLong());
                        dau_susd_index.put("end_dataTime", dis.readLong());
                        dau_susd_index.put("susd_mode", dis.readByte());
                    }
//                    dataMap.put("dau_susd_index", dataList);
                    break;
                case 9:
                    //重复appDataNum个
                    for (int i=0; i<appDataNum; i++){
                        Map<String,Object> key_wave = new HashMap<>();
                        dataList.add(key_wave);

                        key_wave.put("dwStructLen", dis.readInt());
                        key_wave.put("speedID", dis.readLong());
                        key_wave.put("dataTime", dis.readLong());

                        Vib_wave_data vib_wave_data = new Vib_wave_data();
                        key_wave.put("vib_wave_data",vib_wave_data);
                        readWave(dis, vib_wave_data, head7000);

                        key_wave.put("speed", dis.readFloat());
                    }
//                    dataMap.put("key_wave", dataList);
                    break;
                case 10:
                    dau_all_cfg = new DAU_ALL_CFG();
                    dau_all_cfg.setDwStructLen(dis.readInt());

                    //振动通道
                    dau_all_cfg.setVib_num(dis.readInt());
                    DAU_CHL_VIB_CFG vib_chl = dau_all_cfg.getVib_chl();
                    vib_chl.setDwStructLen(dis.readInt());
                    C_DAU_CHL_CFG dau_chl_vib = vib_chl.getDau_chl();
                    readDis2Dau(dis, dau_chl_vib);
                    C_DAU_CHL_VIB_CFG dau_vib_chl = vib_chl.getDau_vib_chl();
                    dau_vib_chl.setId_dauchl(dis.readInt());
                    dau_vib_chl.setChl_no(dis.readShort());
                    dau_vib_chl.setSensor_type(dis.readByte());
                    dau_vib_chl.setEu(readString(dis, 20));
                    dau_vib_chl.setSensor_model(readString(dis, 50));
                    dau_vib_chl.setSensor_vendor(readString(dis, 50));
                    dau_vib_chl.setCurrent_feed(dis.readByte());
                    dau_vib_chl.setScale(dis.readFloat());
                    dau_vib_chl.setZero_shift(dis.readFloat());
                    dau_vib_chl.setAc_corr(dis.readFloat());
                    dau_vib_chl.setDc_corr(dis.readFloat());
                    dau_vib_chl.setAc_intg_corr(dis.readFloat());
                    dau_vib_chl.setInput_vol_gear(dis.readByte());
                    dau_vib_chl.setCable_check(dis.readByte());
                    dau_vib_chl.setV_min(dis.readFloat());
                    dau_vib_chl.setV_max(dis.readFloat());
                    dau_vib_chl.setGraphXMin(dis.readFloat());
                    dau_vib_chl.setGraphXMax(dis.readFloat());
                    dau_vib_chl.setGraphYMin(dis.readFloat());
                    dau_vib_chl.setGraphYMax(dis.readFloat());

                    //过程量通道
                    dau_all_cfg.setPro_num(dis.readInt());
                    DAU_CHL_PRO_CFG pro_chl = dau_all_cfg.getPro_chl();
                    pro_chl.setDwStructLen(dis.readInt());
                    C_DAU_CHL_CFG dau_chl_pro = pro_chl.getDau_chl();
                    readDis2Dau(dis, dau_chl_pro);
                    C_DAU_CHL_PRO_CFG dau_pro_chl = pro_chl.getDau_pro_chl();
                    dau_pro_chl.setId_dauchl(dis.readInt());
                    dau_pro_chl.setChl_no(dis.readShort());
                    dau_pro_chl.setSensor_type(dis.readByte());
                    dau_pro_chl.setEu(readString(dis, 20));
                    dau_pro_chl.setSensor_model(readString(dis, 50));
                    dau_pro_chl.setSensor_vendor(readString(dis, 50));
                    dau_pro_chl.setInstall_angle(dis.readFloat());
                    dau_pro_chl.setCurrent_feed(dis.readByte());
                    dau_pro_chl.setScale(dis.readFloat());
                    dau_pro_chl.setZero_shift(dis.readFloat());
                    dau_pro_chl.setCorr(dis.readFloat());
                    dau_pro_chl.setCable_check(dis.readByte());
                    dau_pro_chl.setV_min(dis.readFloat());
                    dau_pro_chl.setV_max(dis.readFloat());
                    dau_pro_chl.setGraphXMin(dis.readFloat());
                    dau_pro_chl.setGraphXMax(dis.readFloat());
                    dau_pro_chl.setGraphYMin(dis.readFloat());
                    dau_pro_chl.setGraphYMax(dis.readFloat());

                    //数字量通道
                    dau_all_cfg.setDgt_num(dis.readInt());
                    DAU_CHL_DGT_CFG dgt_chl = dau_all_cfg.getDgt_chl();
                    dgt_chl.setDwStructLen(dis.readInt());
                    C_DAU_CHL_CFG dau_chl_dgt = dgt_chl.getDau_chl();
                    readDis2Dau(dis, dau_chl_dgt);
                    C_DAU_CHL_DGT_CFG dau_dgt_chl = dgt_chl.getDau_dgt_chl();
                    dau_dgt_chl.setId_dauchl(dis.readInt());
                    dau_dgt_chl.setChl_no(dis.readShort());
                    dau_dgt_chl.setSensor_type(dis.readByte());
                    dau_dgt_chl.setEu(readString(dis, 20));
                    dau_dgt_chl.setSensor_model(readString(dis, 50));
                    dau_dgt_chl.setSensor_vendor(readString(dis, 50));
                    dau_dgt_chl.setInstall_angle(dis.readFloat());
                    dau_dgt_chl.setVol_trigger(dis.readByte());
                    dau_dgt_chl.setVol_type(dis.readByte());
                    dau_dgt_chl.setVol_gear(dis.readByte());
                    dau_dgt_chl.setPolarity(dis.readByte());
                    dau_dgt_chl.setTrigger_type(dis.readByte());
                    dau_dgt_chl.setCable_check(dis.readByte());
                    dau_dgt_chl.setV_min(dis.readFloat());
                    dau_dgt_chl.setV_max(dis.readFloat());
                    dau_dgt_chl.setAc_corr(dis.readFloat());
                    dau_dgt_chl.setDc_corr(dis.readFloat());

                    //串口通道
                    dau_all_cfg.setCom_num(dis.readInt());
                    DAU_CHL_COM_CFG com_chl = dau_all_cfg.getCom_chl();
                    com_chl.setDwStructLen(dis.readInt());
                    C_DAU_CHL_CFG dau_chl_com = com_chl.getDau_chl();
                    readDis2Dau(dis, dau_chl_com);
                    C_DAU_CHL_COM_CFG dau_com_chl = com_chl.getDau_com_chl();
                    dau_com_chl.setId_dauchl(dis.readInt());
                    dau_com_chl.setChl_no(dis.readShort());
                    dau_com_chl.setChl_type(dis.readByte());
                    dau_com_chl.setMaster_slave(dis.readByte());
                    if (dau_com_chl.getMaster_slave() == 1) {
                        dau_com_chl.setSlaveid(dis.readByte());
                    }
                    dau_com_chl.setCommu_mode(dis.readByte());
                    dau_com_chl.setBaudrate(dis.readInt());
                    dau_com_chl.setParity(dis.readByte());
                    dau_com_chl.setDatabit(dis.readByte());
                    dau_com_chl.setStopbit(dis.readByte());
                    dau_com_chl.setCrc(dis.readByte());

                    //转速测点
                    dau_all_cfg.setSpeed_tag_num(dis.readInt());
                    TAG_SPEED_CFG speed_tag = dau_all_cfg.getSpeed_tag();
                    speed_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_speed = speed_tag.getNode();
                    C_TAG_COMMON tag_comm_speed = speed_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_speed, tag_comm_speed);
                    C_TAG_SPEED_CFG c_tag_speed_cfg = speed_tag.getSpeed_tag();
                    c_tag_speed_cfg.setId_node(dis.readLong());
                    c_tag_speed_cfg.setId_dauchl(dis.readInt());
                    c_tag_speed_cfg.setId_speed(dis.readInt());
                    c_tag_speed_cfg.setId_process(dis.readInt());
                    c_tag_speed_cfg.setId_digital(dis.readInt());
                    c_tag_speed_cfg.setPulse_rev(dis.readFloat());
                    c_tag_speed_cfg.setEv_act_range_type0(dis.readByte());
                    c_tag_speed_cfg.setEv_act_range_min0(dis.readFloat());
                    c_tag_speed_cfg.setEv_act_range_max0(dis.readFloat());
                    c_tag_speed_cfg.setEv_act_range_type1(dis.readByte());
                    c_tag_speed_cfg.setEv_act_range_min1(dis.readFloat());
                    c_tag_speed_cfg.setEv_act_range_max1(dis.readFloat());
                    c_tag_speed_cfg.setHh_act(dis.readByte());
                    c_tag_speed_cfg.setHh_level(dis.readFloat());
                    c_tag_speed_cfg.setH_act(dis.readByte());
                    c_tag_speed_cfg.setH_level(dis.readFloat());
                    c_tag_speed_cfg.setLl_act(dis.readByte());
                    c_tag_speed_cfg.setLl_level(dis.readFloat());
                    c_tag_speed_cfg.setL_act(dis.readByte());
                    c_tag_speed_cfg.setL_level(dis.readFloat());
                    c_tag_speed_cfg.setAlarm_enter(dis.readByte());
                    c_tag_speed_cfg.setAlarm_level(dis.readByte());
                    c_tag_speed_cfg.setSpeed_wave(dis.readByte());
                    c_tag_speed_cfg.setStored_intvl(dis.readShort());
                    c_tag_speed_cfg.setDelta_percent(dis.readShort());
                    c_tag_speed_cfg.setDelta_level(dis.readFloat());
                    c_tag_speed_cfg.setDiretion(dis.readByte());
                    c_tag_speed_cfg.setMolecule(dis.readShort());
                    c_tag_speed_cfg.setDenominator(dis.readShort());
                    c_tag_speed_cfg.setEv_act_range_delta0(dis.readFloat());
                    c_tag_speed_cfg.setEv_act_range_delta1(dis.readFloat());
                    c_tag_speed_cfg.setRange_h(dis.readFloat());
                    c_tag_speed_cfg.setRange_l(dis.readFloat());

                    //数字量测点
                    dau_all_cfg.setDgt_tag_num(dis.readInt());
                    TAG_DGT_CFG dgt_tag = dau_all_cfg.getDgt_tag();
                    dgt_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_dgt = dgt_tag.getNode_cfg();
                    C_TAG_COMMON tag_comm_dgt = dgt_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_dgt, tag_comm_dgt);
                    C_TAG_DGT_CFG c_tag_dgt_cfg = dgt_tag.getDigital_tag();
                    c_tag_dgt_cfg.setId_node(dis.readLong());
                    c_tag_dgt_cfg.setId_dauchl(dis.readInt());
                    c_tag_dgt_cfg.setAlarm_trigg(dis.readByte());
                    c_tag_dgt_cfg.setAlarm_enter(dis.readByte());
                    c_tag_dgt_cfg.setAlarm_leave(dis.readByte());
                    c_tag_dgt_cfg.setStored_intvl(dis.readShort());
                    c_tag_dgt_cfg.setHh_act(dis.readByte());
                    c_tag_dgt_cfg.setHh_level(dis.readFloat());
                    c_tag_dgt_cfg.setH_act(dis.readByte());
                    c_tag_dgt_cfg.setH_level(dis.readFloat());
                    c_tag_dgt_cfg.setLl_act(dis.readByte());
                    c_tag_dgt_cfg.setLl_level(dis.readFloat());
                    c_tag_dgt_cfg.setL_act(dis.readByte());
                    c_tag_dgt_cfg.setL_level(dis.readFloat());
                    c_tag_dgt_cfg.setRange_h(dis.readFloat());
                    c_tag_dgt_cfg.setRange_l(dis.readFloat());

                    //振动量测点
                    dau_all_cfg.setVib_tag_num(dis.readInt());
                    TAG_VIB_CFG vib_tag = dau_all_cfg.getVib_tag();
                    vib_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_vib = vib_tag.getNode_cfg();
                    C_TAG_COMMON tag_comm_vib = vib_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_vib, tag_comm_vib);
                    C_TAG_VIB_CFG c_tag_vib_cfg = vib_tag.getVib_tag();
                    c_tag_vib_cfg.setId_node(dis.readLong());
                    c_tag_vib_cfg.setChl_num(dis.readByte());
                    c_tag_vib_cfg.setId_dauchl1(dis.readInt());
                    c_tag_vib_cfg.setId_dauchl2(dis.readInt());
                    c_tag_vib_cfg.setId_dauchl3(dis.readInt());
                    c_tag_vib_cfg.setId_plain(dis.readInt());
                    c_tag_vib_cfg.setId_speed(dis.readLong());
                    c_tag_vib_cfg.setId_process(dis.readInt());
                    c_tag_vib_cfg.setId_digital(dis.readInt());
                    c_tag_vib_cfg.setVib_type(dis.readByte());
                    c_tag_vib_cfg.setDiretion(dis.readByte());
                    c_tag_vib_cfg.setRef_gap_vol(dis.readFloat());
                    c_tag_vib_cfg.setWave_window(dis.readByte());
                    c_tag_vib_cfg.setWave_fft_lines(dis.readShort());
                    if (c_tag_vib_cfg.getVib_type() == 11 || c_tag_vib_cfg.getVib_type() == 12) {
                        c_tag_vib_cfg.setWave_run_cycle(dis.readShort());
                    }
                    if (c_tag_vib_cfg.getVib_type() == 12 || c_tag_vib_cfg.getVib_type() == 14) {
                        c_tag_vib_cfg.setWave_env_filter(dis.readByte());
                    }
                    if (c_tag_vib_cfg.getVib_type() == 13 || c_tag_vib_cfg.getVib_type() == 14) {
                        c_tag_vib_cfg.setWave_freq_range(dis.readFloat());
                    }
                    c_tag_vib_cfg.setWave_freq_cutoff(dis.readFloat());
                    c_tag_vib_cfg.setWave_avg_type(dis.readByte());
                    c_tag_vib_cfg.setWave_avg_no(dis.readShort());
                    c_tag_vib_cfg.setEv_lines(dis.readShort());
                    c_tag_vib_cfg.setEv_act_range_type0(dis.readByte());
                    c_tag_vib_cfg.setEv_act_range_min0(dis.readFloat());
                    c_tag_vib_cfg.setEv_act_range_max0(dis.readFloat());
                    c_tag_vib_cfg.setEv_act_range_type1(dis.readByte());
                    c_tag_vib_cfg.setEv_act_range_min1(dis.readFloat());
                    c_tag_vib_cfg.setEv_act_range_max1(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_type0(dis.readByte());
                    c_tag_vib_cfg.setWave_act_range_min0(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_max0(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_type1(dis.readByte());
                    c_tag_vib_cfg.setWave_act_range_min1(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_max1(dis.readFloat());
                    c_tag_vib_cfg.setEv_stored_intvl(dis.readShort());
                    c_tag_vib_cfg.setEv_realtime_intvl(dis.readShort());
                    c_tag_vib_cfg.setWave_stored_intvl(dis.readShort());
                    c_tag_vib_cfg.setWave_realtime_intvl(dis.readShort());
                    c_tag_vib_cfg.setAlarm_enter(dis.readByte());
                    c_tag_vib_cfg.setAlarm_leave(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type1(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name1(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point1(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range1(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert1(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm1(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act1(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type2(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name2(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point2(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range2(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert2(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm2(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act2(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type3(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name3(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point3(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range3(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert3(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm3(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act3(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type4(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name4(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point4(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range4(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert4(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm4(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act4(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type5(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name5(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point5(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range5(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert5(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm5(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act5(dis.readByte());

                    c_tag_vib_cfg.setFreq_bd_type6(dis.readByte());
                    c_tag_vib_cfg.setFreq_bd_name6(readString(dis, 20));
                    c_tag_vib_cfg.setFreq_bd_point6(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_range6(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alert6(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_alarm6(dis.readFloat());
                    c_tag_vib_cfg.setFreq_bd_ld_act6(dis.readByte());

                    c_tag_vib_cfg.setAllover_type(dis.readByte());
                    c_tag_vib_cfg.setAllover_name(readString(dis, 20));
                    c_tag_vib_cfg.setAllover_s(dis.readFloat());
                    c_tag_vib_cfg.setAllover_e(dis.readFloat());
                    c_tag_vib_cfg.setAllover_alert(dis.readFloat());
                    c_tag_vib_cfg.setAllover_alarm(dis.readFloat());
                    c_tag_vib_cfg.setAllover_delta_percent(dis.readShort());
                    c_tag_vib_cfg.setAllover_delta_level(dis.readFloat());
                    c_tag_vib_cfg.setAllover_ld_act(dis.readByte());
                    c_tag_vib_cfg.setLd_type(dis.readByte());

                    c_tag_vib_cfg.setLd0(dis.readFloat());
                    c_tag_vib_cfg.setLd1(dis.readFloat());
                    c_tag_vib_cfg.setLd2(dis.readFloat());
                    c_tag_vib_cfg.setLd3(dis.readFloat());
                    c_tag_vib_cfg.setLd4(dis.readFloat());
                    c_tag_vib_cfg.setLd5(dis.readFloat());
                    c_tag_vib_cfg.setLd6(dis.readFloat());
                    c_tag_vib_cfg.setLd7(dis.readFloat());
                    c_tag_vib_cfg.setLd8(dis.readFloat());

                    c_tag_vib_cfg.setLd_percent1(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent2(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent3(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent4(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent5(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent6(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent7(dis.readFloat());
                    c_tag_vib_cfg.setLd_percent8(dis.readFloat());

                    c_tag_vib_cfg.setWave_mode(dis.readByte());
                    if (c_tag_vib_cfg.getWave_mode() == 0 || c_tag_vib_cfg.getWave_mode() == 2) {
                        c_tag_vib_cfg.setFft_thld(dis.readFloat());
                        c_tag_vib_cfg.setFft_max_xf(dis.readShort());
                    }
                    c_tag_vib_cfg.setLf_cursor(dis.readFloat());
                    c_tag_vib_cfg.setHf_cursor(dis.readFloat());
                    c_tag_vib_cfg.setEv_act_range_delta0(dis.readFloat());
                    c_tag_vib_cfg.setEv_act_range_delta1(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_delta0(dis.readFloat());
                    c_tag_vib_cfg.setWave_act_range_delta1(dis.readFloat());
                    c_tag_vib_cfg.setRange_h(dis.readFloat());
                    c_tag_vib_cfg.setRange_l(dis.readFloat());
                    c_tag_vib_cfg.setBearingType(dis.readByte());
                    c_tag_vib_cfg.setNormalSpeed(dis.readFloat());

                    //过程量测点
                    dau_all_cfg.setPro_tag_num(dis.readInt());
                    TAG_PRO_CFG pro_tag = dau_all_cfg.getPro_tag();
                    pro_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_pro = pro_tag.getNode_cfg();
                    C_TAG_COMMON tag_comm_pro = pro_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_pro, tag_comm_pro);
                    C_TAG_PRO_CFG c_tag_pro_cfg = pro_tag.getPro_tag();
                    c_tag_pro_cfg.setId_node(dis.readLong());
                    c_tag_pro_cfg.setId_dauchl(dis.readInt());
                    c_tag_pro_cfg.setId_speed(dis.readLong());
                    c_tag_pro_cfg.setId_process(dis.readInt());
                    c_tag_pro_cfg.setId_digital(dis.readInt());
                    c_tag_pro_cfg.setEv_act_range_type0(dis.readByte());
                    c_tag_pro_cfg.setEv_act_range_min0(dis.readFloat());
                    c_tag_pro_cfg.setEv_act_range_max0(dis.readFloat());
                    c_tag_pro_cfg.setEv_act_range_type1(dis.readByte());
                    c_tag_pro_cfg.setEv_act_range_min1(dis.readFloat());
                    c_tag_pro_cfg.setEv_act_range_max1(dis.readFloat());
                    c_tag_pro_cfg.setHh_act(dis.readByte());
                    c_tag_pro_cfg.setHh_level(dis.readFloat());
                    c_tag_pro_cfg.setH_act(dis.readByte());
                    c_tag_pro_cfg.setH_level(dis.readFloat());
                    c_tag_pro_cfg.setLl_act(dis.readByte());
                    c_tag_pro_cfg.setLl_level(dis.readFloat());
                    c_tag_pro_cfg.setL_act(dis.readByte());
                    c_tag_pro_cfg.setL_level(dis.readFloat());
                    c_tag_pro_cfg.setAlarm_enter(dis.readByte());
                    c_tag_pro_cfg.setAlarm_leave(dis.readByte());
                    c_tag_pro_cfg.setStored_intvl(dis.readShort());
                    c_tag_pro_cfg.setDelta_percent(dis.readShort());
                    c_tag_pro_cfg.setDelta_level(dis.readFloat());
                    c_tag_pro_cfg.setScale(dis.readFloat());
                    c_tag_pro_cfg.setMes_range_act(dis.readByte());
                    c_tag_pro_cfg.setMes_range_min(dis.readFloat());
                    c_tag_pro_cfg.setMes_range_max(dis.readFloat());
                    c_tag_pro_cfg.setEv_act_range_delta0(dis.readFloat());
                    c_tag_pro_cfg.setEv_act_range_delta1(dis.readFloat());
                    c_tag_pro_cfg.setRange_h(dis.readFloat());
                    c_tag_pro_cfg.setRange_l(dis.readFloat());

                    //Modbus测点
                    dau_all_cfg.setMdb_tag_num(dis.readInt());
                    TAG_MDB_CFG mdb_tag = dau_all_cfg.getMdb_tag();
                    mdb_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_mdb = mdb_tag.getNode_cfg();
                    C_TAG_COMMON tag_comm_mdb = mdb_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_mdb, tag_comm_mdb);
                    C_TAG_MDB_CFG c_tag_mdb_cfg = mdb_tag.getModbus_tag();
                    c_tag_mdb_cfg.setId_node(dis.readLong());
                    c_tag_mdb_cfg.setChl_no(dis.readInt());
                    c_tag_mdb_cfg.setData_address(dis.readInt());
                    c_tag_mdb_cfg.setData_mode(dis.readByte());
                    c_tag_mdb_cfg.setSlave_id(dis.readByte());
                    c_tag_mdb_cfg.setTag_type(dis.readByte());
                    c_tag_mdb_cfg.setStored_intvl(dis.readShort());
                    c_tag_mdb_cfg.setDelta_percent(dis.readShort());
                    c_tag_mdb_cfg.setDelta_level(dis.readFloat());
                    c_tag_mdb_cfg.setInput_value_1(dis.readFloat());
                    c_tag_mdb_cfg.setInput_value_2(dis.readFloat());
                    c_tag_mdb_cfg.setRef_value_1(dis.readFloat());
                    c_tag_mdb_cfg.setRef_value_2(dis.readFloat());
                    c_tag_mdb_cfg.setScale(dis.readFloat());
                    c_tag_mdb_cfg.setAlarm_trigg(dis.readByte());
                    c_tag_mdb_cfg.setHh_act(dis.readByte());
                    c_tag_mdb_cfg.setHh_level(dis.readFloat());
                    c_tag_mdb_cfg.setH_act(dis.readByte());
                    c_tag_mdb_cfg.setH_level(dis.readFloat());
                    c_tag_mdb_cfg.setLl_act(dis.readByte());
                    c_tag_mdb_cfg.setLl_level(dis.readFloat());
                    c_tag_mdb_cfg.setL_act(dis.readByte());
                    c_tag_mdb_cfg.setL_level(dis.readFloat());
                    c_tag_mdb_cfg.setAlarm_enter(dis.readByte());
                    c_tag_mdb_cfg.setAlarm_leave(dis.readByte());
                    c_tag_mdb_cfg.setEv_act_range_delta0(dis.readFloat());
                    c_tag_mdb_cfg.setEv_act_range_delta1(dis.readFloat());
                    c_tag_mdb_cfg.setEu_len(dis.readInt());
                    c_tag_mdb_cfg.setEu(readString(dis, c_tag_mdb_cfg.getEu_len()));
                    c_tag_mdb_cfg.setComDataType(dis.readByte());
                    c_tag_mdb_cfg.setRange_h(dis.readFloat());
                    c_tag_mdb_cfg.setRange_l(dis.readFloat());
                    if (c_tag_mdb_cfg.getTag_type() == 1 || c_tag_mdb_cfg.getTag_type() == 2) {
                        c_tag_mdb_cfg.setUnit0_len(dis.readInt());
                        c_tag_mdb_cfg.setUnit0(readString(dis, c_tag_mdb_cfg.getUnit0_len()));
                        c_tag_mdb_cfg.setUnit1_len(dis.readInt());
                        c_tag_mdb_cfg.setUnit1(readString(dis, c_tag_mdb_cfg.getUnit1_len()));
                        if (c_tag_mdb_cfg.getTag_type() == 2) {
                            c_tag_mdb_cfg.setSwPara0(dis.readFloat());
                            c_tag_mdb_cfg.setSwPara1(dis.readFloat());
                        }
                    }

                    //测量组
                    dau_all_cfg.setRun_group_num(dis.readInt());
                    RUN_GROUP_CFG run_group = dau_all_cfg.getRun_group();
                    run_group.setDwStructLen(dis.readInt());
                    C_RUN_GROUP c_run_group = run_group.getRun_group();
                    c_run_group.setId_rungroup(dis.readInt());
                    c_run_group.setName(readString(dis, 50));
                    c_run_group.setId_dau(dis.readShort());
                    c_run_group.setFreq_type(dis.readByte());
                    c_run_group.setId_speed(dis.readLong());
                    c_run_group.setId_process(dis.readLong());
                    c_run_group.setId_digital(dis.readLong());
                    c_run_group.setWave_fft_lines(dis.readShort());
                    if (c_run_group.getFreq_type() == 1) {
                        c_run_group.setWave_freq_range(dis.readFloat());
                    }
                    if (c_run_group.getFreq_type() == 0) {
                        c_run_group.setWave_run_cycles(dis.readShort());
                        c_run_group.setWave_susd_cycles(dis.readShort());
                    }
                    c_run_group.setDelta_speed(dis.readShort());
                    c_run_group.setSusd_stored_intvl(dis.readShort());
                    if (c_run_group.getFreq_type() == 0) {
                        c_run_group.setSu_sample_mode(dis.readByte());
                        c_run_group.setSd_sample_mode(dis.readByte());
                        c_run_group.setRun_speed(dis.readFloat());
                        c_run_group.setSpeed_swing(dis.readFloat());
                        c_run_group.setMin_speed(dis.readFloat());
                        c_run_group.setMax_speed(dis.readFloat());
                    }
                    c_run_group.setEv_act_range_type0(dis.readByte());
                    c_run_group.setEv_act_range_min0(dis.readFloat());
                    c_run_group.setEv_act_range_max0(dis.readFloat());
                    c_run_group.setEv_act_range_type1(dis.readByte());
                    c_run_group.setEv_act_range_min1(dis.readFloat());
                    c_run_group.setEv_act_range_max1(dis.readFloat());
                    c_run_group.setWave_act_range_type0(dis.readByte());
                    c_run_group.setWave_act_range_min0(dis.readFloat());
                    c_run_group.setWave_act_range_max0(dis.readFloat());
                    c_run_group.setWave_act_range_type1(dis.readByte());
                    c_run_group.setWave_act_range_min1(dis.readFloat());
                    c_run_group.setWave_act_range_max1(dis.readFloat());
                    c_run_group.setEv_stored_intvl(dis.readShort());
                    c_run_group.setEv_realtime_intvl(dis.readShort());
                    c_run_group.setWave_stored_intvl(dis.readShort());
                    c_run_group.setWave_realtime_intvl(dis.readShort());
                    if (c_run_group.getFreq_type() == 0) {
                        c_run_group.setBlack_box_triger(dis.readByte());
                        c_run_group.setBlack_box_intvl(dis.readShort());
                    }
                    c_run_group.setEv_lines(dis.readShort());
                    c_run_group.setWave_avg_type(dis.readByte());
                    c_run_group.setWave_avg_no(dis.readShort());
                    c_run_group.setEv_act_range_delta0(dis.readFloat());
                    c_run_group.setEv_act_range_delta1(dis.readFloat());
                    c_run_group.setWave_act_range_delta0(dis.readFloat());
                    c_run_group.setWave_act_range_delta1(dis.readFloat());
                    c_run_group.setId_node(dis.readLong());

                    //2.0新增的网口通道
                    dau_all_cfg.setWlan_chl_num(dis.readInt());
                    DAU_CHL_WLAN_CFG wlan_chl = dau_all_cfg.getWlan_chl();
                    wlan_chl.setDwStructLen(dis.readInt());
                    C_DAU_CHL_CFG dau_chl_wlan = wlan_chl.getDau_chl();
                    readDis2Dau(dis, dau_chl_wlan);
                    C_DAU_CHL_WLAN_CFG c_dau_chl_wlan_cfg = wlan_chl.getDau_wlan_chl();
                    c_dau_chl_wlan_cfg.setId_dauchl(dis.readInt());
                    c_dau_chl_wlan_cfg.setChl_no(dis.readShort());
                    c_dau_chl_wlan_cfg.setMaster_slave(dis.readByte());
                    c_dau_chl_wlan_cfg.setSlave_id(dis.readByte());
                    if (c_dau_chl_wlan_cfg.getMaster_slave() == 0) {
                        c_dau_chl_wlan_cfg.setPoll_time(dis.readInt());
                        c_dau_chl_wlan_cfg.setSlave_ip(readString(dis, 20));
                    }
                    c_dau_chl_wlan_cfg.setPort(dis.readInt());

                    //2.0新增的Modbus转速通道
                    dau_all_cfg.setWlan_chl_num(dis.readInt());
                    TAG_MDB_SPEED_CFG mdb_speed_tag = dau_all_cfg.getMdb_speed_tag();
                    mdb_speed_tag.setDwStructLen(dis.readInt());
                    C_SYS_NODE_CFG node_speed_mdb = mdb_speed_tag.getNode_cfg();
                    C_TAG_COMMON tag_comm_speed_mdb = mdb_speed_tag.getTag_comm();
                    readDis2NodeAndTageComm(dis, node_speed_mdb, tag_comm_speed_mdb);
                    C_TAG_MDB_SPEED_CFG c_tag_mdb_speed_cfg = mdb_speed_tag.getMdb_speed_tag();
                    c_tag_mdb_speed_cfg.setId_node(dis.readLong());
                    c_tag_mdb_speed_cfg.setId_dauchl(dis.readInt());
                    c_tag_mdb_speed_cfg.setHh_act(dis.readByte());
                    c_tag_mdb_speed_cfg.setHh_level(dis.readFloat());
                    c_tag_mdb_speed_cfg.setH_act(dis.readByte());
                    c_tag_mdb_speed_cfg.setH_level(dis.readFloat());
                    c_tag_mdb_speed_cfg.setLl_act(dis.readByte());
                    c_tag_mdb_speed_cfg.setLl_level(dis.readFloat());
                    c_tag_mdb_speed_cfg.setL_act(dis.readByte());
                    c_tag_mdb_speed_cfg.setL_level(dis.readFloat());
                    c_tag_mdb_speed_cfg.setAlarm_enter(dis.readByte());
                    c_tag_mdb_speed_cfg.setAlarm_leave(dis.readByte());
                    c_tag_mdb_speed_cfg.setStored_intvl(dis.readShort());
                    c_tag_mdb_speed_cfg.setDelta_percent(dis.readShort());
                    c_tag_mdb_speed_cfg.setDelta_level(dis.readFloat());
                    c_tag_mdb_speed_cfg.setDiretion(dis.readByte());
                    c_tag_mdb_speed_cfg.setSpeed_fz(dis.readShort());
                    c_tag_mdb_speed_cfg.setSpeed_fm(dis.readShort());
                    c_tag_mdb_speed_cfg.setRange_h(dis.readFloat());
                    c_tag_mdb_speed_cfg.setRange_l(dis.readFloat());
                    c_tag_mdb_speed_cfg.setFuntion_code1(dis.readByte());
                    c_tag_mdb_speed_cfg.setPlc_address1(dis.readInt());
                    c_tag_mdb_speed_cfg.setMdb_datatype1(dis.readByte());

                    c_tag_mdb_speed_cfg.setFuntion_code2(dis.readByte());
                    c_tag_mdb_speed_cfg.setPlc_address2(dis.readInt());
                    c_tag_mdb_speed_cfg.setMdb_datatype2(dis.readByte());

                    c_tag_mdb_speed_cfg.setFuntion_code3(dis.readByte());
                    c_tag_mdb_speed_cfg.setPlc_address3(dis.readInt());
                    c_tag_mdb_speed_cfg.setMdb_datatype3(dis.readByte());

                    break;
                default:
                    break;
            }
            dataMap.put("data", dataList);
            dataMap.put("dau_all_cfg", dau_all_cfg);
            //TODO 将封装好的数据(dataMap)传给cmc

            return 0;
        } catch (Exception e) {
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
            return 1999;
        } finally {
            try {
                dis.close();
                in.close();
            } catch (IOException e) {
                DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
            }
        }
    }

    /**
     * 读取波形数据
     *
     * @param dis           DataInputStream
     * @param vib_wave_data Vib_wave_data
     * @param head7000      Head7000
     * @throws IOException 抛出IO异常
     */
    private void readWave(DataInputStream dis, Vib_wave_data vib_wave_data, Head7000 head7000) throws IOException {
        vib_wave_data.setDwStructLen(dis.readInt());
        vib_wave_data.setFreq_type(dis.readByte());
        if (vib_wave_data.getFreq_type() == 0) {
            vib_wave_data.setCycles(dis.readShort());
            vib_wave_data.setSamples(dis.readShort());
        } else if (vib_wave_data.getFreq_type() == 1) {
            vib_wave_data.setTotalSamples(dis.readInt());
            vib_wave_data.setSampleFreq(dis.readFloat());
        }
        vib_wave_data.setWave_mode(dis.readByte());
        if (vib_wave_data.getWave_mode() == 0) {
            vib_wave_data.setScale1(dis.readFloat());
            vib_wave_data.setL1(dis.readShort());
            byte[] point1 = new byte[4 * vib_wave_data.getL1()];
            dis.read(point1);
            vib_wave_data.setPoint1(point1);
        } else if (vib_wave_data.getWave_mode() == 1) {
            vib_wave_data.setCompressedWaveLen1(dis.readInt());
            vib_wave_data.setWaveScale1(dis.readFloat());
            byte[] bytes = new byte[vib_wave_data.getCompressedWaveLen1()];
            dis.read(bytes);
            vib_wave_data.setCompressedWave1(bytes);
        } else if (vib_wave_data.getWave_mode() == 2) {
            vib_wave_data.setCompressedWaveLen2(dis.readInt());
            vib_wave_data.setWaveScale2(dis.readFloat());
            byte[] bytes = new byte[vib_wave_data.getCompressedWaveLen2()];
            dis.read(bytes);
            vib_wave_data.setCompressedWave2(bytes);
        } else if (vib_wave_data.getWave_mode() == 3) {
            vib_wave_data.setWaveScale3(dis.readFloat());
            byte[] bytes = new byte[vib_wave_data.getTotalSamples()];
            dis.read(bytes);
            vib_wave_data.setWave(bytes);
        } else if (vib_wave_data.getWave_mode() == 255) {
            vib_wave_data.setScale2(dis.readFloat());
            vib_wave_data.setL2(dis.readShort());
            byte[] bytes = new byte[4 * vib_wave_data.getL2()];
            dis.read(bytes);
            vib_wave_data.setPoint2(bytes);
        }
        int softwareVer = head7000.getSoftwareVer();
        if (softwareVer >= 2) {
            vib_wave_data.setWindowsType(dis.readByte());
        }
    }

    /**
     * 读取到DAU
     *
     * @param dis     DataInputStream
     * @param dau_chl C_DAU_CHL_CFG
     * @throws IOException 抛出IO异常
     */
    private void readDis2Dau(DataInputStream dis, C_DAU_CHL_CFG dau_chl) throws IOException {
        dau_chl = dau_chl == null ? new C_DAU_CHL_CFG() : dau_chl;
        dau_chl.setId_dauchl(dis.readInt());
        dau_chl.setId_dau(dis.readShort());
        byte[] nameBytes = new byte[50];
        dis.read(nameBytes);
        dau_chl.setName(FormatTransfer.bytesToString(nameBytes));
        dau_chl.setEnable(dis.readByte());
        dau_chl.setChl_type(dis.readByte());
    }

    /**
     * 读取字节转换为String
     *
     * @param dis    DataInputStream
     * @param length 读取字节长度
     * @return 返回一个字符串
     * @throws IOException 抛出IO异常
     */
    private String readString(DataInputStream dis, int length) throws IOException {
        byte[] bytes = new byte[length];
        dis.read(bytes);
        return FormatTransfer.bytesToString(bytes);
    }

    /**
     * 读取相应的内容到node和tagcomm
     *
     * @param dis            DataInputStream
     * @param node_speed     C_SYS_NODE_CFG
     * @param tag_comm_speed C_TAG_COMMON
     * @throws IOException 抛出IO异常
     */
    private void readDis2NodeAndTageComm(DataInputStream dis, C_SYS_NODE_CFG node_speed, C_TAG_COMMON tag_comm_speed) throws IOException {
        node_speed = node_speed == null ? new C_SYS_NODE_CFG() : node_speed;
        tag_comm_speed = tag_comm_speed == null ? new C_TAG_COMMON() : tag_comm_speed;
        node_speed.setId_node(dis.readLong());
        node_speed.setId_parent(dis.readLong());
        node_speed.setType_node(dis.readByte());
        node_speed.setEnable(dis.readByte());
        node_speed.setName(readString(dis, 100));
        node_speed.setSq_no(dis.readInt());
        node_speed.setComment(readString(dis, 100));
        node_speed.setStringLen(dis.readInt());
        node_speed.setUrl_cfg(readString(dis, node_speed.getStringLen()));

        tag_comm_speed.setId_node(dis.readLong());
        tag_comm_speed.setId_rungroup(dis.readInt());
        tag_comm_speed.setId_dau(dis.readShort());
        tag_comm_speed.setEu(readString(dis, 20));
        tag_comm_speed.setEu_type(dis.readByte());
        tag_comm_speed.setAlarmBlocking(dis.readByte());
    }
}
