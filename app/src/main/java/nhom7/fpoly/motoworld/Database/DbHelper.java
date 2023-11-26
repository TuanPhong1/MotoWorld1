package nhom7.fpoly.motoworld.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "MotoWorlddd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tbHangXe = "create table HANGXE(mahang integer primary key autoincrement,tenhang text)";
        sqLiteDatabase.execSQL(tbHangXe);
        String tbSanPham = "create table SANPHAM(masp integer primary key autoincrement,mahang integer references HANGXE(mahang),matk integer references TKNGUOIDUNG(matk),tensp text,gia integer,loaixe text,mauxe text,namsx integer,dongco text,images text)";
        sqLiteDatabase.execSQL(tbSanPham);
        String tbNguoiDung = "create table NGUOIDUNG(mand integer primary key autoincrement,tennd text,namsinh text,gioitinh text,sdt text,diachi text,matk integer references TKNGUOIDUNG(matk))";
        sqLiteDatabase.execSQL(tbNguoiDung);
        String tbTaiKhoanND = "create table TKNGUOIDUNG(matk integer primary key autoincrement,taikhoan text ,matkhau text)";
        sqLiteDatabase.execSQL(tbTaiKhoanND);
        String tbAdmin = "create table ADMIN(maadmin integer primary key autoincrement,tenadmin text,taikhoan text,matkhau text)";
        sqLiteDatabase.execSQL(tbAdmin);

        sqLiteDatabase.execSQL("insert into HANGXE values(1,'Honda'),(2,'Ducati'),(3,'Yamaha'),(4,'Suzuki')");
//        sqLiteDatabase.execSQL("insert into SANPHAM values(1,1,1,'Air Blade',35000000,'Xe Ga','Đen sẫm',2022,'150cc','img')," +
//                "(2,3,1,'Wave alpha',20000000,'Xe Số','Xanh Đậm',2022,'110cc','img1')," +
//                "(3,2,2,'Dream',15000000,'Xe Số','Trắng',2022,'110cc','img2')," +
//                "(4,4,2,'Vario',40000000,'Xe Ga','Trắng',2022,'150cc','img3')");
        sqLiteDatabase.execSQL("insert into TKNGUOIDUNG values(1,'chien','123'),(2,'phong','123')");
        sqLiteDatabase.execSQL("insert into NGUOIDUNG values(1,'Nguyễn Minh Chiến','2004','Nam','0325555876','Thái Bình',1)");
        sqLiteDatabase.execSQL("insert into ADMIN values(1,'Nguyen Minh Chien','admin','admin'),(2,'Phạm Văn Phong','admin1','admin1'),(3,'Nguyễn Tuấn Phong','admin2','admin2')");
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
