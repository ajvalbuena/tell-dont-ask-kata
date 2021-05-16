package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.*;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class ShippedOrder extends Order{

    public ShippedOrder(int id) {
        super(OrderStatus.SHIPPED, id);
    }

    @Override
    public Order approve(OrderApprovalRequest request) {
       throw new ShippedOrdersCannotBeChangedException();
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        throw new OrderCannotBeShippedTwiceException();
    }
}
