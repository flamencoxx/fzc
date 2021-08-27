package com.fzc.fzcstockus.model;

import java.util.Objects;

/**
 * @author Flamenco.xxx
 * @date 2021/8/25 11:29
 */
public class StockClose {
    private String period;
    private String close;

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public StockClose(String period, String close) {
        this.period = period;
        this.close = close;
    }

    public StockClose() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockClose that = (StockClose) o;
        return Objects.equals(getPeriod(), that.getPeriod()) && Objects.equals(getClose(), that.getClose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPeriod(), getClose());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockClose{");
        sb.append("period='").append(period).append('\'');
        sb.append(", close='").append(close).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
