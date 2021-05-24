package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.OrderStatus;

public class OrderApprovalRequest {
    private final int orderId;
    private final OrderStatus nextStatus;

    public OrderApprovalRequest(int orderId, OrderStatus nextStatus) {
        this.orderId = orderId;
        this.nextStatus = nextStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderStatus getNextStatus() {
        return nextStatus;
    }
}
