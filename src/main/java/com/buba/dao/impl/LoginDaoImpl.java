package com.buba.dao.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.LoginDao;
import com.buba.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/22 09:43
 */
public class LoginDaoImpl implements LoginDao {

    /**
     * 登录
     * @param connection
     * @param userCode
     * @param userPassword
     * @return
     */
    @Override
    public User login(Connection connection, String userCode, String userPassword) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_user where userCode = ? and userPassword = ?");

            Object[] params = {userCode, userPassword};
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
            }
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return user;
    }
}
