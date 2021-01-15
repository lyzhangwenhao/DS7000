package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: TAG_MDB_SPEED_CFG
 * Description: 转速测点（Modbus）数据结构
 *
 * @author 张文豪
 * @date 2021/1/15 16:56
 */
public class TAG_MDB_SPEED_CFG {
    private int dwStructLen;

    private C_SYS_NODE_CFG node_cfg;
    private C_TAG_COMMON tag_comm;

    private C_TAG_MDB_SPEED_CFG mdb_speed_tag;

    public TAG_MDB_SPEED_CFG() {
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

    public C_TAG_MDB_SPEED_CFG getMdb_speed_tag() {
        return mdb_speed_tag;
    }

    public void setMdb_speed_tag(C_TAG_MDB_SPEED_CFG mdb_speed_tag) {
        this.mdb_speed_tag = mdb_speed_tag;
    }
}
