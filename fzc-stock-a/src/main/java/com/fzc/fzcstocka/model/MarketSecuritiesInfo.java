package com.fzc.fzcstocka.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2022/2/21 16:02
 */
@Document(collection = "Market_SecuritiesInfo")
public class MarketSecuritiesInfo implements Serializable {

//    @Id
//    @Field("_id")
//    private Integer id;

    @Field("stock_identity")
    private String stockIdentity;

    private String area;

    private String code;

    @Field("curr_type")
    private String currType;

    private String enname;

    private String exchange;

    private String fullname;

    private String industry;

    @Field("is_hs")
    private String isHs;

    @Field("list_status")
    private String listStatus;

    @Field("listing_date")
    private String listingDate;

    private String market;

    private String name;

    private String symbol;

    @Field("ts_code")
    private String tsCode;

    public MarketSecuritiesInfo() {
    }



//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MarketSecuritiesInfo that = (MarketSecuritiesInfo) o;
        return Objects.equals(getStockIdentity(), that.getStockIdentity()) && Objects.equals(getArea(), that.getArea()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getCurrType(), that.getCurrType()) && Objects.equals(getEnname(), that.getEnname()) && Objects.equals(getExchange(), that.getExchange()) && Objects.equals(getFullname(), that.getFullname()) && Objects.equals(getIndustry(), that.getIndustry()) && Objects.equals(getIsHs(), that.getIsHs()) && Objects.equals(getListStatus(), that.getListStatus()) && Objects.equals(getListingDate(), that.getListingDate()) && Objects.equals(getMarket(), that.getMarket()) && Objects.equals(getName(), that.getName()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getTsCode(), that.getTsCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockIdentity(), getArea(), getCode(), getCurrType(), getEnname(), getExchange(), getFullname(), getIndustry(), getIsHs(), getListStatus(), getListingDate(), getMarket(), getName(), getSymbol(), getTsCode());
    }

    @Override
    public String toString() {
        return "MarketSecuritiesInfo{" +
                "stockIdentity='" + stockIdentity + '\'' +
                ", area='" + area + '\'' +
                ", code='" + code + '\'' +
                ", currType='" + currType + '\'' +
                ", enname='" + enname + '\'' +
                ", exchange='" + exchange + '\'' +
                ", fullname='" + fullname + '\'' +
                ", industry='" + industry + '\'' +
                ", isHs='" + isHs + '\'' +
                ", listStatus='" + listStatus + '\'' +
                ", listingDate='" + listingDate + '\'' +
                ", market='" + market + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", tsCode='" + tsCode + '\'' +
                '}';
    }

    public String getStockIdentity() {
        return stockIdentity;
    }

    public void setStockIdentity(String stockIdentity) {
        this.stockIdentity = stockIdentity;
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

    public String getIsHs() {
        return isHs;
    }

    public void setIsHs(String isHs) {
        this.isHs = isHs;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
