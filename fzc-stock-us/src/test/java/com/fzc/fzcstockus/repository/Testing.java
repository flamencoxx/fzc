package com.fzc.fzcstockus.repository;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsDO;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import com.mysql.cj.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class Testing {

    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Test
    public void insertUsList(){
        String symbol = "https://finnhub.io/api/v1/stock/symbol?exchange=US&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONArray jsonObj = restTemplate.getForObject(symbol, JSONArray.class);

        List<StockUsDO> stockUsDOList = new ArrayList<StockUsDO>();
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (Object o : jsonObj) {
            JSONObject singleJson = JSONUtil.parseObj(o);
            list.add(singleJson);
        }

        for(JSONObject s:list){
            String t1 = s.get("currency").toString();
            String t2 = s.get("description").toString();
            String t3 = s.get("displaySymbol").toString();
            String t4 = s.get("figi").toString();
            String t5 = s.get("mic").toString();
            String t6 = s.get("symbol").toString();
            String t7 = s.get("type").toString();
            StockUsDO stockUsDO = new StockUsDO(t1,t2,t3,t4,t5,t6,t7);
            stockUsDOList.add(stockUsDO);
            System.out.println(t1);
        }

        for (StockUsDO s:stockUsDOList){
            stockUsDORepository.save(s);
        }
        Object json = jsonObj.get(1);
        System.out.println(json);
        System.out.println("=================================================");
    }
    
    
    @Test
    public void Query(){
        List<StockUsDO> stockUsDOList1 = stockUsDORepository.findByMicEquals("XNYS");
        List<StockUsDO> stockUsDOList2 = stockUsDORepository.findByMicEquals("XNAS");
        List<StockUsDO> stockUsDOList = new ArrayList<StockUsDO>(stockUsDOList1);
        stockUsDOList.addAll(stockUsDOList2);
        for(StockUsDO s:stockUsDOList1){
            System.out.println(s.toString());
        }
    }

    @Test
    public void queryAndCommonStock(){
        List<StockUsDO> stockUsDOList1 = stockUsDORepository.findByMicEqualsAndTypeEquals("XNYS", "Common Stock");
        List<StockUsDO> stockUsDOList2 = stockUsDORepository.findByMicEqualsAndTypeEquals("XNAS","Common Stock");
        List<StockUsDO> stockUsDOList = new ArrayList<StockUsDO>();
        stockUsDOList.addAll(stockUsDOList1);
        stockUsDOList.addAll(stockUsDOList2);

        List<StockUsInfoDo> stockUsInfoDoList = new ArrayList<StockUsInfoDo>();
        for(StockUsDO s:stockUsDOList){
            String str1 = s.getCurrency();
            String str2 = s.getDescription();
            String str3 = s.getDisplaySymbol();
            String str4 = s.getFigi();
            String str5 = s.getMic();
            String str6 = s.getSymbol();
            String str7 = s.getType();
            StockUsInfoDo stockUsInfoDo = new StockUsInfoDo(str1,str2,str3,str4,str5,str6,str7);
            System.out.println(stockUsInfoDo.toString());
            stockUsInfoDoList.add(stockUsInfoDo);
            System.out.println(s.toString());
        }

        for(StockUsInfoDo s:stockUsInfoDoList){
            stockUsInfoDoRepository.save(s);
        }
    }

    @Test
    public void test(){
//        float f = 1.5 + 1.0;
//        byte b = 15 + (int)5;
//        System.out.println(b);
        String [] a = {"fzc","fff"};
        List<String> list = Arrays.asList(a);
        System.out.println(list);
    }

    @Test
    public void gitTesting(){

    }

    @Test
    public void CompanyOverview(){
//        4757
        for (int stockId = 4247 ;stockId <=4757; stockId++) {
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(stockId);
            String symbol = stock.getSymbol();
            String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol="+ symbol +"&apikey=3V71JD9X509YKRJY";
            JSONObject json = null;
            try {
                RestTemplate restTemplate = RestTemplateUtils.getInstance();
                json = restTemplate.getForObject(url, JSONObject.class);
                }catch (Exception e){
                e.printStackTrace();
                try {
                    TimeUnit.SECONDS.sleep(10);
                    try{
                        RestTemplate restTemplate = RestTemplateUtils.getInstance();
                        json = restTemplate.getForObject(url, JSONObject.class);
                    }catch (Exception k){
                        k.printStackTrace();
                        continue;
                    }

                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
//            RestTemplate restTemplate = RestTemplateUtils.getInstance();
//            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            assert json != null;
            if(json.isEmpty()){
                continue;


            }
            String Name;
            try {
                Name = json.get("Name").toString();
            }catch (NullPointerException e){
                e.printStackTrace();
                continue;
            }
//            String Name = json.get("Name").toString();
            String Description = json.get("Description").toString();
            String Sector = json.get("Sector").toString();
            String Industry = json.get("Industry").toString();
            String FullTimeEmployees;
            try {
                FullTimeEmployees = json.get("FullTimeEmployees").toString();
            }catch(NullPointerException e){
                e.printStackTrace();
                FullTimeEmployees = null;
            }
//            String FullTimeEmployees = json.get("FullTimeEmployees").toString();
            String MarketCapitalization = json.get("MarketCapitalization").toString();
            String EBITDA = json.get("EBITDA").toString();
            String PERatio = json.get("PERatio").toString();
            String PEGRatio = json.get("PEGRatio").toString();
            String BookValue = json.get("BookValue").toString();
            String DividendPerShare = json.get("DividendPerShare").toString();
            String DividendYield = json.get("DividendYield").toString();
            String EPS = json.get("EPS").toString();
            String ProfitMargin = json.get("ProfitMargin").toString();
            String OperatingMarginTTM = json.get("OperatingMarginTTM").toString();
            String ReturnOnAssetsTTM = json.get("ReturnOnAssetsTTM").toString();
            String ReturnOnEquityTTM = json.get("ReturnOnEquityTTM").toString();
            String RevenueTTM = json.get("RevenueTTM").toString();
            String GrossProfitTTM = json.get("GrossProfitTTM").toString();
            String AnalystTargetPrice = json.get("AnalystTargetPrice").toString();
            String TrailingPE = json.get("TrailingPE").toString();
            String ForwardPE = json.get("ForwardPE").toString();
            String PriceToSalesRatioTTM = json.get("PriceToSalesRatioTTM").toString();
            String PriceToBookRatio = json.get("PriceToBookRatio").toString();
            String EVToRevenue = json.get("EVToRevenue").toString();
            String EVToEBITDA = json.get("EVToEBITDA").toString();
            String Beta = json.get("Beta").toString();


            StockUsInfoDo.CompanyOverview companyOverview = new StockUsInfoDo.CompanyOverview();
            companyOverview.setName(Name);
            companyOverview.setDescription(Description);
            companyOverview.setSector(Sector);
            companyOverview.setIndustry(Industry);
            companyOverview.setFullTimeEmployees(FullTimeEmployees);
            companyOverview.setMarketCapitalization(MarketCapitalization);
            companyOverview.setEBITDA(EBITDA);
            companyOverview.setPERatio(PERatio);
            companyOverview.setPEGRatio(PEGRatio);
            companyOverview.setBookValue(BookValue);
            companyOverview.setDividendPerShare(DividendPerShare);
            companyOverview.setDividendYield(DividendYield);
            companyOverview.setEPS(EPS);
            companyOverview.setProfitMargin(ProfitMargin);
            companyOverview.setOperatingMarginTTM(OperatingMarginTTM);
            companyOverview.setReturnOnAssetsTTM(ReturnOnAssetsTTM);
            companyOverview.setReturnOnEquityTTM(ReturnOnEquityTTM);
            companyOverview.setRevenueTTM(RevenueTTM);
            companyOverview.setGrossProfitTTM(GrossProfitTTM);
            companyOverview.setAnalystTargetPrice(AnalystTargetPrice);
            companyOverview.setTrailingPE(TrailingPE);
            companyOverview.setForwardPE(ForwardPE);
            companyOverview.setPriceToBookRatio(PriceToBookRatio);
            companyOverview.setPriceToSalesRatioTTM(PriceToSalesRatioTTM);
            companyOverview.setEVToEBITDA(EVToEBITDA);
            companyOverview.setEVToRevenue(EVToRevenue);
            companyOverview.setBeta(Beta);

            System.out.println(companyOverview.getName());
            stock.setCompanyOverview(companyOverview);
            stockUsInfoDoRepository.save(stock);

//            if(stockId%5 == 0){
//                try {
//                    TimeUnit.SECONDS.sleep(12);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            try {
                TimeUnit.MILLISECONDS.sleep(15500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }



    }




    @Test
    public void Peers(){
//        4757
        for(int stockId = 1971; stockId <= 4757; stockId++){
            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoById(stockId);
            String symbol = stock.getSymbol();
            String url = "https://finnhub.io/api/v1/stock/peers?symbol="+ symbol +"&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONArray jsonArray = restTemplate.getForObject(url, JSONArray.class);
            List<String> strList = jsonArray.toList(String.class);

            stock.setPeerList(strList);

            for(String s: strList){
                System.out.println(s);
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stockUsInfoDoRepository.save(stock);


        }
    }



    
}
