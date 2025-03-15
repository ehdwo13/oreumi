import java.math.BigDecimal;

abstract class Product implements Promotion {
    protected String name;
    protected BigDecimal price;
    protected double weight;

    protected Product(String name, BigDecimal price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public BigDecimal getDiscountedPrice() {
        return price.subtract(getDiscountAmount());
    }

    public double getWeight() {
        return weight;
    }
}