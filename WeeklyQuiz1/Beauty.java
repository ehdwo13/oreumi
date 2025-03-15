import java.math.BigDecimal;

class Beauty extends Product {
    public Beauty(String name, BigDecimal price, double weight) {
        super(name, price, weight);
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return new BigDecimal("10000");
    }
}