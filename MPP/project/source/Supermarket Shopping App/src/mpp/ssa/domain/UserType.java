package mpp.ssa.domain;

public abstract class UserType {

    public static UserType getUserType(double totalOrderValue) {
        if(totalOrderValue <= 1000) {
            return new Standard();
        } else if(totalOrderValue > 1000 && totalOrderValue <= 2000) {
            return new Silver();
        } else {
            return new Gold();
        }
    }

    public abstract String getTypeName();
    public abstract double calcDiscount(double totalAmount);
}