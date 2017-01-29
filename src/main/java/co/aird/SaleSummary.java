package co.aird;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleSummary {
    private String product;
    private int numberOfSales;
    private BigDecimal totalValue;

    public SaleSummary(String product) {
        this.product = product;
        this.numberOfSales = 0;
        this.totalValue = BigDecimal.ZERO;
    }

    public void addSale(BigDecimal saleValue) {
        this.totalValue = totalValue.add(saleValue);
        this.numberOfSales++;
    }

    @Override
    public String toString() {
        return String.format("%d sales of %s totalling %s", numberOfSales, product,
                totalValue.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
}
