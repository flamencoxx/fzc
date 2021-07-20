package com.fzc.fzcfutu.model;

import java.util.Date;

/**
 * @author 11615
 */
public class CurrentRatio {

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

    public CurrentRatio() {
    }

    public CurrentRatio(Date period, double v) {
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
