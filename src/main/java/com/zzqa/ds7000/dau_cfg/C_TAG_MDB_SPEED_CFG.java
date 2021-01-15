package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: C_TAG_MDB_SPEED_CFG
 * Description: 转速测点（Modbus）信息表
 *
 * @author 张文豪
 * @date 2021/1/15 17:05
 */
public class C_TAG_MDB_SPEED_CFG {
    private long id_node;
    private int id_dauchl;
    private byte hh_act;    //高高报设置有效
    private float hh_level; //高高报阈值
    private byte h_act;
    private float h_level;
    private byte ll_act;
    private float ll_level;
    private byte l_act;
    private float l_level;
    private byte alarm_enter;   //连续达到报警的次数
    private byte alarm_leave;   //连续达到解除报警的次数
    private int stored_intvl;   //数据的存储时间间隔，单位：分钟
    private int delta_percent;  //变化百分比存储
    private float delta_level;  //变化存储触发阈值
    private byte diretion;  //旋转方向;
    private int speed_fz;   //转速比分子
    private int speed_fm;   //转速比分母
    private float range_h;  //量纲范围高
    private float range_l;

    private byte funtion_code1; //预设转速的Modbus功能码
    private int plc_address1;   //预设转速的PLC地址
    private byte mdb_datatype1; //预设转速的通信数据类型

    private byte funtion_code2; //预设转速的Modbus功能码
    private int plc_address2;   //实际转速的PLC地址
    private byte mdb_datatype2; //实际转速的通信数据类型

    private byte funtion_code3; //实际电流的Modbus功能码
    private int plc_address3;   //实际电流的PLC地址
    private byte mdb_datatype3; //实际电流的通信数据类型

    public C_TAG_MDB_SPEED_CFG() {
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }

    public int getId_dauchl() {
        return id_dauchl;
    }

    public void setId_dauchl(int id_dauchl) {
        this.id_dauchl = id_dauchl;
    }

    public byte getHh_act() {
        return hh_act;
    }

    public void setHh_act(byte hh_act) {
        this.hh_act = hh_act;
    }

    public float getHh_level() {
        return hh_level;
    }

    public void setHh_level(float hh_level) {
        this.hh_level = hh_level;
    }

    public byte getH_act() {
        return h_act;
    }

    public void setH_act(byte h_act) {
        this.h_act = h_act;
    }

    public float getH_level() {
        return h_level;
    }

    public void setH_level(float h_level) {
        this.h_level = h_level;
    }

    public byte getLl_act() {
        return ll_act;
    }

    public void setLl_act(byte ll_act) {
        this.ll_act = ll_act;
    }

    public float getLl_level() {
        return ll_level;
    }

    public void setLl_level(float ll_level) {
        this.ll_level = ll_level;
    }

    public byte getL_act() {
        return l_act;
    }

    public void setL_act(byte l_act) {
        this.l_act = l_act;
    }

    public float getL_level() {
        return l_level;
    }

    public void setL_level(float l_level) {
        this.l_level = l_level;
    }

    public byte getAlarm_enter() {
        return alarm_enter;
    }

    public void setAlarm_enter(byte alarm_enter) {
        this.alarm_enter = alarm_enter;
    }

    public byte getAlarm_leave() {
        return alarm_leave;
    }

    public void setAlarm_leave(byte alarm_leave) {
        this.alarm_leave = alarm_leave;
    }

    public int getStored_intvl() {
        return stored_intvl;
    }

    public void setStored_intvl(int stored_intvl) {
        this.stored_intvl = stored_intvl;
    }

    public int getDelta_percent() {
        return delta_percent;
    }

    public void setDelta_percent(int delta_percent) {
        this.delta_percent = delta_percent;
    }

    public float getDelta_level() {
        return delta_level;
    }

    public void setDelta_level(float delta_level) {
        this.delta_level = delta_level;
    }

    public byte getDiretion() {
        return diretion;
    }

    public void setDiretion(byte diretion) {
        this.diretion = diretion;
    }

    public int getSpeed_fz() {
        return speed_fz;
    }

    public void setSpeed_fz(int speed_fz) {
        this.speed_fz = speed_fz;
    }

    public int getSpeed_fm() {
        return speed_fm;
    }

    public void setSpeed_fm(int speed_fm) {
        this.speed_fm = speed_fm;
    }

    public float getRange_h() {
        return range_h;
    }

    public void setRange_h(float range_h) {
        this.range_h = range_h;
    }

    public float getRange_l() {
        return range_l;
    }

    public void setRange_l(float range_l) {
        this.range_l = range_l;
    }

    public byte getFuntion_code1() {
        return funtion_code1;
    }

    public void setFuntion_code1(byte funtion_code1) {
        this.funtion_code1 = funtion_code1;
    }

    public int getPlc_address1() {
        return plc_address1;
    }

    public void setPlc_address1(int plc_address1) {
        this.plc_address1 = plc_address1;
    }

    public byte getMdb_datatype1() {
        return mdb_datatype1;
    }

    public void setMdb_datatype1(byte mdb_datatype1) {
        this.mdb_datatype1 = mdb_datatype1;
    }

    public byte getFuntion_code2() {
        return funtion_code2;
    }

    public void setFuntion_code2(byte funtion_code2) {
        this.funtion_code2 = funtion_code2;
    }

    public int getPlc_address2() {
        return plc_address2;
    }

    public void setPlc_address2(int plc_address2) {
        this.plc_address2 = plc_address2;
    }

    public byte getMdb_datatype2() {
        return mdb_datatype2;
    }

    public void setMdb_datatype2(byte mdb_datatype2) {
        this.mdb_datatype2 = mdb_datatype2;
    }

    public byte getFuntion_code3() {
        return funtion_code3;
    }

    public void setFuntion_code3(byte funtion_code3) {
        this.funtion_code3 = funtion_code3;
    }

    public int getPlc_address3() {
        return plc_address3;
    }

    public void setPlc_address3(int plc_address3) {
        this.plc_address3 = plc_address3;
    }

    public byte getMdb_datatype3() {
        return mdb_datatype3;
    }

    public void setMdb_datatype3(byte mdb_datatype3) {
        this.mdb_datatype3 = mdb_datatype3;
    }
}
