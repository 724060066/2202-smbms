package com.buba.controller;

import com.alibaba.fastjson.JSON;
import com.buba.pojo.User;
import com.buba.service.LoginService;
import com.buba.service.impl.LoginServiceImpl;
import com.buba.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "../login";
    }

    /**
     * 验证旧密码
     * @param session
     * @param pwd
     * @return
     */
    @RequestMapping("/checkpwd")
    @ResponseBody
    public String checkOldPassword(HttpSession session, @RequestParam("oldpassword") String pwd){
        String result = "";
        if (pwd == null || pwd.isEmpty()){
            // 旧密码为空
            result = "error";
        } else {
            User user = (User) session.getAttribute(Constants.USER_SESSION);
            if (user == null) {
                // session过期
                result = "sessionerror";
            } else {
                if (user.getUserPassword().equals(pwd)){
                    // 旧密码正确
                    result = "true";
                } else {
                    // 旧密码错误
                    result = "false";
                }
            }
        }
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return JSON.toJSONString(resultMap);
    }
}
