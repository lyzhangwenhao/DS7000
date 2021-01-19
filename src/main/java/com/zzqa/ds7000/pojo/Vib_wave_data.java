package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

/**
 * ClassName: Vib_wave_data
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/13 10:37
 */
@Component
public class Vib_wave_data {
    private int dwStructLen;    //字节长度
    private byte freq_type;     //采集类型；0：同步整周期，1：按固定频率采集(非同步)

    //freq_type = 0，同步整周期采样
    private int cycles;     //采样周期
    private int samples;    //采样总点数
    //freq_type = 1，按固定频率采集(非同步)
    private int totalSamples;//采样总点数
    private Float sampleFreq;//采样频率

    private byte wave_mode;     //0:频谱（峰-峰，软件版本大于等于1），1:波形，2:FFT Gzip压缩，3:非压缩的原始波形(电压)，频谱(单峰)--兼容老协议:相当于Byte wave_mode=0 且软件版本号为0

    //0：频谱（幅值为峰峰值），软件版本号大于等于1
    private float scale1;        //幅值放大系数
    private int L1;      //频谱数
    private byte[] point1;   //数组长度为4*L(FFT后 谱线位置(2)、幅值(1)=实际幅值*scale、相位(1):相位=实际相位*255/360)

    //1:波形，Gzip压缩，2:FFT，幅值为峰-峰值 Gzip压缩
    private int compressedWaveLen1;      //压缩后的波形长度
    private float waveScale1;            //波形压缩放大倍数
    private byte[] compressedWave1;

    //2:FFT,幅值为峰-峰值 Gzip压缩，小于阈值的幅值/相位设置为0，做整型数Gzip压缩
    private int compressedWaveLen2; //压缩后的波形长度
    private float waveScale2;   //幅值放大系数
    private byte[] compressedWave2;

    //3:原始波形，不需要窗类型
    private float waveScale3;  //波形系数，将波形值转换为原始工程量单位
    private byte[] wave;

    //255:频谱（幅值为峰值），软件版本等于0
    private float scale2;   //幅值放大系数
    private int L2; //频谱数
    byte[] point2;

    //当软件版本大于等于2时增加一个窗类型
    private byte windowsType;

    public Vib_wave_data() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public byte getFreq_type() {
        return freq_type;
    }

    public void setFreq_type(byte freq_type) {
        this.freq_type = freq_type;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getTotalSamples() {
        return totalSamples;
    }

    public void setTotalSamples(int totalSamples) {
        this.totalSamples = totalSamples;
    }

    public Float getSampleFreq() {
        return sampleFreq;
    }

    public void setSampleFreq(Float sampleFreq) {
        this.sampleFreq = sampleFreq;
    }

    public byte getWave_mode() {
        return wave_mode;
    }

    public void setWave_mode(byte wave_mode) {
        this.wave_mode = wave_mode;
    }

    public float getScale1() {
        return scale1;
    }

    public void setScale1(float scale1) {
        this.scale1 = scale1;
    }

    public int getL1() {
        return L1;
    }

    public void setL1(int l1) {
        L1 = l1;
    }

    public byte[] getPoint1() {
        return point1;
    }

    public void setPoint1(byte[] point1) {
        this.point1 = point1;
    }

    public int getCompressedWaveLen1() {
        return compressedWaveLen1;
    }

    public void setCompressedWaveLen1(int compressedWaveLen1) {
        this.compressedWaveLen1 = compressedWaveLen1;
    }

    public float getWaveScale1() {
        return waveScale1;
    }

    public void setWaveScale1(float waveScale1) {
        this.waveScale1 = waveScale1;
    }

    public byte[] getCompressedWave1() {
        return compressedWave1;
    }

    public void setCompressedWave1(byte[] compressedWave1) {
        this.compressedWave1 = compressedWave1;
    }

    public int getCompressedWaveLen2() {
        return compressedWaveLen2;
    }

    public void setCompressedWaveLen2(int compressedWaveLen2) {
        this.compressedWaveLen2 = compressedWaveLen2;
    }

    public float getWaveScale2() {
        return waveScale2;
    }

    public void setWaveScale2(float waveScale2) {
        this.waveScale2 = waveScale2;
    }

    public byte[] getCompressedWave2() {
        return compressedWave2;
    }

    public void setCompressedWave2(byte[] compressedWave2) {
        this.compressedWave2 = compressedWave2;
    }

    public float getWaveScale3() {
        return waveScale3;
    }

    public void setWaveScale3(float waveScale3) {
        this.waveScale3 = waveScale3;
    }

    public byte[] getWave() {
        return wave;
    }

    public void setWave(byte[] wave) {
        this.wave = wave;
    }

    public float getScale2() {
        return scale2;
    }

    public void setScale2(float scale2) {
        this.scale2 = scale2;
    }

    public int getL2() {
        return L2;
    }

    public void setL2(int l2) {
        L2 = l2;
    }

    public byte[] getPoint2() {
        return point2;
    }

    public void setPoint2(byte[] point2) {
        this.point2 = point2;
    }

    public byte getWindowsType() {
        return windowsType;
    }

    public void setWindowsType(byte windowsType) {
        this.windowsType = windowsType;
    }
}
