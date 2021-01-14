package com.zzqa.ds7000.pojo;

import org.springframework.stereotype.Component;

/**
 * ClassName: Procss_data
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/13 15:21
 */
@Component
public class Procss_data {
    private int dwStructLen;
    private long ID;
    private int tagType;    //0:工艺量类型 1：数字开关量类型 2:过程开关量
    private int alarmStatus;    //报警状态
    private float value;
    private int dataType;

    public Procss_data() {
    }

    public int getDwStructLen() {
        return dwStructLen;
    }

    public void setDwStructLen(int dwStructLen) {
        this.dwStructLen = dwStructLen;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public int getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
}
