package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreatedOrder extends Order {

    public CreatedOrder() {
        super(new BigDecimal("0.00"), "EUR", new ArrayList<>(), new BigDecimal("0.00"),OrderStatus.CREATED);
    }

    public CreatedOrder(int id){
        super(OrderStatus.CREATED, id);
    }

}
