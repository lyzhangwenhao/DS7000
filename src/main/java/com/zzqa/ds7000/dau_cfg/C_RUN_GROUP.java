package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: C_RUN_GROUP
 * Description: 测点同步采集组信息表
 *
 * @author 张文豪
 * @date 2021/1/15 16:07
 */
public class C_RUN_GROUP {
    private int id_rungroup;
    private String name;
    private int id_dau;
    private byte freq_type; //采集器类型，0：同步整周期，1：按固定频率采集（非同步）
    private long id_speed;  //转速（数字通道）测点及转速（Modbus）的node_id
    private long id_process;    //过程量测点node_id
    private long id_digital;     //数字量测点node_id
    private int wave_fft_lines;
    private float wave_freq_range;
    private int wave_run_cycles;    //正常采样周期
    private int wave_susd_cycles;   //启停机采样周期
    private int delta_speed;    //启停机转速间隔
    private int susd_stored_intvl;  //启停机平台数据时间间隔
    private byte su_sample_mode;    //启停机的采样方式
    private byte sd_sample_mode;    //停机时的采样方式
    private float run_speed;    //正常运行时的转速
    private float speed_swing;  //转速范围
    private float min_speed;    //最低转速
    private float max_speed;    //最高转速
    private byte ev_act_range_type0;    //特征值参考测点0的类型，0：全范围，1：转速，2：过程量，3：数字量
    private float ev_act_range_min0;
    private float ev_act_range_max0;
    private byte ev_act_range_type1;
    private float ev_act_range_min1;
    private float ev_act_range_max1;
    private byte wave_act_range_type0;  //波形参考测点0的类型
    private float wave_act_range_min0;
    private float wave_act_range_max0;
    private byte wave_act_range_type1;  //波形参考测点1的类型
    private float wave_act_range_min1;
    private float wave_act_range_max1;
    private int ev_stored_intvl;    //特征值存储时间间隔
    private int ev_realtime_intvl;  //特征值实时数据上传间隔
    private int wave_stored_intvl;  //波形存储时间间隔
    private int wave_realtime_intvl;    //波形实时数据上传间隔
    private byte black_box_triger;   //黑匣子触发类型
    private int black_box_intvl;    //触发黑匣子数据监测时间间隔
    private int ev_lines;
    private byte wave_avg_type;
    private int wave_avg_no;
    private float ev_act_range_delta0;  //参考工艺量1变化量
    private float ev_act_range_delta1;
    private float wave_act_range_delta0;
    private float wave_act_range_delta1;
    private long id_node;   //固定频率计算转速参考振动测点

    public C_RUN_GROUP() {
    }

    public int getId_rungroup() {
        return id_rungroup;
    }

