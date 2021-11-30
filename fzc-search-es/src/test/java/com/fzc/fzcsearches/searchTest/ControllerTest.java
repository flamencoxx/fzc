package com.fzc.fzcsearches.searchTest;

import com.fzc.fzcsearches.domain.EsStockUsImport;
import com.fzc.fzcsearches.service.StockUsImportService;
import com.fzc.fzcsearches.util.SearchUtil;
import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/23 17:58
 */
@SpringBootTest
public class ControllerTest {

    @Autowired
    private StockUsImportService stockUsImportService;

    @Test
    public void test1() {
        String str = "International Business Machines";
        List<EsStockUsImport> list = stockUsImportService.searchByKeywords(str);
        list.forEach(k -> System.out.println(k.getSymbol()));
    }

    @Test
    public void utilTest1() {
        String str = "Macy's, Inc., an omnichannel retail organization, operates stores, websites, and mobile applications under the Macy's, Bloomingdale's, and bluemercury brands. It sells a range of merchandise, including apparel and accessories for men, women, and kids; cosmetics; home furnishings; and other consumer goods. As of January 30, 2021, the company operated 727 store locations in 43 states, the District of Columbia, Puerto Rico, and Guam. It also operates in Dubai, the United Arab Emirates and Al Zahra, Kuwait under the license agreements. The company was formerly known as Federated Department Stores, Inc. and changed its name to Macy's, Inc. in 2007. Macy's, Inc. was founded in 1830 and is headquartered in New York, New York.";
        List<String> res = SearchUtil.splitWords3(str);
        res.forEach(r -> System.out.println(r));
        Joiner joiner = Joiner.on(" ").skipNulls();
        String resStr = joiner.join(res);
        System.out.println(resStr);
    }

    @Test
    public void test() {
        String str = "finance";
        boolean res = stockUsImportService.checkIsSector(str);
        System.out.println(res);
    }

}
