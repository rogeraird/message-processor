package co.aird;

import co.aird.messages.AdjustmentSaleMessage;
import co.aird.messages.Message;
import co.aird.messages.MultipleSaleMessage;
import co.aird.messages.SingleSaleMessage;
import co.aird.util.SystemExiter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  A quick demo class to demonstrate an end-to-end scenario, rather than relying on the tests for demos.
 *
 *  This code is quickly thrown together and is not intended to be production-ready ;)
 */
public class Demo {

    public static void main(String... args) {
        MessageProcessor messageProcessor = new MessageProcessor(System.out, new SystemExiter());
        List<Message> messages = createMessages();

        for (Message message : messages) {
            messageProcessor.processMessage(message);
        }
    }

    private static List<Message> createMessages() {
        List<Message> messages = new ArrayList<>();

        // 9 apple sales @ 0.1
        for (int i = 0; i < 9; i++) {
            messages.add(new SingleSaleMessage(new Sale("apple", new BigDecimal(0.1))));
        }

        // 10 sales bananas @ 1
        messages.add(new MultipleSaleMessage(new Sale("banana", new BigDecimal(1)), 10));

        // Modify apple prices by *2
        messages.add(new AdjustmentSaleMessage(
                new Adjustment("apple", AdjustmentOperation.MULTIPLY, new BigDecimal(2)),
                new Sale("apple", new BigDecimal(0.2))));

        // 10 cherry sales @ 0.3
        for (int i = 0; i < 10; i++) {
            messages.add(new SingleSaleMessage(new Sale("cherry", new BigDecimal(0.3))));
        }

        // 10 apple sales @ 0.4
        for (int i = 0; i < 10; i++) {
            messages.add(new SingleSaleMessage(new Sale("apple", new BigDecimal(0.4))));
        }

        // 10 banana sales @ 1
        for (int i = 0; i < 10; i++) {
            messages.add(new SingleSaleMessage(new Sale("banana", new BigDecimal(1))));
        }

        // Modify banana prices -0.5
        messages.add(new AdjustmentSaleMessage(
                new Adjustment("banana", AdjustmentOperation.SUBTRACT,
                        new BigDecimal(0.5)), new Sale("banana", new BigDecimal(0.5))));

        // 8 apple sales @ .1
        for (int i = 0; i < 8; i++) {
            messages.add(new SingleSaleMessage(new Sale("apple", new BigDecimal(0.1))));
        }


        return messages;
    }
}
