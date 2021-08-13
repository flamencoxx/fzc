package com.fzc.fzcstockus.servcie.Impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.*;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.servcie.StockPeerService;
import com.fzc.fzcstockus.servcie.StockUsHistoryTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/8/12 12:53
 */
@Service
@Slf4j
public class StockUsHistoryTableServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockUsHistoryTableService {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockPeerService stockPeerService;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;

    @Override
    public JSONObject findUsTable(String code) {
        JSONObject jsonObject = JSONUtil.createObj();

        JSONArray data = JSONUtil.createArray();

        List<String> dateList = new ArrayList<String>();
        StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code.toUpperCase());
        Annual annual = new Annual();
        try {
            annual = stockUsInfoDo.getBasicFinancials().getAnnual();
        }catch(Exception e){
            e.printStackTrace();
            log.info("该股票还没有Annual数据");
            jsonObject.put("error","该股票还没有Annual数据");
            return jsonObject;
        }
//        Annual annual = stockUsInfoDo.getBasicFinancials().getAnnual();

        List<CashRatio> cashRatioList;
        try {
            cashRatioList = annual.getCashRatio();
        }catch(Exception e){
            e.printStackTrace();
            log.info("该股票还没有Annual数据");
            jsonObject.put("error","该股票还没有Annual数据");
            return jsonObject;
        }


        for(CashRatio cashRatio: cashRatioList){
            String date = cashRatio.getPeriod();
            dateList.add(date);
        }

        int key =1;
        for(String date : dateList){
            JSONObject singleJson = JSONUtil.createObj();
            singleJson.put("date",date);
            singleJson.put("key",key);
            key++;
            for(CashRatio cashRatio : cashRatioList){
                if(cashRatio.getPeriod().equals(date)){
                    singleJson.put("CashRatio",cashRatio.getV());
                }
            }

            List<CurrentRatio> currentRatioList = annual.getCurrentRatio();

            for(CurrentRatio currentRatio : currentRatioList){
                if(currentRatio.getPeriod().equals(date)){
                    singleJson.put("CurrentRatio",currentRatio.getV());
                }
            }


            List<EbitPerShare> ebitPerShareList =new ArrayList<EbitPerShare>();
            try{
                ebitPerShareList = annual.getEbitPerShare();
                for(EbitPerShare ebitPerShare : ebitPerShareList){
                    if(ebitPerShare.getPeriod().equals(date)){
                        singleJson.put("EbitPerShare",ebitPerShare.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(EbitPerShare ebitPerShare : ebitPerShareList){
//                if(ebitPerShare.getPeriod().equals(date)){
//                    singleJson.put("EbitPerShare",ebitPerShare.getV());
//                }
//            }

            List<Eps> epsList =new ArrayList<Eps>();
            try{
                epsList = annual.getEps();
                for(Eps eps : epsList){
                    if(eps.getPeriod().equals(date)){
                        singleJson.put("Eps",eps.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(Eps eps : epsList){
//                if(eps.getPeriod().equals(date)){
//                    singleJson.put("Eps",eps.getV());
//                }
//            }

            List<LongtermDebtTotalAsset> longtermDebtTotalAssetList =new ArrayList<LongtermDebtTotalAsset>();
            try{
                longtermDebtTotalAssetList = annual.getLongtermDebtTotalAsset();
                for(LongtermDebtTotalAsset longtermDebtTotalAsset : longtermDebtTotalAssetList){
                    if(longtermDebtTotalAsset.getPeriod().equals(date)){
                        singleJson.put("LongtermDebtTotalAsset",longtermDebtTotalAsset.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(LongtermDebtTotalAsset longtermDebtTotalAsset : longtermDebtTotalAssetList){
//                if(longtermDebtTotalAsset.getPeriod().equals(date)){
//                    singleJson.put("LongtermDebtTotalAsset",longtermDebtTotalAsset.getV());
//                }
//            }

            List<NetDebtToTotalCapital> netDebtToTotalCapitalList =new ArrayList<NetDebtToTotalCapital>();
            try{
                netDebtToTotalCapitalList = annual.getNetDebtToTotalCapital();
                for(NetDebtToTotalCapital netDebtToTotalCapital : netDebtToTotalCapitalList){
                    if(netDebtToTotalCapital.getPeriod().equals(date)){
                        singleJson.put("NetDebtToTotalCapital",netDebtToTotalCapital.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(NetDebtToTotalCapital netDebtToTotalCapital : netDebtToTotalCapitalList){
//                if(netDebtToTotalCapital.getPeriod().equals(date)){
//                    singleJson.put("NetDebtToTotalCapital",netDebtToTotalCapital.getV());
//                }
//            }

            List<NetMargin> netMarginList =new ArrayList<NetMargin>();
            try{
                netMarginList = annual.getNetMargin();
                for(NetMargin netMargin : netMarginList){
                    if(netMargin.getPeriod().equals(date)){
                        singleJson.put("NetMargin",netMargin.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }


//            for(NetMargin netMargin : netMarginList){
//                if(netMargin.getPeriod().equals(date)){
//                    singleJson.put("NetMargin",netMargin.getV());
//                }
//            }

//            营业利润率
            List<OperatingMargin> operatingMarginList =new ArrayList<OperatingMargin>();
            try{
                operatingMarginList = annual.getOperatingMargin();
                for(OperatingMargin operatingMargin : operatingMarginList){
                    if(operatingMargin.getPeriod().equals(date)){
                        singleJson.put("OperatingMargin",operatingMargin.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(OperatingMargin operatingMargin : operatingMarginList){
//                if(operatingMargin.getPeriod().equals(date)){
//                    singleJson.put("OperatingMargin",operatingMargin.getV());
//                }
//            }

            List<SalesPerShare> salesPerShareList =new ArrayList<SalesPerShare>();
            try{
                salesPerShareList = annual.getSalesPerShare();
                for(SalesPerShare salesPerShare : salesPerShareList){
                    if(salesPerShare.getPeriod().equals(date)){
                        singleJson.put("SalesPerShare",salesPerShare.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }
//            for(SalesPerShare salesPerShare : salesPerShareList){
//                if(salesPerShare.getPeriod().equals(date)){
//                    singleJson.put("SalesPerShare",salesPerShare.getV());
//                }
//            }

            List<TotalDebtToTotalAsset> totalDebtToTotalAssetList =new ArrayList<TotalDebtToTotalAsset>();
            try{
                totalDebtToTotalAssetList = annual.getTotalDebtToTotalAsset();
                for(TotalDebtToTotalAsset totalDebtToTotalAsset : totalDebtToTotalAssetList){
                    if(totalDebtToTotalAsset.getPeriod().equals(date)){
                        singleJson.put("TotalDebtToTotalAsset",totalDebtToTotalAsset.getV());
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }

//            for(TotalDebtToTotalAsset totalDebtToTotalAsset : totalDebtToTotalAssetList){
//                if(totalDebtToTotalAsset.getPeriod().equals(date)){
//                    singleJson.put("TotalDebtToTotalAsset",totalDebtToTotalAsset.getV());
//                }
//            }





            data.add(singleJson);
        }


        jsonObject.put("data",data);
        return jsonObject;
    }
}
