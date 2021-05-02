package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedTwiceException;

import java.math.BigDecimal;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public void ship(ShipmentService shipmentService) {
        if (isCreated() || isRejected()) throw new OrderCannotBeShippedException();
        if (isShipped()) throw new OrderCannotBeShippedTwiceException();

        shipmentService.ship(this);
        status = OrderStatus.SHIPPED;
    }

    private boolean isCreated() {
        return this.getStatus().equals(CREATED);
    }

    private boolean isRejected() {
        return this.getStatus().equals(REJECTED);
    }

    private boolean isShipped() {
        return this.getStatus().equals(SHIPPED);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
