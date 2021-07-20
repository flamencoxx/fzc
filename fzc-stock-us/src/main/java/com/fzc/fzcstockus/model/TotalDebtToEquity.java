package com.fzc.fzcstockus.model;



/**
 * @author Flamenco.xxx
 * @date 2021/6/17 0:43
 */
public class TotalDebtToEquity {


    private String period;
    private String v;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public TotalDebtToEquity(String period, String v) {
        this.period = period;
        this.v = v;
    }

    public TotalDebtToEquity() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TotalDebtToEquity{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
