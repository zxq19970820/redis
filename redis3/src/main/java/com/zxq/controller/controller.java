package com.zxq.controller;


import com.zxq.domain.User;
import com.zxq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private UserService userService;

    /**
     * 单线程情况下的查询
     * @return
     */
//    @RequestMapping("/user/list")
//    public List<User> list(){
//        return  userService.findAll();
//    }

    /**
     * 多线程的情况
     * @return
     */
    @RequestMapping("/user/list")
    public List<User> list(){
        //创建一个固定数量的线程池，线程池的数量并不是越多越好,根据处理器的个数及核心数
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        //1000条线程
//        for(int i=0;i<100;i++){
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    //在线程中调用service方法
//                    userService.findAll();
//                }
//            });
//        }
        return  userService.findAll();
    }


    /**
     *  根据id删除
     * @param id
     * @return
     */
//    @DeleteMapping("user/{id}")  //前端发送post请求，传递参数data:{_mothod:'delete'}
    @RequestMapping("user/{id}")  //前端发送post请求，传递参数data:{_mothod:'delete'}
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().body("删除成功！");
        }catch (Exception e){
            //有异常，删除失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败！");
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(User user){
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("插入成功");
        }catch (Exception e){
            //有异常，插入失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("插入失败");
        }
    }

    /**
     * 更新操作
     * @param user
     * @return
     */
    @PutMapping("/user")//post请求，传递参数 data:{_mothod:'put'}
    public ResponseEntity<String> updateUser(User user){
        try{
            userService.updateUser(user);
            return ResponseEntity.ok().body("更新成功");
        } catch(Exception e) {
//            e.printStackTrace() ;
            //有异常，失败
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失败");
        }
    }
}
