package com.buba.controller;

import com.buba.pojo.User;
import com.buba.service.LoginService;
import com.buba.service.impl.LoginServiceImpl;
import com.buba.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 包含登录、注销、修改密码
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/22 08:50
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录
     * @param model
     * @param userCode
     * @param userPassword
     * @return
     */
    @RequestMapping
    public String login(Model model, HttpSession session, String userCode, String userPassword) {

        LoginService loginService = new LoginServiceImpl();
        User user = loginService.login(userCode, userPassword);
        String view = "";

        if (user != null){
            // 登录成功
            // 将user存入session
            session.setAttribute(Constants.USER_SESSION, user);

            view = "frame";
        } else {
            // 登录失败
            model.addAttribute("error", "用户名或密码错误");
            view = "../login";
        }

        return view;
    }
}
