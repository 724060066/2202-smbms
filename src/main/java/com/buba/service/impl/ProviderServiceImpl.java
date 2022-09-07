package com.buba.service.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.ProviderDao;
import com.buba.dao.impl.ProviderDaoImpl;
import com.buba.pojo.Provider;
import com.buba.service.ProviderService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/24 08:33
 */
public class ProviderServiceImpl implements ProviderService {

    /**
     * 查询供应商信息
     * @return
     */
    @Override
    public List<Provider> listProvider() {
        Connection connection = null;
        List<Provider> providerList = new ArrayList<>();
        try {
            ProviderDao providerDao = new ProviderDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            providerList = providerDao.listProvider(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return providerList;
    }
}
