package com.buba.service.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.BillDao;
import com.buba.dao.LoginDao;
import com.buba.dao.impl.BillDaoImpl;
import com.buba.dao.impl.LoginDaoImpl;
import com.buba.pojo.Bill;
import com.buba.pojo.User;
import com.buba.service.BillService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/23 15:12
 */
public class BillServiceImpl implements BillService {

    /**
     * 查询订单列表
     * @return
     */
    @Override
    public List<Bill> listBill(String proName,
                               String proId, String isPayment) {
        Connection connection = null;
        List<Bill> billList = new ArrayList<>();
        try {
            BillDao billDao = new BillDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            billList = billDao.listBill(connection, proName, proId, isPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return billList;
    }

    /**
     * 添加订单信息
     * @param bill
     */
    @Override
    public void insertBill(Bill bill) {
        Connection connection = null;
        try {
            BillDao billDao = new BillDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            billDao.insertBill(connection, bill);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
    }

    /**
     * 根据id删除订单信息
     * @param billId
     * @return
     */
    @Override
    public String deleteBillById(String billId) {
        Connection connection = null;
        String result = "";
        int num = 0;
        try {
            BillDao billDao = new BillDaoImpl();
            // 取得数据库链接
            connection = BaseDao.getConnection();
            num = billDao.deleteBillById(connection, billId);
            if (num > 0){
                result = "true";
            } else {
                result = "notexist";
            }
        } catch (Exception e) {
            result = "false";
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return result;
    }


}
