package com.shop.service;

import com.shop.entity.Business;
import com.shop.mapper.BusinessMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessService {

    @Resource
    private BusinessMapper businessMapper;

    public Business findBusinessByAccount(String account){

        return businessMapper.findBusinessByAccount(account);
    }

    public Business findBusinessByBid(Integer bid) {
        return businessMapper.findBusinessByBid(bid);
    }
}
