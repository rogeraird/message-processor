package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;

import java.util.List;

public class SingleSaleMessage extends AbstractMessage {

    public SingleSaleMessage(Sale sale) {
        super(sale);
    }

    @Override
    public void process(List<Sale> sales, List<Adjustment> adjustments) {
        sales.add(getSale());
    }
}
