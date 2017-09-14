package mpp.ssa.bus;

import mpp.ssa.domain.Order;
import java.util.List;

public interface IOrderBUS {

    boolean placeOrder(Order order);

    List<Order> getOrdersByCustomer(int customerId);
}