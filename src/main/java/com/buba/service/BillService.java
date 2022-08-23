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
    List<Bill> listBill();
}
