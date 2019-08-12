package com.shop.mapper;


import com.shop.entity.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BusinessMapper {

    Business findBusinessByAccount(String Account);

    @Select("select * from business where bid=#{bid}")
    Business findBusinessByBid(@Param(value = "bid") Integer bid);
}