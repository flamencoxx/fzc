package com.fzc.fzcstockus.PigsTest.MPFOC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 14:41
 */
@XmlRootElement(name = "HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class MPFOCHeader {
    @XmlElement(name = "FILEID")
    private String fileId;
    @XmlElement(name = "OWFILENAME")
    private String owFileName;
    @XmlElement(name = "GENDATE")
    private String gendate;
    @XmlElement(name = "CCY")
    private String ccy;
    @XmlElement(name = "SBMMBR")
    private String sbmmbr;
    @XmlElement(name = "TOTALCNT")
    private String totalCnt;
    @XmlElement(name = "TOTALAMT")
    private String totalAmt;

    public MPFOCHeader() {
    }

    public MPFOCHeader(String fileId, String owFileName, String gendate, String ccy, String sbmmbr, String totalCnt, String totalAmt) {
        this.fileId = fileId;
        this.owFileName = owFileName;
        this.gendate = gendate;
        this.ccy = ccy;
        this.sbmmbr = sbmmbr;
        this.totalCnt = totalCnt;
        this.totalAmt = totalAmt;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOwFileName() {
        return owFileName;
    }

    public void setOwFileName(String owFileName) {
        this.owFileName = owFileName;
    }

    public String getGendate() {
        return gendate;
    }

    public void setGendate(String gendate) {
        this.gendate = gendate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getSbmmbr() {
        return sbmmbr;
    }

    public void setSbmmbr(String sbmmbr) {
        this.sbmmbr = sbmmbr;
    }

    public String getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(String totalCnt) {
        this.totalCnt = totalCnt;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    @Override
    public String toString() {
        return "Header{" +
                "fileId='" + fileId + '\'' +
                ", owFileName='" + owFileName + '\'' +
                ", gendate='" + gendate + '\'' +
                ", ccy='" + ccy + '\'' +
                ", sbmmbr='" + sbmmbr + '\'' +
                ", totalCnt='" + totalCnt + '\'' +
                ", totalAmt='" + totalAmt + '\'' +
                '}';
    }
}
