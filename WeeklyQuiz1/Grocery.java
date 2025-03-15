import java.math.BigDecimal;

class Grocery extends Product {
    public Grocery(String name, BigDecimal price, double weight) {
        super(name, price, weight);
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return new BigDecimal("2000");
    }
}