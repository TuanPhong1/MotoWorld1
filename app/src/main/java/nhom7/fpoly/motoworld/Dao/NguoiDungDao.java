package nhom7.fpoly.motoworld.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom7.fpoly.motoworld.Database.DbHelper;
import nhom7.fpoly.motoworld.Model.NguoiDung;

public class NguoiDungDao {
    private SQLiteDatabase db;

    public NguoiDungDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(NguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("tennd", obj.getTennd());
        values.put("namsinh", obj.getNamsinh());
        values.put("gioitinh", obj.getGioitinh());
        values.put("sdt", obj.getSdt());
        values.put("diachi", obj.getDiachi());
        values.put("matk",obj.getMatk());
        return db.insert("NGUOIDUNG", null, values);
    }

    public boolean update(NguoiDung obj) {
        ContentValues values = new ContentValues();
        values.put("tennd", obj.getTennd());
        values.put("namsinh", obj.getNamsinh());
        values.put("gioitinh", obj.getGioitinh());
        values.put("sdt", obj.getSdt());
        values.put("diachi", obj.getDiachi());
        values.put("matk",obj.getMatk());
        long row =  db.update("NGUOIDUNG", values, "mand=?", new String[]{String.valueOf(obj.getMand())});
        return (row>0);
    }

    public long delete(String id) {
       return db.delete("NGUOIDUNG","mand = ?",new String[]{id});
    }
    public List<NguoiDung> getAll(){
        String sql = "select * from NGUOIDUNG";
        return getData(sql);
    }
    public NguoiDung getID(String id){
        String sql = "select * from NGUOIDUNG where mand=?";
        List<NguoiDung> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String...selectionArgs) {
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            NguoiDung obj = new NguoiDung();
            obj.setMand(Integer.parseInt(cursor.getString(cursor.getColumnIndex("mand"))));
            obj.setTennd(cursor.getString(cursor.getColumnIndex("tennd")));
            obj.setNamsinh(cursor.getString(cursor.getColumnIndex("namsinh")));
            obj.setGioitinh(cursor.getString(cursor.getColumnIndex("gioitinh")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            obj.setDiachi(cursor.getString(cursor.getColumnIndex("diachi")));
            list.add(obj);
        }
        return list;
    }
    public List<NguoiDung> getAllByMAtknd(int matk) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE matk = ?";
        String[] selectionArgs = {String.valueOf(matk)};
        return getData(sql, selectionArgs);
    }
}
