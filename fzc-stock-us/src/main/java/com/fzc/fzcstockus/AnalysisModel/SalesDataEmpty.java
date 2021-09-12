package com.fzc.fzcstockus.AnalysisModel;

import java.util.Objects;

/**
 * @author Flamenco.xxx
 * @date 2021/9/12 11:29
 */
public class SalesDataEmpty {
    private String x;
    private double y;

    public SalesDataEmpty(String x, double y) {
        this.x = x;
        this.y = y;
    }

    public SalesDataEmpty() {
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesDataEmpty that = (SalesDataEmpty) o;
        return Double.compare(that.getY(), getY()) == 0 && Objects.equals(getX(), that.getX());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SalesDataEmpty{");
        sb.append("x='").append(x).append('\'');
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
