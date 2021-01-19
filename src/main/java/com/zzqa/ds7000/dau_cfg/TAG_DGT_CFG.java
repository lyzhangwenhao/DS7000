package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: TAG_DGT_CFG
 * Description: 数字量测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 11:46
 */
@Component
public class TAG_DGT_CFG {
    private int dwStructLen;    //字节长度

    @Autowired
    private C_SYS_NODE_CFG node_cfg;
    @Autowired
    private C_TAG_COMMON tag_comm;
    @Autowired
    private C_TAG_DGT_CFG digital_tag;

    public TAG_DGT_CFG() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public C_SYS_NODE_CFG getNode_cfg() {
        return node_cfg;
    }

    public void setNode_cfg(C_SYS_NODE_CFG node_cfg) {
        this.node_cfg = node_cfg;
    }

    public C_TAG_COMMON getTag_comm() {
        return tag_comm;
    }

    public void setTag_comm(C_TAG_COMMON tag_comm) {
        this.tag_comm = tag_comm;
    }

    public C_TAG_DGT_CFG getDigital_tag() {
        return digital_tag;
    }

    public void setDigital_tag(C_TAG_DGT_CFG digital_tag) {
        this.digital_tag = digital_tag;
    }
}
