import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static ProductProcessor processor = new ProductProcessorImpl();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Xem danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Kiểm tra sản phẩm đắt tiền (>100)");
            System.out.println("4. Tính tổng giá trị sản phẩm");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    ProductProcessor.printProductList(products);
                    break;

                case 2:
                    addProduct();
                    break;

                case 3:
                    checkExpensiveProduct();
                    break;

                case 4:
                    calculateTotal();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    static void addProduct() {
        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        double price;
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                price = Double.parseDouble(sc.nextLine());
                if (price < 0) {
                    System.out.println("Giá phải >= 0");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá không hợp lệ, nhập lại!");
            }
        }

        products.add(new Product(name, price));
        System.out.println("Thêm sản phẩm thành công!");
    }

    static void checkExpensiveProduct() {
        processor.hasExpensiveProduct(products);
    }

    static void calculateTotal() {
        double total = processor.calculateTotalValue(products);
        System.out.println("Tổng giá trị sản phẩm: " + total);
    }
}
