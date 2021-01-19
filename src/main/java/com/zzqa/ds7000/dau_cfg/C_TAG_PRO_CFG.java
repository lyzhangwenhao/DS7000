package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_PRO_CFG
 * Description: 工艺量测点信息表
 *
 * @author 张文豪
 * @date 2021/1/15 15:17
 */
@Component
public class C_TAG_PRO_CFG {
    private long id_node;
    private int id_dauchl;
    private long id_speed;  //转速（数字通道）测点及转速（Modbus）的node_id
    private int id_process; //过程量侧袋内node_id
    private int id_digital; //数字量测点
    private byte ev_act_range_type0;    //特征值参考测点0类型，0：全范围，1：转速，2：过程量，3：数字量，4：本身量
    private float ev_act_range_min0;
    private float ev_act_range_max0;
    private byte ev_act_range_type1;
    private float ev_act_range_min1;
    private float ev_act_range_max1;
    private byte hh_act;    //高高报设置有效
    private float hh_level; //高高报阈值
    private byte h_act;
    private float h_level;
    private byte ll_act;
    private float ll_level;
    private byte l_act;
    private float l_level;
    private byte alarm_enter;   //连续达到报警次数
    private byte alarm_leave;   //连续达到解除报警的次数
    private int stored_intvl;   //数据的存储时间间隔
    private int delta_percent;  //变化百分比存储
    private float delta_level;  //变化存储触发阈值
    private float scale;    //放大倍数
    private byte mes_range_act;     //测量范围有效标志，0：无效，1：有效
    private float mes_range_min;    //测量范围低
    private float mes_range_max;
    private float ev_act_range_delta0;  //参考工艺量1变化量
    private float ev_act_range_delta1;
    private float range_h;  //量纲范围高
    private float range_l;

    public C_TAG_PRO_CFG() {
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

    public long getId_speed() {
        return id_speed;
    }

    public void setId_speed(long id_speed) {
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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public byte getMes_range_act() {
        return mes_range_act;
    }

    public void setMes_range_act(byte mes_range_act) {
        this.mes_range_act = mes_range_act;
    }

    public float getMes_range_min() {
        return mes_range_min;
    }

    public void setMes_range_min(float mes_range_min) {
        this.mes_range_min = mes_range_min;
    }

    public float getMes_range_max() {
        return mes_range_max;
    }

    public void setMes_range_max(float mes_range_max) {
        this.mes_range_max = mes_range_max;
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
