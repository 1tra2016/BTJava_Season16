import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Message> messages = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Messenger ===");
            System.out.println("1. Gửi tin nhắn");
            System.out.println("2. Xem lịch sử chat");
            System.out.println("3. Lọc tin nhắn theo người gửi");
            System.out.println("4. Lọc tin nhắn theo ngày");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    sendMessage();
                    break;
                case "2":
                    showMessages();
                    break;
                case "3":
                    filterBySender();
                    break;
                case "4":
                    filterByDate();
                    break;
                case "0":
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }
    static void sendMessage() {
        System.out.print("Tên người gửi: ");
        String sender = scanner.nextLine();

        System.out.print("Nội dung: ");
        String content = scanner.nextLine();

        messages.add(new Message(sender, content));
        System.out.println("Đã gửi tin nhắn!");
    }
    static void showMessages() {
        if (messages.isEmpty()) {
            System.out.println("Chưa có tin nhắn.");
            return;
        }

        messages.forEach(System.out::println);
    }
    static void filterBySender() {
        System.out.print("Nhập tên người gửi: ");
        String sender = scanner.nextLine();

        messages.stream()
                .filter(m -> m.getSender().equalsIgnoreCase(sender))
                .forEach(System.out::println);
    }
    static void filterByDate() {
        System.out.print("Nhập ngày (dd/MM/yyyy): ");
        String input = scanner.nextLine();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(input, formatter);

            messages.stream()
                    .filter(m -> m.getTimestamp().toLocalDate().equals(date))
                    .forEach(System.out::println);

        } catch (DateTimeParseException e) {
            System.out.println("Sai định dạng ngày! Ví dụ đúng: 01/01/1980");
        }
    }
}
