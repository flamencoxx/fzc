package com.fzc.fzcfutu.model;

import java.util.Date;

/**
 * @author 11615
 */
public class NetDebtToTotalCapital {

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

    public NetDebtToTotalCapital(Date period, double v) {
        this.period = period;
        this.v = v;
    }

    public NetDebtToTotalCapital() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NetDebtToTotalCapital{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
