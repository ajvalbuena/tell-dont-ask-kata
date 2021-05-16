package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;

public class ApprovedOrder extends Order{
    public ApprovedOrder(int id) {
        super(OrderStatus.APPROVED, id);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        if (!request.isApproved()) throw new ApprovedOrderCannotBeRejectedException();
        return this;
    }
}
