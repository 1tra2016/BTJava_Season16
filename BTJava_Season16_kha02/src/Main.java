import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Event> events = new ArrayList<>();

    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    showEvents();
                    break;
                case "3":
                    checkEventTime();
                    break;
                case "4":
                    System.out.println("Thoát chương trình.");
                    return;
                case "5":
                    checkTime();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }

    static void showMenu() {
        System.out.println("\n===== QUẢN LÝ SỰ KIỆN =====");
        System.out.println("1. Thêm sự kiện");
        System.out.println("2. Hiển thị danh sách sự kiện");
        System.out.println("3. Kiểm tra trạng thái sự kiện");
        System.out.println("4. Thoát");
        System.out.print("Chọn: ");
    }

    static void addEvent() {
        try {
            System.out.print("Tên sự kiện: ");
            String name = sc.nextLine();

            LocalDateTime startDate = inputDateTime("Thời gian bắt đầu (dd/MM/yyyy HH:mm): ");
            LocalDateTime endDate = inputDateTime("Thời gian kết thúc (dd/MM/yyyy HH:mm): ");

            if (endDate.isBefore(startDate)) {
                System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu");
                return;
            }

            events.add(new Event(name, startDate, endDate));
            System.out.println("Thêm sự kiện thành công");
        } catch (Exception e) {
            System.out.println("Dữ liệu không hợp lệ");
        }
    }

    static void showEvents() {
        if (events.isEmpty()) {
            System.out.println("Danh sách sự kiện trống");
            return;
        }

        for (Event e : events) {
            System.out.println(e);
        }
    }

    static void checkEventTime() {
        LocalDateTime now = LocalDateTime.now();

        for (Event e : events) {
            System.out.print(e.getName() + " -> ");

            if (now.isBefore(e.getStartDate())) {
                System.out.println("Sắp diễn ra");
            } else if (now.isAfter(e.getEndDate())) {
                System.out.println("Đã kết thúc");
            } else {
                System.out.println("Đang diễn ra");
            }
        }
    }

    static LocalDateTime inputDateTime(String message) {
        while (true) {
            try {
                System.out.print(message);
                String time = sc.nextLine();
                return LocalDateTime.parse(time, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Sai định dạng! Vui lòng nhập theo dd/MM/yyyy HH:mm");
            }
        }
    }

    static void checkTime(){
        LocalDateTime Rnow = LocalDateTime.now();
        System.out.println(Rnow);
    }
}
