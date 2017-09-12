package mpp.ssa.dao;

import java.sql.SQLException;

public interface ICustomerDAO {

    CustomerDO getCustomerByUsername(String username) throws SQLException;

    boolean insertCustomer(CustomerDO customer) throws SQLException;

    boolean updateCustomer(CustomerDO customer) throws SQLException;
}