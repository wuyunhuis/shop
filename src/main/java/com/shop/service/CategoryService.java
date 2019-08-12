package com.shop.service;

import com.shop.entity.Category;
import com.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("categoryService")
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    public List<Category> findCategoty(){
       return categoryMapper.findCategoty();
    }

}
