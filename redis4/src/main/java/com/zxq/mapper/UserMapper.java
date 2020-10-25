package com.zxq.mapper;

import com.zxq.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 13:48
 * 4
 */

@Mapper
public interface UserMapper {

    /**
     * 查询所有的方法
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    @Delete("delete from user where id=#{id}")
    void deleteById(Integer id);

    /**
     * 插入用户
     * @param user
     */
    @Insert("insert into user (id,username,password) values(null,#{username},#{password})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username}，password=#{password} where id=#{id}")
    void updateUser(User user);
}
