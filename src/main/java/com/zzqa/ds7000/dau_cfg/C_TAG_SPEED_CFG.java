package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_SPEED_CFG
 * Description: 转速测点（数字通道）信息表
 *
 * @author 张文豪
 * @date 2021/1/15 11:23
 */
@Component
public class C_TAG_SPEED_CFG {
    private long id_node;       //外键
    private int id_dauchl;      //外键
    private int id_speed;       //转速测点 node_id
    private int id_process;     //过程量测点 node_id
    private int id_digital;     //数字量测点 node_id
    private float pulse_rev;    //每转几个脉冲（转速比）
    /**
     * 特征值参考测点0的类型：
     * 0：全范围
     * 1：转速
     * 2：过程量
     * 3：数字量
     * 4:本身值
     */
    private byte ev_act_range_type0;
    private float ev_act_range_min0;    //特征值参考测点0的值范围低
    private float ev_act_range_max0;    //特征值参考测点0的值范围高
    private byte ev_act_range_type1;    //特征值参考测点1的类型
    private float ev_act_range_min1;    //特征值参考测点1的值范围低
    private float ev_act_range_max1;    //特征值参考测点1的值范围高
    private byte hh_act;    //高高报设置有效
    private float hh_level;     //高高报阀值
    private byte h_act;     //高报设置有效
    private float h_level;      //高报阀值
    private byte ll_act;    //低低报设置有效
    private float ll_level; //低低报阀值
    private byte l_act;     //低报设置有效
    private float l_level;  //低报阀值
    private byte alarm_enter;   //连续达到报警的次数(触发报警)
    private byte alarm_level;   //连续达到接触报警的次数(解除报警)
    private byte speed_wave;    //是否上传转速波形，0：不上传，1：上传
    private int stored_intvl;   //数据的存储时间间隔，单位：分钟 0:不存储
    private int delta_percent;  //变化百分比存储，0：不做变化存储
    private float delta_level;  //变化存储触发阀值
    private byte diretion;      //旋转方向，0：顺时针，1:逆时针
    private int molecule;   //转速比分子
    private int denominator;    //转速比分母
    private float ev_act_range_delta0;  //参考工艺量1变化量（0：不做变化存储）
    private float ev_act_range_delta1;  //参考工艺量1变化量（0：不做变化存储）
    private float range_h;  //量纲范围高，缺省3000
    private float range_l;  //量纲范围低，缺省0，约束条件：比range_h小

    public C_TAG_SPEED_CFG() {
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

    public int getId_speed() {
        return id_speed;
    }

    public void setId_speed(int id_speed) {
        this.id_speed = id_speed;
    }

    public int getId_process() {
        return id_process;
    }

    public void setId_process(int id_process) {
        this.id_process = id_process;
    }

    public int getId_digital() {
        return id_digital;
    }

    public void setId_digital(int id_digital) {
        this.id_digital = id_digital;
    }

    public float getPulse_rev() {
        return pulse_rev;
    }

    public void setPulse_rev(float pulse_rev) {
        this.pulse_rev = pulse_rev;
    }

    public byte getEv_act_range_type0() {
        return ev_act_range_type0;
    }

    public void setEv_act_range_type0(byte ev_act_range_type0) {
        this.ev_act_range_type0 = ev_act_range_type0;
    }

    public float getEv_act_range_min0() {
        return ev_act_range_min0;
    }

    public void setEv_act_range_min0(float ev_act_range_min0) {
        this.ev_act_range_min0 = ev_act_range_min0;
    }

    public float getEv_act_range_max0() {
        return ev_act_range_max0;
    }

    public void setEv_act_range_max0(float ev_act_range_max0) {
        this.ev_act_range_max0 = ev_act_range_max0;
    }

    public byte getEv_act_range_type1() {
        return ev_act_range_type1;
    }

    public void setEv_act_range_type1(byte ev_act_range_type1) {
        this.ev_act_range_type1 = ev_act_range_type1;
    }

    public float getEv_act_range_min1() {
        return ev_act_range_min1;
    }

    public void setEv_act_range_min1(float ev_act_range_min1) {
        this.ev_act_range_min1 = ev_act_range_min1;
    }

    public float getEv_act_range_max1() {
        return ev_act_range_max1;
    }

    public void setEv_act_range_max1(float ev_act_range_max1) {
        this.ev_act_range_max1 = ev_act_range_max1;
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

    public byte getAlarm_level() {
        return alarm_level;
    }

    public void setAlarm_level(byte alarm_level) {
        this.alarm_level = alarm_level;
    }

    public byte getSpeed_wave() {
        return speed_wave;
    }

    public void setSpeed_wave(byte speed_wave) {
        this.speed_wave = speed_wave;
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

    public int getMolecule() {
        return molecule;
    }

    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
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
}
