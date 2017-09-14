package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {

    List<OrderDO> getOrdersByCustomer(int customerId) throws SQLException;

    boolean insertOrder(OrderDO order) throws SQLException;
}