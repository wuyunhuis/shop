package com.shop;

import com.shop.entity.Business;
import com.shop.entity.Product;
import com.shop.mapper.BusinessMapper;
import com.shop.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {


	@Resource
	private ProductMapper productMapper;

	@Resource
	private BusinessMapper businessMapper;

	@Test
	public void contextLoads() {

	Business business=businessMapper.findBusinessByAccount("admin1");
		System.out.println(business.toString());
	}

	@Test
	public void   contextProduct() {
		List<Product> list=productMapper.findProductByLikeName("包");
		for(Product product : list) {
			System.out.println(product.toString());
		}



	}

}
