package it.gabrieletondi.telldontaskkata.domain;

public class ApprovedOrder extends Order{
    public ApprovedOrder(int id) {
        super(OrderStatus.APPROVED, id);
    }
}
