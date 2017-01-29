package co.aird;

import co.aird.messages.AdjustmentSaleMessage;
import co.aird.messages.Message;
import co.aird.messages.SingleSaleMessage;
import co.aird.util.MockSystemExiter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MessageProcessorTest {

    @Test
    public void shouldNotPrintWithLessThanTenMessages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MessageProcessor messageProcessor = new MessageProcessor(new PrintStream(out), new MockSystemExiter());
        SingleSaleMessage message = new SingleSaleMessage(new Sale("apple", new BigDecimal(0.1)));

        for (int i = 0; i < 9; i++) {
            messageProcessor.processMessage(message);
        }

        assertThat(new String(out.toByteArray()), is(""));
    }

    @Test
    public void shouldPrintAtTenMessages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MessageProcessor messageProcessor = new MessageProcessor(new PrintStream(out), new MockSystemExiter());
        SingleSaleMessage message = new SingleSaleMessage(new Sale("apple", new BigDecimal(0.1)));

        for (int i = 0; i < 10; i++) {
            messageProcessor.processMessage(message);
        }

        assertThat(new String(out.toByteArray()).trim(), is("10 sales of apple totalling 1.00"));
    }

    @Test
    public void shouldNotPrintAdjustmentsWithLessThanFiftyMessages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MessageProcessor messageProcessor = new MessageProcessor(new PrintStream(out), new MockSystemExiter());
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        AdjustmentSaleMessage message = new AdjustmentSaleMessage(adjustment, new Sale("apple", new BigDecimal(0.1)));

        for (int i = 0; i < 49; i++) {
            messageProcessor.processMessage(message);
        }

        assertFalse(new String(out.toByteArray()).contains("Add 0.00 to apple's value"));
    }

    @Test
    public void shouldPrintAdjustmentsAtFiftyMessages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MessageProcessor messageProcessor = new MessageProcessor(new PrintStream(out), new MockSystemExiter());
        Adjustment adjustment = new Adjustment("apple", AdjustmentOperation.ADD, BigDecimal.ZERO);
        AdjustmentSaleMessage message = new AdjustmentSaleMessage(adjustment, new Sale("apple", new BigDecimal(0.1)));

        for (int i = 0; i < 50; i++) {
            messageProcessor.processMessage(message);
        }

        assertTrue(new String(out.toByteArray()).trim().endsWith("Add 0.00 to apple's value"));
    }
}
