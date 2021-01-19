package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: TAG_VIB_CFG
 * Description: 振动量测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 13:25
 */
@Component
public class TAG_VIB_CFG {
    private int dwStructLen;    //字节长度

    @Autowired
    private C_SYS_NODE_CFG node_cfg;
    @Autowired
    private C_TAG_COMMON tag_comm;

    @Autowired
    private C_TAG_VIB_CFG vib_tag;

    public TAG_VIB_CFG() {
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

    public C_TAG_VIB_CFG getVib_tag() {
        return vib_tag;
    }

    public void setVib_tag(C_TAG_VIB_CFG vib_tag) {
        this.vib_tag = vib_tag;
    }
}
