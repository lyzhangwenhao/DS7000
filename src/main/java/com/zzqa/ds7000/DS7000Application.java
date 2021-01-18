package com.zzqa.ds7000;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EntityScan(basePackages = {"com.zzqa.ds7000.dau_cfg","com.zzqa.ds7000.pojo"})
public class DS7000Application {

    public static final Logger LOGGER= LoggerFactory.getLogger(DS7000Application.class);

    public static void main(String[] args) {
        SpringApplication.run(DS7000Application.class, args);
    }

}
