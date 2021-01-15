package com.zzqa.ds7000;

import com.alibaba.fastjson.JSON;
import com.zzqa.ds7000.pojo.Head7000;
import com.zzqa.ds7000.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Ds7000ApplicationTests {

    @Test
    void contextLoads() throws Exception{
        Map<String,Object> map = new HashMap<>();
//        map.put("id", 1);
//        map.put("name", "张三");
        map.put("high", 1.8);
        JSON json = (JSON) JSON.toJSON(map);
        Student stu = json.toJavaObject(Student.class);
        System.out.println(stu.getId());
    }

}
