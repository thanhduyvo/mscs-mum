package mpp.ssa.bus;

import mpp.ssa.domain.Customer;

public interface ICustomerBUS {

    boolean login(String username, String password);

    Customer register(Customer customer);

    Customer getCustomerByUsername(String username);
}