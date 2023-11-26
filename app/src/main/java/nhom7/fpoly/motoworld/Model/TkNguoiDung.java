package nhom7.fpoly.motoworld.Model;

public class TkNguoiDung {
    private int matk;
    private String taikhoan,matkhau;

    public TkNguoiDung() {
    }

    public TkNguoiDung(int matk, String taikhoan, String matkhau) {
        this.matk = matk;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
