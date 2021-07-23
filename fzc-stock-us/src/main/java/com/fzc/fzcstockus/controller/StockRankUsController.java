package com.fzc.fzcstockus.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.model.Metric;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/6/26 13:00
 */
@CrossOrigin()
@Slf4j
@Controller
@RequestMapping(value ="/UsRank")
public class StockRankUsController {

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


//    从数据库抓10个数据:通过pageable分页返回，bug返回的总页数需要乘10才能正常显示不然显示页数只有十分之一，很奇怪。
//    美股股市排行，包含约4800支股票主要由纳斯达克全球以及纽约交易所组成。
//    逻辑分为三种情况排序，全部股票都查询all，查询纽交所XNYS，查询纳斯达克XNAS。

    @GetMapping("Financial")
    public ResponseEntity<JSONObject> searchUsRank(
            @RequestParam(value="pageIndex")int pageIndex,
            @RequestParam(value="pageSize")int pageSize,
            @RequestParam(value="sort")String sort,
            @RequestParam(value="node")String node){


        PageRequest pageable = PageRequest.of(pageIndex, pageSize, Sort.by(sort).ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findAll(pageable);

        log.info("当前页码：" + pageResult.getSize()+ "总页数：" + pageResult.getTotalPages());
        log.info(pageResult.toString());

        int allPages = pageResult.getTotalPages() * 10;
        long totalElements = pageResult.getTotalElements();


        String NODE_ALL ="all";

        String NODE_XNYS ="XNYS";

        String NODE_XNAS ="XNAS";


        JSONObject resultJson = JSONUtil.createObj();
        resultJson.put("code",200);
        resultJson.put("message","操作成功");


        if(node.equals(NODE_ALL)){
            JSONObject allJson = JSONUtil.createObj();
            allJson.put("allPages",allPages);
            allJson.put("PageIndex",pageIndex);
            allJson.put("PageSize",pageSize);

            List<StockUsInfoDo> stockUsInfoDoList = stockUsInfoService.findAllByPage(pageIndex, pageSize, sort);

            JSONArray array = new JSONArray();
            for(StockUsInfoDo stockUsInfoDo:stockUsInfoDoList){
                JSONObject singleData = JSONUtil.createObj();

//                从数据库查找数据
                String symbolStr = stockUsInfoDo.getSymbol();
                BasicFinancials basicFinancials = stockUsInfoDo.getBasicFinancials();
                Metric metric = basicFinancials.getMetric();
                String grossMarginAnnual = metric.getGrossMarginAnnual();
                String epsBasicExclExtraItemsAnnual = metric.getEpsBasicExclExtraItemsAnnual();
                String epsGrowth3Y = metric.getEpsGrowth3Y();
                String freeCashFlowPerShareTtm = metric.getFreeCashFlowPerShareTTM();
                String netProfitMarginAnnual = metric.getNetProfitMarginAnnual();
                String peBasicExclExtraTtm = metric.getPeBasicExclExtraTTM();
                String revenueGrowth3Y = metric.getRevenueGrowth3Y();
                String roaRfy = metric.getRoaRfy();
                String roeRfy = metric.getRoeRfy();

//                把数据放进json对象
                singleData.put("symbol",symbolStr);
                singleData.put("grossMarginAnnual",grossMarginAnnual);
                singleData.put("epsBasicExclExtraItemsAnnual",epsBasicExclExtraItemsAnnual);
                singleData.put("epsGrowth3Y",epsGrowth3Y);
                singleData.put("freeCashFlowPerShareTtm",freeCashFlowPerShareTtm);
                singleData.put("netProfitMarginAnnual",netProfitMarginAnnual);
                singleData.put("peBasicExclExtraTtm",peBasicExclExtraTtm);
                singleData.put("revenueGrowth3Y",revenueGrowth3Y);
                singleData.put("roaRfy",roaRfy);
                singleData.put("roeRfy",roeRfy);

                array.add(singleData);

            }
            allJson.put("rank",array);

            resultJson.put("data",allJson);


        }else if(node.equals(NODE_XNYS)){
            JSONObject allJson = JSONUtil.createObj();
            allJson.put("PageIndex",pageIndex);
            allJson.put("allPages",allPages);
            allJson.put("PageSize",pageSize);
            List<StockUsInfoDo> stockUsInfoDoList = stockUsInfoService.findByMicPage(pageIndex, pageSize, NODE_XNYS, sort);

            JSONArray array = new JSONArray();
            for(StockUsInfoDo stockUsInfoDo:stockUsInfoDoList){
                JSONObject singleData = JSONUtil.createObj();

//                从数据库查找数据
                String symbolStr = stockUsInfoDo.getSymbol();
                BasicFinancials basicFinancials = stockUsInfoDo.getBasicFinancials();
                Metric metric = basicFinancials.getMetric();
                String grossMarginAnnual = metric.getGrossMarginAnnual();
                String epsBasicExclExtraItemsAnnual = metric.getEpsBasicExclExtraItemsAnnual();
                String epsGrowth3Y = metric.getEpsGrowth3Y();
                String freeCashFlowPerShareTtm = metric.getFreeCashFlowPerShareTTM();
                String netProfitMarginAnnual = metric.getNetProfitMarginAnnual();
                String peBasicExclExtraTtm = metric.getPeBasicExclExtraTTM();
                String revenueGrowth3Y = metric.getRevenueGrowth3Y();
                String roaRfy = metric.getRoaRfy();
                String roeRfy = metric.getRoeRfy();

//                把数据放进json对象
                singleData.put("symbol",symbolStr);
                singleData.put("grossMarginAnnual",grossMarginAnnual);
                singleData.put("epsBasicExclExtraItemsAnnual",epsBasicExclExtraItemsAnnual);
                singleData.put("epsGrowth3Y",epsGrowth3Y);
                singleData.put("freeCashFlowPerShareTtm",freeCashFlowPerShareTtm);
                singleData.put("netProfitMarginAnnual",netProfitMarginAnnual);
                singleData.put("peBasicExclExtraTtm",peBasicExclExtraTtm);
                singleData.put("revenueGrowth3Y",revenueGrowth3Y);
                singleData.put("roaRfy",roaRfy);
                singleData.put("roeRfy",roeRfy);

                array.add(singleData);

            }
            allJson.put("rank",array);

            resultJson.put("data",allJson);




        }else if(node.equals(NODE_XNAS)){
            JSONObject allJson = JSONUtil.createObj();
            allJson.put("PageIndex",pageIndex);
            allJson.put("allPages",allPages);
            allJson.put("PageSize",pageSize);
            List<StockUsInfoDo> stockUsInfoDoList = stockUsInfoService.findByMicPage(pageIndex, pageSize, NODE_XNAS, sort);


            JSONArray array = new JSONArray();
            for(StockUsInfoDo stockUsInfoDo:stockUsInfoDoList){
                JSONObject singleData = JSONUtil.createObj();

//                从数据库查找数据
                String symbolStr = stockUsInfoDo.getSymbol();
                BasicFinancials basicFinancials = stockUsInfoDo.getBasicFinancials();
                Metric metric = basicFinancials.getMetric();
                String grossMarginAnnual = metric.getGrossMarginAnnual();
                String epsBasicExclExtraItemsAnnual = metric.getEpsBasicExclExtraItemsAnnual();
                String epsGrowth3Y = metric.getEpsGrowth3Y();
                String freeCashFlowPerShareTtm = metric.getFreeCashFlowPerShareTTM();
                String netProfitMarginAnnual = metric.getNetProfitMarginAnnual();
                String peBasicExclExtraTtm = metric.getPeBasicExclExtraTTM();
                String revenueGrowth3Y = metric.getRevenueGrowth3Y();
                String roaRfy = metric.getRoaRfy();
                String roeRfy = metric.getRoeRfy();

//                把数据放进json对象
                singleData.put("symbol",symbolStr);
                singleData.put("grossMarginAnnual",grossMarginAnnual);
                singleData.put("epsBasicExclExtraItemsAnnual",epsBasicExclExtraItemsAnnual);
                singleData.put("epsGrowth3Y",epsGrowth3Y);
                singleData.put("freeCashFlowPerShareTtm",freeCashFlowPerShareTtm);
                singleData.put("netProfitMarginAnnual",netProfitMarginAnnual);
                singleData.put("peBasicExclExtraTtm",peBasicExclExtraTtm);
                singleData.put("revenueGrowth3Y",revenueGrowth3Y);
                singleData.put("roaRfy",roaRfy);
                singleData.put("roeRfy",roeRfy);

                array.add(singleData);

            }
            allJson.put("rank",array);

            resultJson.put("data",allJson);

        }


        log.info("成功调用美股排行数据");

//        System.out.println(resultJson.toString());






        return ResponseEntity.ok(resultJson);
    }
}
