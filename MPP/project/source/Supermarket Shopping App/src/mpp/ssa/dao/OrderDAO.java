package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {

    private DbConnection dbConnection;

    public OrderDAO() {
        dbConnection = new DbConnection();
    }

    private OrderDO extractOrderFromResultSet(ResultSet rs) throws SQLException {
        OrderDO order = new OrderDO();
        order.setId( rs.getInt("id"));
        order.setCustomerId( rs.getInt("customerId"));
        order.setDateCreated(rs.getString("dateCreated"));
        order.setDateShipped(rs.getString("dateShipped"));
        order.setStatus(rs.getString("status"));
        order.setBankCardNo(rs.getString("bankCardNo"));
        order.setShippingAddress(rs.getString("shippingAddress"));
        order.setShippingCost(rs.getDouble("shippingCost"));
        return order;
    }

    @Override
    public OrderDO getOrder(int id) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Order WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractOrderFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public List<OrderDO> getOrdersByCustomer(int customerId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Order WHERE customerId=?");
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            List<OrderDO> orders = new ArrayList<OrderDO>();
            while(rs.next())
            {
                OrderDO order = extractOrderFromResultSet(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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