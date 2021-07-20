package com.fzc.fzcstock.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 11615
 */
@TableName("user_stock")
public class UserStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "stock_id", type = IdType.AUTO)
    private Integer stockId;

    private String stockCode;

    private String stockName;

    private String stockType;

    private String stockUser;

    private Integer stockUserId;

    private Float stockPrice;

    private String time;

    private String stockCreatTime;

    private Float stockPrePrice;

    public UserStock() {
    }

    public UserStock(String stockCode, String stockName, Integer stockUserId, Float stockPrice) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.stockUserId = stockUserId;
        this.stockPrice = stockPrice;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        setTime(sdf.format(date));
    }

    public UserStock(String stockCode, String stockName, String stockUser, Float stockPrice) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.stockUser = stockUser;
        this.stockPrice = stockPrice;

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        setTime(sdf.format(date));
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getStockUser() {
        return stockUser;
    }

    public void setStockUser(String stockUser) {
        this.stockUser = stockUser;
    }

    public Integer getStockUserId() {
        return stockUserId;
    }

    public void setStockUserId(Integer stockUserId) {
        this.stockUserId = stockUserId;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStockCreatTime() {
        return stockCreatTime;
    }

    public void setStockCreatTime(String stockCreatTime) {
        this.stockCreatTime = stockCreatTime;
    }

    public Float getStockPrePrice() {
        return stockPrePrice;
    }

    public void setStockPrePrice(Float stockPrePrice) {
        this.stockPrePrice = stockPrePrice;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserStock{");
        sb.append("stockId=").append(stockId);
        sb.append(", stockCode='").append(stockCode).append('\'');
        sb.append(", stockName='").append(stockName).append('\'');
        sb.append(", stockType='").append(stockType).append('\'');
        sb.append(", stockUser='").append(stockUser).append('\'');
        sb.append(", stockUserId=").append(stockUserId);
        sb.append(", stockPrice=").append(stockPrice);
        sb.append(", currentDate=").append(time);
        sb.append(", stockCreatDate=").append(stockCreatTime);
        sb.append(", stockPrePrice=").append(stockPrePrice);
        sb.append('}');
        return sb.toString();
    }


}
