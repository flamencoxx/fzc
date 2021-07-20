package com.fzc.fzcstockus.model;



/**
 * @author Flamenco.xxx
 * @date 2021/6/17 0:41
 */
public class SalesPerShare {

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

    public SalesPerShare(String period, String v) {
        this.period = period;
        this.v = v;
    }

    public SalesPerShare() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SalesPerShare{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
