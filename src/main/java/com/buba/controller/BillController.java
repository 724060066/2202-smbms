package com.buba.controller;

import com.alibaba.fastjson.JSON;
import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.service.BillService;
import com.buba.service.ProviderService;
import com.buba.service.impl.BillServiceImpl;
import com.buba.service.impl.ProviderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * 添加页面ajax查询供应商下拉列表
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
}
