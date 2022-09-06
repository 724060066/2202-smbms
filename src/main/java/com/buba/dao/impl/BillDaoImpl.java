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
     * @param proName
     * @param proId
     * @param isPayment
     * @return
     * @throws Exception
     */
    @Override
    public List<Bill> listBill(Connection connection, String proName,
                               String proId, String isPayment) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<>();
        if(connection != null){
            List<Object> params = new ArrayList<>();
            StringBuffer sql = new StringBuffer();
            sql.append("select sb.*,sp.proName from ");
            sql.append("smbms_bill sb,smbms_provider sp ");
            sql.append("where sb.providerId = sp.id ");
            if (proName != null && !proName.isEmpty()){
                sql.append("and sb.productName like ? ");
                params.add("%" + proName + "%");
            }
            if (proId != null && !"0".equals(proId)) {
                sql.append("and sb.providerId = ? ");
                params.add(proId);
            }
            if (isPayment != null && !"0".equals(isPayment)) {
                sql.append("and sb.isPayment = ? ");
                params.add(isPayment);
            }

            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params.toArray());
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

    /**
     * 添加订单信息
     * @param connection
     * @param bill
     * @return
     * @throws Exception
     */
    @Override
    public int insertBill(Connection connection, Bill bill) throws Exception {
        PreparedStatement pstm = null;
        int num = 0;
        if(connection != null){
            List<Object> params = new ArrayList<>();
            StringBuffer sql = new StringBuffer();
            sql.append("insert into smbms_bill( ");
            sql.append("billCode, ");
            sql.append("productName, ");
            sql.append("productUnit, ");
            sql.append("productCount, ");
            sql.append("totalPrice, ");
            sql.append("providerId, ");
            sql.append("isPayment, ");
            sql.append("createdBy, ");
            sql.append("creationDate) ");
            sql.append("values(?,?,?,?,?,?,?,?,now()) ");

            params.add(bill.getBillCode());
            params.add(bill.getProductName());
            params.add(bill.getProductUnit());
            params.add(bill.getProductCount());
            params.add(bill.getTotalPrice());
            params.add(bill.getProviderId());
            params.add(bill.getIsPayment());
            params.add(bill.getCreatedBy());

            System.out.println("sql ----> " + sql.toString());
            num = BaseDao.execute(connection, pstm, sql.toString(), params.toArray());

            // 释放资源
            BaseDao.closeResource(null, pstm, null);
        }
        return num;
    }

    /**
     * 根据id删除订单信息
     * @param connection
     * @param billId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteBillById(Connection connection, String billId) throws Exception {
        PreparedStatement pstm = null;
        int num = 0;
        if(connection != null){
            List<Object> params = new ArrayList<>();
            StringBuffer sql = new StringBuffer();
            sql.append("delete1 from smbms_bill where id = ? ");
            params.add(billId);

            System.out.println("sql ----> " + sql.toString());
            num = BaseDao.execute(connection, pstm, sql.toString(), params.toArray());

            // 释放资源
            BaseDao.closeResource(null, pstm, null);
        }
        return num;
    }
}
