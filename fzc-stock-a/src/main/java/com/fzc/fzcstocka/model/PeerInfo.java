package com.fzc.fzcstocka.model;

import java.util.List;
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

    private List<ResAndPeriod> rocList;

    private List<ResAndPeriod> ronaList;

    private List<ResAndPeriod> rotaList;

    private List<ResAndPeriod> gmList;

    private List<ResAndPeriod> omList;

    private List<ResAndPeriod> npmList;

    private List<ResAndPeriod> ldList;




    public PeerInfo() {
    }

    public PeerInfo(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PeerInfo{" +
                "code='" + code + '\'' +
                ", roc='" + roc + '\'' +
                ", rona='" + rona + '\'' +
                ", rota='" + rota + '\'' +
                ", gm='" + gm + '\'' +
                ", om='" + om + '\'' +
                ", npm='" + npm + '\'' +
                ", rocList=" + rocList +
                ", ronaList=" + ronaList +
                ", rotaList=" + rotaList +
                ", gmList=" + gmList +
                ", omList=" + omList +
                ", npmList=" + npmList +
                ", ldList=" + ldList +
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

    public List<ResAndPeriod> getRocList() {
        return rocList;
    }

    public void setRocList(List<ResAndPeriod> rocList) {
        this.rocList = rocList;
    }

    public List<ResAndPeriod> getRonaList() {
        return ronaList;
    }

    public void setRonaList(List<ResAndPeriod> ronaList) {
        this.ronaList = ronaList;
    }

    public List<ResAndPeriod> getRotaList() {
        return rotaList;
    }

    public void setRotaList(List<ResAndPeriod> rotaList) {
        this.rotaList = rotaList;
    }

    public List<ResAndPeriod> getGmList() {
        return gmList;
    }

    public void setGmList(List<ResAndPeriod> gmList) {
        this.gmList = gmList;
    }

    public List<ResAndPeriod> getOmList() {
        return omList;
    }

    public void setOmList(List<ResAndPeriod> omList) {
        this.omList = omList;
    }

    public List<ResAndPeriod> getNpmList() {
        return npmList;
    }

    public void setNpmList(List<ResAndPeriod> npmList) {
        this.npmList = npmList;
    }

    public List<ResAndPeriod> getLdList() {
        return ldList;
    }

    public void setLdList(List<ResAndPeriod> ldList) {
        this.ldList = ldList;
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
