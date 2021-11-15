package com.fzc.fzcfutu;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.model.StockInfo;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockInfoService;
import com.fzc.fzcfutu.testing.yahoo;
import com.fzc.fzcfutu.tool.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FzcFutuApplicationTests {

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
    public void TestYahoo(){
        String url = "https://query1.finance.yahoo.com/v7/finance/quote?symbols=IBM";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("get_product1返回结果：" + result);
        Assert.hasText(result, "get_product1返回结果为空");


    }

    @Test
    public void TestYahoo2(){
        System.out.println("煞笔");

    }

    @Test
    public void Yahoo(){
        yahoo y = new yahoo();
        y.something();
    }

    @Test
    public void Tushare(){
        String url = "http://api.waditu.com";
        JSONObject params = JSONUtil.createObj()
                .put("ts_code","002603.SZ")
                .put("start_date","20190701")
                .put("end_date","");
        JSONObject json = JSONUtil.createObj()
                .put("api_name","daily")
                .put("token","8e17aab159f1840b1ad19ab1b602fb0fcf2bd7c48022109366291aeb")
                .put("params",params)
                .put("fields","");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json.toString(),headers);
        JSONObject result = restTemplate.postForObject(url,request,JSONObject.class);
        String a = result.toString();
        System.out.println(a);
    }

    @Test
    public void insertStock(){
        List<String> fieldsList = new ArrayList<String>();
        String url = "http://api.waditu.com";
        JSONObject listStatus = JSONUtil.createObj()
                .put("list_status","L");
        JSONObject params = JSONUtil.createObj()
                .put("api_name","stock_basic")
                .put("token","8e17aab159f1840b1ad19ab1b602fb0fcf2bd7c48022109366291aeb")
                .put("params",listStatus)
                .put("filed","");
        String result = RestClient.postJson(url, params.toString());
//        System.out.println(result);
        JSONObject json = JSONUtil.parseObj(result);
        JSONObject dataJson = json.getJSONObject("data");
        JSONArray fieldsJson = (JSONArray) dataJson.get("fields");
        JSONArray items = (JSONArray) dataJson.get("items");
        for (int i = 0;i < fieldsJson.size();i++){
            fieldsList.add(fieldsJson.get(i).toString());
            System.out.println("第：" + i +"个==" +fieldsJson.get(i).toString() );
        }

        for(int i = 0;i < items.size();i++){
            JSONArray single = (JSONArray) items.get(i);
            List<String> stockList = new ArrayList<String>();
            for(int j =0;j < single.size();j++){
                System.out.println("第：" + i +"个==" +fieldsList.get(j) +":"+ single.get(j).toString());
                stockList.add(single.get(j).toString());
            }
            StockInfo stock = new StockInfo(stockList.get(0),stockList.get(1),stockList.get(2),stockList.get(3),stockList.get(4),stockList.get(5),stockList.get(6));
            boolean r = stockInfoService.save(stock);
            System.out.println("结果是：" + r);
        }
    }


    @Test
    public void insertMongo(){
        for(int i = 1; i <= 4322;i++){
            StockInfo stock = stockInfoService.getById(i);
            System.out.println(stock.toString());
            StockInfoDO stockInfoDO = new StockInfoDO(stock.getTsCode(),stock.getSymbol(),stock.getName(),stock.getArea(),stock.getIndustry(),stock.getMarket(),stock.getListDate());
            stockInfoRepository.insert(stockInfoDO);
            System.out.println("ok");

        }

    }

    @Test
    public void fff(){
        String url ="http://quotes.money.163.com/service/zycwzb_601398.html?type=report";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        String result = restTemplate.getForObject(url, String.class);
        String[] rowsArray= result.split("/n");
        List<String> rows = new ArrayList<String>(Arrays.asList(rowsArray));
        for(String str:rows){
            String[] dataArray = str.split(",");
            List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
            for(int i=1;i <= 20;i++){
                
            }
        }

        System.out.println(result);

    }

