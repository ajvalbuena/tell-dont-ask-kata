package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreatedOrder extends Order {

    public CreatedOrder() {
        super(new BigDecimal("0.00"), "EUR", new ArrayList<>(), new BigDecimal("0.00"),OrderStatus.CREATED);
    }

    public CreatedOrder(int id){
        super(OrderStatus.CREATED, id);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        this.status = request.isApproved() ? OrderStatus.APPROVED : OrderStatus.REJECTED;
        return this;
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        throw new OrderCannotBeShippedException();
    }
}
