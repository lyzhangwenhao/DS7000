package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: C_DAU_CHL_CFG
 * Description: 通道公共信息表
 *
 * @author 张文豪
 * @date 2021/1/14 17:09
 */
public class C_DAU_CHL_CFG {
    private int id_dauchl;  //主键
    private int id_dau; //外键
    private String name;    //振动通道名称
    private byte enable;    //0:无效，1：有效
    /**
     * 通道类型：
     * 0：振动通道
     * 1:工艺量通道
     * 2:数字通道
     * 3：串口通道
     * 4：网口通道
     */
    private byte chl_type;

    public C_DAU_CHL_CFG() {
    }

    public int getId_dauchl() {
        return id_dauchl;
    }

    public void setId_dauchl(int id_dauchl) {
        this.id_dauchl = id_dauchl;
    }

    public int getId_dau() {
        return id_dau;
    }

    public void setId_dau(int id_dau) {
        this.id_dau = id_dau;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public byte getChl_type() {
        return chl_type;
    }

    public void setChl_type(byte chl_type) {
        this.chl_type = chl_type;
    }
}
