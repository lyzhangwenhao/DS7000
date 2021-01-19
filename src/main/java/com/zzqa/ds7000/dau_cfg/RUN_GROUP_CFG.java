package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: RUN_GROUP_CFG
 * Description: 测量组数据结构
 *
 * @author 张文豪
 * @date 2021/1/15 16:03
 */
@Component
public class RUN_GROUP_CFG {
    private int dwStructLen;
    @Autowired
    private C_RUN_GROUP run_group;

    public RUN_GROUP_CFG() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public C_RUN_GROUP getRun_group() {
        return run_group;
    }

    public void setRun_group(C_RUN_GROUP run_group) {
        this.run_group = run_group;
    }
}