    public void setId_rungroup(int id_rungroup) {
        this.id_rungroup = id_rungroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_dau() {
        return id_dau;
    }

    public void setId_dau(int id_dau) {
        this.id_dau = id_dau;
    }

    public byte getFreq_type() {
        return freq_type;
    }

    public void setFreq_type(byte freq_type) {
        this.freq_type = freq_type;
    }

    public long getId_speed() {
        return id_speed;
    }

    public void setId_speed(long id_speed) {
        this.id_speed = id_speed;
    }

    public long getId_process() {
        return id_process;
    }

    public void setId_process(long id_process) {
        this.id_process = id_process;
    }

    public long getId_digital() {
        return id_digital;
    }

    public void setId_digital(long id_digital) {
        this.id_digital = id_digital;
    }

    public int getWave_fft_lines() {
        return wave_fft_lines;
    }

    public void setWave_fft_lines(int wave_fft_lines) {
        this.wave_fft_lines = wave_fft_lines;
    }

    public float getWave_freq_range() {
        return wave_freq_range;
    }

    public void setWave_freq_range(float wave_freq_range) {
        this.wave_freq_range = wave_freq_range;
    }

    public int getWave_run_cycles() {
        return wave_run_cycles;
    }

    public void setWave_run_cycles(int wave_run_cycles) {
        this.wave_run_cycles = wave_run_cycles;
    }

    public int getWave_susd_cycles() {
        return wave_susd_cycles;
    }

    public void setWave_susd_cycles(int wave_susd_cycles) {
        this.wave_susd_cycles = wave_susd_cycles;
    }

    public int getDelta_speed() {
        return delta_speed;
    }

    public void setDelta_speed(int delta_speed) {
        this.delta_speed = delta_speed;
    }

    public int getSusd_stored_intvl() {
        return susd_stored_intvl;
    }

    public void setSusd_stored_intvl(int susd_stored_intvl) {
        this.susd_stored_intvl = susd_stored_intvl;
    }

    public byte getSu_sample_mode() {
        return su_sample_mode;
    }

    public void setSu_sample_mode(byte su_sample_mode) {
        this.su_sample_mode = su_sample_mode;
    }

    public byte getSd_sample_mode() {
        return sd_sample_mode;
    }

    public void setSd_sample_mode(byte sd_sample_mode) {
        this.sd_sample_mode = sd_sample_mode;
    }

    public float getRun_speed() {
        return run_speed;
    }

    public void setRun_speed(float run_speed) {
        this.run_speed = run_speed;
    }

    public float getSpeed_swing() {
        return speed_swing;
    }

    public void setSpeed_swing(float speed_swing) {
        this.speed_swing = speed_swing;
    }

    public float getMin_speed() {
        return min_speed;
    }

    public void setMin_speed(float min_speed) {
        this.min_speed = min_speed;
    }

    public float getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(float max_speed) {
        this.max_speed = max_speed;
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

    public byte getWave_act_range_type0() {
        return wave_act_range_type0;
    }

    public void setWave_act_range_type0(byte wave_act_range_type0) {
        this.wave_act_range_type0 = wave_act_range_type0;
    }

    public float getWave_act_range_min0() {
        return wave_act_range_min0;
    }

    public void setWave_act_range_min0(float wave_act_range_min0) {
        this.wave_act_range_min0 = wave_act_range_min0;
    }

    public float getWave_act_range_max0() {
        return wave_act_range_max0;
    }

    public void setWave_act_range_max0(float wave_act_range_max0) {
        this.wave_act_range_max0 = wave_act_range_max0;
    }

    public byte getWave_act_range_type1() {
        return wave_act_range_type1;
    }

    public void setWave_act_range_type1(byte wave_act_range_type1) {
        this.wave_act_range_type1 = wave_act_range_type1;
    }

    public float getWave_act_range_min1() {
        return wave_act_range_min1;
    }

    public void setWave_act_range_min1(float wave_act_range_min1) {
        this.wave_act_range_min1 = wave_act_range_min1;
    }

    public float getWave_act_range_max1() {
        return wave_act_range_max1;
    }

    public void setWave_act_range_max1(float wave_act_range_max1) {
        this.wave_act_range_max1 = wave_act_range_max1;
    }

    public int getEv_stored_intvl() {
        return ev_stored_intvl;
    }

    public void setEv_stored_intvl(int ev_stored_intvl) {
        this.ev_stored_intvl = ev_stored_intvl;
    }

    public int getEv_realtime_intvl() {
        return ev_realtime_intvl;
    }

    public void setEv_realtime_intvl(int ev_realtime_intvl) {
        this.ev_realtime_intvl = ev_realtime_intvl;
    }

    public int getWave_stored_intvl() {
        return wave_stored_intvl;
    }

    public void setWave_stored_intvl(int wave_stored_intvl) {
        this.wave_stored_intvl = wave_stored_intvl;
    }

    public int getWave_realtime_intvl() {
        return wave_realtime_intvl;
    }

    public void setWave_realtime_intvl(int wave_realtime_intvl) {
        this.wave_realtime_intvl = wave_realtime_intvl;
    }

    public byte getBlack_box_triger() {
        return black_box_triger;
    }

    public void setBlack_box_triger(byte black_box_triger) {
        this.black_box_triger = black_box_triger;
    }

    public int getBlack_box_intvl() {
        return black_box_intvl;
    }

    public void setBlack_box_intvl(int black_box_intvl) {
        this.black_box_intvl = black_box_intvl;
    }

    public int getEv_lines() {
        return ev_lines;
    }

    public void setEv_lines(int ev_lines) {
        this.ev_lines = ev_lines;
    }

    public byte getWave_avg_type() {
        return wave_avg_type;
    }

    public void setWave_avg_type(byte wave_avg_type) {
        this.wave_avg_type = wave_avg_type;
    }

    public int getWave_avg_no() {
        return wave_avg_no;
    }

    public void setWave_avg_no(int wave_avg_no) {
        this.wave_avg_no = wave_avg_no;
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

    public float getWave_act_range_delta0() {
        return wave_act_range_delta0;
    }

    public void setWave_act_range_delta0(float wave_act_range_delta0) {
        this.wave_act_range_delta0 = wave_act_range_delta0;
    }

    public float getWave_act_range_delta1() {
        return wave_act_range_delta1;
    }

    public void setWave_act_range_delta1(float wave_act_range_delta1) {
        this.wave_act_range_delta1 = wave_act_range_delta1;
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }
}
