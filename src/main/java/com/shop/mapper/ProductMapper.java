package com.shop.mapper;

import com.shop.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product findProductByID(Integer pid);

    List<Product> findAllProduct();

    List<Product> findProductByHot();

    List<Product> findProductByDate();

    @Select("select * from product where  pname like CONCAT('%',#{pname},'%')")
    List<Product> findProductByLikeName(@Param("pname") String pname);

    void insertProduct(Product product);

    List<Product> findProductByBid(Integer bid);

    @Select("select * from product where bid=#{bid} and pname like CONCAT('%',#{pname},'%')")
    List<Product> findProductByBidAdnLikeName(@Param("bid") Integer bid, @Param("pname") String pname);

    @Delete("delete from product where pid=#{pid}")
    void deleteProductById(@Param("pid") Integer pid);

    void EditProductById(Product product);

    @Select("select * from product where bid=#{bid} and repertory<10 ")
    List<Product> findProductByPertory(@Param("bid") Integer bid);

    @Update("update product set repertory=#{repertory} where pid=#{pid}")
    void updateProductRepertory(@Param("pid") Integer pid, @Param("repertory") Integer repertory);

    @Update("update product set repertory=repertory-#{number},sales=sales+#{number} where pid=#{pid}")
    void updateProductRepertoryAndSales(@Param("number")Integer number,@Param("pid")Integer pid);

    @Select("select * from product where wholesale=#{wholesale} ")
    List<Product> findProductByWholesale(@Param("wholesale")Integer wholesale);
    @Select("select * from product where wholesale=#{wholesale} and source=#{source}")
    List<Product> findProductByWholesaleAndSource(@Param("wholesale")Integer wholesale,@Param("source") String source);

    @Select("select distinct source from  product")
    List<Product> findProductBySource();

    @Select("select distinct bid from  product where taxation=0 and postage=0")
    List<Integer> findProductByFreeBid();

    @Select("select * from product where bid=#{businessID} and taxation=0 and postage=0")
    List<Product> findProductByBidFree(@Param(value = "businessID") Integer businessID);

    @Select("select * from product where cid=#{cid}")
    List<Product> findProductByCid(@Param(value = "cid")Integer cid);
}