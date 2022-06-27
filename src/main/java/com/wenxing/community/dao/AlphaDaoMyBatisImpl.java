//为了验证好替换   假设MyBatis方法是比Hibernate更好用的方法，淘汰了Hibernate
package com.wenxing.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary    //为了调用方法的时候优先选择此方法
public class AlphaDaoMyBatisImpl implements AlphaDao{

    @Override
    public String select() {
        return "MyBatis";
    }
}
