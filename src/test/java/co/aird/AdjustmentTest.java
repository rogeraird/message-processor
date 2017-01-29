package co.aird;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdjustmentTest {

    @Test
    public void getProductShouldReturnCorrectProductName() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        assertThat(adjustment.getProduct(), is("apple"));
    }

    @Test
    public void getProductNameShouldReturnCorrectProductNameForDifferentProduct() {
        Adjustment adjustment = new Adjustment("banana", AdjustmentOperation.ADD, BigDecimal.ZERO);
        assertThat(adjustment.getProduct(), is("banana"));
    }

    @Test
    public void getAdjustmentOperationShouldReturnCorrectAdjustmentOperation() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        assertThat(adjustment.getAdjustmentOperation(), is(AdjustmentOperation.ADD));
    }

    @Test
    public void getAdjustmentOperationReturnCorrectAdjustmentOperationForDifferentOperation() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.SUBTRACT, BigDecimal.ZERO);
        assertThat(adjustment.getAdjustmentOperation(), is(AdjustmentOperation.SUBTRACT));
    }

    @Test
    public void getAmountShouldReturnCorrectAmount() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        assertThat(adjustment.getAmount(), is(BigDecimal.ZERO));
    }

    @Test
    public void getAmountShouldReturnCorrectAmountForDifferentValue() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ONE);
        assertThat(adjustment.getAmount(), is(BigDecimal.ONE));
    }

    @Test
    public void toStringForAddShouldReturnCorrectValue() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        assertThat(adjustment.toString(), is("Add 0.00 to apple's value"));
    }

    @Test
    public void toStringForSubtractShouldReturnCorrectValue() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.SUBTRACT, BigDecimal.ZERO);
        assertThat(adjustment.toString(), is("Subtract 0.00 from apple's value"));
    }

    @Test
    public void toStringForMultiplyShouldReturnCorrectValue() {
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.MULTIPLY, BigDecimal.ONE);
        assertThat(adjustment.toString(), is("Multiply apple's value by 1.00"));
    }


}
