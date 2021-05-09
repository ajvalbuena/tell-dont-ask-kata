package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.service.ShipmentService;
import it.gabrieletondi.telldontaskkata.useCase.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public Order(OrderStatus status, int id) {
        this.status = status;
        this.id = id;
    }

    public Order(BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax, OrderStatus status) {
        this.total = total;
        this.currency = currency;
        this.items = items;
        this.tax = tax;
        this.status = status;
    }

    public static Order newBlankOrder() {
        return new Order(new BigDecimal("0.00"),"EUR", new ArrayList<>(), new BigDecimal("0.00"),OrderStatus.CREATED);
    }

    public void ship(ShipmentService shipmentService) {
        if (isStatus(CREATED) || isStatus(REJECTED)) throw new OrderCannotBeShippedException();
        if (isStatus(SHIPPED)) throw new OrderCannotBeShippedTwiceException();

        shipmentService.ship(this);
        status = OrderStatus.SHIPPED;
    }

    public void approve(OrderApprovalRequest request) {
        if (isStatus(SHIPPED)) throw new ShippedOrdersCannotBeChangedException();
        if (request.isApproved() && isStatus(REJECTED)) throw new RejectedOrderCannotBeApprovedException();
        if (!request.isApproved() && isStatus(APPROVED)) throw new ApprovedOrderCannotBeRejectedException();

        status = request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }

    public boolean isStatus(OrderStatus status) {
        return this.getStatus().equals(status);
    }

    public void updateOrderWithOrderItems(){
        updateTax();
        updateTotal();
    }

    private void updateTotal() {
        total = items.stream()
                .map(item -> item.getTaxedAmount())
                .reduce(total, (a, b) -> a.add(b));
    }

    private void updateTax() {
        tax = items.stream()
                .map(item -> item.getTax())
                .reduce(tax, (a, b) -> a.add(b));
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

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

}
