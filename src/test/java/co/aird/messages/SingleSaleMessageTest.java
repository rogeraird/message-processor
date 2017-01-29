package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SingleSaleMessageTest {

    @Test
    public void processShouldAddSaleToListOfSales() {
        List<Sale> saleList = new ArrayList<>();
        Sale appleSale = new Sale("apple", new BigDecimal(0.1));
        SingleSaleMessage singleSaleMessage = new SingleSaleMessage(appleSale);

        singleSaleMessage.process(saleList, new ArrayList<>());
        assertThat(saleList.size(), is(1));
        assertThat(saleList.get(0), is(appleSale));
    }

    @Test
    public void processShouldNotModifyAdjustments() {
        List<Sale> saleList = new ArrayList<>();
        List<Adjustment> adjustments = new ArrayList<>();
        Sale appleSale = new Sale("apple", new BigDecimal(0.1));
        SingleSaleMessage singleSaleMessage = new SingleSaleMessage(appleSale);

        singleSaleMessage.process(saleList, adjustments);
        assertThat(adjustments.size(), is(0));
    }
}