package nhom7.fpoly.motoworld.Model;

public class NguoiDung {
    private int mand,matk;
    private String tennd;
    private String namsinh;
    private String gioitinh;
    private String sdt;
    private String diachi;

    public NguoiDung() {
    }

    public NguoiDung(int mand, int matk, String tennd, String namsinh, String gioitinh, String sdt, String diachi) {
        this.mand = mand;
        this.matk = matk;
        this.tennd = tennd;
        this.namsinh = namsinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getTennd() {
        return tennd;
    }

    public void setTennd(String tennd) {
        this.tennd = tennd;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
