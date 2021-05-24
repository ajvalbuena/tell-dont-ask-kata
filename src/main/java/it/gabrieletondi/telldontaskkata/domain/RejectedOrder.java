package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.NotShippeableOrder;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;

import java.math.BigDecimal;
import java.util.List;

public class RejectedOrder extends NotShippeableOrder {

    public RejectedOrder(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        super(id, total, currency, items, tax);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        if (OrderStatus.APPROVED.equals(request.getNextStatus())) throw new RejectedOrderCannotBeApprovedException();
        return this;
    }

}
