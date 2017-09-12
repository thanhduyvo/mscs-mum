package mpp.ssa.bus;

import mpp.ssa.dao.UserDAO;
import mpp.ssa.dao.UserDO;
import mpp.ssa.domain.Customer;
import mpp.ssa.util.SecurityHelper;

import java.sql.SQLException;

public class CustomerBUS implements ICustomerBUS {

    private UserDAO userDAO;

    public CustomerBUS() {
        userDAO = new UserDAO();
    }

    @Override
    public boolean login(String username, String password) {

        try {
            UserDO user = userDAO.getUserByUsername(username.trim());
            String passwordHashString = SecurityHelper.hashMD5String(password.trim());
            if(user != null && user.getPassword() == passwordHashString) {
                return true;
            }
        } catch (SQLException ex) {
        }

        return false;
    }

    @Override
    public Customer register(Customer customer) {
        return null;
    }
}