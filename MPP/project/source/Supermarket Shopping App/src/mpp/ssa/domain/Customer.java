package mpp.ssa.domain;

import java.util.*;

public class Customer extends User {

    /**
     * Default constructor
     */
    public Customer() {
        setOrderList(new ArrayList<Order>());
        setShoppingCart(new ShoppingCart());
    }

    public Customer(String customerName, String address, String email, String bankCardNo, String shippingAddress) {
        setOrderList(new ArrayList<Order>());
        setShoppingCart(new ShoppingCart());
        setCustomerName(customerName);
        setAddress(address);
        setEmail(email);
        setBankCardNo(bankCardNo);
        setShippingAddress(shippingAddress);
    }

    private List<Order> orderList;

    private ShoppingCart shoppingCart;

    private int customerId;

    private String customerName;

    private String address;

    private String email;

    private String bankCardNo;

    private String shippingAddress;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}