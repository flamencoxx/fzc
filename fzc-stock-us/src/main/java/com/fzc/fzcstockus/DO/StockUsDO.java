package com.fzc.fzcstockus.DO;

import com.fzc.fzcstockus.mongo.IncIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "StockUs")
public class StockUsDO extends IncIdEntity<Integer> {


//    货币

    private String currency;

//    描述

    private String description;

//    显示符号

    private String displaySymbol;


//    Financial Instrument Global Identifier

    private String figi;


//    主要交易所mic

    private String mic;


    private String symbol;

//    Security type

    private String type;


    public StockUsDO(String currency, String description, String displaySymbol, String figi, String mic, String symbol, String type) {
        this.currency = currency;
        this.description = description;
        this.displaySymbol = displaySymbol;
        this.figi = figi;
        this.mic = mic;
        this.symbol = symbol;
        this.type = type;
    }

    public StockUsDO() {
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockUsDO{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", displaySymbol='").append(displaySymbol).append('\'');
        sb.append(", figi='").append(figi).append('\'');
        sb.append(", mic='").append(mic).append('\'');
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(String displaySymbol) {
        this.displaySymbol = displaySymbol;
    }

    public String getFigi() {
        return figi;
    }

    public void setFigi(String figi) {
        this.figi = figi;
    }

    public String getMic() {
        return mic;
    }

    public void setMic(String mic) {
        this.mic = mic;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