//    A股经济数据
    @Test
    public void searchName(){
        for (int i = 4322;i <=4323;i++){

            StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOById(i);

            String symbol =stockInfoDO.getSymbol();
            String url ="http://quotes.money.163.com/service/zycwzb_"+symbol+".html?type=report";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            String result = restTemplate.getForObject(url, String.class);
            String[] rowsArray= result.split("\n");
            List<String> rows = new ArrayList<String>(Arrays.asList(rowsArray));





            List<StockInfoDO.FinancialIndicators> financialIndicatorsList = new ArrayList<StockInfoDO.FinancialIndicators>();

            String str11= rows.get(0);
            String[] dataArray11 = str11.split(",");
            List<String> dataList11 =new ArrayList<String>(Arrays.asList(dataArray11));
            for (int j = 0;j < dataList11.size()-2;j++){
                StockInfoDO.FinancialIndicators financialIndicators = new StockInfoDO.FinancialIndicators();
                financialIndicatorsList.add(financialIndicators);
            }


            for(int k = 0;k < 20;k++){




                if (k == 0){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                       // StockInfoDO.FinancialIndicators financialIndicators = new StockInfoDO.FinancialIndicators();
                        if(!dataList.get(j).equals("--")){

                            financialIndicatorsList.get(j-1).setReportDate(dataList.get(j));
                            System.out.println(dataList.get(j));
                        }
                        else{
                            dataList.set(j,"空");
                            financialIndicatorsList.get(j-1).setReportDate(dataList.get(j));
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 1){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            Double number = Double.parseDouble(dataList.get(j));
                            financialIndicatorsList.get(j-1).setEps(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            Double f = 0.0;
                            financialIndicatorsList.get(j-1).setEps(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 2){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            Double number = Double.parseDouble(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNav(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            Double f = 0.0;
                            financialIndicatorsList.get(j-1).setNav(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 3){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            Double number = Double.parseDouble(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetCashFlowIn(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            Double f = 0.0;
                            financialIndicatorsList.get(j-1).setNetCashFlowIn(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 4){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setRevenueFromMainBusiness(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setRevenueFromMainBusiness(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 5){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setProfitFromMainBusiness(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setProfitFromMainBusiness(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 6){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setProfitFromBusiness(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setProfitFromBusiness(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 7){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setInvestmentIncome(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setInvestmentIncome(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 8){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetNonOperatingIncomeAndExpenses(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setNetNonOperatingIncomeAndExpenses(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 9){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setTotalProfit(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setTotalProfit(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 10){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){

                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetProfit(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setNetProfit(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 11){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetProfitAfter(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setNetProfitAfter(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 12){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetCashFlowsFromOperatingActivities(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setNetCashFlowsFromOperatingActivities(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 13){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setNetIncreaseInCashAndCashEquivalents(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setNetIncreaseInCashAndCashEquivalents(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 14){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setTotalAssets(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setTotalAssets(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 15){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setLiquidAssets(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setLiquidAssets(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 16){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setTotalLiabilities(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setTotalLiabilities(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 17){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setFlowLiabilities(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setFlowLiabilities(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 18){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            long number = Long.parseLong(dataList.get(j));
                            financialIndicatorsList.get(j-1).setShareHolderEquity(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            long f = 0;
                            financialIndicatorsList.get(j-1).setShareHolderEquity(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }

                else if (k == 19){
                    String str1= rows.get(k);
                    String[] dataArray = str1.split(",");
                    List<String> dataList =new ArrayList<String>(Arrays.asList(dataArray));
                    for(int j = 1; j < dataList.size() - 1;j++){

                        if(!dataList.get(j).equals("--")){
                            Double number = Double.parseDouble(dataList.get(j));
                            financialIndicatorsList.get(j-1).setReturnOnNetAssetsWeighted(number);
                            System.out.println(dataList.get(j));
                        }
                        else{
                            Double f = 0.0;
                            financialIndicatorsList.get(j-1).setReturnOnNetAssetsWeighted(f);
                            System.out.println(dataList.get(j));
                        }
                    }
                }



//                else if(4<=k&& k<=18){
//
//                    String str1= rows.get(k);
//                    String[] dataArray = str1.split(",");
//                    List<String> dataList1 =new ArrayList<String>(Arrays.asList(dataArray));
//                    for(int j=1;j < dataList1.size() - 1;j++){
//
//                        if(!dataList1.get(j).equals("--")){
//                            long num = Long.parseLong(dataList1.get(j));
//                            long fzc = num * 10000;
//                            System.out.println(fzc);
//                        }
//                        else{
//                            dataList1.set(j,"空");
//                            System.out.println(dataList1.get(j));
//                        }
//                    }
//
//                }

            }

            stockInfoDO.setFinancialIndicatorsList(financialIndicatorsList);
            stockInfoRepository.save(stockInfoDO);


        }
    }

}
