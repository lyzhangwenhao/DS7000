package com.zzqa.ds7000.pojo;

/**
 * ClassName: Student
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/15 17:53
 */
public class Student {
    private int id;
    private String name;
    private float high;
    private byte sex;

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }
}
