package com.fzc.fzcusershiro.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcusershiro.mapper.UserMapper;
import com.fzc.fzcusershiro.model.User;
import com.fzc.fzcusershiro.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author flamenco.xxx
 * @date 2021/12/14 15:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
