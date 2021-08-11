package com.fzc.fzcfutu.repository;

import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.service.StockInfoService;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StockInfoService stockInfoService;

    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Test
    void contextLoads() {
    }


    @Test
    public void findHistoryList(){
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol("002603");
        System.out.println(stockInfoDO.toString());
        List<StockInfoDO.StockHistoricalData> stockHistoricalDataList = stockInfoDO.getStockHistoricalDataList();
        for(StockInfoDO.StockHistoricalData s:stockHistoricalDataList){
            String str = s.toString();
            System.out.println(str);

            System.out.println(s.getName() + s.getClose());
            System.out.println(s.getClose() + s.getDate());
        }
    }


    @Test
    public void test1(){
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol("002303");
        System.out.println(stockInfoDO.toString());
        List<StockInfoDO.FinancialIndicators> financialIndicatorsList = stockInfoDO.getFinancialIndicatorsList();
        for(StockInfoDO.FinancialIndicators f:financialIndicatorsList){
            System.out.println(f.toString());
            System.out.println(f.getEps());
        }
    }

    @Test
    public void test2(){
        String url ="http://quotes.money.163.com/service/chddata.html?code=1002603&start=20000720&end=202106010";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        String result = restTemplate.getForObject(url, String.class);
        try {
            String str = new String(restTemplate.getForObject(url, String.class).getBytes("ISO-8859-1"),"gb2312");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void refreshAllStockHistory(){

//        1 - 4322
        for (int i = 1;i <=4322;i++) {

            StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOById(i);

            String symbol = stockInfoDO.getSymbol();
            String code = stockInfoDO.getCode();
            String[] codeSplit = code.split("\\.");
//            System.out.println(codeSplit[0] + ":" + codeSplit[1]);
            String url;
            if (codeSplit[1].equals("SZ")){

                url ="http://quotes.money.163.com/service/chddata.html?code=1"+ symbol +"&start=20000720&end=202106010";

            }else {

                url ="http://quotes.money.163.com/service/chddata.html?code=0"+ symbol +"&start=20000720&end=202106010";

            }


            String result = "";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            try {
                result = new String(restTemplate.getForObject(url, String.class).getBytes("ISO-8859-1"),"gb2312");
//                System.out.println(result);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String[] rowsArray= result.split("\n");
            List<String> rows = new ArrayList<String>(Arrays.asList(rowsArray));

            List<StockInfoDO.StockHistoricalData> stockHistoricalDataList = new ArrayList<StockInfoDO.StockHistoricalData>();

            String str1 = rows.get(0);
            String[] dataArray1 = str1.split(",");
            List<String> dataList1 = new ArrayList<String>(Arrays.asList(dataArray1));
            for(int j = 0;j < dataList1.size();j++){
//                System.out.println(dataList1.get(j));
            }

            for(int k = 1; k < rows.size();k++){
//                System.out.println(k);
                String str = rows.get(k);
                String[] dataArray = str.split(",");
                List<String> dataList = new ArrayList<String>(Arrays.asList(dataArray));
//                System.out.println(dataList.get(0)+ "--"+dataList.get(1));

                if(dataList.get(0).equals("None")){
                    dataList.set(0,"0");
                }
                if(dataList.get(1).equals("None")){
                    dataList.set(1,"0");
                }
                if(dataList.get(2).equals("None")){
                    dataList.set(2,"0");
                }
                if(dataList.get(3).equals("None")){
                    dataList.set(3,"0");
                }
                if(dataList.get(4).equals("None")){
                    dataList.set(4,"0");
                }
                if(dataList.get(5).equals("None")){
                    dataList.set(5,"0");
                }
                if(dataList.get(6).equals("None")){
                    dataList.set(6,"0");
                }
                if(dataList.get(7).equals("None")){
                    dataList.set(7,"0");
                }
                if(dataList.get(8).equals("None")){
                    dataList.set(8,"0");
                }
                if(dataList.get(9).equals("None")){
                    dataList.set(9,"0");
                }
                if(dataList.get(10).equals("None")){
                    dataList.set(10,"0");
                }
                if(dataList.get(11).equals("None")){
                    dataList.set(11,"0");
                }
                if(dataList.get(12).equals("None")){
                    dataList.set(12,"0");
                }
                if(dataList.get(13).equals("None")){
                    dataList.set(13,"0");
                }
                if(dataList.get(14).equals("None")){
                    dataList.set(14,"0");
                }
                if(dataList.get(15).equals("None")){
                    dataList.set(15,"0");
                }

                StockInfoDO.StockHistoricalData stockHistoricalData = new StockInfoDO.StockHistoricalData(dataList.get(0),dataList.get(1),dataList.get(2),Double.parseDouble(dataList.get(3)),Double.parseDouble(dataList.get(4)),Double.parseDouble(dataList.get(5)),Double.parseDouble(dataList.get(6)),Double.parseDouble(dataList.get(7)),Double.parseDouble(dataList.get(8)),Double.parseDouble(dataList.get(9)),Double.parseDouble(dataList.get(10)),Double.parseDouble(dataList.get(11)),Double.parseDouble(dataList.get(12)),Double.parseDouble(dataList.get(13)),Double.parseDouble(dataList.get(14)),dataList.get(15));
                stockHistoricalDataList.add(stockHistoricalData);
            }
            stockInfoDO.setStockHistoricalDataList(stockHistoricalDataList);
            stockInfoRepository.save(stockInfoDO);
            System.out.println(stockInfoDO.getCode());


        }
    }
}
