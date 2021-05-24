package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.NotShippeableOrder;
import it.gabrieletondi.telldontaskkata.useCase.OrderApprovalRequest;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreatedOrder extends NotShippeableOrder {

    public CreatedOrder(int id, BigDecimal total, String currency, ArrayList<OrderItem> items, BigDecimal tax) {
        super(id, total, currency, items, tax);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
        return OrderStatus.APPROVED.equals(request.getNextStatus()) ? this.approveOrder() : this.rejectOrder();
    }
}
