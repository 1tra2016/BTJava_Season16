import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Product> products = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        showProducts();
                        break;
                    case 5:
                        filterProducts();
                        break;
                    case 6:
                        calculateTotalPrice();
                        break;
                    case 7:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n===== QUẢN LÝ SẢN PHẨM =====");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Sửa sản phẩm");
        System.out.println("3. Xóa sản phẩm");
        System.out.println("4. Hiển thị danh sách sản phẩm");
        System.out.println("5. Lọc sản phẩm (price > 100)");
        System.out.println("6. Tính tổng giá trị sản phẩm");
        System.out.println("7. Thoát");
        System.out.print("Chọn: ");
    }

    static void addProduct() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (products.containsKey(id)) {
                System.out.println("ID đã tồn tại");
                return;
            }

            System.out.print("Tên sản phẩm: ");
            String name = sc.nextLine();

            System.out.print("Giá: ");
            double price = Double.parseDouble(sc.nextLine());

            products.put(id, new Product(id, name, price));
            System.out.println("Thêm sản phẩm thành công");
        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu không hợp lệ");
        }
    }

    static void updateProduct() {
        System.out.print("Nhập ID sản phẩm cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());

        Product p = products.get(id);
        if (p == null) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }

        System.out.println("Thông tin hiện tại: " + p);

        System.out.print("Tên mới: ");
        p.setName(sc.nextLine());

        System.out.print("Giá mới: ");
        p.setPrice(Double.parseDouble(sc.nextLine()));

        System.out.println("Cập nhật thành công");
    }

    static void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());

        if (products.remove(id) != null) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    static void showProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách trống");
            return;
        }

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    static void filterProducts() {
        System.out.println("Sản phẩm có price > 100:");
        products.values().stream()
                .filter(p -> p.getPrice() > 100)
                .forEach(System.out::println);
    }

    static void calculateTotalPrice() {
        double total = products.values().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Tổng giá trị sản phẩm: " + total);
    }
}
