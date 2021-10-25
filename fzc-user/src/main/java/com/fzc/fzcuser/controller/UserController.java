package com.fzc.fzcuser.controller;

import cn.hutool.json.JSONObject;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.model.UserPayload;
import com.fzc.fzcuser.service.UserInfoService;
import com.fzc.fzcuser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Flamenco.xxx
 * @date 2021/9/15 20:47
 */
@Slf4j
@CrossOrigin()
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;


    @PostMapping("login1")
    @ResponseBody
    public ResponseEntity<JSONObject> login1(
            @RequestBody JSONObject json
    ){
        JSONObject result = new JSONObject();
//        boolean bool = userService.login(username,password);
        String status ;
        String currentAuthority;


        result.put("status","ok");
        result.put("type","account");
        result.put("currentAuthority","admin");
        log.info("username:" + "---------password:" );
        return ResponseEntity.ok(result);
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<JSONObject> login(
            @RequestParam(value="username")String username,
            @RequestParam(value="password")String password,
            @RequestParam(required =false,value="type")String type
    ){
        JSONObject result = new JSONObject();
        boolean bool = userService.login(username,password);
        String status ;
        String currentAuthority;

        if(bool){
            status = "ok";
            type = "account";
            currentAuthority = "admin";
        }else{
            status = "error";
            type = "account";
            currentAuthority = "guest";
        }

        result.put("status",status);
        result.put("type",type);
        result.put("currentAuthority",currentAuthority);
        log.info("username:"+ username + "---------password:" + password);
        return ResponseEntity.ok(result);
    }

    @PostMapping("login2")
    @ResponseBody
    public ResponseEntity<JSONObject> login2(
            @RequestBody UserPayload userPayload
            ){
        String username = userPayload.getUserName();
        String password = userPayload.getPassword();
        JSONObject result = new JSONObject();
        boolean bool = userService.login(username,password);
        String status ;
        String currentAuthority;
        String type;

        if(bool){
            status = "ok";
            type = "account";
            currentAuthority = "admin";
        }else{
            status = "error";
            type = "account";
            currentAuthority = "guest";
        }

        result.put("status",status);
        result.put("type",type);
        result.put("currentAuthority",currentAuthority);
        log.info("username:"+ userPayload.getUserName() + "---------password:" + userPayload.getPassword());
        return ResponseEntity.ok(result);
    }
}
