package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: DAU_CHL_WLAN_CFG
 * Description: 网口通道信息数据结构
 *
 * @author 张文豪
 * @date 2021/1/15 16:27
 */
@Component
public class DAU_CHL_WLAN_CFG {
    private int dwStructLen;
    @Autowired
    private C_DAU_CHL_CFG dau_chl;
    @Autowired
    private C_DAU_CHL_WLAN_CFG dau_wlan_chl;

    public DAU_CHL_WLAN_CFG() {
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

    public C_DAU_CHL_WLAN_CFG getDau_wlan_chl() {
        return dau_wlan_chl;
    }

    public void setDau_wlan_chl(C_DAU_CHL_WLAN_CFG dau_wlan_chl) {
        this.dau_wlan_chl = dau_wlan_chl;
    }
}
