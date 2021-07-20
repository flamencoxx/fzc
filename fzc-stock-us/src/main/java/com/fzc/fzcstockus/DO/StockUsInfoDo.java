package com.fzc.fzcstockus.DO;

import com.fzc.fzcstockus.model.BasicFinancials;
import com.fzc.fzcstockus.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author 11615
 */
@Document(collection = "StockUs_important")
public class StockUsInfoDo extends IncIdEntity<Integer> {


    public static class CompanyOverview extends IncIdEntity<Integer>{


//        private String Symbol;
//        private String AssetType;
        private String Name;
        private String Description;
//        private String CIK;
//        private String Exchange;
//        private String Currency;
//        private String Country;
        private String Sector;
        private String Industry;
//        private String Address;
        private String FullTimeEmployees;
        private String FiscalYearEnd;
//        private Date LatestQuarter;
        private String MarketCapitalization;
        private String EBITDA;
        private String PERatio;
        private String PEGRatio;
        private String BookValue;
        private String DividendPerShare;
        private String DividendYield;
        private String EPS;
        private String RevenuePerShareTTM;
        private String ProfitMargin;
        private String OperatingMarginTTM;
        private String ReturnOnAssetsTTM;
        private String ReturnOnEquityTTM;
        private String RevenueTTM;
        private String GrossProfitTTM;
        private String DilutedEPSTTM;
        private String QuarterlyEarningsGrowthYOY;
        private String QuarterlyRevenueGrowthYOY;
        private String AnalystTargetPrice;
        private String TrailingPE;
        private String ForwardPE;
        private String PriceToSalesRatioTTM;
        private String PriceToBookRatio;
        private String EVToRevenue;
        private String EVToEBITDA;
        private String Beta;
//        private String FiftyWeekHigh;
//        private String FiftyWeekLow;
//        private String FiftyDayMovingAverage;
//        private String TwoHundredDayMovingAverage;
//        private String SharesOutstanding;
//        private String SharesFloat;
//        private String SharesShort;
//        private String SharesShortPriorMonth;
//        private String ShortRatio;
//        private String ShortPercentOutstanding;
//        private String ShortPercentFloat;
//        private String PercentInsiders;
//        private String PercentInstitutions;
//        private String ForwardAnnualDividendRate;
//        private String ForwardAnnualDividendYield;
//        private String PayoutRatio;
//        private Date DividendDate;
//        private Date ExDividendDate;
//        private String LastSplitFactor;
//        private Date LastSplitDate;

//        public String getSymbol() {
//            return Symbol;
//        }
//
//        public void setSymbol(String symbol) {
//            Symbol = symbol;
//        }
//
//        public String getAssetType() {
//            return AssetType;
//        }
//
//        public void setAssetType(String assetType) {
//            AssetType = assetType;
//        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

//        public String getCIK() {
//            return CIK;
//        }
//
//        public void setCIK(String CIK) {
//            this.CIK = CIK;
//        }
//
//        public String getExchange() {
//            return Exchange;
//        }
//
//        public void setExchange(String exchange) {
//            Exchange = exchange;
//        }
//
//        public String getCurrency() {
//            return Currency;
//        }
//
//        public void setCurrency(String currency) {
//            Currency = currency;
//        }
//
//        public String getCountry() {
//            return Country;
//        }
//
//        public void setCountry(String country) {
//            Country = country;
//        }
//
        public String getSector() {
            return Sector;
        }

        public void setSector(String sector) {
            Sector = sector;
        }

        public String getIndustry() {
            return Industry;
        }

        public void setIndustry(String industry) {
            Industry = industry;
        }

//        public String getAddress() {
//            return Address;
//        }
//
//        public void setAddress(String address) {
//            Address = address;
//        }
//
        public String getFullTimeEmployees() {
            return FullTimeEmployees;
        }

        public void setFullTimeEmployees(String fullTimeEmployees) {
            FullTimeEmployees = fullTimeEmployees;
        }

        public String getFiscalYearEnd() {
            return FiscalYearEnd;
        }

        public void setFiscalYearEnd(String fiscalYearEnd) {
            FiscalYearEnd = fiscalYearEnd;
        }

//        public Date getLatestQuarter() {
//            return LatestQuarter;
//        }
//
//        public void setLatestQuarter(Date latestQuarter) {
//            LatestQuarter = latestQuarter;
//        }
//
        public String getMarketCapitalization() {
            return MarketCapitalization;
        }

        public void setMarketCapitalization(String marketCapitalization) {
            MarketCapitalization = marketCapitalization;
        }

        public String getEBITDA() {
            return EBITDA;
        }

        public void setEBITDA(String EBITDA) {
            this.EBITDA = EBITDA;
        }

