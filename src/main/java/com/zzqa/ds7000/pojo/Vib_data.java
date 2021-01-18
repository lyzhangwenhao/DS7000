package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ClassName: Vib_data
 * Description: 振动监测数据
 *
 * @author 张文豪
 * @date 2021/1/13 10:26
 */
@Component
public class Vib_data {
    private int dwStructLen;    //字节长度
    private long id;            //ID

    private int alarmStutas;    //报警状态
    private float voltGap;      //间隙电压(间隙位移值)
    private float AMP_pp;       //总振值
    private float AMP_p;        //峰值
    private float rms;          //有效值
    private float amp1x;        //1倍频幅值
    private float phase1x;      //1倍频相位
    private float amp2x;        //2倍频幅值
    private float phase2x;      //2倍频相位
    private float ampHx;        //0.5~0.6倍频幅值
    private float ampOpt1;      //可选频段1幅值
    private float ampOpt2;      //可选频段2幅值
    private float ampOpt3;      //可选频段3幅值
    private float ampOpt4;      //可选频段4幅值
    private float ampOpt5;      //可选频段5幅值
    private float ampOpt6;      //可选频段6幅值
    private float ampRT;        //残振幅值
    private float ampRTn1X;     //峭度
    private float ampMaxVect;   //最大矢量幅值
    private int dataType;       //2014-6-12增加的存储类型，兼容接第三方数据(不是一个测量组采集)

    private byte appendWave;    //是否带波形，1:带波形，0:不带波形

    private Map<String,Object> wave;     //appendWave=1时有效，波形数据

    public Vib_data() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAlarmStutas() {
        return alarmStutas;
    }

    public void setAlarmStutas(int alarmStutas) {
        this.alarmStutas = alarmStutas;
    }

    public float getVoltGap() {
        return voltGap;
    }

    public void setVoltGap(float voltGap) {
        this.voltGap = voltGap;
    }

    public float getAMP_pp() {
        return AMP_pp;
    }

    public void setAMP_pp(float AMP_pp) {
        this.AMP_pp = AMP_pp;
    }

    public float getAMP_p() {
        return AMP_p;
    }

    public void setAMP_p(float AMP_p) {
        this.AMP_p = AMP_p;
    }

    public float getRms() {
        return rms;
    }

    public void setRms(float rms) {
        this.rms = rms;
    }

    public float getAmp1x() {
        return amp1x;
    }

    public void setAmp1x(float amp1x) {
        this.amp1x = amp1x;
    }

    public float getPhase1x() {
        return phase1x;
    }

    public void setPhase1x(float phase1x) {
        this.phase1x = phase1x;
    }

    public float getAmp2x() {
        return amp2x;
    }

    public void setAmp2x(float amp2x) {
        this.amp2x = amp2x;
    }

    public float getPhase2x() {
        return phase2x;
    }

    public void setPhase2x(float phase2x) {
        this.phase2x = phase2x;
    }

    public float getAmpHx() {
        return ampHx;
    }

    public void setAmpHx(float ampHx) {
        this.ampHx = ampHx;
    }

    public float getAmpOpt1() {
        return ampOpt1;
    }

    public void setAmpOpt1(float ampOpt1) {
        this.ampOpt1 = ampOpt1;
    }

    public float getAmpOpt2() {
        return ampOpt2;
    }

    public void setAmpOpt2(float ampOpt2) {
        this.ampOpt2 = ampOpt2;
    }

    public float getAmpOpt3() {
        return ampOpt3;
    }

    public void setAmpOpt3(float ampOpt3) {
        this.ampOpt3 = ampOpt3;
    }

    public float getAmpOpt4() {
        return ampOpt4;
    }

    public void setAmpOpt4(float ampOpt4) {
        this.ampOpt4 = ampOpt4;
    }

    public float getAmpOpt5() {
        return ampOpt5;
    }

    public void setAmpOpt5(float ampOpt5) {
        this.ampOpt5 = ampOpt5;
    }

    public float getAmpOpt6() {
        return ampOpt6;
    }

    public void setAmpOpt6(float ampOpt6) {
        this.ampOpt6 = ampOpt6;
    }

    public float getAmpRT() {
        return ampRT;
    }

    public void setAmpRT(float ampRT) {
        this.ampRT = ampRT;
    }

    public float getAmpRTn1X() {
        return ampRTn1X;
    }

    public void setAmpRTn1X(float ampRTn1X) {
        this.ampRTn1X = ampRTn1X;
    }

    public float getAmpMaxVect() {
        return ampMaxVect;
    }

    public void setAmpMaxVect(float ampMaxVect) {
        this.ampMaxVect = ampMaxVect;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public byte getAppendWave() {
        return appendWave;
    }

    public void setAppendWave(byte appendWave) {
        this.appendWave = appendWave;
    }

    public Map<String, Object> getWave() {
        return wave;
    }

    public void setWave(Map<String, Object> wave) {
        this.wave = wave;
    }
}
