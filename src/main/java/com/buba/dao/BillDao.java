package com.buba.dao;

import com.buba.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/23 15:02
 */
public interface BillDao {

    /**
     * 查询订单列表
     * @param connection
     * @return
     * @throws Exception
     */
    List<Bill> listBill(Connection connection) throws Exception;
}
