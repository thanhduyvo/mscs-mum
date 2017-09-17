package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAO implements ILineItemDAO {

    private DbConnection dbConnection;

    public LineItemDAO() {
        dbConnection = new DbConnection();
    }

    private LineItemDO extractLineItemFromResultSet(ResultSet rs) throws SQLException {
        LineItemDO lineItem = new LineItemDO();
        lineItem.setId( rs.getString("id"));
        lineItem.setOrderId( rs.getString("orderId"));
        lineItem.setProductId( rs.getString("productId"));
        lineItem.setProductName( rs.getString("productName"));
        lineItem.setQuantity(rs.getInt("quantity"));
        lineItem.setUnitCost(rs.getDouble("unitCost"));
        lineItem.setSubtotal(rs.getDouble("subtotal"));
        return lineItem;
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

    @Override
    public List<LineItemDO> getLineItemsByOrder(String orderId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM LineItem WHERE orderId=?");
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            List<LineItemDO> lineItems = new ArrayList<LineItemDO>();
            while(rs.next())
            {
                LineItemDO lineItem = extractLineItemFromResultSet(rs);
                lineItems.add(lineItem);
            }
            return lineItems;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}