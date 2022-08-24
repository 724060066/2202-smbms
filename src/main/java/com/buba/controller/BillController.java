package com.buba.controller;

import com.buba.pojo.Bill;
import com.buba.pojo.Provider;
import com.buba.service.BillService;
import com.buba.service.ProviderService;
import com.buba.service.impl.BillServiceImpl;
import com.buba.service.impl.ProviderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Bill> billList = billService.listBill(queryProductName, queryProviderId, queryIsPayment);

        ProviderService providerService = new ProviderServiceImpl();
        List<Provider> providerList = providerService.listProvider();

        model.addAttribute("billList", billList);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProductName", queryProductName);
        model.addAttribute("queryProviderId", queryProviderId);
        model.addAttribute("queryIsPayment", queryIsPayment);

        return "billlist";
    }
}
