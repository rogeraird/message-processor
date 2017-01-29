package co.aird;


import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class SaleTest {


    private Sale appleSale;
    private Sale bananaSale;

    @Before
    public void setup() {
        this.appleSale = new Sale("apple", new BigDecimal(0.1));
        this.bananaSale = new Sale("banana", new BigDecimal(0.12));
    }

    @Test
    public void getPriceShouldReturnCorrectValue() {
        assertThat(appleSale.getPrice(), is(new BigDecimal(0.1)));
    }


    @Test
    public void getPriceOfSingleSaleMessageWithDifferentPriceShouldReturnCorrectValue() {
        assertThat(bananaSale.getPrice(), is(new BigDecimal(0.12)));
    }

    @Test
    public void getProductShouldReturnCorrectValue() {
        assertThat(appleSale.getProduct(), is("apple"));
    }

    @Test
    public void salesWithTheSameProductAndPriceShouldBeEqual() {
        Sale secondAppleSale = new Sale("apple", new BigDecimal(0.1));

        assertThat(appleSale, is(equalTo(secondAppleSale)));
    }

    @Test
    public void salesOfSameProductAtDifferentPricesShouldNotBeEqual() {
        Sale secondAppleSale = new Sale("apple", new BigDecimal(0.11));

        assertThat(appleSale, is(not(equalTo(secondAppleSale))));
    }

    @Test
    public void salesOfDifferentProductsAtSamePricesShouldNotBeEqual() {
        Sale secondBananaSale = new Sale("banana", new BigDecimal(0.1));

        assertThat(secondBananaSale, is(not(equalTo(appleSale))));
    }

    @Test
    public void salesOfDifferentProductsAtDifferentPricesShouldNotBeEqual() {
        assertThat(appleSale, is(not(equalTo(bananaSale))));
    }

    @Test
    public void hashCodeShouldReturnSameValueForEquivalentSales() {
        Sale secondAppleSale = new Sale("apple", new BigDecimal(0.1));

        assertThat(appleSale.hashCode(), is(secondAppleSale.hashCode()));
    }

    @Test
    public void hashCodeShouldReturnDifferentValuesForUnequalSales() {
        assertThat(appleSale.hashCode(), is(not(equalTo(bananaSale.hashCode()))));
    }

    @Test
    public void saleShouldNotEqualObjectOfDifferentClass() {
        assertThat(appleSale, is(not(equalTo(""))));
    }
}