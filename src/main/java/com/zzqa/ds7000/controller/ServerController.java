package com.zzqa.ds7000.controller;

import com.alibaba.fastjson.JSON;
import com.zzqa.ds7000.service.impl.SendToDgmDS7000;
import com.zzqa.ds7000.service.interfaces.ISendToDgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ServerController
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/14 10:31
 */
@Controller
public class ServerController {
    @Autowired
    private ISendToDgm iSendToDgm;

    @ResponseBody
    @RequestMapping(value = "DS7000/updateDAU")
    public JSON updateDAU(Map<String,Object> map){
        Map<String,Object> info_map = new HashMap<>();

        return (JSON) JSON.toJSON(info_map);
    }
    /**
     * 操作DAU
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "DS7000/operationDAU")
    public JSON operationDAU(Map<String,Object> map){
        Map<String,Object> info_map = new HashMap<>();
        if (map == null || map.size() == 0){
            info_map.put("msg", 1);
        }
        Object protocolID_o = map.get("protocolID");
        if (protocolID_o == null){
            info_map.put("msg", 1);
        }
        int protocolId = Integer.parseInt(protocolID_o.toString());
        if (protocolId == 8){
            SendToDgmDS7000.upgrade_status.set(1);
        } else if (protocolId == 3){
            SendToDgmDS7000.update_status.set(1);
        }
        info_map.put("msg", 0);
        return (JSON) JSON.toJSON(info_map);
    }
}
