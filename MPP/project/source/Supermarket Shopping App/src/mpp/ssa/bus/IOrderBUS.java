package mpp.ssa.bus;

import mpp.ssa.domain.Order;

public interface IOrderBUS {

    boolean placeOrder(Order order);
}