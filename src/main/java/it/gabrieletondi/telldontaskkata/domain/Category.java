package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

public class Category {
    private final String name;
    private final BigDecimal taxPercentage;

    public Category(String name, BigDecimal taxPercentage) {
        this.name = name;
        this.taxPercentage = taxPercentage;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }
}
