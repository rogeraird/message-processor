package co.aird;

import java.math.BigDecimal;
import java.util.Objects;

public class Sale {

    private String product;
    private BigDecimal price;

    public Sale(String product, BigDecimal price) {
        this.product = product;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sale)) {
            return false;
        }

        Sale otherSale = (Sale) o;

        return (getPrice().compareTo(otherSale.getPrice()) == 0) && getProduct().equals(otherSale.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.product, this.price);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
