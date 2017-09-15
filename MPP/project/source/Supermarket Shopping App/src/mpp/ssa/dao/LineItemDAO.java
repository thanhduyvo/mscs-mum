package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LineItemDAO implements ILineItemDAO {

    private DbConnection dbConnection;

    public LineItemDAO() {
        dbConnection = new DbConnection();
    }

    @Override
    public boolean insertLineItem(LineItemDO lineItem) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO LineItem VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, lineItem.getId());
            ps.setString(2, lineItem.getOrderId());
            ps.setString(3, lineItem.getProductId());
            ps.setString(4, lineItem.getProductName());
            ps.setInt(5, lineItem.getQuantity());
            ps.setDouble(6, lineItem.getUnitCost());
            ps.setDouble(7, lineItem.getSubtotal());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return false;
    }
}