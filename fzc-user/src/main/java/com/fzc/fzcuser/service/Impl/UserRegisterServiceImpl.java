package com.fzc.fzcuser.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.service.UserInfoService;
import com.fzc.fzcuser.service.UserRegisterService;
import org.springframework.stereotype.Service;

/**
 * @author Flamenco.xxx
 * @date 2021/9/17 9:08
 */
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserRegisterService {
}
