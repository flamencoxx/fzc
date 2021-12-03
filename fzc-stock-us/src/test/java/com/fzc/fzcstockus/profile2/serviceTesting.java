package com.fzc.fzcstockus.profile2;

import cn.hutool.json.JSONObject;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.reportedModel.StockUsReport;
import com.fzc.fzcstockus.reportedModel.TestHaa;
import com.fzc.fzcstockus.servcie.FinancialsReportService;
import com.fzc.fzcstockus.servcie.StockUsImportService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 11:07
 */
@SpringBootTest
public class serviceTesting {

    public static final String HELLO = "hello";
    @Autowired
    private FinancialsReportService financialsReportService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Test
    public void test(){
        List<StockUsImport> list = stockUsImportService.list();
        list.forEach(k->{
            StockUsReport stockUsReport = new StockUsReport();
            stockUsReport.setSymbol(k.getSymbol());
            financialsReportService.save(stockUsReport);
        });
    }

    @Test
    public void update() throws Exception {
        financialsReportService.updateReportAll();
    }

    @Test
    public void getFinancial(){
        String code = "ibm";
        FinancialsReported f = financialsReportService.getReportFast(code);
        System.out.println(f.toString());
    }

    @Test
    public void getFinancialKryo(){
        String code = "aapl";
        FinancialsReported f = financialsReportService.getReportKryo(code);
        System.out.println(f.toString());
    }

    @Test
    public void testGson(){
        String code = "ibm";
        String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ code +"&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        TestHaa testHaa = new TestHaa();
        testHaa = json.toBean(TestHaa.class);
        System.out.println(testHaa.toString());
        System.out.println("");
    }

    @Test
    public void coda(){
    }

    @Test
    public void http(){
        URL url = null;
        try {
            url = new URL("https://finnhub.io/api");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
