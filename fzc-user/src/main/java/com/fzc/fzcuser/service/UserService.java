package com.fzc.fzcuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcuser.model.UserDetail;
import com.fzc.fzcuser.model.UserInfo;

/**
 * @author Flamenco.xxx
 * @date 2021/9/15 20:55
 */
public interface UserService extends IService<UserInfo> {

    public String login(String username,String password);

    public UserDetail loadUserByUsername(String username);
}
