package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_DAU_CHL_WLAN_CFG
 * Description: 网口通道信息表
 *
 * @author 张文豪
 * @date 2021/1/15 16:49
 */
@Component
public class C_DAU_CHL_WLAN_CFG {
    private int id_dauchl;  //外键
    private int chl_no; //通道的硬件编号，大于0
    private byte master_slave;  //这个网口在TCP Modbus通讯中的主设备或从设备
    private byte slave_id;  //DAU的从设备号
    private int poll_time;  //扫描速率
    private String slave_ip;    //从设备IP地址
    private int port;   //通讯端口号，取值1~65535,

    public C_DAU_CHL_WLAN_CFG() {
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

    public byte getMaster_slave() {
        return master_slave;
    }

    public void setMaster_slave(byte master_slave) {
        this.master_slave = master_slave;
    }

    public byte getSlave_id() {
        return slave_id;
    }

    public void setSlave_id(byte slave_id) {
        this.slave_id = slave_id;
    }

    public int getPoll_time() {
        return poll_time;
    }

    public void setPoll_time(int poll_time) {
        this.poll_time = poll_time;
    }

    public String getSlave_ip() {
        return slave_ip;
    }

    public void setSlave_ip(String slave_ip) {
        this.slave_ip = slave_ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
