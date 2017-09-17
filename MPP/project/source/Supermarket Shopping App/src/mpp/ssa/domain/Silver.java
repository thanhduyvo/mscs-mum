package mpp.ssa.domain;

public class Silver extends UserType {

    @Override
    public String getTypeName() {
        return "Silver";
    }

    @Override
    public double calcDiscount(double totalAmount) {
        return totalAmount * 0.1;
    }
}