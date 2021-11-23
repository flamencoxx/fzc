package com.fzc.fzcsearches.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author flamenco.xxx
 * @date 2021/11/15 17:40
 */
@Document(indexName = "sui",createIndex = false)
public class EsStockUsImport implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;

    @Field(type=FieldType.Keyword)
    private String symbol;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",store = true)
    private String name;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",store = true)
    private String description;


    @Field(type=FieldType.Text,store = true)
    private String sector;

    @Field(type=FieldType.Text,store = true)
    private String industry;

    @Field(index = false,type = FieldType.Keyword)
    private String marketValue;


    public EsStockUsImport() {
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
        return "EsStockUsImport{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", marketValue='" + marketValue + '\'' +
                '}';
    }
}
