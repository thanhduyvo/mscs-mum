package mpp.ssa.dao;

public class OrderDO {

    public OrderDO() {
    }

    public OrderDO(String id, String customerId, String dateCreated, String dateShipped,
                   String status, String bankCardNo, String shippingAddress, double shippingCost) {
        setId(id);
        setCustomerId(customerId);
        setDateCreated(dateCreated);
        setDateShipped(dateShipped);
        setStatus(status);
        setBankCardNo(bankCardNo);
        setShippingAddress(shippingAddress);
        setShippingCost(shippingCost);
    }

    private String id;

    private String customerId;

    private String dateCreated;

    private String dateShipped;

    private String status;

    private String bankCardNo;

    private String shippingAddress;

    private double shippingCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(String dateShipped) {
        this.dateShipped = dateShipped;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }
}