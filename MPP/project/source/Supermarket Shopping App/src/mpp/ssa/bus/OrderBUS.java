package mpp.ssa.bus;

import mpp.ssa.dao.*;
import mpp.ssa.domain.LineItem;
import mpp.ssa.domain.Order;
import mpp.ssa.domain.Product;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
            UUID orderId = UUID.randomUUID();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            boolean retValue = orderDAO.insertOrder(
                    new OrderDO(
                        orderId.toString(),
                        order.getCustomer().getCustomerId(),
                        dateFormat.format(date),
                        dateFormat.format(date),
                        order.getStatus(),
                        order.getBankCardNo(),
                        order.getShippingAddress(),
                        order.getShippingCost()));
            if(retValue) {
                for(LineItem item : order.getLineItemList()) {
                    LineItemDO lineItemDO = new LineItemDO();
                    UUID lineItemId = UUID.randomUUID();
                    lineItemDO.setId(lineItemId.toString());
                    lineItemDO.setOrderId(orderId.toString());
                    lineItemDO.setProductId(item.getLineItemId());
                    lineItemDO.setProductName(item.getProductName());
                    lineItemDO.setQuantity(item.getQuantity());
                    lineItemDO.setUnitCost(item.getUnitCost());
                    lineItemDO.setSubtotal(item.getSubtotal());
                    lineItemDAO.insertLineItem(lineItemDO);
                }

                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
    }

    @Override
    public List<Order> getOrdersByCustomer(String customerId) {
        try {
            List<OrderDO> orderDOList = orderDAO.getOrdersByCustomer(customerId);
            if(orderDOList != null) {
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                List<Order> orders = new ArrayList<Order>();
                for(OrderDO orderDO : orderDOList) {
                    Date dateCreated = df.parse(orderDO.getDateCreated());
                    Date dateShipped = df.parse(orderDO.getDateShipped());
                    Order order = new Order(orderDO.getId(),
                            dateCreated,
                            dateShipped,
                            orderDO.getStatus(),
                            orderDO.getBankCardNo(),
                            orderDO.getShippingAddress(),
                            orderDO.getShippingCost());
                    List<LineItem> lineItems = LineItemBUS.getLineItemBUS()
                            .getLineItemsByOrder(orderDO.getId());
                    order.setLineItemList(lineItems);
                    orders.add(order);
                }
                return orders;
            }
        } catch (ParseException ex) {
        } catch (SQLException ex) {
        }

        return null;
    }
}