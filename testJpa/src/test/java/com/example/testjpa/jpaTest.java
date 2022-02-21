package com.example.testjpa;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import com.example.testjpa.model.JpaTest;
import com.example.testjpa.repository.JpaTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/1/30 9:52
 */
@SpringBootTest
public class jpaTest {

    @Autowired
    private JpaTestRepository dao;

    @Test
    public void test1(){
        JpaTest jpa = new JpaTest();
        jpa.setId(1L);
        jpa.setAge(10);
        jpa.setInstructed(DateTime.now());
        jpa.setName("fzc");
        jpa.setTreeid(123456);
        dao.save(jpa);
    }

    @Test
    public void test2(){
        Console.log(DateTime.now().toString());
    }

    @Test
    public void test3(){
        for(int i = 0;i < 20;i++){
            JpaTest jpa = new JpaTest();
            int id = i + 2;
            jpa.setId((long) id);
            jpa.setAge(i);
            jpa.setInstructed(DateTime.now());
            jpa.setName("fzc" + i);
            jpa.setTreeid(123456);
            dao.save(jpa);
        }

    }

    @Test
    public void test4(){
        Date date = DateTime.now();
        List<JpaTest> list = dao.findByInstructedAndTreeid(date,123456);
        list.forEach(k->{
           Console.log(k.toString());
        });
        Console.log(list.size());
    }

    @Test
    public void test5(){
        Date date = DateTime.now();
        List<JpaTest> list = dao.findByTreeid(123456);
        list.forEach(k->{
            Console.log(k.toString());
        });
        Console.log(list.size());
    }

    @Test
    public void test6(){
        Date date = DateTime.now();
        String str = "2022-01-28";
        List<JpaTest> list = dao.findByInstructed3(str);
        list.forEach(k->{
            Console.log(k.toString());
        });
        Console.log(list.size());
    }

    @Test
    public void test7(){
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date myDate1 = null;
        try {
            myDate1 = dateFormat1.parse("2022-01-28 10:10:01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Console.log(myDate1);
        List<JpaTest> list = dao.findByInstructed(myDate1);
        list.forEach(k->{
            Console.log(k.toString());
        });
        Console.log(list.size());
    }

    @Test
    public void test8(){
        Date date = DateTime.now();
        String str = "2022-01-30";
        long treeId = 123456;
        List<JpaTest> list = dao.findByTreeid1(treeId);
        list.forEach(k->{
            Console.log(k.toString());
        });
        Console.log(list.size());
    }
}
