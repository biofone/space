package cn.tedu.service;

import cn.tedu.dao.UserDao;
import cn.tedu.domain.Msg;
import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

import java.util.List;

public class UserService {
    UserDao userDao=new UserDao();
    //用户登录
    public User loginUser(String depName, String password) {
        User user= userDao.findUserByNameAndPassword(depName,password);
        if(user!=null){
            //用户名和密码匹配
            return user;
        }else{
            //用户名和密码不匹配
            throw new MsgException("用户名或密码不正确");
        }
    }
    //用户注册
    public void registUser(User user) {
        boolean findUser=userDao.findUserByName(user.getName());
        if(findUser){
            throw new MsgException("用户名已存在");
        }else{
            userDao.addUser(user);
        }
    }
    //用户发布信息
    public void send(Msg msg) {
        userDao.sendMsg(msg);
    }
    //显示信息
    public List<Msg> viewMsg(){
        return userDao.sendMsgMain();
    }

    public void findName(String name) {
        boolean isFind=userDao.findUserByName(name);
        if(isFind){
            throw new MsgException("用户名已存在");
        }else{
            throw new MsgException("该用户名可以使用");
        }
    }

    public void del(String id) {
        userDao.delete(id);
    }
}
