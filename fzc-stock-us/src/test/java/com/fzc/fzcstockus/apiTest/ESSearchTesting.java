package com.fzc.fzcstockus.apiTest;

import com.fzc.fzcstockus.servcie.ESSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/22 11:21
 */
@SpringBootTest
public class ESSearchTesting {

//    @Autowired
//    private ESSearchClient esSearchClient;

    @Autowired
    private ESSearchService esSearchService;

    @Test
    public void test1(){
        String key = "IBM";
//        List<String> list = esSearchClient.searchByKeyword(key);
//        list.forEach(k ->{
//           System.out.println(k);
//        });
    }


    @Test
    public void test2(){
        String key = "IBM";
        List<String> list = esSearchService.searchByKeyword(key);
        list.forEach(k ->{
            System.out.println(k);
        });
    }



}
