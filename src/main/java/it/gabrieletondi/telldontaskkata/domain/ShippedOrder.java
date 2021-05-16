package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.useCase.ShippedOrdersCannotBeChangedException;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class ShippedOrder extends Order{

    public ShippedOrder(int id) {
        super(OrderStatus.SHIPPED, id);
    }

    public Order approve(OrderApprovalRequest request) {
       throw new ShippedOrdersCannotBeChangedException();
    }
}
