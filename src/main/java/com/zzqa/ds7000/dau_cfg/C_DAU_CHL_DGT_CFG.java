package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_DAU_CHL_DGT_CFG
 * Description: 数字通道信息表
 *
 * @author 张文豪
 * @date 2021/1/15 9:36
 */
@Component
public class C_DAU_CHL_DGT_CFG {
    private int id_dauchl;      //外键
    private int chl_no;     //通道的硬件编号，大于0
    /**
     * 传感器类型：
     * 6：转速传感器，单位rpm
     * 7:油颗粒传感器，单位 个
     * 8：数字传感器，单位Closed/Opened
     */
    private byte sensor_type;
    private String eu;      //工程单位
    private String sensor_model;    //传感器的型号
    private String sensor_vendor;   //传感器的生产厂商
    private float install_angle;    //传感器安装角度：0~360°
    private byte vol_trigger;       //触发电平类型，0：自动，1：手动
    private byte vol_type;      //触发电平信号强弱，0：弱键相信号，1：强键相信号
    /**
     * 电平设置，取值1-8
     * 当vol_type=1时，取值：
     * 1:1.73V
     * 2:3.45V
     * 3:5.18V
     * 4:6.91V
     * 5:8.64V
     * 6:10.36V
     * 7:12.09V
     * 8:13.82V
     * 当vol_type=0时，取值：
     * 1:0.16V
     * 2:0.31V
     * 3：0.47V
     * 4:0.63V
     * 5:0.79V
     * 6:0.94V
     * 7:1.10V
     * 8:1.26V
     */
    private byte vol_gear;
    private byte polarity;      //极性，0：正极，1:负极
    private byte trigger_type;  //触发方式，0：前沿触发，1：后沿触发
    private byte cable_check;   //电缆连接检查(Gap电压范围检查开关) 0:不检查，1:检查
    private float v_min;    //最小工作电压，单位V，缺省-22
    private float v_max;    //最大工作电压，单位V，缺省-2
    private float ac_corr;  //交流矫正参数，大于0，缺省1
    private float dc_corr;  //直流矫正参数，大于0，缺省1

    public C_DAU_CHL_DGT_CFG() {
    }

    public int getId_dauchl() {
        return id_dauchl;
    }

    public void setId_dauchl(int id_dauchl) {
        this.id_dauchl = id_dauchl;
    }

    public int getChl_no() {
        return chl_no;
    }

    public void setChl_no(int chl_no) {
        this.chl_no = chl_no;
    }

    public byte getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(byte sensor_type) {
        this.sensor_type = sensor_type;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public String getSensor_model() {
        return sensor_model;
    }

    public void setSensor_model(String sensor_model) {
        this.sensor_model = sensor_model;
    }

    public String getSensor_vendor() {
        return sensor_vendor;
    }

    public void setSensor_vendor(String sensor_vendor) {
        this.sensor_vendor = sensor_vendor;
    }

    public float getInstall_angle() {
        return install_angle;
    }

    public void setInstall_angle(float install_angle) {
        this.install_angle = install_angle;
    }

    public byte getVol_trigger() {
        return vol_trigger;
    }

    public void setVol_trigger(byte vol_trigger) {
        this.vol_trigger = vol_trigger;
    }

    public byte getVol_type() {
        return vol_type;
    }

    public void setVol_type(byte vol_type) {
        this.vol_type = vol_type;
    }

    public byte getVol_gear() {
        return vol_gear;
    }

    public void setVol_gear(byte vol_gear) {
        this.vol_gear = vol_gear;
    }

    public byte getPolarity() {
        return polarity;
    }

    public void setPolarity(byte polarity) {
        this.polarity = polarity;
    }

    public byte getTrigger_type() {
        return trigger_type;
    }

    public void setTrigger_type(byte trigger_type) {
        this.trigger_type = trigger_type;
    }

    public byte getCable_check() {
        return cable_check;
    }

    public void setCable_check(byte cable_check) {
        this.cable_check = cable_check;
    }

    public float getV_min() {
        return v_min;
    }

    public void setV_min(float v_min) {
        this.v_min = v_min;
    }

    public float getV_max() {
        return v_max;
    }

    public void setV_max(float v_max) {
        this.v_max = v_max;
    }

    public float getAc_corr() {
        return ac_corr;
    }

    public void setAc_corr(float ac_corr) {
        this.ac_corr = ac_corr;
    }

    public float getDc_corr() {
        return dc_corr;
    }

    public void setDc_corr(float dc_corr) {
        this.dc_corr = dc_corr;
    }
}
