package com.fzc.fzcstockus.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 11615
 */
@TableName("stock_us_info")
public class StockUsInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String tsCode;
    private String name;
    private String esname;
    private String classify;
    private String listDate;
    private String delistDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEsname() {
        return esname;
    }

    public void setEsname(String esname) {
        this.esname = esname;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getDelistDate() {
        return delistDate;
    }

    public void setDelistDate(String delistDate) {
        this.delistDate = delistDate;
    }

    public StockUsInfo() {
    }

    public StockUsInfo(String tsCode, String name, String esname, String classify, String listDate, String delistDate) {
        this.tsCode = tsCode;
        this.name = name;
        this.esname = esname;
        this.classify = classify;
        this.listDate = listDate;
        this.delistDate = delistDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockUsInfo{");
        sb.append("id=").append(id);
        sb.append(", tsCode='").append(tsCode).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", esname='").append(esname).append('\'');
        sb.append(", classify='").append(classify).append('\'');
        sb.append(", listDate='").append(listDate).append('\'');
        sb.append(", delistDate='").append(delistDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
