package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_DGT_CFG
 * Description: 计数测点（数字通道）信息表
 *
 * @author 张文豪
 * @date 2021/1/15 11:48
 */
@Component
public class C_TAG_DGT_CFG {
    private long id_node;    //外键
    private int id_dauchl;      //外键，引用的数字通道通道id
    private byte alarm_trigg;   //触发条件（开关量测点有效），0:不触发，1：0报警，2:1报警
    private byte alarm_enter;   //连续达到报警的次数（触发报警）
    private byte alarm_leave;   //连续达到解除报警的次数（解除报警）
    private int stored_intvl;   //数据的存储时间间隔，单位：分钟，0：不存储数据
    private byte hh_act;
    private float hh_level;
    private byte h_act;
    private float h_level;
    private byte ll_act;
    private float ll_level;
    private byte l_act;
    private float l_level;
    private float range_h;  //量纲范围高，缺省500
    private float range_l;  //量纲范围低，缺省0；约束条件：比range_h小

    public C_TAG_DGT_CFG() {
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

    public byte getAlarm_trigg() {
        return alarm_trigg;
    }

    public void setAlarm_trigg(byte alarm_trigg) {
        this.alarm_trigg = alarm_trigg;
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
