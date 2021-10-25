package com.fzc.fzcuser.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.model.UserRegister;
import com.fzc.fzcuser.service.UserInfoService;
import com.fzc.fzcuser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author Flamenco.xxx
 * @date 2021/9/17 9:09
 */
@CrossOrigin()
@Controller
@Slf4j
@RequestMapping(value ="/userRegister")
public class UserRegisterController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<JSONObject> registerUser(@RequestBody UserRegister userRegister){

        JSONObject result = JSONUtil.createObj();
        if (userRegister.getUsername().isEmpty()&&
                userRegister.getPassword().isEmpty()&&
                !userRegister.getConfirm().equals(userRegister.getPassword())&&
                userRegister.getEmail().isEmpty()){
            result.put("status","error");
            result.put("currentAuthority","account");
            return  ResponseEntity.ok(result);
        }

        UserInfo user = new UserInfo();
        user.setUsername(userRegister.getUsername());
        user.setPassword(userRegister.getPassword());
        user.setEmail(userRegister.getEmail());
        user.setIsActive(1);
        user.setStatus(1);
        user.setPhone(userRegister.getMobile());
        user.setRole("user");
        Date date = new Date();
        user.setCreateDate(String.valueOf(date));

        int num = userInfoMapper.insert(user);
//        result.put("username",userRegister.getUsername());
//        result.put("mobile",userRegister.getMobile());
        result.put("status","ok");
        result.put("currentAuthority","user");
        log.info("username="+ userRegister.getUsername() + "---num=" + num);
        log.info("phone=" + userRegister.getMobile());
        log.info("email="+ userRegister.getEmail());
        return  ResponseEntity.ok(result);
    }

}
