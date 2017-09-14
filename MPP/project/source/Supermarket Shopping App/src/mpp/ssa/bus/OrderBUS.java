package mpp.ssa.bus;

import mpp.ssa.dao.OrderDAO;
import mpp.ssa.dao.OrderDO;
import mpp.ssa.domain.Order;

import java.sql.SQLException;

public class OrderBUS implements IOrderBUS {

    private OrderDAO orderDAO;

    private OrderBUS() {
        orderDAO = new OrderDAO();
    }

    private static OrderBUS orderBUS;

    public static OrderBUS getOrderBUS() {
        if(orderBUS == null) {
            orderBUS = new OrderBUS();
        }

        return orderBUS;
    }

    @Override
    public boolean placeOrder(Order order) {

        try {
            return orderDAO.insertOrder(new OrderDO(order.getCustomer().getCustomerId(),
                    order.getDateCreated().toString(),
                    order.getDateShipped().toString(),
                    order.getStatus(),
                    order.getBankCardNo(),
                    order.getShippingAddress(),
                    order.getShippingCost()));
        } catch (SQLException ex) {
        }

        return false;
    }
}