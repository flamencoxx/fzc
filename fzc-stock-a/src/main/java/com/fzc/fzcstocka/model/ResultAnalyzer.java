package com.fzc.fzcstocka.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author flamenco.xxx
 * @date 2022/2/22 10:22
 */@Document(collection = "Result_Analyzer")
public class ResultAnalyzer {

     @Id
     @Field("_id")
     private String id;

     private String analyzer;

     private Date period;

     @Field("stock_identity")
     private String stockIdentity;

     private String brief;

     private String reason;

     private String score;

     private String weight;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getAnalyzer() {
          return analyzer;
     }

     public void setAnalyzer(String analyzer) {
          this.analyzer = analyzer;
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

     public String getBrief() {
          return brief;
     }

     public void setBrief(String brief) {
          this.brief = brief;
     }

     public String getReason() {
          return reason;
     }

     public void setReason(String reason) {
          this.reason = reason;
     }

     public String getScore() {
          return score;
     }

     public void setScore(String score) {
          this.score = score;
     }

     public String getWeight() {
          return weight;
     }

     public void setWeight(String weight) {
          this.weight = weight;
     }

     @Override
     public String toString() {
          return "ResultAnalyzer{" +
                  "id='" + id + '\'' +
                  ", analyzer='" + analyzer + '\'' +
                  ", period=" + period +
                  ", stockIdentity='" + stockIdentity + '\'' +
                  ", brief='" + brief + '\'' +
                  ", reason='" + reason + '\'' +
                  ", score='" + score + '\'' +
                  ", weight='" + weight + '\'' +
                  '}';
     }
}
