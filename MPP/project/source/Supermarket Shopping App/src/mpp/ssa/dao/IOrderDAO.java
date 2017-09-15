package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {

    OrderDO getOrder(String id) throws SQLException;

    List<OrderDO> getOrdersByCustomer(String customerId) throws SQLException;

    boolean insertOrder(OrderDO order) throws SQLException;
}