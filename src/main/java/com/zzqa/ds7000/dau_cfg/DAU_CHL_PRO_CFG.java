package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: DAU_CHL_PRO_CFG
 * Description: 过程量通道信息数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/14 17:51
 */
@Component
public class DAU_CHL_PRO_CFG {
    private int dwStructLen;        //字节长度

    @Autowired
    private C_DAU_CHL_CFG dau_chl;
    @Autowired
    private C_DAU_CHL_PRO_CFG dau_pro_chl;

    public DAU_CHL_PRO_CFG() {
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

    public C_DAU_CHL_PRO_CFG getDau_pro_chl() {
        return dau_pro_chl;
    }

    public void setDau_pro_chl(C_DAU_CHL_PRO_CFG dau_pro_chl) {
        this.dau_pro_chl = dau_pro_chl;
    }
}
