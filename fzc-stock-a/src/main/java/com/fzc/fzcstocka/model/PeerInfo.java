package com.fzc.fzcstocka.model;

import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2022/3/7 15:43
 */
public class PeerInfo {

    private String code;

    private String roc;

    private String rona;

    private String rota;

    private String gm;

    private String om;

    private String npm;

    public PeerInfo() {
    }

    public PeerInfo(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PeerListInfo{" +
                "code='" + code + '\'' +
                ", roc='" + roc + '\'' +
                ", rona='" + rona + '\'' +
                ", rota='" + rota + '\'' +
                ", gm='" + gm + '\'' +
                ", om='" + om + '\'' +
                ", npm='" + npm + '\'' +
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
        PeerInfo that = (PeerInfo) o;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getRoc(), that.getRoc()) && Objects.equals(getRona(), that.getRona()) && Objects.equals(getRota(), that.getRota()) && Objects.equals(getGm(), that.getGm()) && Objects.equals(getOm(), that.getOm()) && Objects.equals(getNpm(), that.getNpm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getRoc(), getRona(), getRota(), getGm(), getOm(), getNpm());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRoc() {
        return roc;
    }

    public void setRoc(String roc) {
        this.roc = roc;
    }

    public String getRona() {
        return rona;
    }

    public void setRona(String rona) {
        this.rona = rona;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getGm() {
        return gm;
    }

    public void setGm(String gm) {
        this.gm = gm;
    }

    public String getOm() {
        return om;
    }

    public void setOm(String om) {
        this.om = om;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }
}
