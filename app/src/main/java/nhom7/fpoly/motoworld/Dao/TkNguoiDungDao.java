package nhom7.fpoly.motoworld.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom7.fpoly.motoworld.Database.DbHelper;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;

public class TkNguoiDungDao {
    SQLiteDatabase db;
    public TkNguoiDungDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(TkNguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("taikhoan", obj.getTaikhoan());
        values.put("matkhau", obj.getMatkhau());
        return db.insert("TKNGUOIDUNG", null, values);
    }

    public long updatePass(TkNguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("taikhoan", obj.getTaikhoan());
        values.put("matkhau", obj.getMatkhau());
        return db.update("TKNGUOIDUNG", values, "matk = ?", new String[]{String.valueOf(obj.getMatk())});
    }

    public long delete(String id) {
        return db.delete("TKNGUOIDUNG", "matk= ?", new String[]{String.valueOf(id)});
    }

    public List<TkNguoiDung> getAll() {
        String sql = "SELECT * FROM TKNGUOIDUNG";
        return getData(sql);
    }

    public TkNguoiDung getID(String id) {
        String sql = "SELECT * FROM taikhoanND WHERE matknd=?";
        List<TkNguoiDung> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM TKNGUOIDUNG WHERE taikhoan=? AND matkhau=?";
        List<TkNguoiDung> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
    @SuppressLint("Range")
    public int getMatkndFromTkNguoiDung(String taikhoan, String matkhau) {
        String sql = "SELECT matk FROM TKNGUOIDUNG WHERE taikhoan=? AND matkhau=?";
        String[] selectionArgs = {taikhoan, matkhau};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("matk"));
        }
        return -1;
    }

    @SuppressLint("Range")
    private List<TkNguoiDung> getData(String sql, String... selectionArgs) {
        List<TkNguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            TkNguoiDung obj = new TkNguoiDung();
            obj.setMatk(Integer.parseInt(cursor.getString(cursor.getColumnIndex("matk"))));
            obj.setTaikhoan(cursor.getString(cursor.getColumnIndex("taikhoan")));
            obj.setMatkhau(cursor.getString(cursor.getColumnIndex("matkhau")));
            list.add(obj);
        }
        return list;
    }
}
