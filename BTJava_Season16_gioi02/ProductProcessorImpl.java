import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {

    @Override
    public double calculateTotalValue(List<Product> products) {
        final double[] total = {0}; //trick lỏ
        products.forEach(p -> total[0] = total[0] + p.getPrice());
        return total[0];
    }
}
