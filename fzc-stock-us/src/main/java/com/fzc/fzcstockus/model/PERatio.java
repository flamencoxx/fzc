package com.fzc.fzcstockus.model;

import java.util.Objects;

/**
 * @author Flamenco.xxx
 * @date 2021/8/26 19:27
 */
public class PERatio {

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

    public PERatio(String period, String v) {
        this.period = period;
        this.v = v;
    }

    public PERatio() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PERatio peRatio = (PERatio) o;
        return Objects.equals(getPeriod(), peRatio.getPeriod()) && Objects.equals(getV(), peRatio.getV());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriod(), getV());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperatingMargin{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }
}
