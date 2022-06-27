package com.wenxing.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

//使用第三方bean
@Configuration//配置普通配置类，表示这是配置类，不是普通类
public class AlphaConfig {

    //这段话意思是，这段方法返回的对象将装配到容器里，bean名字即simpleDateFormat
    @Bean
    public SimpleDateFormat simpleDateFormat(){ //这个方法名字就是bean的名字
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //实例化，日期格式

    }
}
