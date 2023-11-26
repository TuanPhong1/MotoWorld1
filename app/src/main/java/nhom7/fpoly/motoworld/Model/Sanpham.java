package nhom7.fpoly.motoworld.Model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int masp, mahang, gia, namsx, trangthai,matk;
    public String tensp, loaixe, mauxe, dongco,image;

    public Sanpham() {
    }


    public Sanpham(int masp, int mahang, int gia, int namsx, int trangthai, int matk, String tensp, String loaixe, String mauxe, String dongco, String image) {
        this.masp = masp;
        this.mahang = mahang;
        this.gia = gia;
        this.namsx = namsx;
        this.trangthai = trangthai;
        this.matk = matk;
        this.tensp = tensp;
        this.loaixe = loaixe;
        this.mauxe = mauxe;
        this.dongco = dongco;
        this.image = image;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMahang() {
        return mahang;
    }

    public void setMahang(int mahang) {
        this.mahang = mahang;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getNamsx() {
        return namsx;
    }

    public void setNamsx(int namsx) {
        this.namsx = namsx;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getMauxe() {
        return mauxe;
    }

    public void setMauxe(String mauxe) {
        this.mauxe = mauxe;
    }

    public String getDongco() {
        return dongco;
    }

    public void setDongco(String dongco) {
        this.dongco = dongco;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}