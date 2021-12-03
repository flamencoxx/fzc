package com.fzc.fzcstockus.reportedModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Arrays;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 10:59
 */
@TableName("stock_us_report")
public class StockUsReport {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String symbol;

    private byte[] report;

    private byte[] reportFast;

    public StockUsReport() {
    }

    @Override
    public String toString() {
        return "StockUsReport{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", report=" + Arrays.toString(report) +
                ", reportFast=" + Arrays.toString(reportFast) +
                '}';
    }

    public byte[] getReportFast() {
        return reportFast;
    }

    public void setReportFast(byte[] reportFast) {
        this.reportFast = reportFast;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public byte[] getReport() {
        return report;
    }

    public void setReport(byte[] report) {
        this.report = report;
    }
}
