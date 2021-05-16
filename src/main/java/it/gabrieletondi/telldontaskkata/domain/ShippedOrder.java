package it.gabrieletondi.telldontaskkata.domain;

public class ShippedOrder extends Order{

    public ShippedOrder(int id) {
        super(OrderStatus.SHIPPED, id);
    }
}
