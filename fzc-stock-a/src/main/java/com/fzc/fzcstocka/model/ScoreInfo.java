package com.fzc.fzcstocka.model;

/**
 * @author Flamenco.xxx
 * @date 2022/4/11 7:47
 */
public class ScoreInfo {
    private String code;

    private String comprehensiveScore;

    private ResultAnalyzer cfResultAnalyzer;

    private ResultAnalyzer mfResultAnalyzer;

    private ResultAnalyzer prResultAnalyzer;;

    private ResultAnalyzer acResultAnalyzer;

    private ResultAnalyzer isResultAnalyzer;

    private ResultAnalyzer lsResultAnalyzer;

    private ResultAnalyzer taResultAnalyzer;

    private String passComprehensiveScore;

    public ScoreInfo() {
    }

    @Override
    public String toString() {
        return "ScoreInfo{" +
                "code='" + code + '\'' +
                ", comprehensiveScore='" + comprehensiveScore + '\'' +
                ", cfResultAnalyzer=" + cfResultAnalyzer +
                ", mfResultAnalyzer=" + mfResultAnalyzer +
                ", prResultAnalyzer=" + prResultAnalyzer +
                ", acResultAnalyzer=" + acResultAnalyzer +
                ", isResultAnalyzer=" + isResultAnalyzer +
                ", lsResultAnalyzer=" + lsResultAnalyzer +
                ", taResultAnalyzer=" + taResultAnalyzer +
                ", passComprehensiveScore='" + passComprehensiveScore + '\'' +
                '}';
    }

    public ResultAnalyzer getLsResultAnalyzer() {
        return lsResultAnalyzer;
    }

    public void setLsResultAnalyzer(ResultAnalyzer lsResultAnalyzer) {
        this.lsResultAnalyzer = lsResultAnalyzer;
    }

    public ResultAnalyzer getTaResultAnalyzer() {
        return taResultAnalyzer;
    }

    public void setTaResultAnalyzer(ResultAnalyzer taResultAnalyzer) {
        this.taResultAnalyzer = taResultAnalyzer;
    }

    public String getPassComprehensiveScore() {
        return passComprehensiveScore;
    }

    public void setPassComprehensiveScore(String passComprehensiveScore) {
        this.passComprehensiveScore = passComprehensiveScore;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComprehensiveScore() {
        return comprehensiveScore;
    }

    public void setComprehensiveScore(String comprehensiveScore) {
        this.comprehensiveScore = comprehensiveScore;
    }

    public ResultAnalyzer getCfResultAnalyzer() {
        return cfResultAnalyzer;
    }

    public void setCfResultAnalyzer(ResultAnalyzer cfResultAnalyzer) {
        this.cfResultAnalyzer = cfResultAnalyzer;
    }

    public ResultAnalyzer getMfResultAnalyzer() {
        return mfResultAnalyzer;
    }

    public void setMfResultAnalyzer(ResultAnalyzer mfResultAnalyzer) {
        this.mfResultAnalyzer = mfResultAnalyzer;
    }

    public ResultAnalyzer getPrResultAnalyzer() {
        return prResultAnalyzer;
    }

    public void setPrResultAnalyzer(ResultAnalyzer prResultAnalyzer) {
        this.prResultAnalyzer = prResultAnalyzer;
    }

    public ResultAnalyzer getAcResultAnalyzer() {
        return acResultAnalyzer;
    }

    public void setAcResultAnalyzer(ResultAnalyzer acResultAnalyzer) {
        this.acResultAnalyzer = acResultAnalyzer;
    }

    public ResultAnalyzer getIsResultAnalyzer() {
        return isResultAnalyzer;
    }

    public void setIsResultAnalyzer(ResultAnalyzer isResultAnalyzer) {
        this.isResultAnalyzer = isResultAnalyzer;
    }
}
