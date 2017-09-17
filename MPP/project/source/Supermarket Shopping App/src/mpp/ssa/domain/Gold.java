package mpp.ssa.domain;

public class Gold extends UserType  {

    @Override
    public String getTypeName() {
        return "Gold";
    }

    @Override
    public double calcDiscount(double totalAmount) {
        return totalAmount * 0.15;
    }
}