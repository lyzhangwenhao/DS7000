package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_MDB_CFG
 * Description: Modbus测点信息
 *
 * @author 张文豪
 * @date 2021/1/15 15:45
 */
@Component
public class C_TAG_MDB_CFG {
    private long id_node;
    private int chl_no; //硬件通道id
    private int data_address;   //地址
    private byte data_mode;
    private byte slave_id;  //过程量的从设备号
    private byte tag_type;  //测点类型，0：工艺量，1：数字开关量，2：过程开光量
    private int stored_intvl;   //数据的存储时间间隔，单位：分钟
    private int delta_percent;  //变化百分比存储
    private float delta_level;  //变化存储触发阈值
    private float input_value_1;    //点1输入值
    private float input_value_2;
    private float ref_value_1;  //点1数值
    private float ref_value_2;
    private float scale;    //放大系数
    private byte alarm_trigg;   //tag_type=1或2时有效，报警触发条件，0：不触发，1:1开报警，2:0关报警
    private byte hh_act;    //高高报设置有效
    private float hh_level; //高高报
    private byte h_act;
    private float h_level;
    private byte ll_act;
    private float ll_level;
    private byte l_act;
    private float l_level;
    private byte alarm_enter;   //连续达到报警次数
    private byte alarm_leave;   //连续达到解除报警次数
    private float ev_act_range_delta0;  //参考工艺量1变化量
    private float ev_act_range_delta1;
    private int eu_len; //工程单位长度
    private String eu;  //工程单位（20）
    private byte comDataType;   //通信数据类型
    private float range_h;  //量纲范围高
    private float range_l;

    //tag_type = 1 或 2时显示以下字段
    private int unit0_len;  //0代表的开关量单位长度
    private String unit0;   //0代表的开关量单位（20）
    private int unit1_len;
    private String unit1;

    //tag_type = 2时
    private float swPara0;  //开关量0值得比较值
    private float swPara1;

    public C_TAG_MDB_CFG() {
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }

    public int getChl_no() {
        return chl_no;
    }

    public void setChl_no(int chl_no) {
        this.chl_no = chl_no;
    }

    public int getData_address() {
        return data_address;
    }

    public void setData_address(int data_address) {
        this.data_address = data_address;
    }

    public byte getData_mode() {
        return data_mode;
    }

    public void setData_mode(byte data_mode) {
        this.data_mode = data_mode;
    }

    public byte getSlave_id() {
        return slave_id;
    }

    public void setSlave_id(byte slave_id) {
        this.slave_id = slave_id;
    }

    public byte getTag_type() {
        return tag_type;
    }

    public void setTag_type(byte tag_type) {
        this.tag_type = tag_type;
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

    public float getInput_value_1() {
        return input_value_1;
    }

    public void setInput_value_1(float input_value_1) {
        this.input_value_1 = input_value_1;
    }

    public float getInput_value_2() {
        return input_value_2;
    }

    public void setInput_value_2(float input_value_2) {
        this.input_value_2 = input_value_2;
    }

    public float getRef_value_1() {
        return ref_value_1;
    }

    public void setRef_value_1(float ref_value_1) {
        this.ref_value_1 = ref_value_1;
    }

    public float getRef_value_2() {
        return ref_value_2;
    }

    public void setRef_value_2(float ref_value_2) {
        this.ref_value_2 = ref_value_2;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public byte getAlarm_trigg() {
        return alarm_trigg;
    }

    public void setAlarm_trigg(byte alarm_trigg) {
        this.alarm_trigg = alarm_trigg;
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

    public float getEv_act_range_delta0() {
        return ev_act_range_delta0;
    }

    public void setEv_act_range_delta0(float ev_act_range_delta0) {
        this.ev_act_range_delta0 = ev_act_range_delta0;
    }

    public float getEv_act_range_delta1() {
        return ev_act_range_delta1;
    }

    public void setEv_act_range_delta1(float ev_act_range_delta1) {
        this.ev_act_range_delta1 = ev_act_range_delta1;
    }

    public int getEu_len() {
        return eu_len;
    }

    public void setEu_len(int eu_len) {
        this.eu_len = eu_len;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public byte getComDataType() {
        return comDataType;
    }

    public void setComDataType(byte comDataType) {
        this.comDataType = comDataType;
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

    public int getUnit0_len() {
        return unit0_len;
    }

    public void setUnit0_len(int unit0_len) {
        this.unit0_len = unit0_len;
    }

    public String getUnit0() {
        return unit0;
    }

    public void setUnit0(String unit0) {
        this.unit0 = unit0;
    }

    public int getUnit1_len() {
        return unit1_len;
    }

    public void setUnit1_len(int unit1_len) {
        this.unit1_len = unit1_len;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public float getSwPara0() {
        return swPara0;
    }

    public void setSwPara0(float swPara0) {
        this.swPara0 = swPara0;
    }

    public float getSwPara1() {
        return swPara1;
    }

    public void setSwPara1(float swPara1) {
        this.swPara1 = swPara1;
    }
}
