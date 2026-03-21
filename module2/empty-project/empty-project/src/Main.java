import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng sinh viên
        SinhVien sv1 = new SinhVien("An", 20);
        sv1.hienThi();

        // Nhập số a, b để tính toán
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập a: ");
        int a = sc.nextInt();

        System.out.print("Nhập b: ");
        int b = sc.nextInt();

        System.out.print("Cộng: " + cong(a, b) + "\n");
        System.out.print("Trừ: " + tru(a, b) + "\n");
        System.out.print("Nhân: " + nhan(a, b) + "\n");
        if (b != 0) {
            System.out.print("Chia: " + chia(a, b) + "\n");
            System.out.print("Chia dư: " + chiaDu(a, b) + "\n");
        }
        sc.close();
    }

    // Hàm cộng
    public static int cong(int a, int b) {
        return a + b;
    }

    // Hàm trừ
    public static int tru(int a, int b) {
        return a - b;
    }

    // Hàm nhân
    public static int nhan(int a, int b) {
        return a * b;
    }

    // Hàm chia
    public static int chia(int a, int b) {
        if (b == 0) {
            System.out.println("Không thể chia cho 0!");
            return 0;
        }
        return a / b;
    }

    // Hàm chia lấy dư
    public static int chiaDu(int a, int b) {
        if (b == 0) {
            System.out.println("Không thể chia cho 0!");
            return 0;
        }
        return a % b;
    }


}



