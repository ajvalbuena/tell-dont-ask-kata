package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.taxedAmount = calculateTaxedAmount();
        this.tax = calculateTax();
    }

    private BigDecimal calculateTaxedAmount() {
        final BigDecimal unitaryTaxedAmount = product.getUnitaryTaxedAmount();
        return unitaryTaxedAmount.multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
    }

    private BigDecimal calculateTax() {
        final BigDecimal unitaryTax = product.getUnitaryTax();
        return unitaryTax.multiply(BigDecimal.valueOf(quantity));
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

}
