package com.fzc.fzcuser.controller;

import cn.hutool.json.JSONObject;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Flamenco.xxx
 * @date 2021/9/15 20:47
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("login")
    public ResponseEntity<JSONObject> login(
            @RequestParam(value="username")String username,
            @RequestParam(value="password")String password
    ){

        return null;
    }
}
