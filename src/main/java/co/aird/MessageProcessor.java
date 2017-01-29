package co.aird;

import co.aird.messages.Message;
import co.aird.util.SystemExiter;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageProcessor {

    private final List<Sale> sales;
    private final List<Adjustment> adjustments;
    private final PrintStream outputStream;
    private final SystemExiter exiter;
    private int messageCount;

    public MessageProcessor(PrintStream outputStream, SystemExiter exiter) {
        this.sales = new ArrayList<>();
        this.adjustments = new ArrayList<>();
        this.outputStream = outputStream;
        this.exiter = exiter;
        this.messageCount = 0;
    }

    public void processMessage(Message message) {
        message.process(sales, adjustments);
        messageCount++;

        if (messageCount % 10 == 0) {
            processSales();
        }

        if (messageCount % 50 == 0) {
            processAdjustments();
            exiter.exit();
        }
    }

    private void processSales() {
        Map<String, SaleSummary> salesSummary = new HashMap<>();
        for (Sale sale : sales) {
            if (!salesSummary.containsKey(sale.getProduct())) {
                SaleSummary summary = new SaleSummary(sale.getProduct());
                salesSummary.put(sale.getProduct(), summary);
            }

            SaleSummary summary = salesSummary.get(sale.getProduct());
            summary.addSale(sale.getPrice());
        }

        for (SaleSummary saleSummary : salesSummary.values()) {
           outputStream.println(saleSummary);
        }
        outputStream.println();
    }

    private void processAdjustments() {
        for (Adjustment adjustment : adjustments) {
            outputStream.println(adjustment);
        }
    }
}
