package nhom7.fpoly.motoworld.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "Motowoowwwwao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tbHangXe = "create table HANGXE(mahang integer primary key autoincrement,tenhang text)";
        sqLiteDatabase.execSQL(tbHangXe);
        String tbSanPham = "create table SANPHAM(masp integer primary key autoincrement,mahang integer references HANGXE(mahang),matk integer references TKNGUOIDUNG(matk),tensp text,gia integer,loaixe text,mauxe text,namsx integer,dongco text,images text,mand integer references NGUOIDUNG(mand),trangthai integer)";
        sqLiteDatabase.execSQL(tbSanPham);
        String tbNguoiDung = "create table NGUOIDUNG(mand integer primary key autoincrement,tennd text,namsinh text,gioitinh text,sdt text,diachi text,matk integer references TKNGUOIDUNG(matk))";
        sqLiteDatabase.execSQL(tbNguoiDung);
        String tbTaiKhoanND = "create table TKNGUOIDUNG(matk integer primary key autoincrement,taikhoan text ,matkhau text)";
        sqLiteDatabase.execSQL(tbTaiKhoanND);
        String yeuthich = "create table YEUTHICH(mayt integer primary key autoincrement,masp integer references SANPHAM(masp) ,matk integer references TKNGUOIDUNG(matk))";
        sqLiteDatabase.execSQL(yeuthich);
        String xedamua = "create table XEDAMUA(maxm integer primary key autoincrement,masp integer references SANPHAM(masp) ,matk integer references TKNGUOIDUNG(matk))";
        sqLiteDatabase.execSQL(xedamua);
        String xedangban = "create table XEDANGBAN(maxb integer primary key autoincrement,masp integer references SANPHAM(masp) ,matk integer references TKNGUOIDUNG(matk))";
        sqLiteDatabase.execSQL(xedangban);

        sqLiteDatabase.execSQL("insert into HANGXE values(1,'Honda'),(2,'Ducati'),(3,'Yamaha'),(4,'Suzuki')");
        sqLiteDatabase.execSQL("insert into TKNGUOIDUNG values(1,'admin','admin'),(2,'chien','1')") ;
        sqLiteDatabase.execSQL("insert into NGUOIDUNG values(1,'Nguyễn Minh Chiến','2004','Nam','0325555876','Thái Bình',1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i!= i1){
            sqLiteDatabase.execSQL("drop table if exists HANGXE");
            sqLiteDatabase.execSQL("drop table if exists SANPHAM");
            sqLiteDatabase.execSQL("drop table if exists NGUOIDUNG");
            sqLiteDatabase.execSQL("drop table if exists TKNGUOIDUNG");
            sqLiteDatabase.execSQL("drop table if exists ADMIN");

        }
    }
}
