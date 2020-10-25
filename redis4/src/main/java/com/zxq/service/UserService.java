package com.zxq.service;

import com.zxq.domain.User;

import java.util.List;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 13:52
 * 4
 */
public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过id删除某个用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 插入user
     * @param user
     */
    void saveUser(User user);


    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);
}
