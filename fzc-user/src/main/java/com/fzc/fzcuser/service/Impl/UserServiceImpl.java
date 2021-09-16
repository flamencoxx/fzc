package com.fzc.fzcuser.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.model.UserDetail;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.service.UserInfoService;
import com.fzc.fzcuser.service.UserService;
import com.fzc.fzcuser.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Flamenco.xxx
 * @date 2021/9/15 20:55
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserInfoService userInfoService;


    @Override
    public UserDetail loadUserByUsername(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        UserInfo user = userInfoService.getOne(wrapper);
        if(user!=null){
            return new UserDetail(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
}
