package com.buba.controller;

import com.buba.pojo.Bill;
import com.buba.service.BillService;
import com.buba.service.impl.BillServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/23 14:59
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @RequestMapping("/listBill")
    public String listBill(Model model) {

        BillService billService = new BillServiceImpl();
        List<Bill> billList = billService.listBill();

        model.addAttribute("billList", billList);

        return "billlist";
    }
}
