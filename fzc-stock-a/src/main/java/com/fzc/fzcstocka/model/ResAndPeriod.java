package com.fzc.fzcstocka.model;

/**
 * @author Flamenco.xxx
 * @date 2022/4/15 13:29
 */
public class ResAndPeriod {

    private String period;

    private String res;

    public ResAndPeriod(String period, String res) {
        this.period = period;
        this.res = res;
    }

    public ResAndPeriod() {
    }

    @Override
    public String toString() {
        return "ResAndPeriod{" +
                "period=" + period +
                ", res='" + res + '\'' +
                '}';
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
