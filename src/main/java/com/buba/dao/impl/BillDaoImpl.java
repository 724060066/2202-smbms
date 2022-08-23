package com.buba.dao.impl;

import com.buba.dao.BaseDao;
import com.buba.dao.BillDao;
import com.buba.pojo.Bill;
import com.buba.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/23 15:03
 */
public class BillDaoImpl implements BillDao {

    /**
     * 查询订单列表
     * @param connection
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> listBill(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select sb.*,sp.proName from ");
            sql.append("smbms_bill sb,smbms_provider sp ");
            sql.append("where sb.providerId = sp.id");

            Object[] params = {};
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setCreationDate(rs.getDate("creationDate"));
                bill.setProviderName(rs.getString("proName"));

                billList.add(bill);
            }
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return billList;
    }
}