package mpp.ssa.bus;

import mpp.ssa.dao.CustomerDAO;
import mpp.ssa.dao.CustomerDO;
import mpp.ssa.dao.UserDAO;
import mpp.ssa.dao.UserDO;
import mpp.ssa.domain.Customer;
import mpp.ssa.util.SecurityHelper;

import java.sql.SQLException;

public class CustomerBUS implements ICustomerBUS {

    private CustomerDAO customerDAO;
    private UserDAO userDAO;

    private CustomerBUS() {
        customerDAO = new CustomerDAO();
        userDAO = new UserDAO();
    }

    private static CustomerBUS customerBUS;

    public static CustomerBUS getCustomerBUS() {
        if(customerBUS == null) {
            customerBUS = new CustomerBUS();
        }

        return customerBUS;
    }

    @Override
    public boolean login(String username, String password) {

        try {
            String passwordHashString = SecurityHelper.hashMD5String(password.trim());
            UserDO user = userDAO.getUserByUserNameAndPassword(username.trim(), passwordHashString.toLowerCase());
            if(user != null) {
                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
    }

    @Override
    public Customer register(Customer customer) {

        try {
            UserDO user = new UserDO();
            user.setUsername(customer.getUsername());
            user.setPassword(SecurityHelper.hashMD5String(customer.getPassword()).toLowerCase());
            boolean retValue = userDAO.insertUser(user);
            if(retValue) {
                CustomerDO customerDO = new CustomerDO();
                customerDO.setUsername(customer.getUsername());
                customerDO.setCustomerName(customer.getCustomerName());
                customerDO.setAddress(customer.getAddress());
                customerDO.setEmail(customer.getEmail());
                customerDO.setBankCardNo(customer.getBankCardNo());
                customerDO.setShippingAddress(customer.getShippingAddress());
                retValue = customerDAO.insertCustomer(customerDO);
                if(retValue) {
                    // find customer
                    CustomerDO foundCustomer = customerDAO.getCustomerByUsername(customer.getUsername());
                    if(foundCustomer != null) {
                        customer.setCustomerId(foundCustomer.getId());
                        return customer;
                    }
                }
            }

            return customer;
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public Customer getCustomerByUsername(String username) {

        try {
            CustomerDO customerDO = customerDAO.getCustomerByUsername(username);
            if(customerDO != null) {
                return new Customer(customerDO.getCustomerName(), customerDO.getAddress(), customerDO.getEmail(),
                        customerDO.getBankCardNo(), customerDO.getShippingAddress());
            }
        } catch (SQLException ex) {
        }

        return null;
    }
}