package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;
import it.gabrieletondi.telldontaskkata.useCase.OrderCannotBeShippedException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreatedOrder extends Order {

    public CreatedOrder(int id, BigDecimal total, String currency, ArrayList<OrderItem> items, BigDecimal tax) {
        super(id, total, currency, items, tax);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        return request.isApproved() ? this.approveOrder(): this.rejectOrder();
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        throw new OrderCannotBeShippedException();
    }
}
