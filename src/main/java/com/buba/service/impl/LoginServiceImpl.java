package com.buba.service.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.LoginDao;
import com.buba.dao.impl.LoginDaoImpl;
import com.buba.pojo.User;
import com.buba.service.LoginService;

import java.sql.Connection;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/22 09:25
 */
public class LoginServiceImpl implements LoginService {

    /**
     * 登录
     * @param userCode
     * @param userPassword
     * @return
     */
    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;
        try {
            LoginDao loginDao = new LoginDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            user = loginDao.login(connection, userCode, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    /**
     * 修改密码
     * @param id
     * @param pwd
     * @return true:修改成功；false：修改失败
     */
    @Override
    public boolean updatePassword(String id, String pwd) {
        Connection connection = null;
        int num = 0;
        boolean flag = false;
        try {
            LoginDao loginDao = new LoginDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            num = loginDao.updatePassword(connection, id, pwd);

            if (num > 0){
                // 修改成功
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
}
