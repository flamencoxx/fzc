package com.fzc.fzcstockus.model;

import java.util.Objects;

/**
 * @author Flamenco.xxx
 * @date 2021/8/26 7:59
 */
public class HistoryPrice {

    private String time;

    private String close;

    private String open;

    private String high;

    private String low;

    private String value;

    public HistoryPrice() {
    }

    public HistoryPrice(String time, String close, String open, String high, String low, String value) {
        this.time = time;
        this.close = close;
        this.open = open;
        this.high = high;
        this.low = low;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoryPrice that = (HistoryPrice) o;
        return getTime().equals(that.getTime()) && Objects.equals(getClose(), that.getClose()) && Objects.equals(getOpen(), that.getOpen()) && Objects.equals(getHigh(), that.getHigh()) && Objects.equals(getLow(), that.getLow()) && Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime(), getClose(), getOpen(), getHigh(), getLow(), getValue());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HistoryPrice{");
        sb.append("time='").append(time).append('\'');
        sb.append(", close='").append(close).append('\'');
        sb.append(", open='").append(open).append('\'');
        sb.append(", high='").append(high).append('\'');
        sb.append(", low='").append(low).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
