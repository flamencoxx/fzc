package com.fzc.fzcfutu.model;

import java.util.Date;

/**
 * @author Flamenco.xxx
 * @date 2021/6/17 0:44
 */
public class TotalDebtToTotalAsset {

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

    public TotalDebtToTotalAsset(Date period, double v) {
        this.period = period;
        this.v = v;
    }

    public TotalDebtToTotalAsset() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TotalDebtToTotalAsset{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
