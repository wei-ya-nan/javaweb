package com.wyn.dao;

import com.wyn.pojo.User;
@SuppressWarnings("all")
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 返回null则没有这个用户
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 返回null 说明用户名或者密码错误
     */
    User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1说明保存失败
     */
    int saveUser(User user);


}
