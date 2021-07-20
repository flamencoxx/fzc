package com.fzc.fzcfutu.model;

import java.util.Date;

/**
 * @author 11615
 */
public class LongtermDebtTotalAsset {

    private Date period;
    private double v;

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public LongtermDebtTotalAsset(Date period, double v) {
        this.period = period;
        this.v = v;
    }

    public LongtermDebtTotalAsset() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LongtermDebtTotalAsset{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
