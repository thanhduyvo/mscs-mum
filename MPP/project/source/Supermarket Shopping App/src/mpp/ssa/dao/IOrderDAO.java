package mpp.ssa.dao;

import java.sql.SQLException;

public interface IOrderDAO {

    boolean insertOrder(OrderDO order) throws SQLException;
}