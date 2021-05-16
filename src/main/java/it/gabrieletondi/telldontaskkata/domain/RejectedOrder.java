package it.gabrieletondi.telldontaskkata.domain;

public class RejectedOrder extends Order{
    public RejectedOrder(int id) {
        super(OrderStatus.REJECTED, id);
    }
}
