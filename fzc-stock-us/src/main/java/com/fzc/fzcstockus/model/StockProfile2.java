package com.fzc.fzcstockus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/12/1 10:10
 */
@TableName("stock_profile2")
public class StockProfile2 {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String symbol;

    private String country;

    private String currency;

    private String exchange;

    private String finnhubIndustry;

    private String ipo;

    private String logo;

    private String name;

    private String phone;

    private String shareOutstanding;

    private String weburl;

    private byte[] images;

    public StockProfile2() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StockProfile2 that = (StockProfile2) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getSymbol(), that.getSymbol()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getCurrency(), that.getCurrency()) && Objects.equals(getExchange(), that.getExchange()) && Objects.equals(getFinnhubIndustry(), that.getFinnhubIndustry()) && Objects.equals(getIpo(), that.getIpo()) && Objects.equals(getLogo(), that.getLogo()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getShareOutstanding(), that.getShareOutstanding()) && Objects.equals(getWeburl(), that.getWeburl()) && Arrays.equals(getImages(), that.getImages());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), getSymbol(), getCountry(), getCurrency(), getExchange(), getFinnhubIndustry(), getIpo(), getLogo(), getName(), getPhone(), getShareOutstanding(), getWeburl());
        result = 31 * result + Arrays.hashCode(getImages());
        return result;
    }

    @Override
    public String toString() {
        return "StockProfile2{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", exchange='" + exchange + '\'' +
                ", finnhubIndustry='" + finnhubIndustry + '\'' +
                ", ipo='" + ipo + '\'' +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", shareOutstanding='" + shareOutstanding + '\'' +
                ", weburl='" + weburl + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getFinnhubIndustry() {
        return finnhubIndustry;
    }

    public void setFinnhubIndustry(String finnhubIndustry) {
        this.finnhubIndustry = finnhubIndustry;
    }

    public String getIpo() {
        return ipo;
    }

    public void setIpo(String ipo) {
        this.ipo = ipo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShareOutstanding() {
        return shareOutstanding;
    }

    public void setShareOutstanding(String shareOutstanding) {
        this.shareOutstanding = shareOutstanding;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }
}
