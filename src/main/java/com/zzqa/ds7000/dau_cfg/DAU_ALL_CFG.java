package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: DAU_ALL_CFG
 * Description: 全信息数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/14 17:01
 */
@Component
public class DAU_ALL_CFG {
    private int dwStructLen;    //结构体字节长度

    //振动通道
    private int vib_num;
    @Autowired
    private DAU_CHL_VIB_CFG vib_chl;

    //过程量通道
    private int pro_num;
    @Autowired
    private DAU_CHL_PRO_CFG pro_chl;

    //数字量通道
    private int dgt_num;
    @Autowired
    private DAU_CHL_DGT_CFG dgt_chl;

    //串口通道信息数据结构
    private int com_num;
    @Autowired
    private DAU_CHL_COM_CFG com_chl;

    //转速测点
    private int speed_tag_num;
    @Autowired
    private TAG_SPEED_CFG speed_tag;

    //数字量测点
    private int dgt_tag_num;
    @Autowired
    private TAG_DGT_CFG dgt_tag;

    //振动测点
    private int vib_tag_num;
    @Autowired
    private TAG_VIB_CFG vib_tag;

    //过程量测点
    private int pro_tag_num;
    @Autowired
    private TAG_PRO_CFG pro_tag;

    //Modbus测点
    private int mdb_tag_num;
    @Autowired
    private TAG_MDB_CFG mdb_tag;

    //测量组
    private int run_group_num;
    @Autowired
    private RUN_GROUP_CFG run_group;

    //2.0新增的网口通道信息
    private int wlan_chl_num;
    @Autowired
    private DAU_CHL_WLAN_CFG wlan_chl;

    //2.0新增的Modbus转速通道
    private int mdb_speed_tag_num;
    @Autowired
    private TAG_MDB_SPEED_CFG mdb_speed_tag;

    public DAU_ALL_CFG() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public int getVib_num() {
        return vib_num;
    }

    public void setVib_num(int vib_num) {
        this.vib_num = vib_num;
    }

    public DAU_CHL_VIB_CFG getVib_chl() {
        return vib_chl;
    }

    public void setVib_chl(DAU_CHL_VIB_CFG vib_chl) {
        this.vib_chl = vib_chl;
    }

    public int getPro_num() {
        return pro_num;
    }

    public void setPro_num(int pro_num) {
        this.pro_num = pro_num;
    }

    public DAU_CHL_PRO_CFG getPro_chl() {
        return pro_chl;
    }

    public void setPro_chl(DAU_CHL_PRO_CFG pro_chl) {
        this.pro_chl = pro_chl;
    }

    public int getDgt_num() {
        return dgt_num;
    }

    public void setDgt_num(int dgt_num) {
        this.dgt_num = dgt_num;
    }

    public DAU_CHL_DGT_CFG getDgt_chl() {
        return dgt_chl;
    }

    public void setDgt_chl(DAU_CHL_DGT_CFG dgt_chl) {
        this.dgt_chl = dgt_chl;
    }

    public int getCom_num() {
        return com_num;
    }

    public void setCom_num(int com_num) {
        this.com_num = com_num;
    }

    public DAU_CHL_COM_CFG getCom_chl() {
        return com_chl;
    }

    public void setCom_chl(DAU_CHL_COM_CFG com_chl) {
        this.com_chl = com_chl;
    }

    public int getSpeed_tag_num() {
        return speed_tag_num;
    }

    public void setSpeed_tag_num(int speed_tag_num) {
        this.speed_tag_num = speed_tag_num;
    }

    public TAG_SPEED_CFG getSpeed_tag() {
        return speed_tag;
    }

    public void setSpeed_tag(TAG_SPEED_CFG speed_tag) {
        this.speed_tag = speed_tag;
    }

    public int getDgt_tag_num() {
        return dgt_tag_num;
    }

    public void setDgt_tag_num(int dgt_tag_num) {
        this.dgt_tag_num = dgt_tag_num;
    }

    public TAG_DGT_CFG getDgt_tag() {
        return dgt_tag;
    }

    public void setDgt_tag(TAG_DGT_CFG dgt_tag) {
        this.dgt_tag = dgt_tag;
    }

    public int getVib_tag_num() {
        return vib_tag_num;
    }

    public void setVib_tag_num(int vib_tag_num) {
        this.vib_tag_num = vib_tag_num;
    }

    public TAG_VIB_CFG getVib_tag() {
        return vib_tag;
    }

    public void setVib_tag(TAG_VIB_CFG vib_tag) {
        this.vib_tag = vib_tag;
    }

    public int getPro_tag_num() {
        return pro_tag_num;
    }

    public void setPro_tag_num(int pro_tag_num) {
        this.pro_tag_num = pro_tag_num;
    }

    public TAG_PRO_CFG getPro_tag() {
        return pro_tag;
    }

    public void setPro_tag(TAG_PRO_CFG pro_tag) {
        this.pro_tag = pro_tag;
    }

    public int getMdb_tag_num() {
        return mdb_tag_num;
    }

    public void setMdb_tag_num(int mdb_tag_num) {
        this.mdb_tag_num = mdb_tag_num;
    }

    public TAG_MDB_CFG getMdb_tag() {
        return mdb_tag;
    }

    public void setMdb_tag(TAG_MDB_CFG mdb_tag) {
        this.mdb_tag = mdb_tag;
    }

    public int getRun_group_num() {
        return run_group_num;
    }

    public void setRun_group_num(int run_group_num) {
        this.run_group_num = run_group_num;
    }

    public RUN_GROUP_CFG getRun_group() {
        return run_group;
    }

    public void setRun_group(RUN_GROUP_CFG run_group) {
        this.run_group = run_group;
    }

    public int getWlan_chl_num() {
        return wlan_chl_num;
    }

    public void setWlan_chl_num(int wlan_chl_num) {
        this.wlan_chl_num = wlan_chl_num;
    }

    public DAU_CHL_WLAN_CFG getWlan_chl() {
        return wlan_chl;
    }

    public void setWlan_chl(DAU_CHL_WLAN_CFG wlan_chl) {
        this.wlan_chl = wlan_chl;
    }

    public int getMdb_speed_tag_num() {
        return mdb_speed_tag_num;
    }

    public void setMdb_speed_tag_num(int mdb_speed_tag_num) {
        this.mdb_speed_tag_num = mdb_speed_tag_num;
    }

    public TAG_MDB_SPEED_CFG getMdb_speed_tag() {
        return mdb_speed_tag;
    }

    public void setMdb_speed_tag(TAG_MDB_SPEED_CFG mdb_speed_tag) {
        this.mdb_speed_tag = mdb_speed_tag;
    }
}
