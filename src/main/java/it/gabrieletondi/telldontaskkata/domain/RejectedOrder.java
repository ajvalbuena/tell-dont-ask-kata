package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;

public class RejectedOrder extends Order {
    public RejectedOrder(int id) {
        super(OrderStatus.REJECTED, id);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        if (request.isApproved()) throw new RejectedOrderCannotBeApprovedException();
        return this;
    }
}
