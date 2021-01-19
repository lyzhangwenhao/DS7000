package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_COMMON
 * Description: 测点公共信息表
 *
 * @author 张文豪
 * @date 2021/1/15 11:11
 */
@Component
public class C_TAG_COMMON {
    long id_node;   //外键，C_node_cfg.id_node
    int id_rungroup;    //测点所属组 null(-1)时表示无组
    int id_dau; //外键，测点所属DAU
    String eu;  //工程单位
    /**
     * 工程单位类型，（如是振动通道，比较传感器类型做出是否积分）：
     * 0：加速度传感器，单位g
     * 1：加速度传感器，单位m/s²
     * 2：速度传感器，单位ips
     * 3：速度传感器，单位mm/s
     * 4：位移传感器，单位um
     * 5：位移传感器，单位mils
     * 6：转速传感器，单位rpm
     * 7：油颗粒传感器，单位 个
     * 8：数字传感器，单位Closed/Opend
     * 20：温度传感器，单位℃
     * 21：压力传感器，Pa
     * 22：流量传感器，N3/h
     * 23：轴位移，um
     * 24：轴位移，mm
     * 255：其他
     */
    byte eu_type;
    byte alarmBlocking;     //报警开启，0：阻止，1：开启

    public C_TAG_COMMON() {
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }

    public int getId_rungroup() {
        return id_rungroup;
    }

    public void setId_rungroup(int id_rungroup) {
        this.id_rungroup = id_rungroup;
    }

    public int getId_dau() {
        return id_dau;
    }

    public void setId_dau(int id_dau) {
        this.id_dau = id_dau;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public byte getEu_type() {
        return eu_type;
    }

    public void setEu_type(byte eu_type) {
        this.eu_type = eu_type;
    }

    public byte getAlarmBlocking() {
        return alarmBlocking;
    }

    public void setAlarmBlocking(byte alarmBlocking) {
        this.alarmBlocking = alarmBlocking;
    }
}
