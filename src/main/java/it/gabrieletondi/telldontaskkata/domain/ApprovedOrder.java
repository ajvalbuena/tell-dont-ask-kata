package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;

import java.math.BigDecimal;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.APPROVED;

public class ApprovedOrder extends Order{

    public ApprovedOrder(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        super(id,total, currency,items, tax);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        if (!request.isApproved()) throw new ApprovedOrderCannotBeRejectedException();
        return this;
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
       return true;
    }


}
