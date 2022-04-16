package com.fzc.fzcstocka.client;

import com.fzc.fzcstocka.DO.StockInfoDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Flamenco.xxx
 * @date 2022/4/12 9:25
 */
@Component
@RestController
@FeignClient(name = "fzc-futu",url="http://127.0.0.1:8083/",path ="/InfoTransform")
//@RequestMapping("/InfoTransform")
public interface InfoTransformClient {

    @GetMapping(value = "/stockInfo")
    String getInfo();

    @GetMapping(value = "/findStockInfo")
    StockInfoDO findStockInfo(@RequestParam(value="stockCode") String stockCode);
}
