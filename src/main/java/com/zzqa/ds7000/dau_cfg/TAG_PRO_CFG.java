package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: TAG_PRO_CFG
 * Description: 过程量测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 15:13
 */
public class TAG_PRO_CFG {
    private int dwStructLen;    //字节长度

    private C_SYS_NODE_CFG node_cfg;
    private C_TAG_COMMON tag_comm;

    private C_TAG_PRO_CFG pro_tag;

    public TAG_PRO_CFG() {
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

    public C_TAG_PRO_CFG getPro_tag() {
        return pro_tag;
    }

    public void setPro_tag(C_TAG_PRO_CFG pro_tag) {
        this.pro_tag = pro_tag;
    }
}
