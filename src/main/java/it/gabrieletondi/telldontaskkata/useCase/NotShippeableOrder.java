package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public abstract  class NotShippeableOrder extends Order {

    public NotShippeableOrder(int id, BigDecimal total, String currency, List<OrderItem> items, BigDecimal tax) {
        super(id, total, currency, items, tax);
    }

    @Override
    public boolean isOrderReadyToBeShipped() {
        return false;
    }

    @Override
    public void cannotBeShipped() {
        throw new OrderCannotBeShippedException();
    }
}
