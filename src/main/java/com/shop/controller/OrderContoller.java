package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.*;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.service.SpelllistService;
import com.shop.service.SpikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class OrderContoller {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @Autowired
    SpelllistService spelllistService;

    @Autowired
    SpikeService spikeService;

    @RequestMapping(value = "/ordersList")
    public String insertOrders (@ModelAttribute Orders orders, Model model, @RequestParam(value = "states",required = false) String states,@RequestParam( defaultValue = "0",value = "channels",required = false) Integer channels,@RequestParam(value = "sid",required = false) Integer sid,HttpSession session){

        productService.updateProductRepertoryAndSales(orders.getNumber(),orders.getPid());
        Product product=productService.findProductByID(orders.getPid());
        orders.setBeizhu("无");
        orders.setChannel(channels);
        orders.setBid(product.getBid());
        orders.setPrice(product.getPrice());
        orders.setTime(new Date());

        orders.setState(1);

        productService.updateProductRepertory(orders.getPid(),product.getRepertory()-1);
        orderService.insertOrders(orders);
        if(channels==1){
            Spelllist spelllist=spelllistService.findBySid(sid);
            spelllistService.updateNumber(spelllist.getNumber(),sid);
            User user=(User)session.getAttribute("user");
            spelllistService.updateUserList(0,sid,user.getUid());
            List<Orders> list=orderService.findOrderList(orders.getUid());

            List<OrderList> lists = new LinkedList<OrderList>();

            for (int i = 0; i < list.size(); i++) {
                OrderList orderList=new OrderList();
                Orders o=list.get(i);
                Product p=productService.findProductByID(o.getPid());
                orderList.setOrders(o);
                orderList.setProduct(p);
                lists.add(i,orderList);
            }
            model.addAttribute("lists",lists);
            return "order_list";
        }else  if(channels==2){
            Spike spike=spikeService.findSpikeBySid(sid);
            spikeService.updateNumber(spike.getNumber()-1,sid);
            List<Orders> list=orderService.findOrderList(orders.getUid());

            List<OrderList> lists = new LinkedList<OrderList>();

            for (int i = 0; i < list.size(); i++) {
                OrderList orderList=new OrderList();
                Orders o=list.get(i);
                Product p=productService.findProductByID(o.getPid());
                orderList.setOrders(o);
                orderList.setProduct(p);
                lists.add(i,orderList);
            }
            model.addAttribute("lists",lists);
            return "order_list";
        }
        List<Orders> list=orderService.findOrderList(orders.getUid());

         List<OrderList> lists = new LinkedList<OrderList>();

         for (int i = 0; i < list.size(); i++) {
            OrderList orderList=new OrderList();
            Orders o=list.get(i);
            Product p=productService.findProductByID(o.getPid());
            orderList.setOrders(o);
            orderList.setProduct(p);
            lists.add(i,orderList);
        }
            model.addAttribute("lists",lists);
            return "order_list";
        }


    @RequestMapping(value = "/findOrderList")
    public  String findOrderList(Model model, HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Orders> list=orderService.findOrderList(user.getUid());
        List<OrderList> lists = new LinkedList<OrderList>();

        for (int i = 0; i < list.size(); i++) {

            OrderList orderList=new OrderList();
            Orders o=list.get(i);
            Product p=productService.findProductByID(o.getPid());
            orderList.setOrders(o);
            orderList.setProduct(p);
            lists.add(i,orderList);
        }

            model.addAttribute("lists",lists);
            return "order_list";
        }

    @RequestMapping(value = "/updateOrderState")
    public String updateOrderState(@RequestParam("oid") String oid,@RequestParam("state") Integer state){

        if(state==2){
            orderService.updateOrderState(3,oid);
            return "redirect:/findOrderList";
        }else {
            orderService.updateOrderState(2,oid);
            return "redirect:/findOrderListByBid";
        }


    }


    @RequestMapping(value = "/findOrderListByBid")
    public  String findOrderListByBid(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model, HttpSession session){
        PageHelper.startPage(pageNum,5);
        Business business=(Business)session.getAttribute("business");
        List<Orders> lists=orderService.findOrderListByBid(business.getBid());
        List<OrderList> list = new LinkedList<OrderList>();

        for (int i = 0; i < lists.size(); i++) {

            OrderList orderList=new OrderList();
            Orders o=lists.get(i);
            Product p=productService.findProductByID(o.getPid());
            orderList.setOrders(o);
            orderList.setProduct(p);
            list.add(i,orderList);
        }

        PageInfo<OrderList> pageInfo = new PageInfo<OrderList>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "adminOrderList";
    }


}
