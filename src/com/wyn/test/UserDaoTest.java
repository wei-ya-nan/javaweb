package com.wyn.test;

import com.wyn.dao.UserDao;

import com.wyn.dao.impl.UserDaoImpl;

import com.wyn.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername() {

        if (userDao.queryUserByUsername("admin1234") == null ){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if ( userDao.queryUserByUsernameAndPassword("admin","admin1234") == null) {
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }

    @Test
    public void saveUser() {
       User user =  new User(null,"admin", "123456", "wzg168@qq.com");
        System.out.println(user.getPassword());
        System.out.println(userDao.saveUser(user));
    }
}