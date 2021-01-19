package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_DAU_CHL_PRO_CFG
 * Description: 工艺量通道信息表
 *
 * @author 张文豪
 * @date 2021/1/14 17:54
 */
@Component
public class C_DAU_CHL_PRO_CFG {
    private int id_dauchl;      //外键
    private int chl_no;     //通道的硬件编号，大于0
    /**
     * 传感器类型：
     * 20：温度传感器，单位℃
     * 21：压力传感器，Pa
     * 22：流量传感器，N3/h
     * 23：轴位移，um
     * 24：轴位移，mm
     * 255：其他
     */
    private byte sensor_type;
    private String eu;      //工程单位
    private String sensor_model;        //传感器的型号
    private String sensor_vendor;       //传感器的生产厂商
    private float install_angle;        //传感器安装角度：0~360°
    private byte current_feed;      //输入档位，0：±10V，1：±20V
    private float scale;        //灵敏系数1.0
    private float zero_shift;       //零点漂移0.0
    private float corr;     //校正参数；必须大于0
    private byte cable_check;       //电缆连接检查(Gap电压范围检查开关) 0：不检查，1：检查
    private float v_min;        //最小工作电压，单位mv
    private float v_max;        //最大工作电压，单位mv
    private float graphXMin;        //传感器输入点1电压，单位mv
    private float graphXMax;        //传感器输入点2电压，单位mv
    private float graphYMin;        //传感器输入点1工程量
    private float graphYMax;        //传感器输入点2工程量

    public C_DAU_CHL_PRO_CFG() {
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

    public byte getCurrent_feed() {
        return current_feed;
    }

    public void setCurrent_feed(byte current_feed) {
        this.current_feed = current_feed;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getZero_shift() {
        return zero_shift;
    }

    public void setZero_shift(float zero_shift) {
        this.zero_shift = zero_shift;
    }

    public float getCorr() {
        return corr;
    }

    public void setCorr(float corr) {
        this.corr = corr;
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

    public float getGraphXMin() {
        return graphXMin;
    }

    public void setGraphXMin(float graphXMin) {
        this.graphXMin = graphXMin;
    }

    public float getGraphXMax() {
        return graphXMax;
    }

    public void setGraphXMax(float graphXMax) {
        this.graphXMax = graphXMax;
    }

    public float getGraphYMin() {
        return graphYMin;
    }

    public void setGraphYMin(float graphYMin) {
        this.graphYMin = graphYMin;
    }

    public float getGraphYMax() {
        return graphYMax;
    }

    public void setGraphYMax(float graphYMax) {
        this.graphYMax = graphYMax;
    }
}
