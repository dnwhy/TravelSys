package com.etc.travelsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//启动类
@SpringBootApplication

@MapperScan(basePackages = {"com.etc.travelsys.mapper"})

public class TravelSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelSysApplication.class, args);
    }

}
