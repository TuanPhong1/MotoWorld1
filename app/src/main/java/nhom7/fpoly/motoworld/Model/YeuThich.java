package nhom7.fpoly.motoworld.Model;

public class YeuThich {
    private int mayt;
    private int masp;
    private int matk;

    public YeuThich() {
    }

    public YeuThich(int mayt, int masp, int matk) {
        this.mayt = mayt;
        this.masp = masp;
        this.matk = matk;
    }

    public int getMayt() {
        return mayt;
    }

    public void setMayt(int mayt) {
        this.mayt = mayt;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }
}
