package com.fzc.fzcsearches.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 15:17
 */
@Document(indexName = "sa",createIndex = false)
public class EsStockAimport  implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long id;

    @Field(type=FieldType.Keyword)
    private String stockIdentity;

    @Field(type=FieldType.Keyword,analyzer = "ik_max_word", store = true)
    private String name;

    @Field(index = false,type = FieldType.Keyword)
    private String area;

    @Field(type=FieldType.Keyword,store = true)
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

    @Field(index = false,type=FieldType.Keyword)
    private String market;

    @Field(type=FieldType.Keyword)
    private String symbol;

    @Field(type=FieldType.Keyword)
    private String tsCode;

    @Field(type=FieldType.Keyword)
    private String detail;



    public EsStockAimport() {
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
                ", detail='" + detail + '\'' +
                '}';
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
