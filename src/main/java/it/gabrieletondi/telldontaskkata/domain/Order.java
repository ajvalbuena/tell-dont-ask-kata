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


    public void newBlankOrder(){
        setStatus(OrderStatus.CREATED);
        setItems(new ArrayList<>());
        setCurrency("EUR");
        setTotal(new BigDecimal("0.00"));
        setTax(new BigDecimal("0.00"));
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
