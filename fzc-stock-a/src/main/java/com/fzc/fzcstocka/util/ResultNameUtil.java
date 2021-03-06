package com.fzc.fzcstocka.util;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 10:44
 */

//  ('3ee3a4ff-a2cf-4244-8f45-c319016ee16b', '现金流肖像',    '根据经营现金流,投资现金流,筹资现金流的情况为企业绘制画像',    analyzer_stock_portrait),
//  ('7e132f82-a28e-4aa9-aaa6-81fa3692b10c', '货币资金分析',  '分析货币资金，详见excel中的对应的ID行',                   analyzer_check_monetary_fund),
//  ('7b0478d3-1e15-4bce-800c-6f89ee743600', '应收预付分析',  '分析应收款和预付款，详见excel中的对应的ID行',              analyzer_check_receivable_and_prepaid),
//  ('fff6c3cf-a6e5-4fa2-9dce-7d0566b581a1', '资产构成分析',  '净资产，商誉，在建工程等项目分析，详见excel中的对应的ID行',   analyzer_asset_composition),
//  ('d2ced262-7a03-4428-9220-3d4a2a8fe201', '利润表分析',    '分析利润，费用以及它们的构成，详见excel中的对应的ID行',      analyzer_income_statement),
//    a460a24e-2261-4ed1-8c42-84025e495bed,流动比速动比
//    fb82909b-c7a3-427a-8bea-880c48c25027,总资产收益率

public class ResultNameUtil {

    public static Map<String, String> resultNameMap;

    public static String getResultName(String resultId) {
        return resultNameMap.get(resultId);
    }

    public static void setResultNameMap(Map<String, String> resultNameMap) {
        ResultNameUtil.resultNameMap = resultNameMap;
    }

    @PostConstruct
    public void init() {
        resultNameMap.put("cf",ResultNameUtil.getCFId());
        resultNameMap.put("mf",ResultNameUtil.getMFId());
        resultNameMap.put("pr",ResultNameUtil.getPRId());
        resultNameMap.put("ac",ResultNameUtil.getACId());
        resultNameMap.put("is",ResultNameUtil.getISId());
        resultNameMap.put("ls",ResultNameUtil.getLSId());
        resultNameMap.put("ta",ResultNameUtil.getTAId());
    }
    /**
     * 现金流肖像
     * @return
     */
    public static String getCFId(){
        return "3ee3a4ff-a2cf-4244-8f45-c319016ee16b";
    }

    /**
     * 货币资金分析
     * @return
     */
    public static String getMFId(){
        return "7e132f82-a28e-4aa9-aaa6-81fa3692b10c";
    }

    /**
     * 应收预付分析
     * @return
     */
    public static String getPRId(){
        return "7b0478d3-1e15-4bce-800c-6f89ee743600";
    }

    /**
     * 资产构成分析
     * @return
     */
    public static String getACId(){
        return "fff6c3cf-a6e5-4fa2-9dce-7d0566b581a1";
    }

    /**
     * 利润表分析
     * @return
     */
    public static String getISId(){
        return "d2ced262-7a03-4428-9220-3d4a2a8fe201";
    }


    public static String getLSId(){
        return "a460a24e-2261-4ed1-8c42-84025e495bed";
    }

    public static String getTAId(){
        return "fb82909b-c7a3-427a-8bea-880c48c25027";
    }
}
