package com.fzc.fzcstocka.service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstocka.mapper.StockAInfoMapper;
import com.fzc.fzcstocka.model.StockAInfo;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.RestTemplateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:34
 */
@Service
@Slf4j
public class StockAInfoServiceImpl  extends ServiceImpl<StockAInfoMapper, StockAInfo> implements StockAInfoService {



    public static final String HTTP_API_TUSHARE_PRO = "http://api.tushare.pro";
    public static final String TOKEN = "2cd7c1c0caf424ccbbdfad489ee875aaf02fb6565fc8a0e0ca0cae65";

    @Override
    public StockAInfo searchByMoreKey(String code) {
        QueryWrapper<StockAInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("code",code)
                .or()
                .eq("symbol", code)
                .or()
                .eq("ts_code",code)
                .or()
                .eq("stock_identity",code);
        StockAInfo stock = null;
        try {
            stock = this.getOne(wrapper);
        } catch (Exception e) {
            log.error("请求的code为无效数据");
            e.printStackTrace();
        }
        return stock;
    }

    @Override
    public void updateMarketValues() {
        List<StockAInfo> list = this.list();
        list.forEach(k -> {
            String marketValue = getMarketValue(k.getTsCode());
            Console.log(marketValue);
            k.setMarketValue(marketValue);
            boolean bool = this.saveOrUpdate(k);
            Console.log(k.getTsCode() + ":" + bool);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public StockAInfo findByStockIdentity(String code) {
        StockAInfo stock;
        try {
            QueryWrapper<StockAInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("stock_identity", code);
            stock = this.getOne(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return stock;
    }

    @Override
    public List<StockAInfo> searchIndustryList(String code) {
        StockAInfo stock = this.findByStockIdentity(code);
        String industry = stock.getIndustry();
        QueryWrapper<StockAInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("industry",industry);
        List<StockAInfo> list = this.list(queryWrapper);
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<String> sortByValues(String code) {
        List<StockAInfo> list = this.searchIndustryList(code);
//        Console.log(list.size());
        Map<String, Long> map = Maps.newHashMap();
        list.forEach(k -> {
//            Console.log(k.getStockIdentity() + "==" + k.getMarketValue());
            map.put(k.getStockIdentity(), Long.parseLong(k.getMarketValue()));
        });
        List<Map.Entry<String, Long>> entryList = Lists.newArrayList(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
//        entryList.forEach(Console::log);
        List<String> resList = entryList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
        Collections.reverse(resList);
//        resList.forEach(Console::log);
        return resList;
    }


    public static String getMarketValue(String code) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String end = formatter.format(date);
        DateTime datetime = DateUtil.lastWeek();
        String start = formatter.format(datetime);
        String api_name = "daily_basic";
        String token = TOKEN;
        String url = HTTP_API_TUSHARE_PRO;
        JSONObject params = new JSONObject();
        params.put("ts_code", code);
        params.put("start_date", start);
        params.put("end_date", end);
        JSONObject requestJson = JSONUtil.createObj();
        requestJson.put("api_name", api_name);
        requestJson.put("token", token);
        requestJson.put("params", params);
        requestJson.put("fields", "");
        Console.log(requestJson.toString());
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.postForObject(url, requestJson, JSONObject.class);
        JSONObject dataJson = (JSONObject) json.get("data");
        JSONArray array = (JSONArray) dataJson.get("items");
        JSONArray resArray = (JSONArray) array.get(0);

        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
// 不使用千分位，即展示为11672283.234，而不是11,672,283.234
        nf.setGroupingUsed(false);
// 设置数的小数部分所允许的最小位数
        nf.setMinimumFractionDigits(0);
// 设置数的小数部分所允许的最大位数
        nf.setMaximumFractionDigits(5);

        Double marketValue = (Double) resArray.get(16) * 10000;
        String res = nf.format(marketValue);
        return res;
    }

    public static String formatToJson(String region) {
        int level = 0;
        StringBuffer preBuffer = new StringBuffer();
        for (int i = 0; i < region.length(); i++) {
            char c = region.charAt(i);
            if (level > 0 && '\n' == preBuffer.charAt(preBuffer.length() - 1)) {
                preBuffer.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    preBuffer.append(c + "\n");
                    level++;
                    break;
                case ',':
                    preBuffer.append(c + "\n");
                    break;
                case '}':
                case ']':
                    preBuffer.append("\n");
                    level--;
                    preBuffer.append(getLevelStr(level));
                    preBuffer.append(c);
                    break;
                default:
                    preBuffer.append(c);
                    break;
            }
        }

        return String.valueOf(preBuffer);

    }

    private static String getLevelStr(int level) {
        StringBuffer lb = new StringBuffer();
        for (int levelTmp = 0; levelTmp < level; levelTmp++) {
            lb.append("\t");
        }
        return String.valueOf(lb);
    }
}
