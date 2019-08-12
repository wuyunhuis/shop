package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.*;
import com.shop.service.ProductService;
import com.shop.service.SpelllistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SpelllistController {
    @Autowired
    SpelllistService spelllistService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/toAddspelllist")
    public String toAddspelllist(Model model, HttpSession session){

        Business business=(Business)session.getAttribute("business");
        List<Product> list=productService.findProductByBid(business.getBid());

        model.addAttribute("list",list);
        return "adminSpelllistAdd";
    }

    @RequestMapping(value = "/spelllistList")
    public String spelllistList(Model model, HttpSession session,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        Business business=(Business)session.getAttribute("business");
        List<Spelllist> listSpell=spelllistService.findByBid(business.getBid());
        List<SpellProduct> list= new LinkedList<SpellProduct>();
        for (int i = 0; i <listSpell.size() ; i++) {
            Spelllist spell= listSpell.get(i);
            Product product=productService.findProductByID(spell.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spell);
            list.add(i,spellProduct);
        }

        PageInfo<SpellProduct> pageInfo = new PageInfo<SpellProduct>(list);

        model.addAttribute("pageInfo",pageInfo);

        return "adminSpelllistList";
    }
    @RequestMapping(value = "/addSpelllist")
    public String addSpelllist(HttpSession session, @ModelAttribute Spelllist spelllist ){

        Integer sid=spelllistService.maxSid();
        sid=sid+1;
        spelllist.setSid(sid);
        System.out.println(spelllist.toString());
        Business business=(Business)session.getAttribute("business");
        spelllist.setBid(business.getBid());
        spelllist.setState(0);
        spelllist.setEixtnum(0);


        spelllistService.insert(spelllist);

        /*List<Spelllist> listSpell=spelllistService.findByBid(business.getBid());
        List<SpellProduct> list= new LinkedList<SpellProduct>();
        for (int i = 0; i <listSpell.size() ; i++) {
           Spelllist spell= listSpell.get(i);
           Product product=productService.findProductByID(spell.getPid());
           SpellProduct spellProduct=new SpellProduct();
           spellProduct.setProduct(product);
           spellProduct.setSpelllist(spell);
           list.add(i,spellProduct);
        }

        PageInfo<SpellProduct> pageInfo = new PageInfo<SpellProduct>(list);

        model.addAttribute("pageInfo",pageInfo);
        return "adminSpelllistList";*/
        return "redirect:/spelllistList";
    }


    @RequestMapping(value = "/productBySpelllistList")
    public String productBySpelllistList(Model model, HttpSession session,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Spelllist> listSpell=spelllistService.findAll();
        List<SpellProduct> list= new LinkedList<SpellProduct>();
        for (int i = 0; i <listSpell.size() ; i++) {
            Spelllist spell= listSpell.get(i);
            Product product=productService.findProductByID(spell.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spell);
            list.add(i,spellProduct);
        }

        PageInfo<SpellProduct> pageInfo = new PageInfo<SpellProduct>(list);

        model.addAttribute("pageInfo",pageInfo);

        return "productBySpelllistList";
    }
    @RequestMapping(value = "/productBySpell")
    public String productBySpell(HttpSession session,@RequestParam(value = "sid") Integer sid,Model model){

        User user=(User)session.getAttribute("user");
        Spelllist spelllist=spelllistService.findBySid(sid);
        if(user==null || spelllist.getEixtnum()==0 ){
            Integer states=0;
            Integer num=spelllist.getNumber()-spelllist.getEixtnum();
            Product product=productService.findProductByID(spelllist.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spelllist);
            model.addAttribute("num",num);
            model.addAttribute("states",states);
            model.addAttribute("spellProduct",spellProduct);
            return "spelllistInfo";
        }else {
            int states=0;
            List<Userlist> userlist=spelllistService.findUserBySid(sid);
            for (int i = 0; i < userlist.size(); i++) {
                if(userlist.get(i).getUid()==user.getUid()){

                    states=1;
                    break;
                }
            }
            Integer num=spelllist.getNumber()-spelllist.getEixtnum();

            if(num==0 && states==1){
                states=2;
                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                System.out.println(states+"qqqqqqqqqqqq");
                return "spelllistInfo";
            }else if(num==0 && states==0){
                states=3;
                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                System.out.println(states+"qqqqqqqqqqqq");
                return "spelllistInfo";
            }

            Product product=productService.findProductByID(spelllist.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spelllist);
            model.addAttribute("num",num);
            model.addAttribute("states",states);
            model.addAttribute("spellProduct",spellProduct);
            System.out.println(states+"qqqqqqqqqqqq");
            return "spelllistInfo";
        }




    }

    @RequestMapping(value = "/addProductSpellUser")
    public String addProductSpellUser (HttpSession session,Model model,@RequestParam(value = "sid") Integer sid){

        Integer usid=spelllistService.findMaxUsid();
        usid=usid+1;
        Spelllist spelllists=spelllistService.findBySid(sid);
        if(spelllists.getEixtnum()+1==spelllists.getNumber()){
            spelllistService.updateExitnumAndState(1,spelllists.getEixtnum()+1,sid);
            System.out.println("更新拼单");
            User user=(User)session.getAttribute("user");
            spelllistService.insertUserList(usid,sid,user.getUid());
            System.out.println("更新userList");
            Spelllist spelllist=spelllistService.findBySid(sid);

            if(user==null || spelllist.getEixtnum()==0 ){
                Integer states=0;
                Integer num=spelllist.getNumber()-spelllist.getEixtnum();
                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                return "spelllistInfo";
            }else {
                int states=0;
                List<Userlist> userlist=spelllistService.findUserBySid(sid);
                for (int i = 0; i < userlist.size(); i++) {
                    if(userlist.get(i).getUid()==user.getUid()){

                        states=1;
                        break;
                    }
                }
                Integer num=spelllist.getNumber()-spelllist.getEixtnum();

                if(num==0 && states==1){
                    states=2;
                    Product product=productService.findProductByID(spelllist.getPid());
                    SpellProduct spellProduct=new SpellProduct();
                    spellProduct.setProduct(product);
                    spellProduct.setSpelllist(spelllist);
                    model.addAttribute("num",num);
                    model.addAttribute("states",states);
                    model.addAttribute("spellProduct",spellProduct);
                    System.out.println(states+"qqqqqqqqqqqq");
                    return "spelllistInfo";
                }else if(num==0 && states==0){
                    states=3;
                    Product product=productService.findProductByID(spelllist.getPid());
                    SpellProduct spellProduct=new SpellProduct();
                    spellProduct.setProduct(product);
                    spellProduct.setSpelllist(spelllist);
                    model.addAttribute("num",num);
                    model.addAttribute("states",states);
                    model.addAttribute("spellProduct",spellProduct);
                    System.out.println(states+"qqqqqqqqqqqq");
                    return "spelllistInfo";
                }

                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                System.out.println(states+"qqqqqqqqqqqq");
                return "spelllistInfo";
            }

        }
        spelllistService.updateExitnum(spelllists.getEixtnum()+1,sid);
        System.out.println("更新拼单");
        User user=(User)session.getAttribute("user");
        spelllistService.insertUserList(usid,sid,user.getUid());
        System.out.println("更新userList");
        Spelllist spelllist=spelllistService.findBySid(sid);

        if(user==null || spelllist.getEixtnum()==0 ){
            Integer states=0;
            Integer num=spelllist.getNumber()-spelllist.getEixtnum();
            Product product=productService.findProductByID(spelllist.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spelllist);
            model.addAttribute("num",num);
            model.addAttribute("states",states);
            model.addAttribute("spellProduct",spellProduct);
            return "spelllistInfo";
        }else {
            int states=0;
            List<Userlist> userlist=spelllistService.findUserBySid(sid);
            for (int i = 0; i < userlist.size(); i++) {
                if(userlist.get(i).getUid()==user.getUid()){

                    states=1;
                    break;
                }
            }
            Integer num=spelllist.getNumber()-spelllist.getEixtnum();

            if(num==0 && states==1){
                states=2;
                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                System.out.println(states+"qqqqqqqqqqqq");
                return "spelllistInfo";
            }else if(num==0 && states==0){
                states=3;
                Product product=productService.findProductByID(spelllist.getPid());
                SpellProduct spellProduct=new SpellProduct();
                spellProduct.setProduct(product);
                spellProduct.setSpelllist(spelllist);
                model.addAttribute("num",num);
                model.addAttribute("states",states);
                model.addAttribute("spellProduct",spellProduct);
                System.out.println(states+"qqqqqqqqqqqq");
                return "spelllistInfo";
            }

            Product product=productService.findProductByID(spelllist.getPid());
            SpellProduct spellProduct=new SpellProduct();
            spellProduct.setProduct(product);
            spellProduct.setSpelllist(spelllist);
            model.addAttribute("num",num);
            model.addAttribute("states",states);
            model.addAttribute("spellProduct",spellProduct);
            System.out.println(states+"qqqqqqqqqqqq");
            return "spelllistInfo";
        }

    }
}
