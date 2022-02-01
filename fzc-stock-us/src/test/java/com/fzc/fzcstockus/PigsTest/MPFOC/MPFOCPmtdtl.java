package com.fzc.fzcstockus.PigsTest.MPFOC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 14:55
 */
@XmlRootElement(name = "PMTDTL")
@XmlAccessorType(XmlAccessType.FIELD)
public class MPFOCPmtdtl {
    @XmlElement(name = "PMTREF")
    private String pmtref;
    @XmlElement(name = "DESTBANK")
    private String destbank;
    @XmlElement(name ="DESTBRCH")
    private String destbrch;
    @XmlElement(name ="DESTACNO")
    private String destacno;
    @XmlElement(name ="DESTACNAME")
    private String destacname;
    @XmlElement(name ="DESTMBR")
    private String destmbr;
    @XmlElement(name = "TRANCODE")
    private String trancode;
    @XmlElement(name = "TRANAMT")
    private String tranamt;
    @XmlElement(name = "ORIGBANK")
    private String origbank;
    @XmlElement(name = "ORIGBRCH")
    private String origbrch;
    @XmlElement(name = "ORIGACNO")
    private String origacno;
    @XmlElement(name = "ORIGACNAME")
    private String origacname;
    @XmlElement(name = "ORIGMBR")
    private String origmbr;

    public MPFOCPmtdtl() {
    }

    public MPFOCPmtdtl(String pmtref, String destbank, String destbrch, String destacno, String destacname, String destmbr, String trancode, String tranamt, String origbank, String origbrch, String origacno, String origacname, String origmbr) {
        this.pmtref = pmtref;
        this.destbank = destbank;
        this.destbrch = destbrch;
        this.destacno = destacno;
        this.destacname = destacname;
        this.destmbr = destmbr;
        this.trancode = trancode;
        this.tranamt = tranamt;
        this.origbank = origbank;
        this.origbrch = origbrch;
        this.origacno = origacno;
        this.origacname = origacname;
        this.origmbr = origmbr;
    }

    public String getPmtref() {
        return pmtref;
    }

    public void setPmtref(String pmtref) {
        this.pmtref = pmtref;
    }

    public String getDestbank() {
        return destbank;
    }

    public void setDestbank(String destbank) {
        this.destbank = destbank;
    }

    public String getDestbrch() {
        return destbrch;
    }

    public void setDestbrch(String destbrch) {
        this.destbrch = destbrch;
    }

    public String getDestacno() {
        return destacno;
    }

    public void setDestacno(String destacno) {
        this.destacno = destacno;
    }

    public String getDestacname() {
        return destacname;
    }

    public void setDestacname(String destacname) {
        this.destacname = destacname;
    }

    public String getDestmbr() {
        return destmbr;
    }

    public void setDestmbr(String destmbr) {
        this.destmbr = destmbr;
    }

    public String getTrancode() {
        return trancode;
    }

    public void setTrancode(String trancode) {
        this.trancode = trancode;
    }

    public String getTranamt() {
        return tranamt;
    }

    public void setTranamt(String tranamt) {
        this.tranamt = tranamt;
    }

    public String getOrigbank() {
        return origbank;
    }

    public void setOrigbank(String origbank) {
        this.origbank = origbank;
    }

    public String getOrigbrch() {
        return origbrch;
    }

    public void setOrigbrch(String origbrch) {
        this.origbrch = origbrch;
    }

    public String getOrigacno() {
        return origacno;
    }

    public void setOrigacno(String origacno) {
        this.origacno = origacno;
    }

    public String getOrigacname() {
        return origacname;
    }

    public void setOrigacname(String origacname) {
        this.origacname = origacname;
    }

    public String getOrigmbr() {
        return origmbr;
    }

    public void setOrigmbr(String origmbr) {
        this.origmbr = origmbr;
    }

    @Override
    public String toString() {
        return "Pmtdtl{" +
                "pmtref='" + pmtref + '\'' +
                ", destbank='" + destbank + '\'' +
                ", destbrch='" + destbrch + '\'' +
                ", destacno='" + destacno + '\'' +
                ", destacname='" + destacname + '\'' +
                ", destmbr='" + destmbr + '\'' +
                ", trancode='" + trancode + '\'' +
                ", tranamt='" + tranamt + '\'' +
                ", origbank='" + origbank + '\'' +
                ", origbrch='" + origbrch + '\'' +
                ", origacno='" + origacno + '\'' +
                ", origacname='" + origacname + '\'' +
                ", origmbr='" + origmbr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MPFOCPmtdtl MPFOCPmtdtl = (MPFOCPmtdtl) o;
        return Objects.equals(getPmtref(), MPFOCPmtdtl.getPmtref()) && Objects.equals(getDestbank(), MPFOCPmtdtl.getDestbank()) && Objects.equals(getDestbrch(), MPFOCPmtdtl.getDestbrch()) && Objects.equals(getDestacno(), MPFOCPmtdtl.getDestacno()) && Objects.equals(getDestacname(), MPFOCPmtdtl.getDestacname()) && Objects.equals(getDestmbr(), MPFOCPmtdtl.getDestmbr()) && Objects.equals(getTrancode(), MPFOCPmtdtl.getTrancode()) && Objects.equals(getTranamt(), MPFOCPmtdtl.getTranamt()) && Objects.equals(getOrigbank(), MPFOCPmtdtl.getOrigbank()) && Objects.equals(getOrigbrch(), MPFOCPmtdtl.getOrigbrch()) && Objects.equals(getOrigacno(), MPFOCPmtdtl.getOrigacno()) && Objects.equals(getOrigacname(), MPFOCPmtdtl.getOrigacname()) && Objects.equals(getOrigmbr(), MPFOCPmtdtl.getOrigmbr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPmtref(), getDestbank(), getDestbrch(), getDestacno(), getDestacname(), getDestmbr(), getTrancode(), getTranamt(), getOrigbank(), getOrigbrch(), getOrigacno(), getOrigacname(), getOrigmbr());
    }
}
