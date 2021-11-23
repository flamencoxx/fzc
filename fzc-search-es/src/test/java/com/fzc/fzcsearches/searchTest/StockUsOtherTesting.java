package com.fzc.fzcsearches.searchTest;

import com.fzc.fzcsearches.service.StockUsOtherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/23 15:49
 */
@SpringBootTest
public class StockUsOtherTesting {

    @Autowired
    private StockUsOtherService stockUsOtherService;

    @Test
    public void Test1(){
        List<String> list = stockUsOtherService.remNameComStr();
        list.forEach( k ->{
            System.out.println(k);
        });
    }

    @Test
    public void Test2(){
        List<String> list = stockUsOtherService.remDescComStr();
        list.forEach( k ->{
            System.out.println(k);
        });
    }

    @Test
    public void Test3(){
        String str = "The Gorman-Rupp Company designs, manufactures, and sells pumps and pump systems worldwide. The company's products include self-priming centrifugal, standard centrifugal, magnetic drive centrifugal, axial and mixed flow, vertical turbine line shaft, submersible, high pressure booster, rotary gear, diaphragm, bellows, and oscillating pumps. Its products are used in water, wastewater, construction, dewatering, industrial, petroleum, original equipment, agriculture, fire protection, military, and other liquid-handling applications, as well as in heating, ventilating, and air conditioning applications. The company markets its products through a network of distributors, manufacturers' representatives, third-party distributor catalogs, direct sales, and commerce. The Gorman-Rupp Company was founded in 1933 and is headquartered in Mansfield, Ohio.";
        String res = stockUsOtherService.convertStr(str);
        System.out.println(res);
    }
}
