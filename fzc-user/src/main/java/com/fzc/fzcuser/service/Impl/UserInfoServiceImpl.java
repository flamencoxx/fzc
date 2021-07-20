package com.fzc.fzcuser.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author 11615
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
