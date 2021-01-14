package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

/**
 * ClassName: Upgrade_file_info
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/14 13:48
 */
@Component
public class Upgrade_file_info {
    int dwStructLen;    //结构长度
    int fileTotal;      //大于0，一次升级的总文件数
    int squenceNo;      //文件的序号（取值1~fileTotal）
    int fileNameLen;    //文件名长度
    byte[] file_name;   //文件名，ASCII编码，长度为fileNameLen
    int destDirLen;     //目标文件夹名长度
    byte[] dest_dir;    //目标文件夹，ASCII编码
    int file_ver;       //文件的版本号
    byte file_groupId;  //文件所属的组；0：CPU程序，1：硬件FPGA程序，2：驱动程序，3：其他类型程序
    byte if_exec;       //是否置可执行标志？0:不执，1：执行
    byte if_forced;     //是否强制升级？0:不强制，1：强制
    byte if_restarted;  //0：不重启，1：软件重启，2：硬件复位重启
    byte if_crc;        //是否做CRC校验，0：不做CRC校验，1：做CRC校验
    int dwFileCrc;
    int file_len;       //文件的长度
    byte[] fileStream;  //长度为file_len

    public Upgrade_file_info() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public int getFileTotal() {
        return fileTotal;
    }

    public void setFileTotal(int fileTotal) {
        this.fileTotal = fileTotal;
    }

    public int getSquenceNo() {
        return squenceNo;
    }

    public void setSquenceNo(int squenceNo) {
        this.squenceNo = squenceNo;
    }

    public int getFileNameLen() {
        return fileNameLen;
    }

    public void setFileNameLen(int fileNameLen) {
        this.fileNameLen = fileNameLen;
    }

    public byte[] getFile_name() {
        return file_name;
    }

    public void setFile_name(byte[] file_name) {
        this.file_name = file_name;
    }

    public int getDestDirLen() {
        return destDirLen;
    }

    public void setDestDirLen(int destDirLen) {
        this.destDirLen = destDirLen;
    }

    public byte[] getDest_dir() {
        return dest_dir;
    }

    public void setDest_dir(byte[] dest_dir) {
        this.dest_dir = dest_dir;
    }

    public int getFile_ver() {
        return file_ver;
    }

    public void setFile_ver(int file_ver) {
        this.file_ver = file_ver;
    }

    public byte getFile_groupId() {
        return file_groupId;
    }

    public void setFile_groupId(byte file_groupId) {
        this.file_groupId = file_groupId;
    }

    public byte getIf_exec() {
        return if_exec;
    }

    public void setIf_exec(byte if_exec) {
        this.if_exec = if_exec;
    }

    public byte getIf_forced() {
        return if_forced;
    }

    public void setIf_forced(byte if_forced) {
        this.if_forced = if_forced;
    }

    public byte getIf_restarted() {
        return if_restarted;
    }

    public void setIf_restarted(byte if_restarted) {
        this.if_restarted = if_restarted;
    }

    public byte getIf_crc() {
        return if_crc;
    }

    public void setIf_crc(byte if_crc) {
        this.if_crc = if_crc;
    }

    public int getDwFileCrc() {
        return dwFileCrc;
    }

    public void setDwFileCrc(int dwFileCrc) {
        this.dwFileCrc = dwFileCrc;
    }

    public int getFile_len() {
        return file_len;
    }

    public void setFile_len(int file_len) {
        this.file_len = file_len;
    }

    public byte[] getFileStream() {
        return fileStream;
    }

    public void setFileStream(byte[] fileStream) {
        this.fileStream = fileStream;
    }
}
