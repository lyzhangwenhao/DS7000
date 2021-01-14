package com.zzqa.ds7000.service.interfaces;

import com.zzqa.ds7000.pojo.Head7000;

import javax.servlet.ServletResponse;

/**
 * ClassName: ISendToDgm
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/12 13:50
 */
public interface ISendToDgm {
    /**
     * 发送时间
     * @param response  ServletResponse
     * @param head7000  包头
     */
    void sendTime(ServletResponse response, Head7000 head7000);

    /**
     * 通信
     * @param response ServletResponse
     * @param data  数据
     * @param head7000 包头
     * @param appDataNum 重复次数
     * @return 返回结果
     */
    boolean conn(ServletResponse response, byte[] data, Head7000 head7000, int appDataNum);


}
