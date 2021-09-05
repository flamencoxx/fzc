package com.fzc.fzcstockus.servcie.Impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.config.RedisConfig;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.*;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockBasicFinancialService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/7/25 10:07
 */
@Service
@Slf4j
public class StockBasicFinancialServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockBasicFinancialService {

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Autowired
    private StockBasicFinancialService stockBasicFinancialService;


    @Override
//    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'basicFinancial:updata:'+#symbol")
    public void updateStockBasicFinancial(String symbol) {

        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        String url = "https://finnhub.io/api/v1/stock/metric?symbol="+ symbol +"&metric=all&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        JSONObject metricJson = (JSONObject) json.get("metric");

//            JSONObject seriesJson = (JSONObject) json.get("series");
//            if (seriesJson.isEmpty()){
//
//            }
//            JSONObject annualJson = (JSONObject) seriesJson.get("annual");

//            System.out.println(metricJson.toString());
//            System.out.println(json.toString());

        BasicFinancials basicFinancials = new BasicFinancials();

        Metric metric = new Metric();

//            MetricString
        String TenDayAverageTradingVolume = metricJson.getStr("10DayAverageTradingVolume");
        String thirteenWeekPriceReturnDaily = metricJson.getStr("13WeekPriceReturnDaily");
        String TwentySixWeekPriceReturnDaily = metricJson.getStr("26WeekPriceReturnDaily");
        String ThreeMonthAverageTradingVolume = metricJson.getStr("3MonthAverageTradingVolume");
        String FiftyTwoWeekHigh = metricJson.getStr("52WeekHigh");
        String FiftyTwoWeekHighDate = metricJson.getStr("52WeekHighDate");
        String FiftyTwoWeekLow = metricJson.getStr("52WeekLow");
        String FiftyTwoWeekLowDate = metricJson.getStr("52WeekLowDate");
        String FiftyTwoWeekPriceReturnDaily = metricJson.getStr("52WeekPriceReturnDaily");
        String FifyDayPriceReturnDaily = metricJson.getStr("5DayPriceReturnDaily");
        String assetTurnoverAnnual = metricJson.getStr("assetTurnoverAnnual");
        String assetTurnoverTTM = metricJson.getStr("assetTurnoverTTM");
        String beta = metricJson.getStr("beta");
        String bookValuePerShareAnnual = metricJson.getStr("bookValuePerShareAnnual");
        String bookValuePerShareQuarterly = metricJson.getStr("bookValuePerShareQuarterly");
        String bookValueShareGrowth5Y = metricJson.getStr("bookValueShareGrowth5Y");
        String capitalSpendingGrowth5Y = metricJson.getStr("capitalSpendingGrowth5Y");
        String cashFlowPerShareAnnual = metricJson.getStr("cashFlowPerShareAnnual");
        String cashFlowPerShareTTM = metricJson.getStr("cashFlowPerShareTTM");
        String cashPerSharePerShareAnnual = metricJson.getStr("cashPerSharePerShareAnnual");
        String cashPerSharePerShareQuarterly = metricJson.getStr("cashPerSharePerShareQuarterly");
        String currentDividendYieldTTM = metricJson.getStr("currentDividendYieldTTM");
        String currentEvDivisionfreeCashFlowAnnual = metricJson.getStr("currentEv/freeCashFlowAnnual");
        String currentEvDivisionfreeCashFlowTTM = metricJson.getStr("currentEv/freeCashFlowTTM");
        String currentRatioAnnual = metricJson.getStr("currentRatioAnnual");
        String currentRatioQuarterly = metricJson.getStr("currentRatioQuarterly");
        String dividendGrowthRate5Y = metricJson.getStr("dividendGrowthRate5Y");
        String dividendPerShare5Y = metricJson.getStr("dividendPerShare5Y");
        String dividendPerShareAnnual = metricJson.getStr("dividendPerShareAnnual");
        String dividendYield5Y = metricJson.getStr("dividendYield5Y");
        String dividendYieldIndicatedAnnual = metricJson.getStr("dividendYieldIndicatedAnnual");
        String dividendsPerShareTTM = metricJson.getStr("dividendsPerShareTTM");
        String ebitdPerShareTTM = metricJson.getStr("ebitdPerShareTTM");
        String ebitdaCagr5Y = metricJson.getStr("ebitdaCagr5Y");
        String ebitdaStringerimCagr5Y = metricJson.getStr("ebitdaInterimCagr5Y");
        String epsBasicExclExtraItemsAnnual = metricJson.getStr("epsBasicExclExtraItemsAnnual");
        String epsBasicExclExtraItemsTTM = metricJson.getStr("epsBasicExclExtraItemsTTM");
        String epsExclExtraItemsAnnual = metricJson.getStr("epsExclExtraItemsAnnual");
        String epsExclExtraItemsTTM = metricJson.getStr("epsExclExtraItemsTTM");
        String epsGrowth3Y = metricJson.getStr("epsGrowth3Y");
        String epsGrowth5Y = metricJson.getStr("epsGrowth5Y");
        String epsGrowthQuarterlyYoy = metricJson.getStr("epsGrowthQuarterlyYoy");
        String epsGrowthTTMYoy = metricJson.getStr("epsGrowthTTMYoy");
        String epsInclExtraItemsAnnual = metricJson.getStr("epsInclExtraItemsAnnual");
        String epsInclExtraItemsTTM = metricJson.getStr("epsInclExtraItemsTTM");
        String epsNormalizedAnnual = metricJson.getStr("epsNormalizedAnnual");
        String focfCagr5Y = metricJson.getStr("focfCagr5Y");
        String freeCashFlowAnnual = metricJson.getStr("freeCashFlowAnnual");
        String freeCashFlowPerShareTTM = metricJson.getStr("freeCashFlowPerShareTTM");
        String freeCashFlowTTM = metricJson.getStr("freeCashFlowTTM");
        String freeOperatingCashFlowDivisionrevenue5Y = metricJson.getStr("freeOperatingCashFlow/revenue5Y");
        String freeOperatingCashFlowDivisionrevenueTTM = metricJson.getStr("freeOperatingCashFlow/revenueTTM");
        String grossMargin5Y = metricJson.getStr("grossMargin5Y");
        String grossMarginAnnual = metricJson.getStr("grossMarginAnnual");
        String grossMargStringTM = metricJson.getStr("grossMarginTTM");
        String inventoryTurnoverAnnual = metricJson.getStr("inventoryTurnoverAnnual");
        String inventoryTurnoverTTM = metricJson.getStr("inventoryTurnoverTTM");
        String longTermDebtDivisionequityAnnual = metricJson.getStr("longTermDebt/equityAnnual");
        String longTermDebtDivisionequityQuarterly = metricJson.getStr("longTermDebt/equityQuarterly");
        String marketCapitalization = metricJson.getStr("marketCapitalization");
        String monthToDatePriceReturnDaily = metricJson.getStr("monthToDatePriceReturnDaily");
        String netDebtAnnual = metricJson.getStr("netDebtAnnual");
        String netDebtStringerim = metricJson.getStr("netDebtInterim");
        String netIncomeEmployeeAnnual = metricJson.getStr("netIncomeEmployeeAnnual");
        String netIncomeEmployeeTTM = metricJson.getStr("netIncomeEmployeeTTM");
        String netStringerestCoverageAnnual = metricJson.getStr("netInterestCoverageAnnual");
        String netStringerestCoverageTTM = metricJson.getStr("netInterestCoverageTTM");
        String netMarginGrowth5Y = metricJson.getStr("netMarginGrowth5Y");
        String netProfitMargin5Y = metricJson.getStr("netProfitMargin5Y");
        String netProfitMarginAnnual = metricJson.getStr("netProfitMarginAnnual");
        String netProfitMargStringTM = metricJson.getStr("netProfitMarginTTM");
        String operatingMargin5Y = metricJson.getStr("operatingMargin5Y");
        String operatingMarginAnnual = metricJson.getStr("operatingMarginAnnual");
        String operatingMargStringTM = metricJson.getStr("operatingMarginTTM");
        String payoutRatioAnnual = metricJson.getStr("payoutRatioAnnual");
        String payoutRatioTTM = metricJson.getStr("payoutRatioTTM");
        String pbAnnual = metricJson.getStr("pbAnnual");
        String pbQuarterly = metricJson.getStr("pbQuarterly");
        String pcfShareTTM = metricJson.getStr("pcfShareTTM");
        String peBasicExclExtraTTM = metricJson.getStr("peBasicExclExtraTTM");
        String peExclExtraAnnual = metricJson.getStr("peExclExtraAnnual");
        String peExclExtraHighTTM = metricJson.getStr("peExclExtraHighTTM");
        String peExclExtraTTM = metricJson.getStr("peExclExtraTTM");
        String peExclLowTTM = metricJson.getStr("peExclLowTTM");
        String peInclExtraTTM = metricJson.getStr("peInclExtraTTM");
        String peNormalizedAnnual = metricJson.getStr("peNormalizedAnnual");
        String pfcfShareAnnual = metricJson.getStr("pfcfShareAnnual");
        String pfcfShareTTM = metricJson.getStr("pfcfShareTTM");
        String pretaxMargin5Y = metricJson.getStr("pretaxMargin5Y");
        String pretaxMarginAnnual = metricJson.getStr("pretaxMarginAnnual");
        String pretaxMargStringTM = metricJson.getStr("pretaxMarginTTM");
        String priceRelativeToSAndP50013Week = metricJson.getStr("priceRelativeToS&P50013Week");
        String priceRelativeToSAndP50026Week = metricJson.getStr("priceRelativeToS&P50026Week");
        String priceRelativeToSAndP5004Week = metricJson.getStr("priceRelativeToS&P5004Week");
        String priceRelativeToSAndP50052Week = metricJson.getStr("priceRelativeToS&P50052Week");
        String priceRelativeToSAndP500Ytd = metricJson.getStr("priceRelativeToS&P500Ytd");
        String psAnnual = metricJson.getStr("psAnnual");
        String psTTM = metricJson.getStr("psTTM");
        String ptbvAnnual = metricJson.getStr("ptbvAnnual");
        String ptbvQuarterly = metricJson.getStr("ptbvQuarterly");
        String quickRatioAnnual = metricJson.getStr("quickRatioAnnual");
        String quickRatioQuarterly = metricJson.getStr("quickRatioQuarterly");
        String receivablesTurnoverAnnual = metricJson.getStr("receivablesTurnoverAnnual");
        String receivablesTurnoverTTM = metricJson.getStr("receivablesTurnoverTTM");
        String revenueEmployeeAnnual = metricJson.getStr("revenueEmployeeAnnual");
        String revenueEmployeeTTM = metricJson.getStr("revenueEmployeeTTM");
        String revenueGrowth3Y = metricJson.getStr("revenueGrowth3Y");
        String revenueGrowth5Y = metricJson.getStr("revenueGrowth5Y");
        String revenueGrowthQuarterlyYoy = metricJson.getStr("revenueGrowthQuarterlyYoy");
        String revenueGrowthTTMYoy = metricJson.getStr("revenueGrowthTTMYoy");
        String revenuePerShareAnnual = metricJson.getStr("revenuePerShareAnnual");
        String revenuePerShareTTM = metricJson.getStr("revenuePerShareTTM");
        String revenueShareGrowth5Y = metricJson.getStr("revenueShareGrowth5Y");
        String roaRfy = metricJson.getStr("roaRfy");
        String roaa5Y = metricJson.getStr("roaa5Y");
        String roae5Y = metricJson.getStr("roae5Y");
        String roaeTTM = metricJson.getStr("roaeTTM");
        String roeRfy = metricJson.getStr("roeRfy");
        String roeTTM = metricJson.getStr("roeTTM");
        String roi5Y = metricJson.getStr("roi5Y");
        String roiAnnual = metricJson.getStr("roiAnnual");
        String roiTTM = metricJson.getStr("roiTTM");
        String tangibleBookValuePerShareAnnual = metricJson.getStr("tangibleBookValuePerShareAnnual");
        String tangibleBookValuePerShareQuarterly = metricJson.getStr("tangibleBookValuePerShareQuarterly");
        String tbvCagr5Y = metricJson.getStr("tbvCagr5Y");
        String totalDebtDivisiontotalEquityAnnual = metricJson.getStr("totalDebt/totalEquityAnnual");
        String totalDebtDivisiontotalEquityQuarterly = metricJson.getStr("totalDebt/totalEquityQuarterly");
        String totalDebtCagr5Y = metricJson.getStr("totalDebtCagr5Y");
        String yearToDatePriceReturnDaily = metricJson.getStr("yearToDatePriceReturnDaily");




//            MetricSet
        metric.setTenDayAverageTradingVolume(TenDayAverageTradingVolume);
        metric.setThirteenWeekPriceReturnDaily(thirteenWeekPriceReturnDaily);
        metric.setTwentySixWeekPriceReturnDaily(TwentySixWeekPriceReturnDaily);
        metric.setThreeMonthAverageTradingVolume(ThreeMonthAverageTradingVolume);
        metric.setFiftyTwoWeekHigh(FiftyTwoWeekHigh);
        metric.setFiftyTwoWeekHighDate(FiftyTwoWeekHighDate);
        metric.setFiftyTwoWeekLow(FiftyTwoWeekLow);
        metric.setFiftyTwoWeekLowDate(FiftyTwoWeekLowDate);
        metric.setFiftyTwoWeekPriceReturnDaily(FiftyTwoWeekPriceReturnDaily);
        metric.setFifyDayPriceReturnDaily(FifyDayPriceReturnDaily);
        metric.setAssetTurnoverAnnual(assetTurnoverAnnual);
        metric.setAssetTurnoverTTM(assetTurnoverTTM);
        metric.setBeta(beta);
        metric.setBookValuePerShareAnnual(bookValuePerShareAnnual);
        metric.setBookValuePerShareQuarterly(bookValuePerShareQuarterly);
        metric.setBookValueShareGrowth5Y(bookValueShareGrowth5Y);
        metric.setCapitalSpendingGrowth5Y(capitalSpendingGrowth5Y);
        metric.setCashFlowPerShareAnnual(cashFlowPerShareAnnual);
        metric.setCashFlowPerShareTTM(cashFlowPerShareTTM);
        metric.setCashPerSharePerShareAnnual(cashPerSharePerShareAnnual);
        metric.setCashPerSharePerShareQuarterly(cashPerSharePerShareQuarterly);
        metric.setCurrentDividendYieldTTM(currentDividendYieldTTM);
        metric.setCurrentEvDivisionfreeCashFlowAnnual(currentEvDivisionfreeCashFlowAnnual);
        metric.setCurrentEvDivisionfreeCashFlowTTM(currentEvDivisionfreeCashFlowTTM);
        metric.setCurrentRatioAnnual(currentRatioAnnual);
        metric.setCurrentRatioQuarterly(currentRatioQuarterly);
        metric.setDividendGrowthRate5Y(dividendGrowthRate5Y);
        metric.setDividendPerShare5Y(dividendPerShare5Y);
        metric.setDividendPerShareAnnual(dividendPerShareAnnual);
        metric.setDividendYield5Y(dividendYield5Y);
        metric.setDividendYieldIndicatedAnnual(dividendYieldIndicatedAnnual);
        metric.setDividendsPerShareTTM(dividendsPerShareTTM);
        metric.setEbitdPerShareTTM(ebitdPerShareTTM);
        metric.setEbitdaCagr5Y(ebitdaCagr5Y);
        metric.setEbitdaStringerimCagr5Y(ebitdaStringerimCagr5Y);
        metric.setEpsBasicExclExtraItemsAnnual(epsBasicExclExtraItemsAnnual);
        metric.setEpsBasicExclExtraItemsTTM(epsBasicExclExtraItemsTTM);
        metric.setEpsExclExtraItemsAnnual(epsExclExtraItemsAnnual);
        metric.setEpsExclExtraItemsTTM(epsExclExtraItemsTTM);
        metric.setEpsGrowth3Y(epsGrowth3Y);
        metric.setEpsGrowth5Y(epsGrowth5Y);
        metric.setEpsGrowthQuarterlyYoy(epsGrowthQuarterlyYoy);
        metric.setEpsGrowthTTMYoy(epsGrowthTTMYoy);
        metric.setEpsInclExtraItemsAnnual(epsInclExtraItemsAnnual);
        metric.setEpsInclExtraItemsTTM(epsInclExtraItemsTTM);
        metric.setEpsNormalizedAnnual(epsNormalizedAnnual);
        metric.setFocfCagr5Y(focfCagr5Y);
        metric.setFreeCashFlowAnnual(freeCashFlowAnnual);
        metric.setFreeCashFlowTTM(freeCashFlowTTM);
        metric.setFreeCashFlowPerShareTTM(freeCashFlowPerShareTTM);
        metric.setFreeOperatingCashFlowDivisionrevenue5Y(freeOperatingCashFlowDivisionrevenue5Y);
        metric.setFreeOperatingCashFlowDivisionrevenueTTM(freeOperatingCashFlowDivisionrevenueTTM);
        metric.setGrossMargin5Y(grossMargin5Y);
        metric.setGrossMarginAnnual(grossMarginAnnual);
        metric.setGrossMargStringTM(grossMargStringTM);
        metric.setInventoryTurnoverAnnual(inventoryTurnoverAnnual);
        metric.setInventoryTurnoverTTM(inventoryTurnoverTTM);
        metric.setLongTermDebtDivisionequityAnnual(longTermDebtDivisionequityAnnual);
        metric.setLongTermDebtDivisionequityQuarterly(longTermDebtDivisionequityQuarterly);
        metric.setMarketCapitalization(marketCapitalization);
        metric.setMonthToDatePriceReturnDaily(monthToDatePriceReturnDaily);
        metric.setNetDebtAnnual(netDebtAnnual);
        metric.setNetDebtAnnual(netDebtAnnual);
        metric.setNetDebtStringerim(netDebtStringerim);
        metric.setNetIncomeEmployeeAnnual(netIncomeEmployeeAnnual);
        metric.setNetIncomeEmployeeTTM(netIncomeEmployeeTTM);
        metric.setNetStringerestCoverageAnnual(netStringerestCoverageAnnual);
        metric.setNetStringerestCoverageTTM(netStringerestCoverageTTM);
        metric.setNetMarginGrowth5Y(netMarginGrowth5Y);
        metric.setNetProfitMargin5Y(netProfitMargin5Y);
        metric.setNetProfitMarginAnnual(netProfitMarginAnnual);
        metric.setNetProfitMargStringTM(netProfitMargStringTM);
        metric.setOperatingMargin5Y(operatingMargin5Y);
        metric.setOperatingMarginAnnual(operatingMarginAnnual);
        metric.setOperatingMargStringTM(operatingMargStringTM);
        metric.setPayoutRatioAnnual(payoutRatioAnnual);
        metric.setPayoutRatioTTM(payoutRatioTTM);
        metric.setPbAnnual(pbAnnual);
        metric.setPbQuarterly(pbQuarterly);
        metric.setPcfShareTTM(pcfShareTTM);
        metric.setPeBasicExclExtraTTM(peBasicExclExtraTTM);
        metric.setPeExclExtraAnnual(peExclExtraAnnual);
        metric.setPeExclExtraHighTTM(peExclExtraHighTTM);
        metric.setPeExclExtraTTM(peExclExtraTTM);
        metric.setPeExclLowTTM(peExclLowTTM);
        metric.setPeInclExtraTTM(peInclExtraTTM);
        metric.setPeNormalizedAnnual(peNormalizedAnnual);
        metric.setPfcfShareAnnual(pfcfShareAnnual);
        metric.setPfcfShareTTM(pfcfShareTTM);
        metric.setPretaxMargin5Y(pretaxMargin5Y);
        metric.setPretaxMarginAnnual(pretaxMarginAnnual);
        metric.setPretaxMargStringTM(pretaxMargStringTM);
        metric.setPriceRelativeToSAndP50013Week(priceRelativeToSAndP50013Week);
        metric.setPriceRelativeToSAndP50026Week(priceRelativeToSAndP50026Week);
        metric.setPriceRelativeToSAndP5004Week(priceRelativeToSAndP5004Week);
        metric.setPriceRelativeToSAndP50052Week(priceRelativeToSAndP50052Week);
        metric.setPriceRelativeToSAndP500Ytd(priceRelativeToSAndP500Ytd);
        metric.setPsAnnual(psAnnual);
        metric.setPsTTM(psTTM);
        metric.setPtbvAnnual(ptbvAnnual);
        metric.setPtbvQuarterly(ptbvQuarterly);
        metric.setQuickRatioAnnual(quickRatioAnnual);
        metric.setQuickRatioQuarterly(quickRatioQuarterly);
        metric.setReceivablesTurnoverAnnual(receivablesTurnoverAnnual);
        metric.setReceivablesTurnoverTTM(receivablesTurnoverTTM);
        metric.setRevenueEmployeeAnnual(revenueEmployeeAnnual);
        metric.setRevenueEmployeeTTM(revenueEmployeeTTM);
        metric.setRevenueGrowth3Y(revenueGrowth3Y);
        metric.setRevenueGrowth5Y(revenueGrowth5Y);
        metric.setRevenueGrowthQuarterlyYoy(revenueGrowthQuarterlyYoy);
        metric.setRevenueGrowthTTMYoy(revenueGrowthTTMYoy);
        metric.setRevenuePerShareAnnual(revenuePerShareAnnual);
        metric.setRevenuePerShareTTM(revenuePerShareTTM);
        metric.setRevenueShareGrowth5Y(revenueShareGrowth5Y);
        metric.setRoaRfy(roaRfy);
        metric.setRoaa5Y(roaa5Y);
        metric.setRoae5Y(roae5Y);
        metric.setRoaeTTM(roaeTTM);
        metric.setRoeRfy(roeRfy);
        metric.setRoeTTM(roeTTM);
        metric.setRoi5Y(roi5Y);
        metric.setRoiAnnual(roiAnnual);
        metric.setRoiTTM(roiTTM);
        metric.setTangibleBookValuePerShareAnnual(tangibleBookValuePerShareAnnual);
        metric.setTangibleBookValuePerShareQuarterly(tangibleBookValuePerShareQuarterly);
        metric.setTbvCagr5Y(tbvCagr5Y);
        metric.setTotalDebtDivisiontotalEquityAnnual(totalDebtDivisiontotalEquityAnnual);
        metric.setTotalDebtDivisiontotalEquityQuarterly(totalDebtDivisiontotalEquityQuarterly);
        metric.setTotalDebtCagr5Y(totalDebtCagr5Y);
        metric.setYearToDatePriceReturnDaily(yearToDatePriceReturnDaily);


//            BasicFinancial set

        basicFinancials.setMetric(metric);
        basicFinancials.setMetricType(json.getStr("metricType"));
        basicFinancials.setSymbol(json.getStr("symbol"));

        JSONObject seriesJson = (JSONObject) json.get("series");
        if (seriesJson.isEmpty()){
            basicFinancials.setAnnual(null);
            stock.setBasicFinancials(basicFinancials);
            stockUsInfoDoRepository.save(stock);
        }



        JSONObject annualJson = (JSONObject) seriesJson.get("annual");


//            所有jsonArray
        JSONArray cashRatioJsonArray = annualJson.getJSONArray("cashRatio");
        JSONArray currentRatioJsonArray = annualJson.getJSONArray("currentRatio");
        JSONArray ebitPerShareJsonArray = annualJson.getJSONArray("ebitPerShare");
        JSONArray epsJsonArray = annualJson.getJSONArray("eps");
        JSONArray grossMarginJsonArray = annualJson.getJSONArray("grossMargin");
        JSONArray longtermDebtTotalAssetJsonArray = annualJson.getJSONArray("longtermDebtTotalAsset");
        JSONArray longtermDebtTotalCapitalJsonArray = annualJson.getJSONArray("longtermDebtTotalCapital");
        JSONArray longtermDebtTotalEquityJsonArray = annualJson.getJSONArray("longtermDebtTotalEquity");
        JSONArray netDebtToTotalCapitalJsonArray = annualJson.getJSONArray("netDebtToTotalCapital");
        JSONArray netDebtToTotalEquityJsonArray = annualJson.getJSONArray("netDebtToTotalEquity");
        JSONArray netMarginJsonArray = annualJson.getJSONArray("netMargin");
        JSONArray operatingMarginJsonArray = annualJson.getJSONArray("operatingMargin");
        JSONArray pretaxMarginJsonArray = annualJson.getJSONArray("pretaxMargin");
        JSONArray salesPerShareJsonArray = annualJson.getJSONArray("salesPerShare");
        JSONArray sgaToSaleJsonArray = annualJson.getJSONArray("sgaToSale");
        JSONArray totalDebtToEquityJsonArray = annualJson.getJSONArray("totalDebtToEquity");
        JSONArray totalDebtToTotalAssetJsonArray = annualJson.getJSONArray("totalDebtToTotalAsset");
        JSONArray totalDebtToTotalCapitalJsonArray = annualJson.getJSONArray("totalDebtToTotalCapital");
        JSONArray totalRatioJsonArray = annualJson.getJSONArray("totalRatio");


//            list创建
        List<CashRatio> cashRatioList = new ArrayList<CashRatio>();
        List<CurrentRatio> currentRatioList = new ArrayList<CurrentRatio>();
        List<EbitPerShare> ebitPerShareList = new ArrayList<EbitPerShare>();
        List<Eps> epsList = new ArrayList<Eps>();
        List<GrossMargin> grossMarginList = new ArrayList<GrossMargin>();
        List<LongtermDebtTotalAsset> longtermDebtTotalAssetList = new ArrayList<LongtermDebtTotalAsset>();
        List<LongtermDebtTotalCapital> longtermDebtTotalCapitalList = new ArrayList<LongtermDebtTotalCapital>();
        List<LongtermDebtTotalEquity> longtermDebtTotalEquityList = new ArrayList<LongtermDebtTotalEquity>();
        List<NetDebtToTotalCapital> netDebtToTotalCapitalList = new ArrayList<NetDebtToTotalCapital>();
        List<NetDebtToTotalEquity> netDebtToTotalEquityList = new ArrayList<NetDebtToTotalEquity>();
        List<NetMargin> netMarginList = new ArrayList<NetMargin>();
        List<OperatingMargin> operatingMarginList = new ArrayList<OperatingMargin>();
        List<PretaxMargin> pretaxMarginList = new ArrayList<PretaxMargin>();
        List<SalesPerShare> salesPerShareList = new ArrayList<SalesPerShare>();
        List<SgaToSale> sgaToSaleList = new ArrayList<SgaToSale>();
        List<TotalDebtToEquity> totalDebtToEquityList = new ArrayList<TotalDebtToEquity>();
        List<TotalDebtToTotalAsset> totalDebtToTotalAssetList = new ArrayList<TotalDebtToTotalAsset>();
        List<TotalDebtToTotalCapital> totalDebtToTotalCapitalList = new ArrayList<TotalDebtToTotalCapital>();
        List<TotalRatio> totalRatioList = new ArrayList<TotalRatio>();


//            用for循环jsonArray
        for(JSONObject jsonObject: cashRatioJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            CashRatio cashRatio = new CashRatio(period,v);
            cashRatioList.add(cashRatio);
        }


        for(JSONObject jsonObject: currentRatioJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            CurrentRatio currentRatio = new CurrentRatio(period,v);
            currentRatioList.add(currentRatio);
        }


        if(ebitPerShareJsonArray == null){
            EbitPerShare ebitPerShare = new EbitPerShare();
            ebitPerShareList.add(ebitPerShare);
        }else{

            for(JSONObject jsonObject: ebitPerShareJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                EbitPerShare ebitPerShare = new EbitPerShare(period,v);
                ebitPerShareList.add(ebitPerShare);
            }
        }


//            for(JSONObject jsonObject: ebitPerShareJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                EbitPerShare ebitPerShare = new EbitPerShare(period,v);
//                ebitPerShareList.add(ebitPerShare);
//            }


        if(epsJsonArray == null){
            Eps eps = new Eps();
            epsList.add(eps);
        }else{
            for(JSONObject jsonObject: epsJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                Eps eps = new Eps(period,v);
                epsList.add(eps);
            }
        }

//            for(JSONObject jsonObject: epsJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                Eps eps = new Eps(period,v);
//                epsList.add(eps);
//            }


        if(grossMarginJsonArray == null){
            GrossMargin grossMargin = new GrossMargin();
            grossMarginList.add(grossMargin);
        }else{
            for(JSONObject jsonObject: grossMarginJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                GrossMargin grossMargin = new GrossMargin(period,v);
                grossMarginList.add(grossMargin);
            }
        }

//            for(JSONObject jsonObject: grossMarginJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                GrossMargin grossMargin = new GrossMargin(period,v);
//                grossMarginList.add(grossMargin);
//            }


        for(JSONObject jsonObject: longtermDebtTotalAssetJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            LongtermDebtTotalAsset longtermDebtTotalAsset = new LongtermDebtTotalAsset(period,v);
            longtermDebtTotalAssetList.add(longtermDebtTotalAsset);
        }


        for(JSONObject jsonObject: longtermDebtTotalCapitalJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            LongtermDebtTotalCapital longtermDebtTotalCapital = new LongtermDebtTotalCapital(period,v);
            longtermDebtTotalCapitalList.add(longtermDebtTotalCapital);
        }


        for(JSONObject jsonObject: longtermDebtTotalEquityJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            LongtermDebtTotalEquity longtermDebtTotalEquity = new LongtermDebtTotalEquity(period,v);
            longtermDebtTotalEquityList.add(longtermDebtTotalEquity);
        }


        for(JSONObject jsonObject: netDebtToTotalCapitalJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            NetDebtToTotalCapital netDebtToTotalCapital = new NetDebtToTotalCapital(period,v);
            netDebtToTotalCapitalList.add(netDebtToTotalCapital);
        }


        for(JSONObject jsonObject: netDebtToTotalEquityJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            NetDebtToTotalEquity netDebtToTotalEquity = new NetDebtToTotalEquity(period,v);
            netDebtToTotalEquityList.add(netDebtToTotalEquity);
        }


        if(netMarginJsonArray == null){
            NetMargin netMargin = new NetMargin();
            netMarginList.add(netMargin);
        }else{
            for(JSONObject jsonObject: netMarginJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                NetMargin netMargin = new NetMargin(period,v);
                netMarginList.add(netMargin);
            }
        }

//            for(JSONObject jsonObject: netMarginJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                NetMargin netMargin = new NetMargin(period,v);
//                netMarginList.add(netMargin);
//            }


        if(operatingMarginJsonArray == null){
            OperatingMargin operatingMargin = new OperatingMargin();
            operatingMarginList.add(operatingMargin);
        }else{
            for(JSONObject jsonObject: operatingMarginJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                OperatingMargin operatingMargin = new OperatingMargin(period,v);
                operatingMarginList.add(operatingMargin);
            }
        }

//            for(JSONObject jsonObject: operatingMarginJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                OperatingMargin operatingMargin = new OperatingMargin(period,v);
//                operatingMarginList.add(operatingMargin);
//            }

        if(pretaxMarginJsonArray == null){
            PretaxMargin pretaxMargin = new PretaxMargin();
            pretaxMarginList.add(pretaxMargin);
        }else{
            for(JSONObject jsonObject: pretaxMarginJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                PretaxMargin pretaxMargin = new PretaxMargin(period,v);
                pretaxMarginList.add(pretaxMargin);
            }
        }

//            for(JSONObject jsonObject: pretaxMarginJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                PretaxMargin pretaxMargin = new PretaxMargin(period,v);
//                pretaxMarginList.add(pretaxMargin);
//            }

        if(salesPerShareJsonArray == null){
            SalesPerShare salesPerShare = new SalesPerShare();
            salesPerShareList.add(salesPerShare);
        }else{
            for(JSONObject jsonObject: salesPerShareJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                SalesPerShare salesPerShare = new SalesPerShare(period,v);
                salesPerShareList.add(salesPerShare);
            }
        }

//            for(JSONObject jsonObject: salesPerShareJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                SalesPerShare salesPerShare = new SalesPerShare(period,v);
//                salesPerShareList.add(salesPerShare);
//            }



        if(sgaToSaleJsonArray == null){
            SgaToSale sgaToSale = new SgaToSale();
            sgaToSaleList.add(sgaToSale);
        }else{
            for(JSONObject jsonObject: sgaToSaleJsonArray.toList(JSONObject.class)){
                String period = jsonObject.getStr("period");
                String v = jsonObject.getStr("v");
                SgaToSale sgaToSale = new SgaToSale(period,v);
                sgaToSaleList.add(sgaToSale);
            }

        }

//            for(JSONObject jsonObject: sgaToSaleJsonArray.toList(JSONObject.class)){
//                String period = jsonObject.getStr("period");
//                String v = jsonObject.getStr("v");
//                SgaToSale sgaToSale = new SgaToSale(period,v);
//                sgaToSaleList.add(sgaToSale);
//            }


        for(JSONObject jsonObject: totalDebtToEquityJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            TotalDebtToEquity totalDebtToEquity = new TotalDebtToEquity(period,v);
            totalDebtToEquityList.add(totalDebtToEquity);
        }


        for(JSONObject jsonObject: totalDebtToTotalAssetJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            TotalDebtToTotalAsset totalDebtToTotalAsset = new TotalDebtToTotalAsset(period,v);
            totalDebtToTotalAssetList.add(totalDebtToTotalAsset);
        }


        for(JSONObject jsonObject: totalDebtToTotalCapitalJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            TotalDebtToTotalCapital totalDebtToTotalCapital = new TotalDebtToTotalCapital(period,v);
            totalDebtToTotalCapitalList.add(totalDebtToTotalCapital);
        }

        for(JSONObject jsonObject: totalRatioJsonArray.toList(JSONObject.class)){
            String period = jsonObject.getStr("period");
            String v = jsonObject.getStr("v");
            TotalRatio totalRatio = new TotalRatio(period,v);
            totalRatioList.add(totalRatio);
        }


        for(CashRatio s: cashRatioList){
//            System.out.println(s);
        }




//            System.out.println(metric.toString());

        Annual annual = new Annual();
        annual.setCashRatio(cashRatioList);
        annual.setCurrentRatio(currentRatioList);
        annual.setEbitPerShare(ebitPerShareList);
        annual.setEps(epsList);
        annual.setGrossMargin(grossMarginList);
        annual.setLongtermDebtTotalAsset(longtermDebtTotalAssetList);
        annual.setLongtermDebtTotalCapital(longtermDebtTotalCapitalList);
        annual.setLongtermDebtTotalEquity(longtermDebtTotalEquityList);
        annual.setNetDebtToTotalCapital(netDebtToTotalCapitalList);
        annual.setNetDebtToTotalEquity(netDebtToTotalEquityList);
        annual.setNetMargin(netMarginList);
        annual.setOperatingMargin(operatingMarginList);
        annual.setPretaxMargin(pretaxMarginList);
        annual.setSalesPerShare(salesPerShareList);
        annual.setSgaToSale(sgaToSaleList);
        annual.setTotalDebtToEquity(totalDebtToEquityList);
        annual.setTotalDebtToTotalAsset(totalDebtToTotalAssetList);
        annual.setTotalDebtToTotalCapital(totalDebtToTotalCapitalList);
        annual.setTotalRatio(totalRatioList);
        basicFinancials.setAnnual(annual);
        stock.setBasicFinancials(basicFinancials);
        stockUsInfoDoRepository.save(stock);

        log.info("[更新美股基本面数据BasicFinancial]:"+symbol + ":"+ stock.getSymbol());
//        System.out.println("现在是"+symbol + ":"+ stock.getSymbol());

        /*try {
            TimeUnit.MILLISECONDS.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public BasicFinancials findBasicFinancialsBySymbol(String symbol) {
        StockUsInfoDo result = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
        if( result != null){
            stockBasicFinancialService.updateStockBasicFinancial(symbol);
            StockUsInfoDo stockUsInfoDo = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
            BasicFinancials basicFinancials = stockUsInfoDo.getBasicFinancials();
            System.out.println("findBasicFinancialsBySymbol:成功执行");
            return basicFinancials;
        }else{
            return null;
        }
    }
}
