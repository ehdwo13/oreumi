import java.math.BigDecimal;

class LargeAppliance extends Product {
    public LargeAppliance(String name, BigDecimal price, double weight) {
        super(name, price, weight);
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return BigDecimal.ZERO;
    }
}