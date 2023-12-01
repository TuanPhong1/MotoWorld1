package nhom7.fpoly.motoworld.Model;

public class XeDaMua {
    private int maxm;
    private int masp;
    private int matk;

    public XeDaMua() {
    }

    public XeDaMua(int maxm, int masp, int matk) {
        this.maxm = maxm;
        this.masp = masp;
        this.matk = matk;
    }

    public int getMaxm() {
        return maxm;
    }

    public void setMaxm(int maxm) {
        this.maxm = maxm;
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
