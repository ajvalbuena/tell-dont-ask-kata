package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final Category category;
    private BigDecimal unitaryTax;
    private final BigDecimal unitaryTaxedAmount;

    public Product(String name, BigDecimal price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.unitaryTax = this.price.divide(valueOf(100)).multiply(this.category.getTaxPercentage()).setScale(2, HALF_UP);
        this.unitaryTaxedAmount = this.price.add(unitaryTax).setScale(2, HALF_UP);
    }

    public BigDecimal getUnitaryTax() {
        return unitaryTax;
    }

    public BigDecimal getUnitaryTaxedAmount() {
        return unitaryTaxedAmount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
