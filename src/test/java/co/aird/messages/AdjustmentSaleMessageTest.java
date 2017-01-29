package co.aird.messages;

import co.aird.Adjustment;
import co.aird.AdjustmentOperation;
import co.aird.Sale;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AdjustmentSaleMessageTest {

    private AdjustmentSaleMessage adjustmentSaleMessage;
    private List<Sale> sales;
    private Adjustment adjustment;
    private Sale appleSale;
    private List<Adjustment> adjustments;

    @Before
    public void setup() {
        adjustment = new Adjustment("apple", AdjustmentOperation.ADD, new BigDecimal(0));
        appleSale = new Sale("apple", new BigDecimal(0.1));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, appleSale);

        sales = new ArrayList<>();
        adjustments = new ArrayList<>();
    }

    @Test
    public void processMessageShouldAddSaleToList() {
        adjustmentSaleMessage.process(sales, adjustments);
        assertThat(sales.size(), is(1));
        assertThat(sales.get(0), is(appleSale));
    }

    @Test
    public void processMessageShouldAddAdjustmentToList() {
        adjustmentSaleMessage.process(sales, adjustments);
        assertThat(adjustments.size(), is(1));
        assertThat(adjustments.get(0), is(adjustment));
    }

    @Test
    public void addingToApplesShouldModifyPreviousSales() {
        sales.add(appleSale);
        adjustment = new Adjustment("apple", AdjustmentOperation.ADD, new BigDecimal(0.1));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, appleSale);
        adjustmentSaleMessage.process(sales, adjustments);

        Sale expectedSale = new Sale("apple", new BigDecimal(0.2));

        assertThat(sales.get(0), is(expectedSale));
    }

    @Test
    public void subtractingFromApplesShouldModifyPreviousSales() {
        sales.add(appleSale);
        adjustment = new Adjustment("apple", AdjustmentOperation.SUBTRACT, new BigDecimal(0.05));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, appleSale);
        adjustmentSaleMessage.process(sales, adjustments);

        Sale expectedSale = new Sale("apple", new BigDecimal(0.05));

        assertThat(sales.get(0), is(expectedSale));
    }

    @Test
    public void multiplyingApplesShouldModifyPreviousSales() {
        sales.add(appleSale);
        adjustment = new Adjustment("apple", AdjustmentOperation.MULTIPLY, new BigDecimal(2));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, appleSale);
        adjustmentSaleMessage.process(sales, adjustments);

        Sale expectedSale = new Sale("apple", new BigDecimal(0.2));

        assertThat(sales.get(0), is(expectedSale));
    }

    @Test
    public void multiplyingApplesShouldNotModifyNewSale() {
        sales.add(appleSale);
        adjustment = new Adjustment("apple", AdjustmentOperation.ADD, new BigDecimal(0.1));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, new Sale("apple", new BigDecimal(0.3)));
        adjustmentSaleMessage.process(sales, adjustments);

        assertThat(sales.get(1).getPrice(), is(new BigDecimal(0.3)));
    }

    @Test
    public void multiplyingApplesShouldNotModifyBananas() {
        sales.add(new Sale("banana", new BigDecimal(0.1)));
        adjustment = new Adjustment("apple", AdjustmentOperation.MULTIPLY, new BigDecimal(2));
        adjustmentSaleMessage = new AdjustmentSaleMessage(adjustment, new Sale("apple", new BigDecimal(0.3)));
        adjustmentSaleMessage.process(sales, adjustments);

        assertThat(sales.get(0).getPrice(), is(new BigDecimal(0.1)));
    }



}