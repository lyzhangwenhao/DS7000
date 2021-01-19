package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: C_TAG_VIB_CFG
 * Description: 振动测点信息表
 *
 * @author 张文豪
 * @date 2021/1/15 13:28
 */
@Component
public class C_TAG_VIB_CFG {
    private long id_node;
    private byte chl_num;   //引用的通道数
    private int id_dauchl1; //引用的通道ID1
    private int id_dauchl2; //引用的通道ID2
    private int id_dauchl3; //引用的通道ID3
    private int id_plain;   //轴承面ID null(-1)时表示没有轴承面
    private long id_speed;  //转速（数字通道）及转速（Modbus）测点的node_id，null（-1）时表示没有
    private int id_process; //过程量测点node_id
    private int id_digital; //数字量测点node_id
    private byte vib_type;  //振动测点类型，11：阶次分析振动，12：阶次分析包络，13：异步振动，14：异步包络
    private byte diretion;  //旋转方向，0：顺时针，1：逆时针
    private float ref_gap_vol;  //参考间隙电压，单位mv
    private byte wave_window;   //窗类型，0：矩形窗，1：汉宁窗
    /**
     * vit_type=13或14时，波形FFT的谱线数：100,200,400,800,1600,3200,6400
     * vib_type=11或12时，每周期采样点数：32,64,127,256,512,1024,2048,4096
     */
    private int wave_fft_lines;
    /**
     * 采样周期（阶次分析），vib_type=11或12时有效：4,8,16,32,64,128,256,512
     */
    private int wave_run_cycle;
    private byte wave_env_filter;   //包络过滤频率，vib_type=12或14时有效：1:200HZ，2:2KHZ，3:10KHZ，4:40KHZ
    /**
     * 波形开始频率，vib_type=13或14时有效：
     * 10:0-10Hz，0-600RMP
     * 20:0-20Hz,0-1200RMP
     * 50:0-50Hz,0-3000RMP
     * 100:0-100Hz,0-6000RMP
     * 200:0-200Hz,0-12000RMP
     * 500:0-500Hz,0-30000RMP
     * 1000:0-1kHz,0-6wRMP
     * 2000:0-2kHz,0-12wRMP
     * 5000:0-5kHz,0-30wRMP
     * 10000:0-10kHz,0-60wRMP
     * 20000:0-20kHz,0-120wRMP
     * 40000:0-40kHz,0-240wRMP
     */
    private float wave_freq_range;
    private float wave_freq_cutoff;     //波形的低通频率
    private byte wave_avg_type;     //波形平均类型，0：频率，1：时间
    private int wave_avg_no;        //波形平均数
    private int ev_lines;       //特征值的计算所需谱线数
    private byte ev_act_range_type0;    //特征值参考测点0的类型，0：全范围，1:转速，2：过程量，3：数字量
    private float ev_act_range_min0;    //特征值参考测点0的值范围低
    private float ev_act_range_max0;    //特征值参考测点0的值范围高
    private byte ev_act_range_type1;
    private float ev_act_range_min1;
    private float ev_act_range_max1;
    private byte wave_act_range_type0;  //波形参考测点0的类型，0：与特征类型一样，1：转速，2：过程量，3：数字量
    private float wave_act_range_min0;
    private float wave_act_range_max0;
    private byte wave_act_range_type1;
    private float wave_act_range_min1;
    private float wave_act_range_max1;
    private int ev_stored_intvl;    //特征值存储时间间隔
    private int ev_realtime_intvl;  //特征值实时数据上传间隔
    private int wave_stored_intvl;  //波形存储时间间隔
    private int wave_realtime_intvl;    //波形实时数据上传间隔
    private byte alarm_enter;   //连续达到报警的次数
    private byte alarm_leave;   //连续达到解除报警的次数
    private byte freq_bd_type1;     //频段一类型：0无效，1：转速跟随，2：固定转速
    private String freq_bd_name1;   //频段一名称
    private float freq_bd_point1;   //频段一中心点，单位：当freq_band_type=1时为倍频，=2时为RPM
    private float freq_bd_range1;   //频段一的搜索半径范围，单位RPM
    private float freq_bd_alert1;   //频段一预报警门限
    private float freq_bd_alarm1;   //频段一主报警门限
    private byte freq_bd_ld_act1;   //频段一工况关联放大标志，0：无效，1：有效
    private byte freq_bd_type2;
    private String freq_bd_name2;
    private float freq_bd_point2;
    private float freq_bd_range2;
    private float freq_bd_alert2;
    private float freq_bd_alarm2;
    private byte freq_bd_ld_act2;
    private byte freq_bd_type3;
    private String freq_bd_name3;
    private float freq_bd_point3;
    private float freq_bd_range3;
    private float freq_bd_alert3;
    private float freq_bd_alarm3;
    private byte freq_bd_ld_act3;
    private byte freq_bd_type4;
    private String freq_bd_name4;
    private float freq_bd_point4;
    private float freq_bd_range4;
    private float freq_bd_alert4;
    private float freq_bd_alarm4;
    private byte freq_bd_ld_act4;
    private byte freq_bd_type5;
    private String freq_bd_name5;
    private float freq_bd_point5;
    private float freq_bd_range5;
    private float freq_bd_alert5;
    private float freq_bd_alarm5;
    private byte freq_bd_ld_act5;
    private byte freq_bd_type6;
    private String freq_bd_name6;
    private float freq_bd_point6;
    private float freq_bd_range6;
    private float freq_bd_alert6;
    private float freq_bd_alarm6;
    private byte freq_bd_ld_act6;
    private byte allover_type;  //总振值报警类型，0：无效，1：频带，2：整个波形
    private String allover_name;    //名称
    private float allover_s;    //频段起始点，单位RPM
    private float allover_e;    //频段结束点，单位RPM
    private float allover_alert;    //频段一报警门限
    private float allover_alarm;    //频段一主报警门限
    private int allover_delta_percent;  //总振值变化百分比存储
    private float allover_delta_level;  //总振值变化存储触发阈值
    private byte allover_ld_act;    //频段一工况关联放大标志
    private byte ld_type;   //工况类型：0：无工况，1:转速，2：过程量
    private float ld0;  //工况边界0
    private float ld1;
    private float ld2;
    private float ld3;
    private float ld4;
    private float ld5;
    private float ld6;
    private float ld7;
    private float ld8;
    private float ld_percent1;  //工况1下的报警门限放大倍数
    private float ld_percent2;
    private float ld_percent3;
    private float ld_percent4;
    private float ld_percent5;
    private float ld_percent6;
    private float ld_percent7;
    private float ld_percent8;
    private byte wave_mode; //波形压缩类型，非空
    private float fft_thld; //发TT压缩阈值，当wave_mode=0或2时有效
    private int fft_max_xf; //FTT取值的保留倍频，当wave_mode为0和2时有效，缺省为2
    private float lf_cursor;    //神经网络的频率游标低
    private float hf_cursor;    //神经网络的频率游标高
    private float ev_act_range_delta0;  //参考工艺量1变化量（0：不做变化存储）
    private float ev_act_range_delta1;  //参考工艺量2变化量（0：不做变化存储）
    private float wave_act_range_delta0;    //参考工艺量1变化量
    private float wave_act_range_delta1;    //参考工艺量2变化量
    private float range_h;  //量纲范围高
    private float range_l;  //量纲范围低
    private byte bearingType; //检测的轴承类型 0：滑动轴承，1：滚动轴承
    private float normalSpeed;  //工作转速

