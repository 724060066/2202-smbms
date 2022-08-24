package com.buba.dao;

import com.buba.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * 供应商管理Dao接口
 */
public interface ProviderDao {

    /**
     * 查询供应商信息
     * @param connection
     * @return
     * @throws Exception
     */
    List<Provider> listProvider(Connection connection) throws Exception;
}
