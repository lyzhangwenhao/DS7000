package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: DAU_CHL_COM_CFG
 * Description: 串口通道信息数据结构
 *
 * @author 张文豪
 * @date 2021/1/15 9:58
 */
public class DAU_CHL_COM_CFG {
    private int dwStructLen;

    private C_DAU_CHL_CFG dau_chl;
    private C_DAU_CHL_COM_CFG dau_com_chl;

    public DAU_CHL_COM_CFG() {
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

    public C_DAU_CHL_COM_CFG getDau_com_chl() {
        return dau_com_chl;
    }

    public void setDau_com_chl(C_DAU_CHL_COM_CFG dau_com_chl) {
        this.dau_com_chl = dau_com_chl;
    }
}
