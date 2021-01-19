package com.zzqa.ds7000.dau_cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName: TAG_SPEED_CFG
 * Description: 转速测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 10:29
 */
@Component
public class TAG_SPEED_CFG {
    private int dwStructLen;    //字节长度

    //TagCommCfg信息
    @Autowired
    private C_SYS_NODE_CFG node;
    @Autowired
    private C_TAG_COMMON tag_comm;

    @Autowired
    private C_TAG_SPEED_CFG speed_tag;

    public TAG_SPEED_CFG() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public C_SYS_NODE_CFG getNode() {
        return node;
    }

    public void setNode(C_SYS_NODE_CFG node) {
        this.node = node;
    }

    public C_TAG_COMMON getTag_comm() {
        return tag_comm;
    }

    public void setTag_comm(C_TAG_COMMON tag_comm) {
        this.tag_comm = tag_comm;
    }

    public C_TAG_SPEED_CFG getSpeed_tag() {
        return speed_tag;
    }

    public void setSpeed_tag(C_TAG_SPEED_CFG speed_tag) {
        this.speed_tag = speed_tag;
    }
}
