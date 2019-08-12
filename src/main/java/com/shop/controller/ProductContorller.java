package com.shop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.entity.Business;
import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
public class ProductContorller{
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    private static String UPLOADED_FOLDER ="E://IdeaProjects//shop//src//main//resources//static//shop//images//upload//";

    @RequestMapping(value = "/index")
    public String findHotAndNewProduct(Model model, @ModelAttribute User user,HttpSession session){
        List<Product> hList=productService.findProductByHot();
        List<Product> nList=productService.findProductByDate();
        model.addAttribute("hList",hList);
        model.addAttribute("nList",nList);

        List<Category> categoty=categoryService.findCategoty();

        session.setAttribute("categoty",categoty);
        if(user.getUsername()!=null||user.getPassword()!=null){
          User user1= userService.findUserName(user.getUsername());
          if(user.getPassword().equals(user1.getPassword())){
             session.setAttribute("user",user1);
             return "main";
          }else {
              return "login";
          }
        }
        return "main";
    }

    @RequestMapping(value = "/adminProductList")
    public String productList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpSession session){
        PageHelper.startPage(pageNum,4);
        Business business=(Business)session.getAttribute("business");
        List<Product> list=productService.findProductByBid(business.getBid());
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "adminProductList";
    }

    @RequestMapping(value = "/findProductByLikeName")
    public String findProductByLikeName(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, HttpSession session, @ModelAttribute Product product){

        PageHelper.startPage(pageNum,4);
        Business business=(Business)session.getAttribute("business");
        String pname=product.getPname();
        System.out.println(pname);
        if(pname==null){
            pname=(String)session.getAttribute("adminPname");
            System.out.println("ture"+pname);
        }else {
            session.setAttribute("adminPname",pname);
            System.out.println("false"+pname);
        }
        List<Product> list=productService.findProductByBidAdnLikeName(business.getBid(),pname);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "adminLikeProductList";
    }

    @RequestMapping(value = "/deleteProductById")
    public String deleteProductById(Integer pid){
        productService.deleteProductById(pid);
        return "redirect:/adminProductList";
    }

    @RequestMapping(value = "/EditProductById")
    public String EditProductById(@ModelAttribute Product product,@RequestParam("file") MultipartFile file,
                                  RedirectAttributes redirectAttributes,HttpSession session){
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage("/shop/images/upload/"+file.getOriginalFilename());
      /*  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        */

        product.setPdate(new Date());
        product.setSales(0);
        Business business=(Business)session.getAttribute("business");
        Integer bid=business.getBid();
        product.setBid(bid);
        System.out.println(product.toString());
        productService.EditProductById(product);
        return  "redirect:/adminProductList";

    }

    @RequestMapping(value = "/findProductByID")
    public String findProductByID(Model model,Integer pid){
        Product product=productService.findProductByID(pid);
        model.addAttribute("product",product);
        return "adminProductEdit";
    }

    @RequestMapping(value = "/toProductAdd")
    public  String toProductAdd(){
        return "adminProductAdd";
    }

    @RequestMapping(value = "/productAdd")
    public  String productAdd(@ModelAttribute Product product,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,HttpSession session){

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage("/shop/images/upload/"+file.getOriginalFilename());

        product.setPdate(new Date());
        product.setSales(0);
        Business business=(Business)session.getAttribute("business");
        Integer bid=business.getBid();
        product.setBid(bid);
        System.out.println(product.toString());
        productService.productAdd(product);
        return "redirect:/adminProductList";
    }

    @RequestMapping(value = "/productRepertoryList")
    public String productRepertoryList(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpSession session){
        PageHelper.startPage(pageNum,4);
        Business business=(Business)session.getAttribute("business");
        List<Product> list=productService.findProductByPertory(business.getBid());
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "AdminProductRepertoryList";

    }
    @RequestMapping(value = "/updateProductRepertory")
    public  String updateProductRepertory(@ModelAttribute Product product){
        productService.updateProductRepertory(product.getPid(),product.getRepertory());
        return "redirect:/productRepertoryList";
    }

    @RequestMapping(value = "/findProductByPID")
    public String findProductByPID(@ModelAttribute Product product,Model model){
        Product product1=productService.findProductByID(product.getPid());
        model.addAttribute("product",product1);
        return "product_info";
    }


    @RequestMapping(value = "/findProductLikeName")
    public String findProductLikeName(@RequestParam(value = "name",required = false) String name,Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpSession session){

        if(name!=null){
            PageHelper.startPage(pageNum,8);
            session.setAttribute("name",name);
            List<Product> list=productService.findProductByLikeName(name);

            PageInfo<Product> pageInfo = new PageInfo<Product>(list);

            model.addAttribute("pageInfo",pageInfo);
            return "productLikeList";
        }else {
            PageHelper.startPage(pageNum,8);
            name=(String) session.getAttribute("name");
            List<Product> list=productService.findProductByLikeName(name);


            PageInfo<Product> pageInfo = new PageInfo<Product>(list);
            model.addAttribute("pageInfo",pageInfo);
            return "productLikeList";
        }

    }
    @RequestMapping(value = "/productWholesaleList")
    public String productWholesaleList(@RequestParam(value = "source" ,required = false)  String source,@RequestParam(value = "wholesale",required = false) Integer wholesale,HttpSession session,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam(value = "star",required = false) Integer star){

       List<Product>  sourceList=productService.findProductBySource();
        session.setAttribute("sourceList",sourceList);
        if(wholesale==null && source ==null){

            if(wholesale==null){

                PageHelper.startPage(pageNum,8);
                wholesale=1;
                session.setAttribute("wholesale",wholesale);
                List<Product> list=productService.findProductByWholesale(wholesale);
                PageInfo<Product> pageInfo = new PageInfo<Product>(list);
                model.addAttribute("pageInfo",pageInfo);
                return "productWholesaleList";
            }else {
                wholesale =(Integer) session.getAttribute("wholesale");
                source =(String) session.getAttribute("source");
                List<Product> list=productService.findProductByWholesaleAndSource(wholesale,source);

                PageInfo<Product> pageInfo = new PageInfo<Product>(list);
                model.addAttribute("pageInfo",pageInfo);
                return "productWholesaleList";
            }

        }{
            PageHelper.startPage(pageNum,8);
            session.setAttribute("wholesale",wholesale);
            session.setAttribute("source",source);
            List<Product> list=productService.findProductByWholesaleAndSource(wholesale,source);
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);
            model.addAttribute("pageInfo",pageInfo);
            return "productWholesaleList";
        }


    }

    @RequestMapping(value = "/findProductByCid")
    public String findProductByCid(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,HttpSession session,@RequestParam(value = "cid",required = false) Integer cid){

        if(cid!=null){
            session.setAttribute("cid",cid);
            PageHelper.startPage(pageNum,8);
            List<Product> list=productService.findProductByCid(cid);
            PageInfo<Product> pageInfo = new PageInfo<Product>(list);

            model.addAttribute("pageInfo",pageInfo);
            return "productByCidList";
        }

        cid=(Integer) session.getAttribute("cid");
        PageHelper.startPage(pageNum,8);
        List<Product> list=productService.findProductByCid(cid);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);

        model.addAttribute("pageInfo",pageInfo);
        return "productByCidList";
    }

    @RequestMapping(value = "/xinshou")
    public  String xinshou(){
        return "xinshou";
    }

}
