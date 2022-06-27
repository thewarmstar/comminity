package com.wenxing.community.dao;

import com.wenxing.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    //动态sql,有时候要拼路径，有时候不拼

    //@Param:给参数取别名
    // 如果是动态的（在<if>里使用），并且只有一个参数，必须取别名！
    int selectDiscussPostRows(@Param("userId") int userId);

}
