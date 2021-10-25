package com.fzc.fzcuser.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcuser.mapper.UserInfoMapper;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11615
 */
@CrossOrigin()
@Controller
@RequestMapping(value ="/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    private UserInfoService userInfoService;


    @GetMapping("selectOne")
    public ResponseEntity<UserInfo> selectById(@RequestParam(value="id") Integer userId){
        UserInfo userInfo = userInfoService.getById(userId);
        if (userInfo == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(userInfo);
    }


    @GetMapping("query")
    public ResponseEntity<UserInfo> queryUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_name", username)
                .eq("user_password",password);
        UserInfo userInfo = userInfoService.getOne(queryWrapper);
        if (userInfo == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(userInfo);
    }


    @GetMapping("findOne")
    public ResponseEntity<UserInfo> findOne(@RequestParam(value="username") String username){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        UserInfo user = userInfoService.getOne(queryWrapper);
        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping("delete")
    public ResponseEntity<Boolean> deleteRecordByName(@RequestParam(value="username") String username){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        boolean result = userInfoService.remove(queryWrapper);
        return ResponseEntity.ok(result);
    }

    @GetMapping("role")
    public ResponseEntity<String> findRoleByName(@RequestParam(value="username") String username){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        UserInfo user = userInfoService.getOne(queryWrapper);
        String roleName = user.getRole();
        return ResponseEntity.ok(roleName);
    }
    
    
}
