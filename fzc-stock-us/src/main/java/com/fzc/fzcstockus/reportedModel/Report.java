package com.fzc.fzcstockus.reportedModel;

import java.util.List;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 9:54
 */

public class Report {

    private List<Bs> bs;
    private List<Cf> cf;
    private List<Ic> ic;
    public void setBs(List<Bs> bs) {
        this.bs = bs;
    }
    public List<Bs> getBs() {
        return bs;
    }

    public void setCf(List<Cf> cf) {
        this.cf = cf;
    }
    public List<Cf> getCf() {
        return cf;
    }

    public void setIc(List<Ic> ic) {
        this.ic = ic;
    }
    public List<Ic> getIc() {
        return ic;
    }

    public Report() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(getBs(), report.getBs()) && Objects.equals(getCf(), report.getCf()) && Objects.equals(getIc(), report.getIc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBs(), getCf(), getIc());
    }

    @Override
    public String toString() {
        return "Report{" +
                "bs=" + bs +
                ", cf=" + cf +
                ", ic=" + ic +
                '}';
    }
}
