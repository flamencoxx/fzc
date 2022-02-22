package com.example;

import cn.hutool.core.lang.Console;
import com.fzc.fzcstocka.model.MarketSecuritiesInfo;
import com.fzc.fzcstocka.repository.StockAInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 16:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class repositoryTest {

    @Autowired
    private StockAInfoRepository dao;


    @Test
    public void test1() {
        String code = "000001";
        MarketSecuritiesInfo stock = dao.findBySymbol(code);
        Console.log(stock);
    }
}
