package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreatedOrder extends Order {

    public CreatedOrder() {
        super(new BigDecimal("0.00"), "EUR", new ArrayList<>(), new BigDecimal("0.00"));
    }

    public CreatedOrder(int id){
        super(id);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        return request.isApproved() ? this.approve2(): this.reject();
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        throw new OrderCannotBeShippedException();
    }
}
