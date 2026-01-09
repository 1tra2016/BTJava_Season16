import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface ProductProcessor {

    double calculateTotalValue(List<Product> products);

    static void printProductList(List<Product> products) {
        products.forEach(System.out::println);
    }

    default void hasExpensiveProduct(List<Product> products) {
        Predicate<Product> expensive = p -> p.getPrice() > 100;

        List<Product> expensiveProducts = products.stream()
                .filter(expensive)
                .collect(Collectors.toList());

        if (!expensiveProducts.isEmpty()) {
            System.out.println("Sản phẩm đắt tiền (>100):");
            expensiveProducts.forEach(System.out::println);
        }
    }

}
