package mpp.ssa.domain;

public class Silver extends UserType {

    @Override
    public double calcDiscount() {
        return 10;
    }
}