package com.zzqa.ds7000.service.interfaces;

import com.zzqa.ds7000.pojo.Head7000;

/**
 * ClassName: ISaveData
 * Description:
 *
 * @author 张文豪
 * @date 2021/1/13 15:50
 */
public interface ISaveData {
    /**
     * 保存数据
     * @param head7000 采集器传过来的数据
     */
    int saveData(Head7000 head7000);
}
