package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;

import java.math.BigDecimal;
import java.util.List;

public class MultipleSaleMessage extends AbstractMessage implements Message {

    private final int numberOfSales;

    public MultipleSaleMessage(Sale sale, int numberOfSales) {
        super(sale);
        this.numberOfSales = numberOfSales;
    }

    @Override
    public void process(List<Sale> sales, List<Adjustment> adjustments) {
        for (int i = 0; i < numberOfSales; i++) {
            sales.add(new Sale(getSale().getProduct(), new BigDecimal(getSale().getPrice().toString())));
        }
    }
}
