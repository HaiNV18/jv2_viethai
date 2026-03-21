public class SinhVien {

    String ten;
    int tuoi;

    // Hàm khởi tạo
    public SinhVien(String ten, int tuoi) {
        this.ten = ten;
        this.tuoi = tuoi;
    }

    public void hienThi() {
        System.out.println("Tên: " + ten);
        System.out.println("Tuổi: " + tuoi);
    }
}