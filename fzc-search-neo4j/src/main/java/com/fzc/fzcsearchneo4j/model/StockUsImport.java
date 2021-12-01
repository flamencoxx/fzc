package com.fzc.fzcsearchneo4j.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/11/9 14:31
 */
@TableName("stock_us_import")
public class StockUsImport {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String symbol;

    private String name;

    private String description;


    private String sector;

    private String industry;

    private String marketValue;

    public StockUsImport() {
    }

    public StockUsImport(String symbol) {
        this.symbol = symbol;
    }

    public StockUsImport(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }


    public StockUsImport(Long id, String symbol, String name, String description, String sector, String industry, String marketValue) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.sector = sector;
        this.industry = industry;
        this.marketValue = marketValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "StockUsImport{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", marketValue='" + marketValue + '\'' +
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
        StockUsImport that = (StockUsImport) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getSector(), that.getSector()) && Objects.equals(getIndustry(), that.getIndustry()) && Objects.equals(getMarketValue(), that.getMarketValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSymbol(), getName(), getDescription(), getSector(), getIndustry(), getMarketValue());
    }


}
