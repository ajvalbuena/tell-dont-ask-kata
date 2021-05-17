package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;

import java.math.BigDecimal;
import java.util.List;

public class RejectedOrder extends Order {

    public RejectedOrder(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        super(id,total, currency,items, tax);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        if (request.isApproved()) throw new RejectedOrderCannotBeApprovedException();
        return this;
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        throw new OrderCannotBeShippedException();
    }
}
