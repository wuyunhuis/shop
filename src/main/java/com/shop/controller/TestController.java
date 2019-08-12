package com.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test")
    public String test(){
        return "testaaa";
    }
}
