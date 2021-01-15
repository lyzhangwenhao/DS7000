package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: DAU_ALL_CFG
 * Description: 全信息数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/14 17:01
 */
public class DAU_ALL_CFG {
    int dwStructLen;    //结构体字节长度

    //振动通道
    private int vib_num;
    private DAU_CHL_VIB_CFG vib_chl;

    //过程量通道
    private int pro_num;
    private DAU_CHL_PRO_CFG pro_chl;

    //数字量通道
    private int dgt_num;
    private DAU_CHL_DGT_CFG dgt_chl;

    //串口通道信息数据结构
    private int com_num;
    private DAU_CHL_COM_CFG com_chl;

    //转速测点
    private int speed_tag_num;
    private TAG_SPEED_CFG speed_tag;

    //数字量测点
    private int dgt_tag_num;
    private TAG_DGT_CFG dgt_tag;

    //振动测点
    private int vib_tab_num;
    private TAG_VIB_CFG vib_tag;

    //过程量测点
    private int pro_tag_num;
    private TAG_PRO_CFG pro_tag;

    //Modbus测点
    private int mdb_tag_num;
    private TAG_MDB_CFG mdb_tag;

    //测量组
    private int run_group_num;
    private RUN_GROUP_CFG run_group;

    //2.0新增的网口通道信息
    private int wlan_chl_num;
    private DAU_CHL_WLAN_CFG wlan_chl;

    //2.0新增的Modbus转速通道
    private int mdb_speed_tag_num;
    private TAG_MDB_SPEED_CFG mdb_speed_tag;


}
