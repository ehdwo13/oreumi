import java.math.BigDecimal;

class Cart {
    private final Product[] products;

    public Cart(Product[] products) {
        this.products = products;
    }

    public int calculateDeliveryCharge() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        double totalWeight = 0;
        for (Product product : products) {
            totalPrice = totalPrice.add(product.getDiscountedPrice());
            totalWeight += product.getWeight();
        }
        int deliveryCharge;
        if (totalWeight < 3) {
            deliveryCharge = 1000;
        } else if (totalWeight < 10) {
            deliveryCharge = 5000;
        } else {
            deliveryCharge = 10000;
        }
        if (totalPrice.compareTo(new BigDecimal("100000")) >= 0) {
            deliveryCharge = 0;
        } else if (totalPrice.compareTo(new BigDecimal("30000")) >= 0) {
            deliveryCharge -= 1000;
        }
        return deliveryCharge;
    }
}