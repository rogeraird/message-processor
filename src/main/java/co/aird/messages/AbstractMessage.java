package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;

import java.util.List;

public abstract class AbstractMessage implements Message {

    private final Sale sale;

    public AbstractMessage(Sale sale) {
        this.sale = sale;
    }

    @Override
    public Sale getSale() {
        return this.sale;
    }

    @Override
    public abstract void process(List<Sale> sales, List<Adjustment> adjustments);
}
