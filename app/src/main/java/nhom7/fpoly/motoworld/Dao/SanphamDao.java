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

public class SanphamDao {
    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public SanphamDao(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sanpham obj,int matk){
        ContentValues values = new ContentValues();
        values.put("mahang",obj.getMahang());
        values.put("tensp",obj.getTensp());
        values.put("matk",matk);
        values.put("mand",obj.getMand());
        values.put("gia",obj.getGia());
        values.put("loaixe",obj.getLoaixe());
        values.put("mauxe",obj.getMauxe());
        values.put("namsx",obj.getNamsx());
        values.put("dongco",obj.getDongco());
        values.put("images",obj.getImage());
//        values.put("trangthai",obj.getTrangthai());
        return db.insert("SANPHAM",null,values);
    }
    public int update(Sanpham obj){
        ContentValues values = new ContentValues();
        values.put("mahang",obj.getMahang());
        values.put("tensp",obj.getTensp());
        values.put("gia",obj.getGia());
        values.put("loaixe",obj.getLoaixe());
        values.put("mauxe",obj.getMauxe());
        values.put("namsx",obj.getNamsx());
        values.put("dongco",obj.getDongco());
        values.put("images",obj.getImage());
//        values.put("trangthai",obj.getTrangthai());
        return db.update("SANPHAM",values,"masp=?",new String[]{String.valueOf(obj.getMasp())});
    }
    public int delete(String id){
        return db.delete("SANPHAM","masp=?",new String[]{id});
    }
    @SuppressLint("Range")
    public List<Sanpham> getdata(String sql, String...selectionArgs){
        List<Sanpham> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Sanpham sp = new Sanpham();
            sp.setMasp(c.getInt(c.getColumnIndex("masp")));
            sp.setMahang(c.getInt(c.getColumnIndex("mahang")));
            sp.setTensp(c.getString(c.getColumnIndex("tensp")));
            sp.setMatk(c.getInt(c.getColumnIndex("matk")));
            sp.setMand(c.getInt(c.getColumnIndex("mand")));
            sp.setGia(c.getInt(c.getColumnIndex("gia")));
            sp.setLoaixe(c.getString(c.getColumnIndex("loaixe")));
            sp.setMauxe(c.getString(c.getColumnIndex("mauxe")));
            sp.setNamsx(c.getInt(c.getColumnIndex("namsx")));
            sp.setImage(c.getString(c.getColumnIndex("images")));
            sp.setDongco(c.getString(c.getColumnIndex("dongco")));
//            sp.setTrangthai(c.getInt(c.getColumnIndex("trangthai")));
            list.add(sp);
        }
        return list;
    }
    public List<Sanpham> getAll(){
        String sql = "select * from SANPHAM";
        return getdata(sql);
    }
    public Sanpham getID(String id){
        String sql = "select * from SANPHAM where masp=?";
        List<Sanpham> list = getdata(sql,id);
        return list.get(0);
    }
    public List<Sanpham> getAllByMAtknd(int matk) {
        String sql = "SELECT * FROM SANPHAM WHERE matk = ?";
        String[] selectionArgs = {String.valueOf(matk)};
        return getdata(sql, selectionArgs);
    }
}
