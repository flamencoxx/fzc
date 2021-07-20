package com.fzc.fzcfutu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.mapper.StockInfoMapper;
import com.fzc.fzcfutu.model.StockInfo;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockInfoService;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 11615
 */
@Service
public class StockInfoServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockInfoService {
    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;


    @Override
    public List<StockInfoDO.StockHistoricalData> findStockHistoryBySymbol(String symbol) {
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol(symbol);
        return stockInfoDO.getStockHistoricalDataList();
    }

    @Override
    public void refreshStockInfo() {
        for(int i = 1; i <= 4322;i++){
            StockInfo stock = stockInfoService.getById(i);
            System.out.println(stock.toString());
            StockInfoDO stockInfoDO = new StockInfoDO(stock.getTsCode(),stock.getSymbol(),stock.getName(),stock.getArea(),stock.getIndustry(),stock.getMarket(),stock.getListDate());
            stockInfoRepository.insert(stockInfoDO);
            System.out.println("ok");

        }
    }



    @Override
    public void refreshAllStockInfo(int start,int end) {




        for (int i = start;i <=end;i++){

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

    @Override
    public void refreshAllStockHistory(int start, int end) {
        for (int i = start;i <=end;i++) {

            StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOById(i);

            String symbol = stockInfoDO.getSymbol();
            String code = stockInfoDO.getCode();
            String[] codeSplit = code.split("\\.");
            System.out.println(codeSplit[0] + ":" + codeSplit[1]);
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
                System.out.println(result);
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
                System.out.println(dataList1.get(j));
            }

            for(int k = 1; k < rows.size();k++){
                System.out.println(k);
                String str = rows.get(k);
                String[] dataArray = str.split(",");
                List<String> dataList = new ArrayList<String>(Arrays.asList(dataArray));
                System.out.println(dataList.get(0)+ "--"+dataList.get(1));

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
                System.out.println(stockHistoricalData.toString());
                stockHistoricalDataList.add(stockHistoricalData);
            }
            stockInfoDO.setStockHistoricalDataList(stockHistoricalDataList);
            stockInfoRepository.save(stockInfoDO);


        }
    }
}
