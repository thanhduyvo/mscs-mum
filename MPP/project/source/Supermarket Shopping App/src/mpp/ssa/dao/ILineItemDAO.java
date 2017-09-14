package mpp.ssa.dao;

import java.sql.SQLException;

public interface ILineItemDAO {

    boolean insertLineItem(LineItemDO lineItem) throws SQLException;
}