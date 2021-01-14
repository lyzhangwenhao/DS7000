package com.zzqa.ds7000.controller;

import com.zzqa.ds7000.DS7000Application;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.service.interfaces.ISaveData;
import com.zzqa.ds7000.service.interfaces.ISendToDgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: ControlCenter
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/12 11:33
 */
@Controller
public class ControlCenter {

    @Autowired
    private ISendToDgm iSendToDgm;
    @Autowired
    private ISaveData iSaveData;

    @RequestMapping(value = "/DS7000/controlCenterDS7000")
    public void controlCenterDS7000(ServletRequest request, ServletResponse response){
        InputStream in ;
        try {
            in = request.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            Head7000 head7000 = new Head7000(dis);
            //关闭流
            dis.close();
            in.close();

            int protocolID = head7000.getProtocolID();
            int code = 0;
            switch (protocolID){
                case 1:     //subpid=-:DAU向服务器传递检测数据 ；subpid=2:机泵DAU向服务器端传递检测数据，特征值、时间标签、存储标签、报警标志由服务器计算
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":传递检测数据");
                    //向cmc传递检测数据
                    code = iSaveData.saveData(head7000);
                    //返回Head
                    head7000.setErrorCode(code);
                    iSendToDgm.conn(response, null, head7000, 0);
                    break;
                case 2:     //DAU向服务器获取UTC时间
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":获取UTC时间");
                    iSendToDgm.sendTime(response, head7000);
                    break;
                case 3:     //客户端修改DAU设置，需要同步到DAU
                    break;
                case 4:     //DAU向服务器获取DAU设置
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":获取DAU设置");
                    break;
                case 6:     //subPid=-:DAU向服务器传送DAU运行日记代码；subPid=2:机泵DAU向服务器传送日志代码，服务器需要加上时间标签
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":传递DAU运行日记代码");
                    code = iSaveData.saveData(head7000);
                    //返回Head
                    head7000.setErrorCode(code);
                    iSendToDgm.conn(response, null, head7000, 0);
                    break;
                case 7:     //DAU向服务器传送DAU启停机索引记录
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":传递DAU启停索引记录");
                    code = iSaveData.saveData(head7000);
                    //返回Head
                    head7000.setErrorCode(code);
                    iSendToDgm.conn(response, null, head7000, 0);
                    break;
                case 8:     //服务器返回DAU各类型软件(升级功能)
                    break;
                case 9:     //DAU向服务器传送转速测点的波形数据
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":传递转速键相波形数据");
                    code = iSaveData.saveData(head7000);
                    //返回Head(暂时注释掉，协议里没写要返回什么数据)
//                    head7000.setErrorCode(code);
//                    iSendToDgm.conn(response, null, head7000, 0);
                    break;
                case 10:    //DAU向服务器传递DAU设置，服务器更新振动测点的GAP电压
                    break;
                case 255:   //心跳包协议，发一个协议头
                    DS7000Application.LOGGER.info(Thread.currentThread().getStackTrace()[1].getClassName()+":心跳包");
                    iSendToDgm.sendDgmTask(response, head7000);
                    break;
                default:
                    break;
            }

        } catch (IOException e){
            DS7000Application.LOGGER.error(Thread.currentThread().getStackTrace()[1].getClassName(),e);
        }
    }
}
