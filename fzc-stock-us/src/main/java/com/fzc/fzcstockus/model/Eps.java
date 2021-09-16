package com.fzc.fzcstockus.model;


import java.util.Objects;

/**
 * @author 11615
 */
public class Eps {

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

    public Eps() {
    }

    public Eps(String period, String v) {
        this.period = period;
        this.v = v;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Eps{");
        sb.append("period=").append(period);
        sb.append(", v=").append(v);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eps eps = (Eps) o;
        return Objects.equals(getPeriod(), eps.getPeriod()) && Objects.equals(getV(), eps.getV());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriod(), getV());
    }
}
