package com.zzqa.ds7000.service.impl;

import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Upgrade_file_info;
import com.zzqa.ds7000.service.interfaces.ISendToDgm;
import com.zzqa.ds7000.util.FormatTransfer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                head7000.setProtocolID(3);//TODO
            }
            if (upgrade_status.get() == 1){
                head7000.setProtocolID(8);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(baos);
                //从CMC获取升级内容
                List<Upgrade_file_info> upgradeList = new ArrayList<>();//TODO
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
            byte[] data = getDgmConfig(head7000,nodeId);

            conn(response,data,head7000,1 );
        } catch (Exception e) {
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
            head7000.setErrorCode(1999);
            conn(response, null, head7000, 0);
        }
    }

    private byte[] getDgmConfig(Head7000 head7000,long nodeId){
        byte[] data = null;
        //TODO 根据条件向CMC获取采集器设置，现假定返回的结果为Map<String,Object>
        Map<String,Object> dgmConfigMap = new HashMap<>();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

//            dos.writeInt(dgmConfigMap.get("dwStructLen"));


            dos.flush();
            baos.flush();
            data = baos.toByteArray();
            dos.close();
            baos.close();
        } catch (Exception e){
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(), e);
        }
        return data;
    }
}
