package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.*;

import java.math.BigDecimal;
import java.util.List;

import static it.gabrieletondi.telldontaskkata.domain.OrderStatus.*;

public class ShippedOrder extends Order{

    public ShippedOrder(int id){
        super(id);
    }
    public ShippedOrder(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        super(id,total, currency,items, tax);
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
