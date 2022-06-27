//接口的实现类
package com.wenxing.community.dao;

import org.springframework.stereotype.Repository;//为了让容器自动扫描装配bean,引入的@respository（因为是访问数据库的bean）

@Repository("alphaHibernate")  //重命名该Bean的名字  默认是类名，即alphaDaoHibernateImpl

public class AlphaDaoHibernateImpl implements AlphaDao{ //引用接口
    @Override
    public String select() {
        return "Hibernate";
    }
}
