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
    private float scale;        //幅值放大系数
    private int L;      //频谱数
    private byte[] point;   //数组长度为4*L(FFT后 谱线位置(2)、幅值(1)=实际幅值*scale、相位(1):相位=实际相位*255/360)

    //1:波形，Gzip压缩，2:FFT，幅值为峰-峰值 Gzip压缩
    private int compressedWaveLen;      //压缩后的波形长度
    private float waveScale;            //波形压缩放大倍数
    private byte[] compressedWave;

    //3:原始波形，不需要窗类型

}
