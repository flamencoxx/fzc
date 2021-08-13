package com.fzc.fzcfutu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcfutu.DO.StockInfoDO;
import com.fzc.fzcfutu.mapper.StockInfoMapper;
import com.fzc.fzcfutu.model.StockInfo;
import com.fzc.fzcfutu.repository.StockInfoRepository;
import com.fzc.fzcfutu.service.StockHistoryService;
import com.fzc.fzcfutu.service.StockInfoService;
import com.fzc.fzcfutu.tool.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/13 9:12
 */
@Service
public class StockHistoyServiceImpl extends ServiceImpl<StockInfoMapper, StockInfo> implements StockHistoryService {
    @Autowired
    private StockInfoRepository stockInfoRepository;

    @Autowired
    private StockInfoService stockInfoService;


    @Override
    public Boolean fetchHistoryData(String stockCode) {




        boolean bool = false;



            StockInfoDO stockInfoDO = stockInfoRepository.findStockInfoDOBySymbol(stockCode);


            String symbol = stockInfoDO.getSymbol();
            String code = stockInfoDO.getCode();
            String[] codeSplit = code.split("\\.");
            System.out.println(codeSplit[0] + ":" + codeSplit[1]);
            String url;
            if (codeSplit[1].equals("SZ")){

                url ="http://quotes.money.163.com/service/chddata.html?code=1"+ symbol +"&start=20000720&end=" + new SimpleDateFormat("yyyyMMdd").format(new Date());

            }else {

                url ="http://quotes.money.163.com/service/chddata.html?code=0"+ symbol +"&start=20000720&end=" + new SimpleDateFormat("yyyyMMdd").format(new Date());

            }


            String result = "";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            try {
                result = new String(restTemplate.getForObject(url, String.class).getBytes("ISO-8859-1"),"gb2312");
                if(!result.isEmpty()){
                    bool = true;
                }
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




        return bool;
    }
}
