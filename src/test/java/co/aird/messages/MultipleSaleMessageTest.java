package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MultipleSaleMessageTest {
    private Sale appleSale;
    private MultipleSaleMessage multipleSaleMessage;
    private List<Sale> sales;
    private List<Adjustment> adjustments;

    private void setUpMultipleSaleMessage(int numberOfSales) {
        appleSale = new Sale("apple", new BigDecimal(0.1));
        multipleSaleMessage = new MultipleSaleMessage(appleSale, numberOfSales);
        sales = new ArrayList<>();
        adjustments = new ArrayList<>();
    }


    @Test
    public void processingMultipleSaleMessagesShouldAddCorrectNumberOfSalesToList() {
        setUpMultipleSaleMessage(10);

        multipleSaleMessage.process(sales, adjustments);
        assertThat(sales.size(), is(10));
    }

    @Test
    public void processingMultipleSaleMessagesShouldAddCorrectDifferentNumberOfSalesToList() {
        setUpMultipleSaleMessage(15);

        multipleSaleMessage.process(sales, adjustments);
        assertThat(sales.size(), is(15));
    }

    @Test
    public void processingMultipleSaleMessageShouldNotModifyAdjustments() {
        setUpMultipleSaleMessage(10);

        multipleSaleMessage.process(sales, adjustments);
        assertThat(adjustments.size(), is(0));
    }

}