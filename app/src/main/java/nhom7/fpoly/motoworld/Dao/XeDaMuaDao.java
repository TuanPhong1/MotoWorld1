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
import nhom7.fpoly.motoworld.Model.XeDaMua;

public class XeDaMuaDao {
    private SQLiteDatabase db;

    public XeDaMuaDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(XeDaMua obj) {
        ContentValues values = new ContentValues();
        values.put("masp", obj.getMasp());
        values.put("matk", obj.getMatk());
        return db.insert("XEDAMUA", null, values);
    }

    public long delete(String id) {
        return db.delete("XEDAMUA", "maxm = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    private List<XeDaMua> getData(String sql, String... selectionArgs) {
        List<XeDaMua> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            XeDaMua obj = new XeDaMua();
            obj.setMaxm(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maxm"))));
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
                "INNER JOIN XEDAMUA ON SANPHAM.masp = XEDAMUA.masp " +
                "WHERE XEDAMUA.matk = ?";

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
            sp.setTrangthai(c.getInt(c.getColumnIndex("trangthai")));
            list.add(sp);
        }

        c.close();
        return list;
    }

}
