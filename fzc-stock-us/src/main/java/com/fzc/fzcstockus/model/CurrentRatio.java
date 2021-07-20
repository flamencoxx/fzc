package com.fzc.fzcstockus.model;



/**
 * @author 11615
 */
public class CurrentRatio {

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

    public CurrentRatio() {
    }

    public CurrentRatio(String period, String v) {
        this.period = period;
        this.v = v;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CurrentRatio{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}