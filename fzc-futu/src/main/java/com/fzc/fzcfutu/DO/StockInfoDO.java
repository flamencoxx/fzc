package com.fzc.fzcfutu.DO;

import com.fzc.fzcfutu.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 11615
 */
@Document(collection = "StockInfo")
public class StockInfoDO extends IncIdEntity<Integer> {



    public static class StockHistoricalData{

//        16个参数
//        日期
        private String date;

//        股票代号
        private String stockSymbol;

//        股票名字
        private String name;

//        收盘价
        private Double close;


//        最高价
        private Double high;

//        最低价
        private Double low;

//        开盘价
        private Double open;

//        前收盘
        private Double preClose;


//        涨跌额
        private Double amountUpOrDown;


//        涨跌幅
        private Double upOrDown;

//        换手率
        private Double changeHandsRate;


//        成交量
        private Double volume;


//        成交金额
        private Double transactionAmount;

//        总市值
        private Double marketValue;

//        流通市值
        private Double flowMarketValue;


//        成交笔数
        private String numberOfTransactions;


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStockSymbol() {
            return stockSymbol;
        }

        public void setStockSymbol(String stockSymbol) {
            this.stockSymbol = stockSymbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getClose() {
            return close;
        }

        public void setClose(Double close) {
            this.close = close;
        }

        public Double getHigh() {
            return high;
        }

        public void setHigh(Double high) {
            this.high = high;
        }

        public Double getLow() {
            return low;
        }

        public void setLow(Double low) {
            this.low = low;
        }

        public Double getOpen() {
            return open;
        }

        public void setOpen(Double open) {
            this.open = open;
        }

        public Double getPreClose() {
            return preClose;
        }

        public void setPreClose(Double preClose) {
            this.preClose = preClose;
        }

        public Double getAmountUpOrDown() {
            return amountUpOrDown;
        }

        public void setAmountUpOrDown(Double amountUpOrDown) {
            this.amountUpOrDown = amountUpOrDown;
        }

        public Double getUpOrDown() {
            return upOrDown;
        }

        public void setUpOrDown(Double upOrDown) {
            this.upOrDown = upOrDown;
        }

        public Double getChangeHandsRate() {
            return changeHandsRate;
        }

        public void setChangeHandsRate(Double changeHandsRate) {
            this.changeHandsRate = changeHandsRate;
        }

        public Double getVolume() {
            return volume;
        }

        public void setVolume(Double volume) {
            this.volume = volume;
        }

        public Double getTransactionAmount() {
            return transactionAmount;
        }

        public void setTransactionAmount(Double transactionAmount) {
            this.transactionAmount = transactionAmount;
        }

        public Double getMarketValue() {
            return marketValue;
        }

        public void setMarketValue(Double marketValue) {
            this.marketValue = marketValue;
        }

        public Double getFlowMarketValue() {
            return flowMarketValue;
        }

        public void setFlowMarketValue(Double flowMarketValue) {
            this.flowMarketValue = flowMarketValue;
        }

        public String getNumberOfTransactions() {
            return numberOfTransactions;
        }

        public void setNumberOfTransactions(String numberOfTransactions) {
            this.numberOfTransactions = numberOfTransactions;
        }


        public StockHistoricalData(String date, String stockSymbol, String name, Double close, Double high, Double low, Double open, Double preClose, Double amountUpOrDown, Double upOrDown, Double changeHandsRate, Double volume, Double transactionAmount, Double marketValue, Double flowMarketValue, String numberOfTransactions) {
            this.date = date;
            this.stockSymbol = stockSymbol;
            this.name = name;
            this.close = close;
            this.high = high;
            this.low = low;
            this.open = open;
            this.preClose = preClose;
            this.amountUpOrDown = amountUpOrDown;
            this.upOrDown = upOrDown;
            this.changeHandsRate = changeHandsRate;
            this.volume = volume;
            this.transactionAmount = transactionAmount;
            this.marketValue = marketValue;
            this.flowMarketValue = flowMarketValue;
            this.numberOfTransactions = numberOfTransactions;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("StockHistoricalData{");
            sb.append("date='").append(date).append('\'');
            sb.append(", stockSymbol='").append(stockSymbol).append('\'');
            sb.append(", name='").append(name).append('\'');
            sb.append(", close=").append(close);
            sb.append(", high=").append(high);
            sb.append(", low=").append(low);
            sb.append(", open=").append(open);
            sb.append(", preClose=").append(preClose);
            sb.append(", amountUpOrDown=").append(amountUpOrDown);
            sb.append(", upOrDown=").append(upOrDown);
            sb.append(", changeHandsRate=").append(changeHandsRate);
            sb.append(", volume=").append(volume);
            sb.append(", transactionAmount=").append(transactionAmount);
            sb.append(", marketValue=").append(marketValue);
            sb.append(", flowMarketValue=").append(flowMarketValue);
            sb.append(", numberOfTransactions='").append(numberOfTransactions).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }



    public static class FinancialIndicators{

//        报告日期
        private String reportDate;

//        每股收益
        private Double eps;

//        每股净资产
        private Double nav;

//        每股经营活动产生的现金流量净额
        private Double netCashFlowIn;

//        主营业务收入
        private Long revenueFromMainBusiness;

//        主营业务利润
        private Long profitFromMainBusiness;

//        营业利润
        private Long profitFromBusiness;

//        投资收益
        private Long investmentIncome;

//        营业外收支净额
        private Long netNonOperatingIncomeAndExpenses;


//        总利润
        private Long totalProfit;

//        净利润
        private Long netProfit;

//        净利润(扣除非经常性损益后)
        private Long netProfitAfter;

//        经营活动产生的现金流量净额
        private Long netCashFlowsFromOperatingActivities;

//        现金及现金等价物净增加额
        private Long netIncreaseInCashAndCashEquivalents;

//        总资产
        private Long totalAssets;

//        流动资产
        private Long liquidAssets;

//        总负债
        private Long totalLiabilities;

//        流动负债
        private Long flowLiabilities;

//        股东权益不含少数股东权益
        private Long shareHolderEquity;


//        净资产收益率加权
        private Double returnOnNetAssetsWeighted;


        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

        public Double getEps() {
            return eps;
        }

        public void setEps(Double eps) {
            this.eps = eps;
        }

        public Double getNav() {
            return nav;
        }

        public void setNav(Double nav) {
            this.nav = nav;
        }

        public Double getNetCashFlowIn() {
            return netCashFlowIn;
        }

        public void setNetCashFlowIn(Double netCashFlowIn) {
            this.netCashFlowIn = netCashFlowIn;
        }

        public Long getRevenueFromMainBusiness() {
            return revenueFromMainBusiness;
        }

        public void setRevenueFromMainBusiness(Long revenueFromMainBusiness) {
            this.revenueFromMainBusiness = revenueFromMainBusiness;
        }

        public Long getProfitFromMainBusiness() {
            return profitFromMainBusiness;
        }

        public void setProfitFromMainBusiness(Long profitFromMainBusiness) {
            this.profitFromMainBusiness = profitFromMainBusiness;
        }

        public Long getProfitFromBusiness() {
            return profitFromBusiness;
        }

        public void setProfitFromBusiness(Long profitFromBusiness) {
            this.profitFromBusiness = profitFromBusiness;
        }

        public Long getInvestmentIncome() {
            return investmentIncome;
        }

        public void setInvestmentIncome(Long investmentIncome) {
            this.investmentIncome = investmentIncome;
        }

        public Long getNetNonOperatingIncomeAndExpenses() {
            return netNonOperatingIncomeAndExpenses;
        }

        public void setNetNonOperatingIncomeAndExpenses(Long netNonOperatingIncomeAndExpenses) {
            this.netNonOperatingIncomeAndExpenses = netNonOperatingIncomeAndExpenses;
        }

        public Long getTotalProfit() {
            return totalProfit;
        }

        public void setTotalProfit(Long totalProfit) {
            this.totalProfit = totalProfit;
        }

        public Long getNetProfit() {
            return netProfit;
        }

        public void setNetProfit(Long netProfit) {
            this.netProfit = netProfit;
        }

        public Long getNetProfitAfter() {
            return netProfitAfter;
        }

        public void setNetProfitAfter(Long netProfitAfter) {
            this.netProfitAfter = netProfitAfter;
        }

        public Long getNetCashFlowsFromOperatingActivities() {
            return netCashFlowsFromOperatingActivities;
        }

        public void setNetCashFlowsFromOperatingActivities(Long netCashFlowsFromOperatingActivities) {
            this.netCashFlowsFromOperatingActivities = netCashFlowsFromOperatingActivities;
        }

        public Long getNetIncreaseInCashAndCashEquivalents() {
            return netIncreaseInCashAndCashEquivalents;
        }

        public void setNetIncreaseInCashAndCashEquivalents(Long netIncreaseInCashAndCashEquivalents) {
            this.netIncreaseInCashAndCashEquivalents = netIncreaseInCashAndCashEquivalents;
        }

        public Long getTotalAssets() {
            return totalAssets;
        }

        public void setTotalAssets(Long totalAssets) {
            this.totalAssets = totalAssets;
        }

        public Long getLiquidAssets() {
            return liquidAssets;
        }

        public void setLiquidAssets(Long liquidAssets) {
            this.liquidAssets = liquidAssets;
        }

        public Long getTotalLiabilities() {
            return totalLiabilities;
        }

        public void setTotalLiabilities(Long totalLiabilities) {
            this.totalLiabilities = totalLiabilities;
        }

        public Long getFlowLiabilities() {
            return flowLiabilities;
        }

        public void setFlowLiabilities(Long flowLiabilities) {
            this.flowLiabilities = flowLiabilities;
        }

        public Long getShareHolderEquity() {
            return shareHolderEquity;
        }

        public void setShareHolderEquity(Long shareHolderEquity) {
            this.shareHolderEquity = shareHolderEquity;
        }

        public Double getReturnOnNetAssetsWeighted() {
            return returnOnNetAssetsWeighted;
        }

        public void setReturnOnNetAssetsWeighted(Double returnOnNetAssetsWeighted) {
            this.returnOnNetAssetsWeighted = returnOnNetAssetsWeighted;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("FinancialIndicators{");
            sb.append("reportDate='").append(reportDate).append('\'');
            sb.append(", eps=").append(eps);
            sb.append(", nav=").append(nav);
            sb.append(", netCashFlowIn=").append(netCashFlowIn);
            sb.append(", revenueFromMainBusiness=").append(revenueFromMainBusiness);
            sb.append(", profitFromMainBusiness=").append(profitFromMainBusiness);
            sb.append(", profitFromBusiness=").append(profitFromBusiness);
            sb.append(", investmentIncome=").append(investmentIncome);
            sb.append(", netNonOperatingIncomeAndExpenses=").append(netNonOperatingIncomeAndExpenses);
            sb.append(", totalProfit=").append(totalProfit);
            sb.append(", netProfit=").append(netProfit);
            sb.append(", netProfitAfter=").append(netProfitAfter);
            sb.append(", netCashFlowsFromOperatingActivities=").append(netCashFlowsFromOperatingActivities);
            sb.append(", netIncreaseInCashAndCashEquivalents=").append(netIncreaseInCashAndCashEquivalents);
            sb.append(", totalAssets=").append(totalAssets);
            sb.append(", liquidAssets=").append(liquidAssets);
            sb.append(", totalLiabilities=").append(totalLiabilities);
            sb.append(", flowLiabilities=").append(flowLiabilities);
            sb.append(", shareHolderEquity=").append(shareHolderEquity);
            sb.append(", returnOnNetAssetsWeighted=").append(returnOnNetAssetsWeighted);
            sb.append('}');
            return sb.toString();
        }
    }

    private String code;
    private String symbol;
    private String name;
    private String area;
    private String industry;
    private String market;
    private String listDate;
    private FinancialIndicators financialIndicators;

    public  List<FinancialIndicators> financialIndicatorsList;

    private FinancialIndicators[] financialIndicatorsArray;

    public List<StockHistoricalData> stockHistoricalDataList;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public FinancialIndicators getFinancialIndicators() {
        return financialIndicators;
    }

    public void setFinancialIndicators(FinancialIndicators financialIndicators) {
        this.financialIndicators = financialIndicators;
    }

    public List<FinancialIndicators> getFinancialIndicatorsList() {
        return financialIndicatorsList;
    }

    public void setFinancialIndicatorsList(List<FinancialIndicators> financialIndicatorsList) {
        this.financialIndicatorsList = financialIndicatorsList;
    }

    public FinancialIndicators[] getFinancialIndicatorsArray() {
        return financialIndicatorsArray;
    }

    public void setFinancialIndicatorsArray(FinancialIndicators[] financialIndicatorsArray) {
        this.financialIndicatorsArray = financialIndicatorsArray;
    }

    public List<StockHistoricalData> getStockHistoricalDataList() {
        return stockHistoricalDataList;
    }

    public void setStockHistoricalDataList(List<StockHistoricalData> stockHistoricalDataList) {
        this.stockHistoricalDataList = stockHistoricalDataList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockInfoDO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", industry='").append(industry).append('\'');
        sb.append(", market='").append(market).append('\'');
        sb.append(", listDate='").append(listDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public StockInfoDO(String code, String symbol, String name, String area, String industry, String market, String listDate) {
        this.code = code;
        this.symbol = symbol;
        this.name = name;
        this.area = area;
        this.industry = industry;
        this.market = market;
        this.listDate = listDate;
    }

    public StockInfoDO(String code, String symbol, String name, String area, String industry, String market, String listDate, FinancialIndicators financialIndicators) {
        this.code = code;
        this.symbol = symbol;
        this.name = name;
        this.area = area;
        this.industry = industry;
        this.market = market;
        this.listDate = listDate;
        this.financialIndicators = financialIndicators;
    }

    public StockInfoDO() {
    }
}
