package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.Business;
import com.shop.entity.OrderList;
import com.shop.entity.Product;
import com.shop.service.BusinessService;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;


@Controller
public class BusinessController {

    @Resource
    BusinessService businessService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/loginAdmin")
    public String  adminLogin(){

        return "adminLogin";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession session, @ModelAttribute Business user ){
        Business business=businessService.findBusinessByAccount(user.getAccount());
        if(user.getPassword().equals(business.getPassword())){
            session.setAttribute ("business",business);
            return "admin";
        }else {
            return "adminLogin";
        }

    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        return "adminLogin";
    }

    @RequestMapping(value = "/findBusinessByFree")
    public String findBusinessByFree(Model model){


        List<Integer> bids=productService.findProductByFreeBid();
        List<Business> list=new LinkedList<Business>();;
        for(int i=0;i<bids.size();i++){
            Business business=new Business();
            business=businessService.findBusinessByBid(bids.get(i));
            list.add(i,business);
        }
        model.addAttribute("list",list);
        return "bussinessFreeList";
    }
    @RequestMapping(value = "/findProductByBidFree")
    public String findProductByBidFree(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpSession session,@RequestParam(value = "businessID",required = false) Integer businessID){

        if(businessID!=null){
            session.setAttribute("businessID",businessID);
            PageHelper.startPage(pageNum,8);
            List<Product> list=productService.findProductByBidFree(businessID);
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);

            model.addAttribute("pageInfo",pageInfo);
            return "productByBidFreeList";
        }

        businessID=(Integer) session.getAttribute("businessID");
        PageHelper.startPage(pageNum,8);
        List<Product> list=productService.findProductByBidFree(businessID);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);

        model.addAttribute("pageInfo",pageInfo);
        return "productByBidFreeList";
    }

}
