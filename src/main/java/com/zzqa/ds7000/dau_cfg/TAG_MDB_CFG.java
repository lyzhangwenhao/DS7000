package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: TAG_MDB_CFG
 * Description: Modbus测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 15:44
 */
public class TAG_MDB_CFG {
    private int dwStructLen;

    private C_SYS_NODE_CFG node_cfg;
    private C_TAG_COMMON tag_comm;

    private C_TAG_MDB_CFG modbus_tag;

    public TAG_MDB_CFG() {
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

    public C_TAG_MDB_CFG getModbus_tag() {
        return modbus_tag;
    }

    public void setModbus_tag(C_TAG_MDB_CFG modbus_tag) {
        this.modbus_tag = modbus_tag;
    }
}
