package co.aird.messages;

import co.aird.Sale;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AbstractMessageTest {

    @Test
    public void getSaleShouldReturnCorrectValue() {
        Sale appleSale = new Sale("apple", new BigDecimal(0.1));
        AbstractMessage abstractMessage = new MockMessage(appleSale);

        assertThat(abstractMessage.getSale(), is(appleSale));
    }

    @Test
    public void getSaleShouldReturnCorrectValueWhenDifferentSaleIsGiven() {
        Sale bananaSale = new Sale("banana", new BigDecimal(0.11));
        AbstractMessage abstractMessage = new MockMessage(bananaSale);

        assertThat(abstractMessage.getSale(), is(bananaSale));
    }

}