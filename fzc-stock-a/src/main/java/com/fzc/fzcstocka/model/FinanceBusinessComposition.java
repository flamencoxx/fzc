package com.fzc.fzcstocka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author Flamenco.xxx
 * @date 2022/4/14 8:25
 */
@Document(collection = "Finance_BusinessComposition")
public class FinanceBusinessComposition {

    @Id
    @Field("_id")
    private String id;

    private String classification;

    private Date period;

    @Field("stock_identity")
    private String stockIdentity;

    @Field("bz_cost")
    private String bzCost;

    @Field("bz_item")
    private String bzItem;

    @Field("bz_profit")
    private String bzProfit;

    @Field("bz_sales")
    private double bzSales;

    @Field("curr_type")
    private String currType;


    public FinanceBusinessComposition() {
    }

    @Override
    public String toString() {
        return "FinanceBusinessComposition{" +
                "id='" + id + '\'' +
                ", classification='" + classification + '\'' +
                ", period=" + period +
                ", stockIdentity='" + stockIdentity + '\'' +
                ", bzCost='" + bzCost + '\'' +
                ", bzItem='" + bzItem + '\'' +
                ", bzProfit='" + bzProfit + '\'' +
                ", bzSales='" + bzSales + '\'' +
                ", currType='" + currType + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getStockIdentity() {
        return stockIdentity;
    }

    public void setStockIdentity(String stockIdentity) {
        this.stockIdentity = stockIdentity;
    }

    public String getBzCost() {
        return bzCost;
    }

    public void setBzCost(String bzCost) {
        this.bzCost = bzCost;
    }

    public String getBzItem() {
        return bzItem;
    }

    public void setBzItem(String bzItem) {
        this.bzItem = bzItem;
    }

    public String getBzProfit() {
        return bzProfit;
    }

    public void setBzProfit(String bzProfit) {
        this.bzProfit = bzProfit;
    }

    public double getBzSales() {
        return bzSales;
    }

    public void setBzSales(double bzSales) {
        this.bzSales = bzSales;
    }

    public String getCurrType() {
        return currType;
    }

    public void setCurrType(String currType) {
        this.currType = currType;
    }
}
