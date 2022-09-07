package com.buba.service;

import com.buba.pojo.Bill;

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

    /**
     * 根据id查询订单信息
     * @param billId
     * @return
     */
    Bill getBillById(String billId);

    /**
     * 根据id修改订单信息
     * @param bill
     */
    void updateBillById(Bill bill);
}
