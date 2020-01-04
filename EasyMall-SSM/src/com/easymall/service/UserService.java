package com.easymall.service;

import com.easymall.dao.UserDao;
import com.easymall.domain.User;
import com.easymall.exception.MsgException;

public class UserService {
    //service需要访问数据库，所以创建UserDao类
    UserDao userDao = new UserDao();
    /**
     * 用户注册
     * @param user 用户信息对象
     */
    public void registUser(User user) {
        //用户名是否存在
        boolean findUser =  userDao.findUserByUsername(user.getUsername());

        if(findUser){
            //如果用户名存在，作出用户已存在的提示
            //用户名存在，向上抛出一个自定义异常，其中可以反馈错误信息。
            throw new MsgException("用户名已存在");
        }else{
            //如果用户名不存在，则完成注册。
            userDao.addUser(user);
        }


    }

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 用户信息对象
     */
    public User loginUser(String username, String password) {
        //将username和password发送到数据库查询用户。
        User user =  userDao.findUserByUsernameAndPassword(username,password);
        if(user != null){
            //用户名和密码匹配，则登录成功
            return user;
        }else{
            //用户名或密码不匹配，则登录失败
            throw new MsgException("用户名或密码不正确");
        }

    }



}
