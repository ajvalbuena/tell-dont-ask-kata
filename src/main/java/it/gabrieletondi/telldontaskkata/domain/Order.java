package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

import java.math.BigDecimal;
import java.util.List;

public abstract class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private int id;

    public Order(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        this.id = id;
        this.total = total;
        this.currency = currency;
        this.items = items;
        this.tax = tax;
    }

    public abstract boolean isOrderReadyToBeShipped();

    public abstract Order approve(OrderApprovalRequest request);


    public Order shipOrder() {
        return new ShippedOrder(this.id, this.total, this.currency, this.items, this.tax);
    }

    protected Order approveOrder() {
        return new ApprovedOrder(this.id, this.total, this.currency, this.items, this.tax);
    }

    protected Order rejectOrder() {
        return new RejectedOrder(this.id, this.total, this.currency, this.items, this.tax);
    }

    public void cannotBeShipped() {
        return;
    }

    public Order updateOrderWithOrderItems() {
        this.tax = updateTax(this);
        this.total = updateTotal(this);
        return this;
    }

    private BigDecimal updateTotal(Order order) {
        return order.getItems().stream()
                .map(item -> item.getTaxedAmount())
                .reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b));
    }

    private BigDecimal updateTax(Order order) {
        return order.getItems().stream()
                .map(item -> item.getTax())
                .reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b));
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public int getId() {
        return id;
    }


}
