package co.aird.messages;

import co.aird.Adjustment;
import co.aird.Sale;

import java.util.List;

public interface Message {

    public Sale getSale();

    public void process(List<Sale> sales, List<Adjustment> adjustments);

}
