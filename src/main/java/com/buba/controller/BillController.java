package com.buba.controller;

import com.alibaba.fastjson.JSON;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.pojo.User;
import com.buba.service.BillService;
import com.buba.service.ProviderService;
import com.buba.service.impl.BillServiceImpl;
import com.buba.service.impl.ProviderServiceImpl;
import com.buba.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理controller
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/23 14:59
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @RequestMapping("/listBill")
    public String listBill(Model model, String queryProductName,
                           String queryProviderId, String queryIsPayment) {

        BillService billService = new BillServiceImpl();
        // 查询订单列表
        List<Bill> billList = billService.listBill(queryProductName, queryProviderId, queryIsPayment);

        ProviderService providerService = new ProviderServiceImpl();
        // 查询供应商下拉列表
        List<Provider> providerList = providerService.listProvider();

        model.addAttribute("billList", billList);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);

        return "billlist";
    }

    /**
     * 页面ajax查询供应商下拉列表
     * @return
     */
    @RequestMapping(value = "/listProviderForSelect", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listProviderForSelect() {
        ProviderService providerService = new ProviderServiceImpl();
        // 查询供应商下拉列表
        List<Provider> providerList = providerService.listProvider();

        return JSON.toJSONString(providerList);
    }

    /**
     * 添加订单信息
     * @param bill
     * @param session
     * @return
     */
    @RequestMapping("/insertBill")
    public String insertBill(Bill bill, HttpSession session) {
        // 取得session中的用户信息
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        bill.setCreatedBy(user.getId());

        BillService billService = new BillServiceImpl();
        // 添加订单信息
        billService.insertBill(bill);

        return "redirect:/bill/listBill";
    }

    /**
     * 根据id删除订单信息
     * @param billId
     * @return
     */
    @RequestMapping("/deleteBill")
    @ResponseBody
    public String deleteBillById(String billId) {
        BillService billService = new BillServiceImpl();
        String result = billService.deleteBillById(billId);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("delResult", result);
        return JSON.toJSONString(resultMap);
    }

    /**
     *
     * @param model
     * @param billId
     * @return
     */
    @RequestMapping("/getBillById")
    public String getBillById(Model model, String billId){
        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(billId);

        model.addAttribute("bill", bill);

        return "billview";
    }

    /**
     * 修改页面初始化
     * @param model
     * @param billId
     * @return
     */
    @RequestMapping("/getBillForUpdate")
    public String getBillForUpdate(Model model, String billId) {
        BillService billService = new BillServiceImpl();
        Bill bill = billService.getBillById(billId);

        model.addAttribute("bill", bill);

        return "billmodify";
    }

    @RequestMapping("/updateBillById")
    public String updateBillById(Bill bill, HttpSession session) {
        // 取得session中的用户信息
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        bill.setModifyBy(user.getId());

        BillService billService = new BillServiceImpl();
        billService.updateBillById(bill);

        return "redirect:/bill/listBill";
    }
}
