package com.fzc.fzcstockus.model;

/**
 * @author Flamenco.xxx
 * @date 2021/9/10 22:57
 */
public class PeList {
    private String symbol;
    private String pe;
    private String roe;
    private String revenueGross3Y;
    private String freeCashFlowPerShare;
    private String grossMarginAnnual;
    private String netProfitMarginAnnual;

    public PeList(String symbol, String pe, String roe) {
        this.symbol = symbol;
        this.pe = pe;
        this.roe = roe;
    }

    public PeList(String symbol, String pe) {
        this.symbol = symbol;
        this.pe = pe;
    }


    public PeList(String symbol, String pe, String roe, String revenueGross3Y, String freeCashFlowPerShare, String grossMarginAnnual, String netProfitMarginAnnual) {
        this.symbol = symbol;
        this.pe = pe;
        this.roe = roe;
        this.revenueGross3Y = revenueGross3Y;
        this.freeCashFlowPerShare = freeCashFlowPerShare;
        this.grossMarginAnnual = grossMarginAnnual;
        this.netProfitMarginAnnual = netProfitMarginAnnual;
    }

    public PeList() {
    }

    public String getNetProfitMarginAnnual() {
        return netProfitMarginAnnual;
    }

    public void setNetProfitMarginAnnual(String netProfitMarginAnnual) {
        this.netProfitMarginAnnual = netProfitMarginAnnual;
    }

    public String getGrossMarginAnnual() {
        return grossMarginAnnual;
    }

    public void setGrossMarginAnnual(String grossMarginAnnual) {
        this.grossMarginAnnual = grossMarginAnnual;
    }

    public String getFreeCashFlowPerShare() {
        return freeCashFlowPerShare;
    }

    public void setFreeCashFlowPerShare(String freeCashFlowPerShare) {
        this.freeCashFlowPerShare = freeCashFlowPerShare;
    }

    public String getRevenueGross3Y() {
        return revenueGross3Y;
    }

    public void setRevenueGross3Y(String revenueGross3Y) {
        this.revenueGross3Y = revenueGross3Y;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PeList{");
        sb.append("symbol='").append(symbol).append('\'');
        sb.append(", pe='").append(pe).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
