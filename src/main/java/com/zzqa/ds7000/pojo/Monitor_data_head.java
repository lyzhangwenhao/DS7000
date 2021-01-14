package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

/**
 * ClassName: Monitor_data_head
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/13 10:16
 */
@Component
public class Monitor_data_head {
    private int dwStructLen;    //字节长度
    private long dataTime;      //UTC时间
    private byte speedStutas;   //运行状态
    private int dataType;       //数据类型
    private int samples;        //每周采样点数
    private int cycles;         //采样周期
    private int vibNum;         //振动通道的个数
    private int procNum;        //工艺量通道个数
    private int waveSpeedNum;   //采集到的转速个数(不同周期对应的转速)
    private float[] speed;      //waveSpeedNum个转速(oxff7fffff)表示无效
    private float porcessValue; //过程量值(oxff7fffff)表示无效
    private float digitalValue; //数字量值(开关量)(oxff7fffff)表示无效

    public Monitor_data_head() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public byte getSpeedStutas() {
        return speedStutas;
    }

    public void setSpeedStutas(byte speedStutas) {
        this.speedStutas = speedStutas;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getVibNum() {
        return vibNum;
    }

    public void setVibNum(int vibNum) {
        this.vibNum = vibNum;
    }

    public int getProcNum() {
        return procNum;
    }

    public void setProcNum(int procNum) {
        this.procNum = procNum;
    }

    public int getWaveSpeedNum() {
        return waveSpeedNum;
    }

    public void setWaveSpeedNum(int waveSpeedNum) {
        this.waveSpeedNum = waveSpeedNum;
    }

    public float[] getSpeed() {
        return speed;
    }

    public void setSpeed(float[] speed) {
        this.speed = speed;
    }

    public float getPorcessValue() {
        return porcessValue;
    }

    public void setPorcessValue(float porcessValue) {
        this.porcessValue = porcessValue;
    }

    public float getDigitalValue() {
        return digitalValue;
    }

    public void setDigitalValue(float digitalValue) {
        this.digitalValue = digitalValue;
    }
}
