package com.buba.dao;

import com.buba.pojo.User;

import java.sql.Connection;

/**
 * 登录、注销、修改密码dao的接口
 */
public interface LoginDao {

    /**
     * 登录
     * @param connection
     * @param userCode
     * @param userPassword
     * @return
     */
    User login(Connection connection, String userCode, String userPassword) throws Exception;

    /**
     * 修改密码
     * @param connection
     * @param id
     * @param pwd
     * @return
     * @throws Exception
     */
    int updatePassword(Connection connection, String id, String pwd) throws Exception;

}
