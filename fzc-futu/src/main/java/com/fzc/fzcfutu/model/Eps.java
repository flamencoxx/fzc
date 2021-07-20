package com.fzc.fzcfutu.model;

import java.util.Date;

/**
 * @author 11615
 */
public class Eps {

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

    public Eps() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Eps{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
