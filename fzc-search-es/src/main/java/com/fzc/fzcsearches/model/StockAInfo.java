package com.fzc.fzcsearches.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 14:20
 */
@TableName("stock_a_info")
public class StockAInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String stockIdentity;

    private String name;

    private String area;

    private String code;

    private String currType;

    private String enname;

    private String exchange;

    private String fullname;

    private String industry;

    private String market;

    private String symbol;

    private String tsCode;

    private Date listingDate;

    private String detail;

    public StockAInfo() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StockAInfo that = (StockAInfo) o;
        return getId() == that.getId() && Objects.equals(getStockIdentity(), that.getStockIdentity()) && Objects.equals(getName(), that.getName()) && Objects.equals(getArea(), that.getArea()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getCurrType(), that.getCurrType()) && Objects.equals(getEnname(), that.getEnname()) && Objects.equals(getExchange(), that.getExchange()) && Objects.equals(getFullname(), that.getFullname()) && Objects.equals(getIndustry(), that.getIndustry()) && Objects.equals(getMarket(), that.getMarket()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getTsCode(), that.getTsCode()) && Objects.equals(getListingDate(), that.getListingDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStockIdentity(), getName(), getArea(), getCode(), getCurrType(), getEnname(), getExchange(), getFullname(), getIndustry(), getMarket(), getSymbol(), getTsCode(), getListingDate());
    }

    @Override
    public String toString() {
        return "StockAInfo{" +
                "id=" + id +
                ", stockIdentity='" + stockIdentity + '\'' +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", code='" + code + '\'' +
                ", currType='" + currType + '\'' +
                ", enname='" + enname + '\'' +
                ", exchange='" + exchange + '\'' +
                ", fullname='" + fullname + '\'' +
                ", industry='" + industry + '\'' +
                ", market='" + market + '\'' +
                ", symbol='" + symbol + '\'' +
                ", tsCode='" + tsCode + '\'' +
                ", listingDate='" + listingDate + '\'' +
                '}';
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockIdentity() {
        return stockIdentity;
    }

    public void setStockIdentity(String stockIdentity) {
        this.stockIdentity = stockIdentity;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrType() {
        return currType;
    }

    public void setCurrType(String currType) {
        this.currType = currType;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }
}
