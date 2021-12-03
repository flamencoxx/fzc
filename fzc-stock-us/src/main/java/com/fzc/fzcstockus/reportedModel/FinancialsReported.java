package com.fzc.fzcstockus.reportedModel;

import java.util.List;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 10:13
 */

public class FinancialsReported {

    private String cik;
    private List<Data> data;
    private String symbol;
    public void setCik(String cik) {
        this.cik = cik;
    }
    public String getCik() {
        return cik;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

    public FinancialsReported() {
    }

    @Override
    public String toString() {
        return "FinancialsReported{" +
                "cik='" + cik + '\'' +
                ", data=" + data +
                ", symbol='" + symbol + '\'' +
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
        FinancialsReported that = (FinancialsReported) o;
        return Objects.equals(getCik(), that.getCik()) && Objects.equals(getData(), that.getData()) && Objects.equals(getSymbol(), that.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCik(), getData(), getSymbol());
    }
}
