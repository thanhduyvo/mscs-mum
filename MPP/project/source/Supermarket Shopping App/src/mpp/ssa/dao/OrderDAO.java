package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO implements IOrderDAO {

    private DbConnection dbConnection;

    public OrderDAO() {
        dbConnection = new DbConnection();
    }

    @Override
    public boolean insertOrder(OrderDO order) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Order VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getDateCreated());
            ps.setString(3, order.getDateShipped());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getBankCardNo());
            ps.setString(6, order.getShippingAddress());
            ps.setDouble(7, order.getShippingCost());
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