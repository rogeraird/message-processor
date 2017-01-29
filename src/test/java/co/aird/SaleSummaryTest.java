package co.aird;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SaleSummaryTest {

    @Test
    public void saleValueShouldStartAtZero() {
        SaleSummary saleSummary = new SaleSummary("apple");

        assertThat(saleSummary.getTotalValue(), is(BigDecimal.ZERO));
    }

    @Test
    public void addingSaleShouldAddToValue() {
        SaleSummary saleSummary = new SaleSummary("apple");
        saleSummary.addSale(new BigDecimal(0.1));

        assertThat(saleSummary.getTotalValue(), is(new BigDecimal(0.1)));
    }

    @Test
    public void toStringShouldReturnTotalSalesProductNameAndTotalValue() {
        SaleSummary saleSummary = new SaleSummary("apple");
        saleSummary.addSale(new BigDecimal(0.2));
        saleSummary.addSale(new BigDecimal(0.3));

        assertThat(saleSummary.toString(), is("2 sales of apple totalling 0.50"));
    }

}