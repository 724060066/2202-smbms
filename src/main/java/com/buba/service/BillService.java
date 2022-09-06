package com.buba.service;

import com.buba.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * 订单service接口
 */
public interface BillService {

    /**
     * 查询订单列表
     * @return
     */
    List<Bill> listBill(String proName,
                        String proId, String isPayment);

    /**
     * 添加订单信息
     * @param bill
     */
    void insertBill(Bill bill);

    /**
     * 根据id删除订单信息
     * @param billId
     * @return
     */
    String deleteBillById(String billId);
}
