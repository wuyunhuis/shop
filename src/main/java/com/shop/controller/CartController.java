package com.shop.controller;

import com.shop.entity.*;
import com.shop.service.ProductService;
import com.shop.service.SpelllistService;
import com.shop.service.SpikeService;
import com.shop.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    SpelllistService spelllistService;

    @Autowired
    SpikeService spikeService;

    @RequestMapping(value = "/addCart")
    public String addCart(@RequestParam(value = "pid",required = false) Integer pid, @RequestParam(value = "number",required = false) Integer number,HttpSession session,@RequestParam(value = "channel",required = false) String channel,Model model){

        if(channel.equals("立即购买")){
            CartItem cartItems=new CartItem();
            cartItems.setNumber(number);
            Product product=productService.findProductByID(pid);
            cartItems.setProduct(product);
            model.addAttribute("cartItems",cartItems);
            String uuid= UUIDUtils.getUUID();
            model.addAttribute("uuid",uuid);
            return "order_info";
        }else {
            CartItem cartItem = new CartItem();
            cartItem.setNumber(number);
            Product product = productService.findProductByID(pid);
            cartItem.setProduct(product);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            cart.addCart(cartItem);
            session.setAttribute("cart", cart);
            return "cat";
        }
    }

    @RequestMapping(value = "/removeCart")
    public String removeCart(HttpSession session,@RequestParam(value = "pid") Integer pid){
        // 获得购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            session.setAttribute("cart", cart);
            return "cat";
        }
        // 调用购物车中移除的方法:
        cart.removeCart(pid);
        // 返回页面:
        return "cat";
    }
    @RequestMapping(value = "/clearCart")
    public String clearCart(HttpSession session){
        // 获得购物车对象.
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart==null){
            cart=new Cart();
            session.setAttribute("cart", cart);
            return "cat";
        }
        // 调用购物车中清空方法.
        cart.clearCart();
        return "cat";
    }

    @RequestMapping(value = "/addOrderInfo")
    public String addOrderInfo(@RequestParam(value = "pid") Integer pid, @RequestParam(value = "number") Integer number,Model model,HttpSession session){

        CartItem cartItems = new CartItem();
        cartItems.setNumber(number);
        session.setAttribute("pid",pid);
        Product product = productService.findProductByID(pid);
        cartItems.setProduct(product);
        String uuid= UUIDUtils.getUUID();
        model.addAttribute("uuid",uuid);
        model.addAttribute("cartItems",cartItems);
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clearCart();
        return "order_info";
    }
    @RequestMapping(value = "/cartList")
    public  String cartList(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            return "cat";
        }{
            return "cat";
        }

    }
    @RequestMapping(value = "/buyProductPrice")
    public String buyProductPrice(@RequestParam(value = "pid",required = false) Integer pid, Model model) {

        CartItem cartItems = new CartItem();
        cartItems.setNumber(1);
        Product product = productService.findProductByID(pid);
        cartItems.setProduct(product);
        model.addAttribute("cartItems", cartItems);
        String uuid = UUIDUtils.getUUID();
        model.addAttribute("uuid", uuid);
        return "order_info";
    }

    @RequestMapping(value = "/buySpellPrice")
    public String buySpellPrice(@RequestParam(value = "sid") Integer sid, Model model) {

        Spelllist spelllis=spelllistService.findBySid(sid);
        Product product=productService.findProductByID(spelllis.getPid());
        SpellProduct spellProduct=new SpellProduct();
        spellProduct.setSpelllist(spelllis);
        spellProduct.setProduct(product);
        model.addAttribute("spellProduct",spellProduct);
        model.addAttribute("channels",1);
        double toal=spelllis.getPrice()+product.getPostage()+product.getTaxation();
        String uuid = UUIDUtils.getUUID();
        model.addAttribute("uuid", uuid);
        model.addAttribute("toal",toal);
        return "orderSp_info";
    }
    @RequestMapping(value = "/buySpikePrice")
    public String buySpikePrice(@RequestParam(value = "sid") Integer sid, Model model) {

        Spike spikes=spikeService.findSpikeBySid(sid);
        Product product=productService.findProductByID(spikes.getPid());
        SpikeProduct spikeProduct=new SpikeProduct();
        spikeProduct.setSpike(spikes);
        spikeProduct.setProduct(product);
        model.addAttribute("spikeProduct",spikeProduct);
        model.addAttribute("channels",2);
        double toal=spikes.getPrice()+product.getPostage()+product.getTaxation();
        String uuid = UUIDUtils.getUUID();
        model.addAttribute("uuid", uuid);
        model.addAttribute("toal",toal);
        return "orderSpike_info";
    }
}
