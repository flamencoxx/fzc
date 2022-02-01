package com.fzc.fzcstockus.PigsTest.MPFOC;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 14:38
 */
@XmlRootElement(name = "MPFOUTWARD")
@XmlAccessorType(XmlAccessType.FIELD)
public class MPFOutClearXml {
    @XmlElement(name = "HEADER")
    private MPFOCHeader MPFOCHeader;
    @XmlElement(name = "PMTDTL")
    private MPFOCPmtdtl MPFOCPmtdtl;

    public MPFOCHeader getHeader() {
        return MPFOCHeader;
    }

    public void setHeader(MPFOCHeader MPFOCHeader) {
        this.MPFOCHeader = MPFOCHeader;
    }

    public MPFOCPmtdtl getPmtdtl() {
        return MPFOCPmtdtl;
    }

    public void setPmtdtl(MPFOCPmtdtl MPFOCPmtdtl) {
        this.MPFOCPmtdtl = MPFOCPmtdtl;
    }

    public MPFOutClearXml() {
    }

    public MPFOutClearXml(MPFOCHeader MPFOCHeader, MPFOCPmtdtl MPFOCPmtdtl) {
        this.MPFOCHeader = MPFOCHeader;
        this.MPFOCPmtdtl = MPFOCPmtdtl;
    }

    @Override
    public String toString() {
        return "MPFOutClearXml{" +
                "header=" + MPFOCHeader +
                ", pmtdtl=" + MPFOCPmtdtl +
                '}';
    }
}
