package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO implements ICustomerDAO {

    private DbConnection dbConnection;

    public CustomerDAO() {
        dbConnection = new DbConnection();
    }

    private CustomerDO extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        CustomerDO customer = new CustomerDO();
        customer.setId( rs.getString("id"));
        customer.setUsername( rs.getString("username"));
        customer.setCustomerName( rs.getString("customerName"));
        customer.setAddress(rs.getString("address"));
        customer.setEmail(rs.getString("email"));
        customer.setBankCardNo(rs.getString("bankCardNo"));
        customer.setShippingAddress(rs.getString("shippingAddress"));
        return customer;
    }

    public CustomerDO getCustomerByUsername(String username) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Customer WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractCustomerFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return null;
    }

    public boolean insertCustomer(CustomerDO customer) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getUsername());
            ps.setString(3, customer.getCustomerName());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getEmail());
            ps.setString(6, customer.getBankCardNo());
            ps.setString(7, customer.getShippingAddress());
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

    public boolean updateCustomer(CustomerDO customer) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Customer SET customerName=?, address=?, email=?, bankCardNo=?, shippingAddress=? WHERE username=?");
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getBankCardNo());
            ps.setString(5, customer.getShippingAddress());
            ps.setString(6, customer.getUsername());
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