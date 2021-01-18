package com.zzqa.ds7000.service.impl;

import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Monitor_data_head;
import com.zzqa.ds7000.pojo.Procss_data;
import com.zzqa.ds7000.pojo.Vib_data;
import com.zzqa.ds7000.service.interfaces.ISaveData;
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
                                Map<String,Object> vib_wave_data = new HashMap<>();
                                vib_data.setWave(vib_wave_data);

                                vib_wave_data.put("dwStructLen", dis.readInt());
                                int freq_type = dis.readByte();
                                int cycles = 0;
                                int samples = 0;
                                int totalSamples = 0;
                                vib_wave_data.put("freq_type",freq_type);
                                if (freq_type == 0){
                                    cycles = dis.readShort();
                                    samples = dis.readShort();
                                    vib_wave_data.put("cycles", cycles);
                                    vib_wave_data.put("samples", samples);
                                } else if (freq_type == 1){
                                    totalSamples = dis.readInt();
                                    vib_wave_data.put("sampleFreq", dis.readFloat());
                                }
                                int wave_mode = dis.readByte();
                                if (wave_mode == 0 || wave_mode == 255){
                                    vib_wave_data.put("scale", dis.readFloat());
                                    int l = dis.readShort();
                                    vib_wave_data.put("L", l);
                                    byte[] point = new byte[4*l];
                                    dis.read(point);
                                    vib_wave_data.put("point", point);
                                } else if (wave_mode == 1 || wave_mode == 2){
                                    //TODO 协议中为unit，不知道什么意思（compressedWaveLen）
                                    int compressedWaveLen = dis.readInt();
                                    vib_wave_data.put("compressed_wave_len", compressedWaveLen);
                                    vib_wave_data.put("wave_scale", dis.readFloat());
                                    byte[] compressedWare = new byte[compressedWaveLen];
                                    dis.read(compressedWare);
                                    vib_wave_data.put("compressed_wave", compressedWare);
                                } else if (wave_mode == 3) {
                                    vib_wave_data.put("wave_scale", dis.readFloat());
                                    byte[] wave = new byte[totalSamples];
                                    dis.read(wave);
                                    vib_wave_data.put("wave", wave);
                                }
                                int softwareVer = head7000.getSoftwareVer();
                                if (softwareVer >= 2){
                                    vib_wave_data.put("windows_type", dis.readByte());
                                }
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
                    dataMap.put("monitor_data", dataList);
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
                    dataMap.put("dau_log_code", dataList);
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
                    dataMap.put("dau_susd_index", dataList);
                    break;
                case 9:
                    //重复appDataNum个
                    for (int i=0; i<appDataNum; i++){
                        Map<String,Object> key_wave = new HashMap<>();
                        dataList.add(key_wave);

                        key_wave.put("dwStructLen", dis.readInt());
                        key_wave.put("speedID", dis.readLong());
                        key_wave.put("dataTime", dis.readLong());

                        Map<String,Object> vib_wave_data = new HashMap<>();
                        key_wave.put("vib_wave_data",vib_wave_data);

                        vib_wave_data.put("dwStructLen", dis.readInt());
                        int freq_type = dis.readByte();
                        int cycles = 0;
                        int samples = 0;
                        int totalSamples = 0;
                        vib_wave_data.put("freq_type",freq_type);
                        if (freq_type == 0){
                            cycles = dis.readShort();
                            samples = dis.readShort();
                            vib_wave_data.put("cycles", cycles);
                            vib_wave_data.put("samples", samples);
                        } else if (freq_type == 1){
                            totalSamples = dis.readInt();
                            vib_wave_data.put("sampleFreq", dis.readFloat());
                        }
                        int wave_mode = dis.readByte();
                        if (wave_mode == 0 || wave_mode == 255){
                            vib_wave_data.put("scale", dis.readFloat());
                            int l = dis.readShort();
                            vib_wave_data.put("L", l);
                            byte[] point = new byte[4*l];
                            dis.read(point);
                            vib_wave_data.put("point", point);
                        } else if (wave_mode == 1 || wave_mode == 2){
                            //TODO 协议中为unit，不知道什么意思（compressedWaveLen）
                            int compressedWaveLen = dis.readInt();
                            vib_wave_data.put("compressed_wave_len", compressedWaveLen);
                            vib_wave_data.put("wave_scale", dis.readFloat());
                            byte[] compressedWare = new byte[compressedWaveLen];
                            dis.read(compressedWare);
                            vib_wave_data.put("compressed_wave", compressedWare);
                        } else if (wave_mode == 3) {
                            vib_wave_data.put("wave_scale", dis.readFloat());
                            byte[] wave = new byte[totalSamples];
                            dis.read(wave);
                            vib_wave_data.put("wave", wave);
                        }
                        int softwareVer = head7000.getSoftwareVer();
                        if (softwareVer >= 2){
                            vib_wave_data.put("windows_type", dis.readByte());
                        }

                        key_wave.put("speed", dis.readFloat());
                    }
                    dataMap.put("key_wave", dataList);
                    break;
                default:
                    break;
            }
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
}
