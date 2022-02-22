package com.fzc.fzcsearches.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 15:17
 */
@Document(indexName = "stock_a",createIndex = false)
public class EsStockAimport {

    @Id
    private Long id;

    @Field(type=FieldType.Keyword)
    private String stockIdentity;

    @Field(type=FieldType.Keyword)
    private String name;

    @Field(index = false,type = FieldType.Keyword)
    private String area;

    @Field(type=FieldType.Keyword)
    private String code;

    @Field(index = false,type = FieldType.Keyword)
    private String currType;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",store = true)
    private String enname;

    @Field(index = false,type = FieldType.Keyword)
    private String exchange;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",store = true)
    private String fullname;

    @Field(type=FieldType.Keyword)
    private String industry;

    @Field(type=FieldType.Keyword)
    private String market;

    @Field(type=FieldType.Keyword)
    private String symbol;

    @Field(type=FieldType.Keyword)
    private String tsCode;



    public EsStockAimport() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EsStockAimport that = (EsStockAimport) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStockIdentity(), that.getStockIdentity()) && Objects.equals(getName(), that.getName()) && Objects.equals(getArea(), that.getArea()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getCurrType(), that.getCurrType()) && Objects.equals(getEnname(), that.getEnname()) && Objects.equals(getExchange(), that.getExchange()) && Objects.equals(getFullname(), that.getFullname()) && Objects.equals(getIndustry(), that.getIndustry()) && Objects.equals(getMarket(), that.getMarket()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getTsCode(), that.getTsCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStockIdentity(), getName(), getArea(), getCode(), getCurrType(), getEnname(), getExchange(), getFullname(), getIndustry(), getMarket(), getSymbol(), getTsCode());
    }

    @Override
    public String toString() {
        return "EsStockAimport{" +
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
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


}
