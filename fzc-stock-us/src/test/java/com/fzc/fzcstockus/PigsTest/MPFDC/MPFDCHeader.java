package com.fzc.fzcstockus.PigsTest.MPFDC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 15:33
 */
@XmlRootElement(name = "HEADER")
@XmlAccessorType(XmlAccessType.FIELD)
public class MPFDCHeader {
    @XmlElement(name = "FILEID")
    private String fileId;
    @XmlElement(name = "OWFILENAME")
    private String owfilename;
    @XmlElement(name = "GENDATE")
    private String gendate;
    @XmlElement(name = "CLRDATE")
    private String clrdate;
    @XmlElement(name = "CCY")
    private String ccy;
    @XmlElement(name = "RCVMBR")
    private String rcvmbr;
    @XmlElement(name = "RESPCODE")
    private String respcode;
    @XmlElement(name = "RESPDESC")
    private String respdesc;
    @XmlElement(name = "TOTALSUBCNT")
    private String totalsubcnt;
    @XmlElement(name = "TOTALACPCNT")
    private String totalacpcnt;
    @XmlElement(name = "TOTALREJCNT")
    private String totalrejcnt;

    public MPFDCHeader() {
    }

    public MPFDCHeader(String fileId, String owfilename, String gendate, String clrdate, String ccy, String rcvmbr, String respcode, String respdesc, String totalsubcnt, String totalacpcnt, String totalrejcnt) {
        this.fileId = fileId;
        this.owfilename = owfilename;
        this.gendate = gendate;
        this.clrdate = clrdate;
        this.ccy = ccy;
        this.rcvmbr = rcvmbr;
        this.respcode = respcode;
        this.respdesc = respdesc;
        this.totalsubcnt = totalsubcnt;
        this.totalacpcnt = totalacpcnt;
        this.totalrejcnt = totalrejcnt;
    }


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOwfilename() {
        return owfilename;
    }

    public void setOwfilename(String owfilename) {
        this.owfilename = owfilename;
    }

    public String getGendate() {
        return gendate;
    }

    public void setGendate(String gendate) {
        this.gendate = gendate;
    }

    public String getClrdate() {
        return clrdate;
    }

    public void setClrdate(String clrdate) {
        this.clrdate = clrdate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getRcvmbr() {
        return rcvmbr;
    }

    public void setRcvmbr(String rcvmbr) {
        this.rcvmbr = rcvmbr;
    }

    public String getRespcode() {
        return respcode;
    }

    public void setRespcode(String respcode) {
        this.respcode = respcode;
    }

    public String getRespdesc() {
        return respdesc;
    }

    public void setRespdesc(String respdesc) {
        this.respdesc = respdesc;
    }

    public String getTotalsubcnt() {
        return totalsubcnt;
    }

    public void setTotalsubcnt(String totalsubcnt) {
        this.totalsubcnt = totalsubcnt;
    }

    public String getTotalacpcnt() {
        return totalacpcnt;
    }

    public void setTotalacpcnt(String totalacpcnt) {
        this.totalacpcnt = totalacpcnt;
    }

    public String getTotalrejcnt() {
        return totalrejcnt;
    }

    public void setTotalrejcnt(String totalrejcnt) {
        this.totalrejcnt = totalrejcnt;
    }

    @Override
    public String toString() {
        return "MPFDCHeader{" +
                "fileId='" + fileId + '\'' +
                ", owfilename='" + owfilename + '\'' +
                ", gendate='" + gendate + '\'' +
                ", clrdate='" + clrdate + '\'' +
                ", ccy='" + ccy + '\'' +
                ", rcvmbr='" + rcvmbr + '\'' +
                ", respcode='" + respcode + '\'' +
                ", respdesc='" + respdesc + '\'' +
                ", totalsubcnt='" + totalsubcnt + '\'' +
                ", totalacpcnt='" + totalacpcnt + '\'' +
                ", totalrejcnt='" + totalrejcnt + '\'' +
                '}';
    }
}
