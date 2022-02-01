package com.fzc.fzcstockus.PigsTest.MPFDC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 15:50
 */
@XmlRootElement(name = "MPFDATACAPRESP")
@XmlAccessorType(XmlAccessType.FIELD)
public class MPFResponseXml {
    @XmlElement(name = "HEADER")
    private MPFDCHeader mpfdcHeader;
    @XmlElement(name = "PMTDTL")
    private MPFDCPmtdtl mpfdcPmtdtl;

    public MPFResponseXml() {
    }

    public MPFResponseXml(MPFDCHeader mpfdcHeader, MPFDCPmtdtl mpfdcPmtdtl) {
        this.mpfdcHeader = mpfdcHeader;
        this.mpfdcPmtdtl = mpfdcPmtdtl;
    }

    public MPFDCHeader getMpfdcHeader() {
        return mpfdcHeader;
    }

    public void setMpfdcHeader(MPFDCHeader mpfdcHeader) {
        this.mpfdcHeader = mpfdcHeader;
    }

    public MPFDCPmtdtl getMpfdcPmtdtl() {
        return mpfdcPmtdtl;
    }

    public void setMpfdcPmtdtl(MPFDCPmtdtl mpfdcPmtdtl) {
        this.mpfdcPmtdtl = mpfdcPmtdtl;
    }

    @Override
    public String toString() {
        return "MPFResponseXml{" +
                "mpfdcHeader=" + mpfdcHeader +
                ", mpfdcPmtdtl=" + mpfdcPmtdtl +
                '}';
    }
}
