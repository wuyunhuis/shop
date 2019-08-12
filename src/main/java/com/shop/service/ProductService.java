package com.shop.service;

import com.shop.entity.Product;
import com.shop.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    public List<Product> findProductByHot(){
        List<Product> list=productMapper.findProductByHot();
        return list;
    }

    public  List<Product> findProductByDate(){
        List<Product> list=productMapper.findProductByDate();
        return list;
    }

    public  List<Product> findAllProduct(){
        return productMapper.findAllProduct();
    }

    public  List<Product> findProductByBid(Integer bid){
        return productMapper.findProductByBid(bid);
    }

    public List<Product> findProductByLikeName(String pname){
        return productMapper.findProductByLikeName(pname);
    }

    public List<Product> findProductByBidAdnLikeName(Integer bid,String pname){
        return productMapper.findProductByBidAdnLikeName(bid,pname);
    }

    public void deleteProductById(Integer pid) {
        productMapper.deleteProductById(pid);
    }

    @Transactional
    public void EditProductById(Product product) {
        productMapper.EditProductById(product);
    }

    public Product findProductByID(Integer pid){
        return productMapper.findProductByID(pid);
    }

    public void productAdd(Product product){
        productMapper.insertProduct(product);
    }

    public List<Product> findProductByPertory(Integer bid) {
        return productMapper.findProductByPertory(bid);
    }

    public void updateProductRepertory(Integer pid, Integer repertory) {
        productMapper.updateProductRepertory( pid,  repertory);
    }

    public void updateProductRepertoryAndSales(Integer number,Integer pid) {
        productMapper.updateProductRepertoryAndSales(number,pid);
    }

    public List<Product> findProductByWholesale(Integer wholesale) {
        return productMapper.findProductByWholesale(wholesale);
    }

    public List<Product> findProductByWholesaleAndSource(Integer wholesale,String source) {
        return productMapper.findProductByWholesaleAndSource(wholesale,source);
    }

    public List<Product> findProductBySource() {
        return productMapper.findProductBySource();
    }

    public List<Integer> findProductByFreeBid() {
         return productMapper.findProductByFreeBid();
    }

    public List<Product> findProductByBidFree(Integer businessID) {
        return productMapper.findProductByBidFree(businessID);
    }

    public List<Product> findProductByCid(Integer cid) {
        return productMapper.findProductByCid(cid);
    }
}
