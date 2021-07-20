package com.fzc.fzcstockus.model;

/**
 * @author Flamenco.xxx
 * @date 2021/6/17 7:49
 */
public class BasicFinancials {
    private Metric metric;
    private String metricType;
    private Annual annual;
    private String symbol;


    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public String getMetricType() {
        return metricType;
    }

    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    public Annual getAnnual() {
        return annual;
    }

    public void setAnnual(Annual annual) {
        this.annual = annual;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BasicFinancials(Metric metric, String metricType, Annual annual, String symbol) {
        this.metric = metric;
        this.metricType = metricType;
        this.annual = annual;
        this.symbol = symbol;
    }

    public BasicFinancials() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasicFinancials{");
        sb.append("metric=").append(metric);
        sb.append(", metricType='").append(metricType).append('\'');
        sb.append(", annual=").append(annual);
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
