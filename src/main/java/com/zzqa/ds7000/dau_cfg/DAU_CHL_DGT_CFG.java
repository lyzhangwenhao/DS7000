package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: DAU_CHL_DGT_CFG
 * Description: 数字量通道信息数据结构
 *
 * @author 张文豪
 * @date 2021/1/15 9:34
 */
@Component
public class DAU_CHL_DGT_CFG {
    private int dwStructLen;        //字节长度

    @Autowired
    private C_DAU_CHL_CFG dau_chl;
    @Autowired
    private C_DAU_CHL_DGT_CFG dau_dgt_chl;

    public DAU_CHL_DGT_CFG() {
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

    public C_DAU_CHL_DGT_CFG getDau_dgt_chl() {
        return dau_dgt_chl;
    }

    public void setDau_dgt_chl(C_DAU_CHL_DGT_CFG dau_dgt_chl) {
        this.dau_dgt_chl = dau_dgt_chl;
    }
}
