package co.aird.messages;

import co.aird.Adjustment;
import co.aird.AdjustmentOperation;
import co.aird.Sale;

import java.util.List;

public class AdjustmentSaleMessage extends AbstractMessage {

    private final Adjustment adjustment;

    public AdjustmentSaleMessage(Adjustment adjustment, Sale sale) {
        super(sale);
        this.adjustment = adjustment;
    }


    @Override
    public void process(List<Sale> sales, List<Adjustment> adjustments) {
      adjustments.add(adjustment);

      for (Sale sale : sales) {
          if (sale.getProduct().equals(adjustment.getProduct())) {
              if (adjustment.getAdjustmentOperation() == AdjustmentOperation.ADD) {
                  sale.setPrice(sale.getPrice().add(adjustment.getAmount()));
              } else if (adjustment.getAdjustmentOperation() == AdjustmentOperation.SUBTRACT) {
                  sale.setPrice(sale.getPrice().subtract(adjustment.getAmount()));
              } else if (adjustment.getAdjustmentOperation() == AdjustmentOperation.MULTIPLY) {
                  sale.setPrice(sale.getPrice().multiply(adjustment.getAmount()));
              }
          }
      }
        sales.add(getSale());
    }
}
