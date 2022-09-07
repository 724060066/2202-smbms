package com.buba.dao.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.ProviderDao;
import com.buba.pojo.Provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/24 08:37
 */
public class ProviderDaoImpl implements ProviderDao {

    /**
     * 查询供应商信息
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Provider> listProvider(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Provider> providerList = new ArrayList<>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_provider");

            Object[] params = {};
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("proCode"));
                provider.setProName(rs.getString("proName"));
                provider.setProDesc(rs.getString("proDesc"));
                provider.setProContact(rs.getString("proContact"));
                provider.setProPhone(rs.getString("proPhone"));
                provider.setProAddress(rs.getString("proAddress"));
                provider.setProFax(rs.getString("proFax"));

                providerList.add(provider);
            }
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return providerList;
    }
}
