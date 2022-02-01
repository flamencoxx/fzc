package com.fzc.fzcstockus.XmlTest;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 11:34
 */
@Data
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlRootNode {

    private String name;
    private int age;
    private String type;
    @XmlElementWrapper(name = "childLists")
    @XmlElement(name = "childList")
    private List<Child> childrenList;
}

