package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderStatus;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class OrderApprovalUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final OrderApprovalUseCase useCase = new OrderApprovalUseCase(orderRepository);
    private Order initialOrder(OrderStatus status) {return new Order(status, 1);}

    @Test
    public void approvedExistingOrder() throws Exception {
        Order initialOrder = initialOrder(OrderStatus.CREATED);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, true);

        useCase.run(request);
        final Order savedOrder = orderRepository.getSavedOrder();

        assertThat(savedOrder.getStatus(), is(OrderStatus.APPROVED));
    }


    @Test
    public void rejectedExistingOrder() throws Exception {
        Order initialOrder = initialOrder(OrderStatus.CREATED);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, false);

        useCase.run(request);
        final Order savedOrder = orderRepository.getSavedOrder();

        assertThat(savedOrder.getStatus(), is(OrderStatus.REJECTED));
    }

    @Test(expected = RejectedOrderCannotBeApprovedException.class)
    public void cannotApproveRejectedOrder() throws Exception {
        Order initialOrder = initialOrder(OrderStatus.REJECTED);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, true);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = ApprovedOrderCannotBeRejectedException.class)
    public void cannotRejectApprovedOrder() throws Exception {
        Order initialOrder = initialOrder(OrderStatus.APPROVED);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, false);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = ShippedOrdersCannotBeChangedException.class)
    public void shippedOrdersCannotBeApproved() throws Exception {
        Order initialOrder = new Order(OrderStatus.SHIPPED,1);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, true);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }

    @Test(expected = ShippedOrdersCannotBeChangedException.class)
    public void shippedOrdersCannotBeRejected() throws Exception {
        Order initialOrder = new Order(OrderStatus.SHIPPED,1);
        orderRepository.addOrder(initialOrder);
        OrderApprovalRequest request = new OrderApprovalRequest(1, false);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
    }
}
