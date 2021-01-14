package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * ClassName: Head7000
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/12 10:34
 */
@Component
public class Head7000 {
    private int headLen;
    private byte[] guid = new byte[16];
    private int factoryID;
    private long DauID;     //根据协议的不同表示不同的含义：测点ID，机组ID，DAU ID，测量组ID
    private int protocolID;
    private int subPID;
    private int protocolVer;    //协议版本
    private int softwareVer;    //软件版本
    private int lever;
    private int errorCode;
    private int operationDataLen;   //数据区长度
    private int appDataNum;
    private byte[] maindata = null;

    public Head7000() {
    }

    public Head7000(DataInputStream dis) throws IOException {
        this.headLen = dis.readShort();
        dis.read(guid);
        this.factoryID = dis.readInt();
        DauID = dis.readLong();
        this.protocolID = dis.readByte();
        this.subPID = dis.readByte();
        this.protocolVer = dis.readInt();
        this.softwareVer = dis.readInt();
        this.lever = dis.readByte();
        this.errorCode = dis.readInt();
        this.operationDataLen = dis.readInt();
        this.appDataNum = dis.readInt();
        if (Integer.MAX_VALUE > operationDataLen){
            maindata = new byte[operationDataLen];
        }
        if (maindata != null){
            dis.readFully(maindata);
        }
    }

    public static byte[] creatHead(Head7000 head7000) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);

        dos.write(head7000.getHeadLen());
        dos.write(head7000.getGuid());
        dos.write(head7000.getFactoryID());
        dos.write(head7000.getGuid());
        dos.write(head7000.getProtocolID());
        dos.write(head7000.getSubPID());
        dos.write(head7000.getProtocolVer());
        dos.write(head7000.getSoftwareVer());
        dos.write(head7000.getLever());
        dos.write(head7000.getErrorCode());
        dos.write(head7000.getOperationDataLen());
        dos.write(head7000.getAppDataNum());

        dos.close();
        buffer.close();
        return buffer.toByteArray();
    }

    public int getHeadLen() {
        return headLen;
    }

    public void setHeadLen(int headLen) {
        this.headLen = headLen;
    }

    public byte[] getGuid() {
        return guid;
    }

    public void setGuid(byte[] guid) {
        this.guid = guid;
    }

    public int getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(int factoryID) {
        this.factoryID = factoryID;
    }

    public long getDauID() {
        return DauID;
    }

    public void setDauID(long dauID) {
        DauID = dauID;
    }

    public int getProtocolID() {
        return protocolID;
    }

    public void setProtocolID(int protocolID) {
        this.protocolID = protocolID;
    }

    public int getSubPID() {
        return subPID;
    }

    public void setSubPID(int subPID) {
        this.subPID = subPID;
    }

    public int getProtocolVer() {
        return protocolVer;
    }

    public void setProtocolVer(int protocolVer) {
        this.protocolVer = protocolVer;
    }

    public int getSoftwareVer() {
        return softwareVer;
    }

    public void setSoftwareVer(int softwareVer) {
        this.softwareVer = softwareVer;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getOperationDataLen() {
        return operationDataLen;
    }

    public void setOperationDataLen(int operationDataLen) {
        this.operationDataLen = operationDataLen;
    }

    public int getAppDataNum() {
        return appDataNum;
    }

    public void setAppDataNum(int appDataNum) {
        this.appDataNum = appDataNum;
    }

    public byte[] getMaindata() {
        return maindata;
    }

    public void setMaindata(byte[] maindata) {
        this.maindata = maindata;
    }
}

