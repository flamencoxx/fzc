package com.fzc.fzcstockus.reportedModel;

import java.util.Objects;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 9:53
 */

public class Ic {

    private String label;
    private String concept;
    private String unit;
    private long value;
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
    public String getConcept() {
        return concept;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return unit;
    }

    public void setValue(long value) {
        this.value = value;
    }
    public long getValue() {
        return value;
    }

    public Ic() {
    }

    @Override
    public String toString() {
        return "Ic{" +
                "label='" + label + '\'' +
                ", concept='" + concept + '\'' +
                ", unit='" + unit + '\'' +
                ", value=" + value +
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
        Ic ic = (Ic) o;
        return getValue() == ic.getValue() && Objects.equals(getLabel(), ic.getLabel()) && Objects.equals(getConcept(), ic.getConcept()) && Objects.equals(getUnit(), ic.getUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getConcept(), getUnit(), getValue());
    }
}
