package com.zzqa.ds7000.service.impl;

import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Monitor_data_head;
import com.zzqa.ds7000.pojo.Procss_data;
import com.zzqa.ds7000.pojo.Vib_data;
import com.zzqa.ds7000.service.interfaces.ISendToDgm;
import com.zzqa.ds7000.util.FormatTransfer;
import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SendToDgmDS7000
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/12 13:52
 */
@Service
public class SendToDgmDS7000 implements ISendToDgm {

    /**
     * 发送时间
     *
     * @param response ServletResponse
     * @param head7000 包头
     */
    @Override
    public void sendTime(ServletResponse response, Head7000 head7000) {
        if (head7000 == null){  //防止出现空指针
            head7000 = new Head7000();
        }
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            int i = FormatTransfer.getUtcTime();
            dos.writeInt(i);
            byte[] time_data = baos.toByteArray();

            dos.flush();
            dos.close();
            baos.flush();
            baos.close();
            conn(response, time_data, head7000, head7000.getAppDataNum());
        } catch (IOException e) {
            head7000.setErrorCode(1999);    //返回错误代码
            conn(response, null, head7000,0);
        }
    }

    /**
     * 通信
     *
     * @param response ServletResponse
     * @param data     数据
     * @param head7000     包头
     * @param appDataNum  重复次数
     * @return  返回结果
     */
    @Override
    public boolean conn(ServletResponse response, byte[] data, Head7000 head7000, int appDataNum) {
        boolean bool = true;
        try {
            int dataLen = 0;
            if (data != null) {
                dataLen = data.length;
            }
            OutputStream os = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] head = Head7000.creatHead(head7000);
            baos.write(head);
            if (dataLen != 0) {
                if (appDataNum > 0){
                    //重复appDataNum个，如果为1则为不重复
                    for (int i=0; i<appDataNum; i++){
                        baos.write(data);
                    }
                }
            }
            byte[] b = baos.toByteArray();
            baos.flush();
            baos.close();
            // 写出
            os.write(b);
            os.flush();
            os.close();
        } catch (IOException e) {
            // 基础发送方法错误
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(),e);
            bool = false;
        }
        return bool;
    }


}
