package mpp.ssa;

import mpp.ssa.domain.Customer;

public class UserData {

    private Customer customer;

    public UserData() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void clear() {
        customer = null;
    }
}