package com.fzc.fzcuser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzc.fzcuser.model.UserInfo;
import com.fzc.fzcuser.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class FzcUserApplicationTests {

    @Autowired
    private UserInfoService userInfoService;
    @Test
    void contextLoads() {
    }
    @Test
    public void Test1(){
        UserInfo userInfo = new UserInfo("test1","123456","0","user");
        boolean save = userInfoService.save(userInfo);
        System.out.println(save);
    }
    @Test
    public void TestsSaveBatch(){
        int num1 = 20;
        List<UserInfo> userList = new ArrayList<UserInfo>();
        for(int i = 0;i<num1;i++){
            UserInfo userInfo = new UserInfo("慰" + i,"123456","0","user");
            userList.add(userInfo);
        }
        boolean result = userInfoService.saveOrUpdateBatch(userList);
        List<UserInfo> allUsers = userInfoService.list();
        if(result){
            for(int i = 0;i <allUsers.size();i++){
                UserInfo user = allUsers.get(i);
                System.out.println(user.toString());
            }
        }
    }

    @Test
    public void TestSearch(){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        String name = "慰";
        queryWrapper.like("user_name", name)
        .isNotNull("is_active");
        List<UserInfo> allUsers = userInfoService.list(queryWrapper);

        if(true){
            for(int i = 0;i <allUsers.size();i++){
                UserInfo user = allUsers.get(i);
                System.out.println(user.toString());
            }
        }

    }

}
