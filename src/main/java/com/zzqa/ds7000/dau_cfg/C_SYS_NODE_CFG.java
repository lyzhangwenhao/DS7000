package com.zzqa.ds7000.dau_cfg;

import org.springframework.stereotype.Component;

/**
 * ClassName: TAG_COMMON_CFG
 * Description: 系统节点表
 *
 * @author 张文豪
 * @date 2021/1/15 10:30
 */
@Component
public class C_SYS_NODE_CFG {
    /**
     * 主键，节点ID，大于等于0，节点的编码方式：
     * tt xxxxx sssss ppppp
     * tt：表示节点类型：参见字段：type_node
     * xxxxx：表示工厂位移ID：1~99999
     * sssss：表示DAU唯一ID：1~99999
     * ppppp：表示测点唯一ID：1~99999
     */
    private long id_node;
    private long id_parent; //父节点，not null；-1表示没有父节点
    /**
     * 节点类型，非空：
     * 0：普通节点
     * 1：工厂节点
     * 2：机组节点
     * 3：子机组节点
     * 10：第三方测点节点
     * 11：阶次振动测点节点
     * 12:阶次包络测点节点
     * 13：异步振动测点节点
     * 14：异步振动包络测点节点
     * 20：过程量测点节点
     * 21:转速测点（数字通道）节点
     * 22：开关量测点节点
     * 24：计数测点（数字通道）节点
     * 25：转速测点（MODBUS）节点
     * 23:Modbus测点节点
     */
    private byte type_node;
    private byte enable;    //节点是否有效，0：无效，1：有效
    private String name;    //节点名称，非空
    private int sq_no;      //是父节点里排序号，从0开始
    private String comment; //备注
    private int stringLen;
    private String url_cfg; //长度由stringLen确定

    public C_SYS_NODE_CFG() {
    }

    public long getId_node() {
        return id_node;
    }

    public void setId_node(long id_node) {
        this.id_node = id_node;
    }

    public long getId_parent() {
        return id_parent;
    }

    public void setId_parent(long id_parent) {
        this.id_parent = id_parent;
    }

    public byte getType_node() {
        return type_node;
    }

    public void setType_node(byte type_node) {
        this.type_node = type_node;
    }

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSq_no() {
        return sq_no;
    }

    public void setSq_no(int sq_no) {
        this.sq_no = sq_no;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStringLen() {
        return stringLen;
    }

    public void setStringLen(int stringLen) {
        this.stringLen = stringLen;
    }

    public String getUrl_cfg() {
        return url_cfg;
    }

    public void setUrl_cfg(String url_cfg) {
        this.url_cfg = url_cfg;
    }
}
