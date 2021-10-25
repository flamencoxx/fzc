package com.fzc.fzcuser.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcuser.mapper.UserInfoMapper;

import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.service.UserInfoService;
import com.fzc.fzcuser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * @author Flamenco.xxx
 * @date 2021/9/15 20:55
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {


    @Autowired
    private UserInfoService userInfoService;


    @Override
    public UserInfo loadUserByUsername(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        UserInfo user = userInfoService.getOne(wrapper);
        if(user!=null){
            return user;
        }
        return null;
    }

    @Override
    public boolean login(String username, String password) {
        boolean result = false;
        //密码需要客户端加密后传递

            UserInfo user = loadUserByUsername(username);
            if(user==null){
                return false;
            }
            if(!user.getPassword().equals(password)){
                return false;
            }
            result = true;

        return true;
    }
}
