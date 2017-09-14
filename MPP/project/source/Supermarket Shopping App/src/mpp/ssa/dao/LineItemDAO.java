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
            PreparedStatement ps = connection.prepareStatement("INSERT INTO LineItem VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, lineItem.getOrderId());
            ps.setInt(2, lineItem.getProductId());
            ps.setString(3, lineItem.getProductName());
            ps.setInt(4, lineItem.getQuantity());
            ps.setDouble(5, lineItem.getUnitCost());
            ps.setDouble(6, lineItem.getSubtotal());
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