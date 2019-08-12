package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.*;
import com.shop.service.ProductService;
import com.shop.service.SpikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SpikeController {
    @Autowired
    SpikeService spikeService;
    @Autowired
    ProductService productService;
    @RequestMapping(value = "/toAddsSpike")
    public String toAddSpike(Model model, HttpSession session){

        Business business=(Business)session.getAttribute("business");
        List<Product> list=productService.findProductByBid(business.getBid());

        model.addAttribute("list",list);
        return "adminSpikeAdd";
    }
    @RequestMapping(value = "/SpikeList")
    public String SpikeList(Model model, HttpSession session,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,8);
        Business business=(Business)session.getAttribute("business");
        List<Spike> listSpike=spikeService.findByBid(business.getBid());
        List<SpikeProduct> list= new LinkedList<SpikeProduct>();
        for (int i = 0; i <listSpike.size() ; i++) {
            Spike spike1= listSpike.get(i);
            Product product=productService.findProductByID(spike1.getPid());
            SpikeProduct spikeProduct=new SpikeProduct();
            spikeProduct.setProduct(product);
            spikeProduct.setSpike(spike1);
            list.add(i,spikeProduct);
        }
        PageInfo<SpikeProduct> pageInfo = new PageInfo<SpikeProduct>(list);

        model.addAttribute("pageInfo",pageInfo);


        return "adminSpikeList";
    }
    @RequestMapping(value = "/addSpikeList")
    public String addSpikeList(Model model, HttpSession session, @ModelAttribute Spike spike , @RequestParam(value = "times") String times){

        String format="yyyy-MM-dd hh:mm:ss";
        try{
             Integer sid=spikeService.maxSid();
            sid=sid+1;
            Date time=new SimpleDateFormat(format).parse(times);
            spike.setTime(time);

            spike.setSid(sid);
            Business business=(Business)session.getAttribute("business");
            spike.setBid(business.getBid());
            spike.setState(0);
            System.out.println(spike.toString());
            spikeService.insert(spike);

            return "redirect:/SpikeList";
        }catch (ParseException e){
            e.printStackTrace();
        }

        return "redirect:/SpikeList";
    }

    @RequestMapping(value = "/productBySpikeList")
    public String productBySpikeList(Model model, HttpSession session,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,4);
        List<Spike> listSpike=spikeService.findAll();
        List<SpikeProduct> list= new LinkedList<SpikeProduct>();
        for (int i = 0; i <listSpike.size() ; i++) {
            Spike spike1= listSpike.get(i);
            Product product=productService.findProductByID(spike1.getPid());
            SpikeProduct spikeProduct=new SpikeProduct();
            spikeProduct.setProduct(product);
            spikeProduct.setSpike(spike1);
            list.add(i,spikeProduct);
        }
        PageInfo<SpikeProduct> pageInfo = new PageInfo<SpikeProduct>(list);

        model.addAttribute("pageInfo",pageInfo);


        return "productBySpikeList";
    }


    @RequestMapping(value = "/productSpikeByPids")
    public  String productSpikeByPid(@RequestParam(value = "sid") Integer sid,Model model){
        Spike spike=spikeService.findSpikeBySid(sid);
        Date date1=new Date();
        Date date2=spike.getTime();
        int compareTo = date1.compareTo(date2);

        if(compareTo>=0){

            if(spike.getNumber()==0){
                Integer states=2;
                spikeService.upateSpike(states,sid);
                Product product=productService.findProductByID(spike.getPid());
                SpikeProduct spikeProduct=new SpikeProduct();
                spikeProduct.setSpike(spike);
                spikeProduct.setProduct(product);
                model.addAttribute("spikeProduct",spikeProduct);
                model.addAttribute("states",states);
                return "spiketInfo";
            }else {
                Integer states=1;
                spikeService.upateSpike(states,sid);
                Product product=productService.findProductByID(spike.getPid());
                SpikeProduct spikeProduct=new SpikeProduct();
                spikeProduct.setSpike(spike);
                spikeProduct.setProduct(product);
                model.addAttribute("spikeProduct",spikeProduct);
                model.addAttribute("states",states);
                return "spiketInfo";
            }

        }else {

            Integer states=0;
            Product product=productService.findProductByID(spike.getPid());
            SpikeProduct spikeProduct=new SpikeProduct();
            spikeProduct.setSpike(spike);
            spikeProduct.setProduct(product);
            model.addAttribute("spikeProduct",spikeProduct);
            model.addAttribute("states",states);
            return "spiketInfo";
        }


    }
}
