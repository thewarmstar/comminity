package com.wenxing.community.service;

import com.wenxing.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service //业务组件，加这个包   还是为了使用容器  这是注解，加了以后容器会自动调用这个包
//@Scope("prototype") //scope默认参数是“singleton”,只用一次，一般都会用这个   改了之后，每次访问bean,都会生成新的实例
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao; //注入了alphadao

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct  //方法会在构造器之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy  //在销毁对象之前调用
    public void destory(){
        System.out.println("销毁AlphaService");
    }

    //实现了service依赖于dao的方式
    public String find(){
        return alphaDao.select();
    }
}
