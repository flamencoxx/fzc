package com.fzc.fzcstockus.reportedModel;

import java.util.Date;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 9:54
 */

public class Data {

    private String accessNumber;
    private String symbol;
    private String cik;
    private int year;
    private int quarter;
    private String form;
    private Date startDate;
    private Date endDate;
    private Date filedDate;
    private Date acceptedDate;
    private Report report;
    public void setAccessNumber(String accessNumber) {
        this.accessNumber = accessNumber;
    }
    public String getAccessNumber() {
        return accessNumber;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setCik(String cik) {
        this.cik = cik;
    }
    public String getCik() {
        return cik;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }
    public int getQuarter() {
        return quarter;
    }

    public void setForm(String form) {
        this.form = form;
    }
    public String getForm() {
        return form;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return endDate;
    }



    public void setFiledDate(Date filedDate) {
        this.filedDate = filedDate;
    }
    public Date getFiledDate() {
        return filedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }
    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setReport(Report report) {
        this.report = report;
    }
    public Report getReport() {
        return report;
    }


    @Override
    public String toString() {
        return "Data{" +
                "accessNumber='" + accessNumber + '\'' +
                ", symbol='" + symbol + '\'' +
                ", cik='" + cik + '\'' +
                ", year=" + year +
                ", quarter=" + quarter +
                ", form='" + form + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", filedDate=" + filedDate +
                ", acceptedDate=" + acceptedDate +
                ", report=" + report +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        return getYear() == data.getYear() && getQuarter() == data.getQuarter() && Objects.equals(getAccessNumber(), data.getAccessNumber()) && Objects.equals(getSymbol(), data.getSymbol()) && Objects.equals(getCik(), data.getCik()) && Objects.equals(getForm(), data.getForm()) && Objects.equals(getStartDate(), data.getStartDate()) && Objects.equals(getEndDate(), data.getEndDate()) && Objects.equals(getFiledDate(), data.getFiledDate()) && Objects.equals(getAcceptedDate(), data.getAcceptedDate()) && Objects.equals(getReport(), data.getReport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessNumber(), getSymbol(), getCik(), getYear(), getQuarter(), getForm(), getStartDate(), getEndDate(), getFiledDate(), getAcceptedDate(), getReport());
    }
}
