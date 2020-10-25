package com.zxq.service.impl;

import com.zxq.domain.User;
import com.zxq.mapper.UserMapper;
import com.zxq.service.UserService;
import com.zxq.utils.RedisUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2 * @Author: zxq
 * 3 * @Date: 2020/10/24 13:50
 * 4
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil2 redisUtil2;

    //缓存用在service层
    @Autowired
    private RedisTemplate redisTemplate;



    //先从redis中取，没有再查询数据库，存入redis，将结果返回、
    //如果redis中有，直接返回
    @Override
    public List<User> findAll() {
        //从缓存中获取用户列表
        List<User> userList =  redisUtil2.getUserList("userList");


        //多条线程得到的结果都是null，同时都在这里等待
        if(userList==null||userList.size()==0){
            synchronized (this) {
                //在第一条线程执行userMapper.findALL获取userList之前，有多条线程都执行到了这里，
                System.out.println("从数据库中查询");
                //没有缓存，查询数据库
                userList = userMapper.findAll();

                //将用户列表存入缓存
                redisUtil2.add("userList", userList);
            }
        }else {
            System.out.println("从缓存中获取");
        }
        return userList;
    }

    /**
     * 删除用户，根据id删除
     * @param id
     * 如果执行了增删改的操作，需要删除缓存中的数据
     */
    @Override
    public void deleteById(Integer id) {
        //清空缓存中的数据
//        redisTemplate.delete("userList");
        redisUtil2.de("userList");
        //根据id删除用户
        userMapper.deleteById(id);
    }

    /**
     * 插入操作，清空缓存
     * @param user
     */
    @Override
    public void saveUser(User user) {
        //清空缓存
        redisUtil2.de("userList");
        //执行插入的操作
        userMapper.saveUser(user);
    }

    /**
     * 更新用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        //清空缓存
        redisTemplate.delete("userList");
        //调用更新的方法
        userMapper.updateUser(user);
    }
}
