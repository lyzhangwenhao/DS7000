package com.zzqa.ds7000.dau_cfg;

/**
 * ClassName: TAG_SPEED_CFG
 * Description: 转速测点数据结构描述
 *
 * @author 张文豪
 * @date 2021/1/15 10:29
 */
public class TAG_SPEED_CFG {
    private int dwStructLen;    //字节长度

    //TagCommCfg信息
    private C_SYS_NODE_CFG node;
    private C_TAG_COMMON tag_comm;

    private C_TAG_SPEED_CFG speed_tag;
}
