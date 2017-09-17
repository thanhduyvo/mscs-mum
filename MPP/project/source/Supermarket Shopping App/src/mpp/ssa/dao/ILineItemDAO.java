package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface ILineItemDAO {

    boolean insertLineItem(LineItemDO lineItem) throws SQLException;

    List<LineItemDO> getLineItemsByOrder(String orderId) throws SQLException;
}