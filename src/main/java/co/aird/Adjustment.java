package co.aird;

import java.math.BigDecimal;

public class Adjustment {

    private final AdjustmentOperation adjustmentOperation;
    private final BigDecimal amount;
    private String product;

    public Adjustment(String product, AdjustmentOperation adjustmentOperation, BigDecimal amount) {
        this.adjustmentOperation = adjustmentOperation;
        this.amount = amount;
        this.product = product;
    }

    public AdjustmentOperation getAdjustmentOperation() {
        return adjustmentOperation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public String toString() {
        if (adjustmentOperation == AdjustmentOperation.ADD) {
            return String.format("Add %s to %s's value",
                    amount.setScale(2, BigDecimal.ROUND_HALF_UP), product);
        } else if (adjustmentOperation == AdjustmentOperation.SUBTRACT) {
            return String.format("Subtract %s from %s's value",
                    amount.setScale(2, BigDecimal.ROUND_HALF_UP), product);
        } else if (adjustmentOperation == AdjustmentOperation.MULTIPLY) {
            return String.format("Multiply %s's value by %s", product,
                    amount.setScale(2, BigDecimal.ROUND_HALF_UP), product);
        }
        return "ERROR: Unimplemented operation type " + adjustmentOperation;
    }
}
