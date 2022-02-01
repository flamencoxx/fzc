package com.fzc.fzcstockus.servcie.Impl;

import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.mapper.StockUsReportMapper;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.reportedModel.StockUsReport;
import com.fzc.fzcstockus.servcie.FinancialsReportService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import com.fzc.fzcstockus.util.KryoSerializerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 10:48
 */
@Service
@Slf4j
public class FinancialsReportServiceImpl  extends ServiceImpl<StockUsReportMapper, StockUsReport> implements FinancialsReportService {




    @Override
    public void updateReport(String code) {
        String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ code +"&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        FinancialsReported financialsReported = new FinancialsReported();
        financialsReported = json.toBean(FinancialsReported.class);
        byte[] bytes = KryoSerializerUtils.serialize(financialsReported);
        QueryWrapper<StockUsReport> queryWrapper = new QueryWrapper<StockUsReport>();
        queryWrapper.eq("symbol",code);
        StockUsReport stockUsReport = this.getOne(queryWrapper);
        stockUsReport.setReport(bytes);
        this.updateById(stockUsReport);
    }

    @Override
    public void updateReports() {
        List<StockUsReport> list = this.list();
        list.forEach(k->{
            String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ k.getSymbol() +"&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            FinancialsReported financialsReported = new FinancialsReported();
            financialsReported = json.toBean(FinancialsReported.class);
            byte[] bytes = KryoSerializerUtils.serialize(financialsReported);
            QueryWrapper<StockUsReport> queryWrapper = new QueryWrapper<StockUsReport>();
            queryWrapper.eq("symbol",k.getSymbol());
            StockUsReport stockUsReport = this.getOne(queryWrapper);
            stockUsReport.setReport(bytes);
            this.updateById(stockUsReport);
        });
    }

    @Override
    public void updateReportFast(String code) {
        String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ code +"&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        FinancialsReported financialsReported = new FinancialsReported();
        financialsReported = json.toBean(FinancialsReported.class);
        String jsonStr = JSON.toJSONString(financialsReported);
        byte[] bytes = jsonStr.getBytes();
        QueryWrapper<StockUsReport> queryWrapper = new QueryWrapper<StockUsReport>();
        queryWrapper.eq("symbol",code);
        StockUsReport stockUsReport = this.getOne(queryWrapper);
        stockUsReport.setReportFast(bytes);
        this.updateById(stockUsReport);
    }

    @Override
    public void updateReportsFast() {
        List<StockUsReport> list = this.list();
        list.forEach(k->{
            String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ k.getSymbol() +"&token=c32mkoaad3ieculvpcsg";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            FinancialsReported financialsReported = new FinancialsReported();
            financialsReported = json.toBean(FinancialsReported.class);
            String jsonStr = JSON.toJSONString(financialsReported);
            byte[] bytes = jsonStr.getBytes();
            QueryWrapper<StockUsReport> queryWrapper = new QueryWrapper<StockUsReport>();
            queryWrapper.eq("symbol",k.getSymbol());
            StockUsReport stockUsReport = this.getOne(queryWrapper);
            stockUsReport.setReportFast(bytes);
            this.updateById(stockUsReport);
        });
    }

    @Override
    public void updateReportAll() {
        List<StockUsReport> list = this.list();
        list.forEach(k->{
            try {
                String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ k.getSymbol() +"&token=c32mkoaad3ieculvpcsg";
                RestTemplate restTemplate = RestTemplateUtils.getInstance();
                JSONObject json = restTemplate.getForObject(url, JSONObject.class);
                FinancialsReported financialsReported = new FinancialsReported();
                if(json == null){
                    return;
                }
                financialsReported = json.toBean(FinancialsReported.class);
                String jsonStr = JSON.toJSONString(financialsReported);
                byte[] bytes = jsonStr.getBytes();
                byte[] bytes2 = KryoSerializerUtils.serialize(financialsReported);
                QueryWrapper<StockUsReport> queryWrapper = new QueryWrapper<StockUsReport>();
                queryWrapper.eq("id",k.getId());
                StockUsReport stockUsReport = this.getOne(queryWrapper);
                stockUsReport.setReport(bytes2);
                stockUsReport.setReportFast(bytes);
                this.updateById(stockUsReport);
                System.out.println(k.getId()+ ":" + k.getSymbol());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public FinancialsReported getReportFast(String code) {
        QueryWrapper<StockUsReport> query = new QueryWrapper<>();
        query.eq("symbol",code);
        TimeInterval timer = new TimeInterval();
        StockUsReport stockUsReport = this.getOne(query);
        Console.log(timer.interval());
        FinancialsReported obj = JSON.parseObject(stockUsReport.getReportFast(),FinancialsReported.class);
        return obj;
    }

    @Override
    public FinancialsReported getReportKryo(String code) {
        QueryWrapper<StockUsReport> query = new QueryWrapper<>();
        query.eq("symbol",code);
        StockUsReport stockUsReport = this.getOne(query);
        FinancialsReported obj = (FinancialsReported) KryoSerializerUtils.deserialize(stockUsReport.getReport());
        return obj;
    }
}
