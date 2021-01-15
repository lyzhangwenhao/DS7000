package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: C_DAU_CHL_COM_CFG
 * Description: 串口通道信息表
 *
 * @author 张文豪
 * @date 2021/1/15 10:00
 */
public class C_DAU_CHL_COM_CFG {
    private int id_dauchl;      //外键
    private int chl_no;     //通道的硬件编号，大于0
    private byte chl_type;  //通道类型，0:超级终端，1：MODBUS
    private byte master_slave;      //这个串口在MODBUS通讯中的主设备或从设备，0：主设备，1：从设备
    private byte slaveid;       //DAU的从设备号，取值：1-247（仅当master_slave=1时有效）
    private byte commu_mode;    //传输方式，取值，0：字符串方式，1：二进制方式
    private int baudrate;       //波特率，取值：110,300,600,1200,2400,4800,9600,19200,38400,56000,128000,256000
    private byte parity;    //奇偶校验，0：无，1：奇校验，2：偶校验
    private byte databit;   //数据位，取值，5,6,7,8
    private byte stopbit;   //停止位，1:1位，2:2位，3:1.5位
    private byte crc;   //是否有CRC校验，0：不带CRC校验，1：带CRC校验

    public C_DAU_CHL_COM_CFG() {
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

    public byte getChl_type() {
        return chl_type;
    }

    public void setChl_type(byte chl_type) {
        this.chl_type = chl_type;
    }

    public byte getMaster_slave() {
        return master_slave;
    }

    public void setMaster_slave(byte master_slave) {
        this.master_slave = master_slave;
    }

    public byte getSlaveid() {
        return slaveid;
    }

    public void setSlaveid(byte slaveid) {
        this.slaveid = slaveid;
    }

    public byte getCommu_mode() {
        return commu_mode;
    }

    public void setCommu_mode(byte commu_mode) {
        this.commu_mode = commu_mode;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    public byte getParity() {
        return parity;
    }

    public void setParity(byte parity) {
        this.parity = parity;
    }

    public byte getDatabit() {
        return databit;
    }

    public void setDatabit(byte databit) {
        this.databit = databit;
    }

    public byte getStopbit() {
        return stopbit;
    }

    public void setStopbit(byte stopbit) {
        this.stopbit = stopbit;
    }

    public byte getCrc() {
        return crc;
    }

    public void setCrc(byte crc) {
        this.crc = crc;
    }
}
