package com.shop.service;

import com.shop.entity.User;
import com.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {
    @Autowired
    private  UserMapper userMapper;
    public User findUserName(String username) {

        return userMapper.findUserName(username);
    }

    @Transactional
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }
}
