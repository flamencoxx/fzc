package com.fzc.fzcstockus.XmlTest;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 11:40
 */
@Data
//@XmlRootElement(name = "childList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Child {

    @XmlElement(name = "childName")
    private String childName;
    @XmlElement(name = "chileAge")
    private int chileAge;

    public Child() {
    }

    public Child(String childName, int chileAge) {
        this.childName = childName;
        this.chileAge = chileAge;
    }
}
