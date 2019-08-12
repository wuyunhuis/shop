package com.shop.controller;

import com.shop.entity.User;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/loginMain")
    public String login(){
        return  "login";
    }

    @RequestMapping(value = "/register")
    public String register(){
        return  "register";
    }

    @RequestMapping(value = "/logoutMain",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        return "redirect:/index";
    }
    @RequestMapping(value = "/saveUser")
    public String saveUser(Model model,@ModelAttribute User user){

        user.setState(0);
        userService.saveUser(user);
        model.addAttribute("username",user.getUsername());
        return "login";
    }
}
