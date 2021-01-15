package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: DAU_CHL_VIB_CFG
 * Description: 振动通道信息数据结构
 *
 * @author 张文豪
 * @date 2021/1/14 17:05
 */
public class DAU_CHL_VIB_CFG {
    private int dwStructLen;    //字节长度

    private C_DAU_CHL_CFG dau_chl;//通道公共信息表
    private C_DAU_CHL_VIB_CFG dau_vib_chl;

    public DAU_CHL_VIB_CFG() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public C_DAU_CHL_CFG getDau_chl() {
        return dau_chl;
    }

    public void setDau_chl(C_DAU_CHL_CFG dau_chl) {
        this.dau_chl = dau_chl;
    }

    public C_DAU_CHL_VIB_CFG getDau_vib_chl() {
        return dau_vib_chl;
    }

    public void setDau_vib_chl(C_DAU_CHL_VIB_CFG dau_vib_chl) {
        this.dau_vib_chl = dau_vib_chl;
    }
}
