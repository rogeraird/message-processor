package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;

import java.util.List;

public class MockMessage extends AbstractMessage {

    public MockMessage(Sale sale) {
        super(sale);
    }

    @Override
    public void process(List<Sale> sales, List<Adjustment> adjustments) {
        // Do nothing
    }
}
