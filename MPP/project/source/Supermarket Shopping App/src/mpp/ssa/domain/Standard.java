package mpp.ssa.domain;

public class Standard extends UserType  {

    @Override
    public String getTypeName() {
        return "Standard";
    }

    @Override
    public double calcDiscount(double totalAmount) {
        return 0;
    }
}