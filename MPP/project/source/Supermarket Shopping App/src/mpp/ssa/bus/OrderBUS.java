package mpp.ssa.bus;

import mpp.ssa.dao.LineItemDAO;
import mpp.ssa.dao.OrderDAO;
import mpp.ssa.dao.OrderDO;
import mpp.ssa.dao.ProductDO;
import mpp.ssa.domain.Order;
import mpp.ssa.domain.Product;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBUS implements IOrderBUS {

    private OrderDAO orderDAO;
    private LineItemDAO lineItemDAO;

    private OrderBUS() {
        orderDAO = new OrderDAO();
        lineItemDAO = new LineItemDAO();
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
            boolean retValue = orderDAO.insertOrder(new OrderDO(order.getCustomer().getCustomerId(),
                    order.getDateCreated().toString(),
                    order.getDateShipped().toString(),
                    order.getStatus(),
                    order.getBankCardNo(),
                    order.getShippingAddress(),
                    order.getShippingCost()));
            if(retValue) {

            }
        } catch (SQLException ex) {
        }

        return false;
    }

    @Override
    public List<Order> getOrdersByCustomer(int customerId) {
        try {
            List<OrderDO> orderDOList = orderDAO.getOrdersByCustomer(customerId);
            if(orderDOList != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                List<Order> orders = new ArrayList<Order>();
                for(OrderDO orderDO : orderDOList) {
                    Date dateCreated = df.parse(orderDO.getDateCreated());
                    Date dateShipped = df.parse(orderDO.getDateShipped());
                    orders.add(new Order(orderDO.getId(),
                            dateCreated,
                            dateShipped,
                            orderDO.getStatus(),
                            orderDO.getBankCardNo(),
                            orderDO.getShippingAddress(),
                            orderDO.getShippingCost()));
                }
                return orders;
            }
        } catch (ParseException ex) {
        } catch (SQLException ex) {
        }

        return null;
    }
}