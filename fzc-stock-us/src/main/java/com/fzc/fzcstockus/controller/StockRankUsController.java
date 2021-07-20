package com.fzc.fzcstockus.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.model.Metric;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockUsInfoService;
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
@Controller
@RequestMapping(value ="/UsRank")
public class StockRankUsController {

    @Autowired
    private StockUsInfoService stockUsInfoService;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


//    从数据库抓10个数据:

    @GetMapping("Financial")
    public ResponseEntity<JSONObject> searchUsRank(
            @RequestParam(value="pageIndex")int pageIndex,
            @RequestParam(value="pageSize")int pageSize,
            @RequestParam(value="sort")String sort,
            @RequestParam(value="node")String node){


        PageRequest pageable = PageRequest.of(pageIndex, pageSize, Sort.by(sort).ascending());

        Page<StockUsInfoDo> pageResult = stockUsInfoDoRepository.findAll(pageable);

        int allPages = pageResult.getTotalPages();


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


        System.out.println("调用了一次");

//        System.out.println(resultJson.toString());






        return ResponseEntity.ok(resultJson);
    }
}