        public String getPERatio() {
            return PERatio;
        }

        public void setPERatio(String PERatio) {
            this.PERatio = PERatio;
        }

        public String getPEGRatio() {
            return PEGRatio;
        }

        public void setPEGRatio(String PEGRatio) {
            this.PEGRatio = PEGRatio;
        }

        public String getBookValue() {
            return BookValue;
        }

        public void setBookValue(String bookValue) {
            BookValue = bookValue;
        }

        public String getDividendPerShare() {
            return DividendPerShare;
        }

        public void setDividendPerShare(String dividendPerShare) {
            DividendPerShare = dividendPerShare;
        }

        public String getDividendYield() {
            return DividendYield;
        }

        public void setDividendYield(String dividendYield) {
            DividendYield = dividendYield;
        }

        public String getEPS() {
            return EPS;
        }

        public void setEPS(String EPS) {
            this.EPS = EPS;
        }

        public String getRevenuePerShareTTM() {
            return RevenuePerShareTTM;
        }

        public void setRevenuePerShareTTM(String revenuePerShareTTM) {
            RevenuePerShareTTM = revenuePerShareTTM;
        }

        public String getProfitMargin() {
            return ProfitMargin;
        }

        public void setProfitMargin(String profitMargin) {
            ProfitMargin = profitMargin;
        }

        public String getOperatingMarginTTM() {
            return OperatingMarginTTM;
        }

        public void setOperatingMarginTTM(String operatingMarginTTM) {
            OperatingMarginTTM = operatingMarginTTM;
        }

        public String getReturnOnAssetsTTM() {
            return ReturnOnAssetsTTM;
        }

        public void setReturnOnAssetsTTM(String returnOnAssetsTTM) {
            ReturnOnAssetsTTM = returnOnAssetsTTM;
        }

        public String getReturnOnEquityTTM() {
            return ReturnOnEquityTTM;
        }

        public void setReturnOnEquityTTM(String returnOnEquityTTM) {
            ReturnOnEquityTTM = returnOnEquityTTM;
        }

        public String getRevenueTTM() {
            return RevenueTTM;
        }

        public void setRevenueTTM(String revenueTTM) {
            RevenueTTM = revenueTTM;
        }

        public String getGrossProfitTTM() {
            return GrossProfitTTM;
        }

        public void setGrossProfitTTM(String grossProfitTTM) {
            GrossProfitTTM = grossProfitTTM;
        }

        public String getDilutedEPSTTM() {
            return DilutedEPSTTM;
        }

        public void setDilutedEPSTTM(String dilutedEPSTTM) {
            DilutedEPSTTM = dilutedEPSTTM;
        }

        public String getQuarterlyEarningsGrowthYOY() {
            return QuarterlyEarningsGrowthYOY;
        }

        public void setQuarterlyEarningsGrowthYOY(String quarterlyEarningsGrowthYOY) {
            QuarterlyEarningsGrowthYOY = quarterlyEarningsGrowthYOY;
        }

        public String getQuarterlyRevenueGrowthYOY() {
            return QuarterlyRevenueGrowthYOY;
        }

        public void setQuarterlyRevenueGrowthYOY(String quarterlyRevenueGrowthYOY) {
            QuarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
        }

        public String getAnalystTargetPrice() {
            return AnalystTargetPrice;
        }

        public void setAnalystTargetPrice(String analystTargetPrice) {
            AnalystTargetPrice = analystTargetPrice;
        }

        public String getTrailingPE() {
            return TrailingPE;
        }

        public void setTrailingPE(String trailingPE) {
            TrailingPE = trailingPE;
        }

        public String getForwardPE() {
            return ForwardPE;
        }

        public void setForwardPE(String forwardPE) {
            ForwardPE = forwardPE;
        }

        public String getPriceToSalesRatioTTM() {
            return PriceToSalesRatioTTM;
        }

        public void setPriceToSalesRatioTTM(String priceToSalesRatioTTM) {
            PriceToSalesRatioTTM = priceToSalesRatioTTM;
        }

        public String getPriceToBookRatio() {
            return PriceToBookRatio;
        }

        public void setPriceToBookRatio(String priceToBookRatio) {
            PriceToBookRatio = priceToBookRatio;
        }

        public String getEVToRevenue() {
            return EVToRevenue;
        }

        public void setEVToRevenue(String EVToRevenue) {
            this.EVToRevenue = EVToRevenue;
        }

        public String getEVToEBITDA() {
            return EVToEBITDA;
        }

        public void setEVToEBITDA(String EVToEBITDA) {
            this.EVToEBITDA = EVToEBITDA;
        }

        public String getBeta() {
            return Beta;
        }