    public C_TAG_VIB_CFG() {
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }

    public byte getChl_num() {
        return chl_num;
    }

    public void setChl_num(byte chl_num) {
        this.chl_num = chl_num;
    }

    public int getId_dauchl1() {
        return id_dauchl1;
    }

    public void setId_dauchl1(int id_dauchl1) {
        this.id_dauchl1 = id_dauchl1;
    }

    public int getId_dauchl2() {
        return id_dauchl2;
    }

    public void setId_dauchl2(int id_dauchl2) {
        this.id_dauchl2 = id_dauchl2;
    }

    public int getId_dauchl3() {
        return id_dauchl3;
    }

    public void setId_dauchl3(int id_dauchl3) {
        this.id_dauchl3 = id_dauchl3;
    }

    public int getId_plain() {
        return id_plain;
    }

    public void setId_plain(int id_plain) {
        this.id_plain = id_plain;
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

    public byte getVib_type() {
        return vib_type;
    }

    public void setVib_type(byte vib_type) {
        this.vib_type = vib_type;
    }

    public byte getDiretion() {
        return diretion;
    }

    public void setDiretion(byte diretion) {
        this.diretion = diretion;
    }

    public float getRef_gap_vol() {
        return ref_gap_vol;
    }

    public void setRef_gap_vol(float ref_gap_vol) {
        this.ref_gap_vol = ref_gap_vol;
    }

    public byte getWave_window() {
        return wave_window;
    }

    public void setWave_window(byte wave_window) {
        this.wave_window = wave_window;
    }

    public int getWave_fft_lines() {
        return wave_fft_lines;
    }

    public void setWave_fft_lines(int wave_fft_lines) {
        this.wave_fft_lines = wave_fft_lines;
    }

    public int getWave_run_cycle() {
        return wave_run_cycle;
    }

    public void setWave_run_cycle(int wave_run_cycle) {
        this.wave_run_cycle = wave_run_cycle;
    }

    public byte getWave_env_filter() {
        return wave_env_filter;
    }

    public void setWave_env_filter(byte wave_env_filter) {
        this.wave_env_filter = wave_env_filter;
    }

    public float getWave_freq_range() {
        return wave_freq_range;
    }

    public void setWave_freq_range(float wave_freq_range) {
        this.wave_freq_range = wave_freq_range;
    }

    public float getWave_freq_cutoff() {
        return wave_freq_cutoff;
    }

    public void setWave_freq_cutoff(float wave_freq_cutoff) {
        this.wave_freq_cutoff = wave_freq_cutoff;
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

    public int getEv_lines() {
        return ev_lines;
    }

    public void setEv_lines(int ev_lines) {
        this.ev_lines = ev_lines;
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

    public byte getFreq_bd_type1() {
        return freq_bd_type1;
    }

    public void setFreq_bd_type1(byte freq_bd_type1) {
        this.freq_bd_type1 = freq_bd_type1;
    }

    public String getFreq_bd_name1() {
        return freq_bd_name1;
    }

    public void setFreq_bd_name1(String freq_bd_name1) {
        this.freq_bd_name1 = freq_bd_name1;
    }

    public float getFreq_bd_point1() {
        return freq_bd_point1;
    }

    public void setFreq_bd_point1(float freq_bd_point1) {
        this.freq_bd_point1 = freq_bd_point1;
    }

    public float getFreq_bd_range1() {
        return freq_bd_range1;
    }

    public void setFreq_bd_range1(float freq_bd_range1) {
        this.freq_bd_range1 = freq_bd_range1;
    }

    public float getFreq_bd_alert1() {
        return freq_bd_alert1;
    }

    public void setFreq_bd_alert1(float freq_bd_alert1) {
        this.freq_bd_alert1 = freq_bd_alert1;
    }

    public float getFreq_bd_alarm1() {
        return freq_bd_alarm1;
    }

    public void setFreq_bd_alarm1(float freq_bd_alarm1) {
        this.freq_bd_alarm1 = freq_bd_alarm1;
    }

    public byte getFreq_bd_ld_act1() {
        return freq_bd_ld_act1;
    }

    public void setFreq_bd_ld_act1(byte freq_bd_ld_act1) {
        this.freq_bd_ld_act1 = freq_bd_ld_act1;
    }

    public byte getFreq_bd_type2() {
        return freq_bd_type2;
    }

    public void setFreq_bd_type2(byte freq_bd_type2) {
        this.freq_bd_type2 = freq_bd_type2;
    }

    public String getFreq_bd_name2() {
        return freq_bd_name2;
    }

    public void setFreq_bd_name2(String freq_bd_name2) {
        this.freq_bd_name2 = freq_bd_name2;
    }

    public float getFreq_bd_point2() {
        return freq_bd_point2;
    }

    public void setFreq_bd_point2(float freq_bd_point2) {
        this.freq_bd_point2 = freq_bd_point2;
    }

    public float getFreq_bd_range2() {
        return freq_bd_range2;
    }

    public void setFreq_bd_range2(float freq_bd_range2) {
        this.freq_bd_range2 = freq_bd_range2;
    }

    public float getFreq_bd_alert2() {
        return freq_bd_alert2;
    }

    public void setFreq_bd_alert2(float freq_bd_alert2) {
        this.freq_bd_alert2 = freq_bd_alert2;
    }

    public float getFreq_bd_alarm2() {
        return freq_bd_alarm2;
    }

    public void setFreq_bd_alarm2(float freq_bd_alarm2) {
        this.freq_bd_alarm2 = freq_bd_alarm2;
    }

    public byte getFreq_bd_ld_act2() {
        return freq_bd_ld_act2;
    }

    public void setFreq_bd_ld_act2(byte freq_bd_ld_act2) {
        this.freq_bd_ld_act2 = freq_bd_ld_act2;
    }

    public byte getFreq_bd_type3() {
        return freq_bd_type3;
    }

    public void setFreq_bd_type3(byte freq_bd_type3) {
        this.freq_bd_type3 = freq_bd_type3;
    }

    public String getFreq_bd_name3() {
        return freq_bd_name3;
    }

    public void setFreq_bd_name3(String freq_bd_name3) {
        this.freq_bd_name3 = freq_bd_name3;
    }

    public float getFreq_bd_point3() {
        return freq_bd_point3;
    }

    public void setFreq_bd_point3(float freq_bd_point3) {
        this.freq_bd_point3 = freq_bd_point3;
    }

    public float getFreq_bd_range3() {
        return freq_bd_range3;
    }

    public void setFreq_bd_range3(float freq_bd_range3) {
        this.freq_bd_range3 = freq_bd_range3;
    }

    public float getFreq_bd_alert3() {
        return freq_bd_alert3;
    }

    public void setFreq_bd_alert3(float freq_bd_alert3) {
        this.freq_bd_alert3 = freq_bd_alert3;
    }

    public float getFreq_bd_alarm3() {
        return freq_bd_alarm3;
    }

    public void setFreq_bd_alarm3(float freq_bd_alarm3) {
        this.freq_bd_alarm3 = freq_bd_alarm3;
    }

    public byte getFreq_bd_ld_act3() {
        return freq_bd_ld_act3;
    }

    public void setFreq_bd_ld_act3(byte freq_bd_ld_act3) {
        this.freq_bd_ld_act3 = freq_bd_ld_act3;
    }

    public byte getFreq_bd_type4() {
        return freq_bd_type4;
    }

    public void setFreq_bd_type4(byte freq_bd_type4) {
        this.freq_bd_type4 = freq_bd_type4;
    }

    public String getFreq_bd_name4() {
        return freq_bd_name4;
    }

    public void setFreq_bd_name4(String freq_bd_name4) {
        this.freq_bd_name4 = freq_bd_name4;
    }

    public float getFreq_bd_point4() {
        return freq_bd_point4;
    }

    public void setFreq_bd_point4(float freq_bd_point4) {
        this.freq_bd_point4 = freq_bd_point4;
    }

    public float getFreq_bd_range4() {
        return freq_bd_range4;
    }

    public void setFreq_bd_range4(float freq_bd_range4) {
        this.freq_bd_range4 = freq_bd_range4;
    }

    public float getFreq_bd_alert4() {
        return freq_bd_alert4;
    }

    public void setFreq_bd_alert4(float freq_bd_alert4) {
        this.freq_bd_alert4 = freq_bd_alert4;
    }

    public float getFreq_bd_alarm4() {
        return freq_bd_alarm4;
    }

    public void setFreq_bd_alarm4(float freq_bd_alarm4) {
        this.freq_bd_alarm4 = freq_bd_alarm4;
    }

    public byte getFreq_bd_ld_act4() {
        return freq_bd_ld_act4;
    }

    public void setFreq_bd_ld_act4(byte freq_bd_ld_act4) {
        this.freq_bd_ld_act4 = freq_bd_ld_act4;
    }

    public byte getFreq_bd_type5() {
        return freq_bd_type5;
    }

    public void setFreq_bd_type5(byte freq_bd_type5) {
        this.freq_bd_type5 = freq_bd_type5;
    }

    public String getFreq_bd_name5() {
        return freq_bd_name5;
    }

    public void setFreq_bd_name5(String freq_bd_name5) {
        this.freq_bd_name5 = freq_bd_name5;
    }

    public float getFreq_bd_point5() {
        return freq_bd_point5;
    }

    public void setFreq_bd_point5(float freq_bd_point5) {
        this.freq_bd_point5 = freq_bd_point5;
    }

    public float getFreq_bd_range5() {
        return freq_bd_range5;
    }

    public void setFreq_bd_range5(float freq_bd_range5) {
        this.freq_bd_range5 = freq_bd_range5;
    }

    public float getFreq_bd_alert5() {
        return freq_bd_alert5;
    }

    public void setFreq_bd_alert5(float freq_bd_alert5) {
        this.freq_bd_alert5 = freq_bd_alert5;
    }

    public float getFreq_bd_alarm5() {
        return freq_bd_alarm5;
    }

    public void setFreq_bd_alarm5(float freq_bd_alarm5) {
        this.freq_bd_alarm5 = freq_bd_alarm5;
    }

    public byte getFreq_bd_ld_act5() {
        return freq_bd_ld_act5;
    }

    public void setFreq_bd_ld_act5(byte freq_bd_ld_act5) {
        this.freq_bd_ld_act5 = freq_bd_ld_act5;
    }

    public byte getFreq_bd_type6() {
        return freq_bd_type6;
    }

    public void setFreq_bd_type6(byte freq_bd_type6) {
        this.freq_bd_type6 = freq_bd_type6;
    }

    public String getFreq_bd_name6() {
        return freq_bd_name6;
    }

    public void setFreq_bd_name6(String freq_bd_name6) {
        this.freq_bd_name6 = freq_bd_name6;
    }

    public float getFreq_bd_point6() {
        return freq_bd_point6;
    }

    public void setFreq_bd_point6(float freq_bd_point6) {
        this.freq_bd_point6 = freq_bd_point6;
    }

    public float getFreq_bd_range6() {
        return freq_bd_range6;
    }

    public void setFreq_bd_range6(float freq_bd_range6) {
        this.freq_bd_range6 = freq_bd_range6;
    }

    public float getFreq_bd_alert6() {
        return freq_bd_alert6;
    }

    public void setFreq_bd_alert6(float freq_bd_alert6) {
        this.freq_bd_alert6 = freq_bd_alert6;
    }

    public float getFreq_bd_alarm6() {
        return freq_bd_alarm6;
    }

    public void setFreq_bd_alarm6(float freq_bd_alarm6) {
        this.freq_bd_alarm6 = freq_bd_alarm6;
    }

    public byte getFreq_bd_ld_act6() {
        return freq_bd_ld_act6;
    }

    public void setFreq_bd_ld_act6(byte freq_bd_ld_act6) {
        this.freq_bd_ld_act6 = freq_bd_ld_act6;
    }

    public byte getAllover_type() {
        return allover_type;
    }

    public void setAllover_type(byte allover_type) {
        this.allover_type = allover_type;
    }

    public String getAllover_name() {
        return allover_name;
    }

    public void setAllover_name(String allover_name) {
        this.allover_name = allover_name;
    }

    public float getAllover_s() {
        return allover_s;
    }

    public void setAllover_s(float allover_s) {
        this.allover_s = allover_s;
    }

    public float getAllover_e() {
        return allover_e;
    }

    public void setAllover_e(float allover_e) {
        this.allover_e = allover_e;
    }

    public float getAllover_alert() {
        return allover_alert;
    }

    public void setAllover_alert(float allover_alert) {
        this.allover_alert = allover_alert;
    }

    public float getAllover_alarm() {
        return allover_alarm;
    }

    public void setAllover_alarm(float allover_alarm) {
        this.allover_alarm = allover_alarm;
    }

    public int getAllover_delta_percent() {
        return allover_delta_percent;
    }

    public void setAllover_delta_percent(int allover_delta_percent) {
        this.allover_delta_percent = allover_delta_percent;
    }

    public float getAllover_delta_level() {
        return allover_delta_level;
    }

    public void setAllover_delta_level(float allover_delta_level) {
        this.allover_delta_level = allover_delta_level;
    }

    public byte getAllover_ld_act() {
        return allover_ld_act;
    }

    public void setAllover_ld_act(byte allover_ld_act) {
        this.allover_ld_act = allover_ld_act;
    }

    public byte getLd_type() {
        return ld_type;
    }

    public void setLd_type(byte ld_type) {
        this.ld_type = ld_type;
    }

    public float getLd0() {
        return ld0;
    }

    public void setLd0(float ld0) {
        this.ld0 = ld0;
    }

    public float getLd1() {
        return ld1;
    }

    public void setLd1(float ld1) {
        this.ld1 = ld1;
    }

    public float getLd2() {
        return ld2;
    }

    public void setLd2(float ld2) {
        this.ld2 = ld2;
    }

    public float getLd3() {
        return ld3;
    }

    public void setLd3(float ld3) {
        this.ld3 = ld3;
    }

    public float getLd4() {
        return ld4;
    }

    public void setLd4(float ld4) {
        this.ld4 = ld4;
    }

    public float getLd5() {
        return ld5;
    }

    public void setLd5(float ld5) {
        this.ld5 = ld5;
    }

    public float getLd6() {
        return ld6;
    }

    public void setLd6(float ld6) {
        this.ld6 = ld6;
    }

    public float getLd7() {
        return ld7;
    }

    public void setLd7(float ld7) {
        this.ld7 = ld7;
    }

    public float getLd8() {
        return ld8;
    }

    public void setLd8(float ld8) {
        this.ld8 = ld8;
    }

    public float getLd_percent1() {
        return ld_percent1;
    }

    public void setLd_percent1(float ld_percent1) {
        this.ld_percent1 = ld_percent1;
    }

    public float getLd_percent2() {
        return ld_percent2;
    }

    public void setLd_percent2(float ld_percent2) {
        this.ld_percent2 = ld_percent2;
    }

    public float getLd_percent3() {
        return ld_percent3;
    }

    public void setLd_percent3(float ld_percent3) {
        this.ld_percent3 = ld_percent3;
    }

    public float getLd_percent4() {
        return ld_percent4;
    }

    public void setLd_percent4(float ld_percent4) {
        this.ld_percent4 = ld_percent4;
    }

    public float getLd_percent5() {
        return ld_percent5;
    }

    public void setLd_percent5(float ld_percent5) {
        this.ld_percent5 = ld_percent5;
    }

    public float getLd_percent6() {
        return ld_percent6;
    }

    public void setLd_percent6(float ld_percent6) {
        this.ld_percent6 = ld_percent6;
    }

    public float getLd_percent7() {
        return ld_percent7;
    }

    public void setLd_percent7(float ld_percent7) {
        this.ld_percent7 = ld_percent7;
    }

    public float getLd_percent8() {
        return ld_percent8;
    }

    public void setLd_percent8(float ld_percent8) {
        this.ld_percent8 = ld_percent8;
    }

    public byte getWave_mode() {
        return wave_mode;
    }

    public void setWave_mode(byte wave_mode) {
        this.wave_mode = wave_mode;
    }

    public float getFft_thld() {
        return fft_thld;
    }

    public void setFft_thld(float fft_thld) {
        this.fft_thld = fft_thld;
    }

    public int getFft_max_xf() {
        return fft_max_xf;
    }

    public void setFft_max_xf(int fft_max_xf) {
        this.fft_max_xf = fft_max_xf;
    }

    public float getLf_cursor() {
        return lf_cursor;
    }

    public void setLf_cursor(float lf_cursor) {
        this.lf_cursor = lf_cursor;
    }

    public float getHf_cursor() {
        return hf_cursor;
    }

    public void setHf_cursor(float hf_cursor) {
        this.hf_cursor = hf_cursor;
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

    public byte getBearingType() {
        return bearingType;
    }

    public void setBearingType(byte bearingType) {
        this.bearingType = bearingType;
    }

    public float getNormalSpeed() {
        return normalSpeed;
    }

    public void setNormalSpeed(float normalSpeed) {
        this.normalSpeed = normalSpeed;
    }
}
