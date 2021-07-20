package com.fzc.fzcstockus.model;



/**
 * @author 11615
 */
public class GrossMargin {

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

    public GrossMargin(String period, String v) {
        this.period = period;
        this.v = v;
    }


    public GrossMargin() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GrossMargin{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
