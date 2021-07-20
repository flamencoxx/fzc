package com.fzc.fzcfutu.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * @author 11615
 */
@TableName("stock_info")
public class StockInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String tsCode;
    private String symbol;
    private String name;
    private String area;
    private String industry;
    private String market;
    private String listDate;

    public StockInfo(String tsCode, String symbol, String name, String area, String industry, String market, String listDate) {
        this.tsCode = tsCode;
        this.symbol = symbol;
        this.name = name;
        this.area = area;
        this.industry = industry;
        this.market = market;
        this.listDate = listDate;
    }

    public StockInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockInfo{");
        sb.append("id=").append(id);
        sb.append(", tsCode='").append(tsCode).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", industry='").append(industry).append('\'');
        sb.append(", market='").append(market).append('\'');
        sb.append(", listDate='").append(listDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
