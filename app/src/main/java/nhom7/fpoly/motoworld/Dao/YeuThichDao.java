package nhom7.fpoly.motoworld.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom7.fpoly.motoworld.Database.DbHelper;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.Model.YeuThich;

public class YeuThichDao {
    private SQLiteDatabase db;

    public YeuThichDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(YeuThich obj) {
        ContentValues values = new ContentValues();
        values.put("masp", obj.getMasp());
        values.put("matk", obj.getMatk());
        return db.insert("YEUTHICH", null, values);
    }

    public long delete(String id) {
        return db.delete("YEUTHICH", "mayt = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    private List<YeuThich> getData(String sql, String... selectionArgs) {
        List<YeuThich> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            YeuThich obj = new YeuThich();
            obj.setMayt(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mayt"))));
            obj.setMasp(Integer.parseInt(cursor.getString(cursor.getColumnIndex("masp"))));
            obj.setMatk(Integer.parseInt(cursor.getString(cursor.getColumnIndex("matk"))));
            list.add(obj);
        }
        return list;
    }

    @SuppressLint("Range")
    public List<Sanpham> getSanPhamInGioHangByMatkd(int matknd) {
        List<Sanpham> list = new ArrayList<>();

        String query = "SELECT SANPHAM.* " +
                "FROM SANPHAM " +
                "INNER JOIN YEUTHICH ON SANPHAM.masp = YEUTHICH.masp " +
                "WHERE YEUTHICH.matk = ?";

        Cursor c = db.rawQuery(query, new String[]{String.valueOf(matknd)});
        while (c.moveToNext()){
            Sanpham sp = new Sanpham();
            sp.setMasp(c.getInt(c.getColumnIndex("masp")));
            sp.setMahang(c.getInt(c.getColumnIndex("mahang")));
            sp.setTensp(c.getString(c.getColumnIndex("tensp")));
            sp.setMatk(c.getInt(c.getColumnIndex("matk")));
            sp.setGia(c.getInt(c.getColumnIndex("gia")));
            sp.setLoaixe(c.getString(c.getColumnIndex("loaixe")));
            sp.setMauxe(c.getString(c.getColumnIndex("mauxe")));
            sp.setNamsx(c.getInt(c.getColumnIndex("namsx")));
            sp.setImage(c.getString(c.getColumnIndex("images")));
            sp.setDongco(c.getString(c.getColumnIndex("dongco")));
            list.add(sp);
        }

        c.close();
        return list;
    }

}