        public void setBeta(String beta) {
            Beta = beta;
        }

//        public String getFiftyWeekHigh() {
//            return FiftyWeekHigh;
//        }
//
//        public void setFiftyWeekHigh(String fiftyWeekHigh) {
//            FiftyWeekHigh = fiftyWeekHigh;
//        }
//
//        public String getFiftyWeekLow() {
//            return FiftyWeekLow;
//        }
//
//        public void setFiftyWeekLow(String fiftyWeekLow) {
//            FiftyWeekLow = fiftyWeekLow;
//        }
//
//        public String getFiftyDayMovingAverage() {
//            return FiftyDayMovingAverage;
//        }
//
//        public void setFiftyDayMovingAverage(String fiftyDayMovingAverage) {
//            FiftyDayMovingAverage = fiftyDayMovingAverage;
//        }
//
//        public String getTwoHundredDayMovingAverage() {
//            return TwoHundredDayMovingAverage;
//        }
//
//        public void setTwoHundredDayMovingAverage(String twoHundredDayMovingAverage) {
//            TwoHundredDayMovingAverage = twoHundredDayMovingAverage;
//        }
//
//        public String getSharesOutstanding() {
//            return SharesOutstanding;
//        }
//
//        public void setSharesOutstanding(String sharesOutstanding) {
//            SharesOutstanding = sharesOutstanding;
//        }
//
//        public String getSharesFloat() {
//            return SharesFloat;
//        }
//
//        public void setSharesFloat(String sharesFloat) {
//            SharesFloat = sharesFloat;
//        }
//
//        public String getSharesShort() {
//            return SharesShort;
//        }
//
//        public void setSharesShort(String sharesShort) {
//            SharesShort = sharesShort;
//        }
//
//        public String getSharesShortPriorMonth() {
//            return SharesShortPriorMonth;
//        }
//
//        public void setSharesShortPriorMonth(String sharesShortPriorMonth) {
//            SharesShortPriorMonth = sharesShortPriorMonth;
//        }
//
//        public String getShortRatio() {
//            return ShortRatio;
//        }
//
//        public void setShortRatio(String shortRatio) {
//            ShortRatio = shortRatio;
//        }
//
//        public String getShortPercentOutstanding() {
//            return ShortPercentOutstanding;
//        }
//
//        public void setShortPercentOutstanding(String shortPercentOutstanding) {
//            ShortPercentOutstanding = shortPercentOutstanding;
//        }
//
//        public String getShortPercentFloat() {
//            return ShortPercentFloat;
//        }
//
//        public void setShortPercentFloat(String shortPercentFloat) {
//            ShortPercentFloat = shortPercentFloat;
//        }
//
//        public String getPercentInsiders() {
//            return PercentInsiders;
//        }
//
//        public void setPercentInsiders(String percentInsiders) {
//            PercentInsiders = percentInsiders;
//        }
//
//        public String getPercentInstitutions() {
//            return PercentInstitutions;
//        }
//
//        public void setPercentInstitutions(String percentInstitutions) {
//            PercentInstitutions = percentInstitutions;
//        }
//
//        public String getForwardAnnualDividendRate() {
//            return ForwardAnnualDividendRate;
//        }
//
//        public void setForwardAnnualDividendRate(String forwardAnnualDividendRate) {
//            ForwardAnnualDividendRate = forwardAnnualDividendRate;
//        }
//
//        public String getForwardAnnualDividendYield() {
//            return ForwardAnnualDividendYield;
//        }
//
//        public void setForwardAnnualDividendYield(String forwardAnnualDividendYield) {
//            ForwardAnnualDividendYield = forwardAnnualDividendYield;
//        }
//
//        public String getPayoutRatio() {
//            return PayoutRatio;
//        }
//
//        public void setPayoutRatio(String payoutRatio) {
//            PayoutRatio = payoutRatio;
//        }
//
//        public Date getDividendDate() {
//            return DividendDate;
//        }
//
//        public void setDividendDate(Date dividendDate) {
//            DividendDate = dividendDate;
//        }
//
//        public Date getExDividendDate() {
//            return ExDividendDate;
//        }
//
//        public void setExDividendDate(Date exDividendDate) {
//            ExDividendDate = exDividendDate;
//        }
//
//        public String getLastSplitFactor() {
//            return LastSplitFactor;
//        }
//
//        public void setLastSplitFactor(String lastSplitFactor) {
//            LastSplitFactor = lastSplitFactor;
//        }
//
//        public Date getLastSplitDate() {
//            return LastSplitDate;
//        }
//
//        public void setLastSplitDate(Date lastSplitDate) {
//            LastSplitDate = lastSplitDate;
//        }
//
        public CompanyOverview() {
        }


        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("CompanyOverview{");
            sb.append("Name='").append(Name).append('\'');
            sb.append(", Description='").append(Description).append('\'');
            sb.append(", Sector='").append(Sector).append('\'');
            sb.append(", Industry='").append(Industry).append('\'');
            sb.append(", FullTimeEmployees='").append(FullTimeEmployees).append('\'');
            sb.append(", FiscalYearEnd='").append(FiscalYearEnd).append('\'');
            sb.append(", MarketCapitalization='").append(MarketCapitalization).append('\'');
            sb.append(", EBITDA='").append(EBITDA).append('\'');
            sb.append(", PERatio='").append(PERatio).append('\'');
            sb.append(", PEGRatio='").append(PEGRatio).append('\'');
            sb.append(", BookValue='").append(BookValue).append('\'');
            sb.append(", DividendPerShare='").append(DividendPerShare).append('\'');
            sb.append(", DividendYield='").append(DividendYield).append('\'');
            sb.append(", EPS='").append(EPS).append('\'');
            sb.append(", RevenuePerShareTTM='").append(RevenuePerShareTTM).append('\'');
            sb.append(", ProfitMargin='").append(ProfitMargin).append('\'');
            sb.append(", OperatingMarginTTM='").append(OperatingMarginTTM).append('\'');
            sb.append(", ReturnOnAssetsTTM='").append(ReturnOnAssetsTTM).append('\'');
            sb.append(", ReturnOnEquityTTM='").append(ReturnOnEquityTTM).append('\'');
            sb.append(", RevenueTTM='").append(RevenueTTM).append('\'');
            sb.append(", GrossProfitTTM='").append(GrossProfitTTM).append('\'');
            sb.append(", DilutedEPSTTM='").append(DilutedEPSTTM).append('\'');
            sb.append(", QuarterlyEarningsGrowthYOY='").append(QuarterlyEarningsGrowthYOY).append('\'');
            sb.append(", QuarterlyRevenueGrowthYOY='").append(QuarterlyRevenueGrowthYOY).append('\'');
            sb.append(", AnalystTargetPrice='").append(AnalystTargetPrice).append('\'');
            sb.append(", TrailingPE='").append(TrailingPE).append('\'');
            sb.append(", ForwardPE='").append(ForwardPE).append('\'');
            sb.append(", PriceToSalesRatioTTM='").append(PriceToSalesRatioTTM).append('\'');
            sb.append(", PriceToBookRatio='").append(PriceToBookRatio).append('\'');
            sb.append(", EVToRevenue='").append(EVToRevenue).append('\'');
            sb.append(", EVToEBITDA='").append(EVToEBITDA).append('\'');
            sb.append(", Beta='").append(Beta).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }




    //    货币

    private String currency;

//    描述

    private String description;

//    显示符号

    private String displaySymbol;


//    Financial Instrument Global Identifier

    private String figi;


//    主要交易所mic

    private String mic;


    private String symbol;

//    Security type

    private String type;

    private CompanyOverview companyOverview;

    private List<String> peerList;

    private BasicFinancials basicFinancials;


    public StockUsInfoDo(String currency, String description, String displaySymbol, String figi, String mic, String symbol, String type) {
        this.currency = currency;
        this.description = description;
        this.displaySymbol = displaySymbol;
        this.figi = figi;
        this.mic = mic;
        this.symbol = symbol;
        this.type = type;
    }

    public StockUsInfoDo(String currency, String description, String displaySymbol, String figi, String mic, String symbol, String type, CompanyOverview companyOverview, List<String> peerList, BasicFinancials basicFinancials) {
        this.currency = currency;
        this.description = description;
        this.displaySymbol = displaySymbol;
        this.figi = figi;
        this.mic = mic;
        this.symbol = symbol;
        this.type = type;
        this.companyOverview = companyOverview;
        this.peerList = peerList;
        this.basicFinancials = basicFinancials;
    }

    public StockUsInfoDo() {
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockUsInfoDo{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", displaySymbol='").append(displaySymbol).append('\'');
        sb.append(", figi='").append(figi).append('\'');
        sb.append(", mic='").append(mic).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public BasicFinancials getBasicFinancials() {
        return basicFinancials;
    }

    public void setBasicFinancials(BasicFinancials basicFinancials) {
        this.basicFinancials = basicFinancials;
    }

    public List<String> getPeerList() {
        return peerList;
    }

    public void setPeerList(List<String> peerList) {
        this.peerList = peerList;
    }

    public CompanyOverview getCompanyOverview() {
        return companyOverview;
    }

    public void setCompanyOverview(CompanyOverview companyOverview) {
        this.companyOverview = companyOverview;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
