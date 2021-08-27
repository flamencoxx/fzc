package com.fzc.fzcfutu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.mapper.StockInfoMapper;
import com.fzc.fzcfutu.model.StockInfo;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockFinancialService;
import com.fzc.fzcfutu.service.StockInfoService;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 10:36
 */
@Service
@Slf4j
public class StockFinancialServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockFinancialService {


    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;


    @Override
    public boolean fetchStockFinancial(String code) {

        boolean bool = false;
        StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol(code);

        String symbol =stockInfoDO.getSymbol();
        String url ="http://quotes.money.163.com/service/zycwzb_"+symbol+".html?type=report";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();


        String result = restTemplate.getForObject(url, String.class);
        assert result != null;
        if (!result.isEmpty()){
            bool = true;
        }
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        dataList.set(j,"空");
                        financialIndicatorsList.get(j-1).setReportDate(dataList.get(j));
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        Double f = 0.0;
                        financialIndicatorsList.get(j-1).setEps(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        Double f = 0.0;
                        financialIndicatorsList.get(j-1).setNav(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        Double f = 0.0;
                        financialIndicatorsList.get(j-1).setNetCashFlowIn(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setRevenueFromMainBusiness(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setProfitFromMainBusiness(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setProfitFromBusiness(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setInvestmentIncome(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setNetNonOperatingIncomeAndExpenses(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setTotalProfit(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setNetProfit(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setNetProfitAfter(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setNetCashFlowsFromOperatingActivities(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setNetIncreaseInCashAndCashEquivalents(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setTotalAssets(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setLiquidAssets(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setTotalLiabilities(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setFlowLiabilities(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        long f = 0;
                        financialIndicatorsList.get(j-1).setShareHolderEquity(f);
//                        System.out.println(dataList.get(j));
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
//                        System.out.println(dataList.get(j));
                    }
                    else{
                        Double f = 0.0;
                        financialIndicatorsList.get(j-1).setReturnOnNetAssetsWeighted(f);
//                        System.out.println(dataList.get(j));
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


        return bool;
    }
}
