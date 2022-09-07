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
     * @param proName
     * @param proId
     * @param isPayment
     * @return
     * @throws Exception
     */
    List<Bill> listBill(Connection connection, String proName,
                        String proId, String isPayment) throws Exception;

    /**
     * 添加订单信息
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int insertBill(Connection connection, Bill bill) throws Exception;

    /**
     * 根据id删除订单信息
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    int deleteBillById(Connection connection, String billId) throws Exception;

    /**
     * 根据id查询订单信息
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    Bill getBillById(Connection connection, String billId) throws Exception;

    /**
     * 根据id修改订单信息
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    int updateBillById(Connection connection, Bill bill) throws Exception;
}
