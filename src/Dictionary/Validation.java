package Dictionary;
import java.util.Scanner;

public class Validation {
    public static Scanner sc = new Scanner(System.in);

    /**
     * @return nhập dữ liệu dạng chuỗi, kết thúc bởi 2 lân enter.
     */
    public static String inputString() {
        String old;
        String input;
        while (true) {
            input = "";
            do {
                old = sc.nextLine().trim();
                input = input.concat(old + "\n");
            } while (!old.equals(""));
            if (!input.equals("")) {
                return input.trim();
            }
        }
    }

    /**
     * Nhập giá trị int nằm trong khoảng cho trước.
     * @param min nhỏ nhất
     * @param max lớn nhất.
     * @return int
     */
    public static int inputInteger(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(sc.nextLine().trim());
                if (choice < min || choice > max) {
                    System.out.println("Please enter a positive number!!!");
                    System.out.print("Enter again: ");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter Integer");
                System.out.print("Enter again: ");
            }
        }
    }
}
