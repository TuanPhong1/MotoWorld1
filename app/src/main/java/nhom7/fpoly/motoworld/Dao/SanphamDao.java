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

    public SanphamDao(Context context) {
        DbHelper dbHelper =new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//    public ArrayList<Sanpham> getDSSanPham(){
//        ArrayList<Sanpham> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//
//        Cursor cursor = sqLiteDatabase.rawQuery(" select sp.masp,hx.mahang,sp.gia,sp.namsx,sp.trangthai,sp.tensp,sp.loaixe,sp.mauxe,sp.dongco from SANPHAM sp,HANGXE hx where sp.mahang = hx.mahang", null);
//        if (cursor.getCount() != 0){
//            cursor.moveToFirst();
//            do
//                list.add(new Sanpham(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
//            while (cursor.moveToNext());
//        }
//        return list;
//    }

    public long insert(Sanpham obj){
        ContentValues values = new ContentValues();
        values.put("masp",obj.getMasp());
        values.put("mahang",obj.getMahang());
        values.put("tensp",obj.getTensp());
        values.put("gia",obj.getGia());
        values.put("loaixe",obj.getLoaixe());
        values.put("mauxe",obj.getMauxe());
        values.put("namsx",obj.getNamsx());
        values.put("dongco",obj.getDongco());
//        values.put("trangthai",obj.getTrangthai());
        return db.insert("SANPHAM",null,values);
    }
    public int update(Sanpham obj){
        ContentValues values = new ContentValues();
        values.put("masp",obj.getMasp());
        values.put("mahang",obj.getMahang());
        values.put("tensp",obj.getTensp());
        values.put("gia",obj.getGia());
        values.put("loaixe",obj.getLoaixe());
        values.put("mauxe",obj.getMauxe());
        values.put("namsx",obj.getNamsx());
        values.put("dongco",obj.getDongco());
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
            sp.setGia(c.getInt(c.getColumnIndex("gia")));
            sp.setLoaixe(c.getString(c.getColumnIndex("loaixe")));
            sp.setMauxe(c.getString(c.getColumnIndex("mauxe")));
            sp.setNamsx(c.getInt(c.getColumnIndex("namsx")));
            sp.setDongco(c.getString(c.getColumnIndex("dongco")));
//            sp.setTrangthai(c.getInt(c.getColumnIndex("trangthai")));
            list.add(sp);
        }
        return list;
    }
    public List<Sanpham>   getAll(){
        String sql = "select * from SANPHAM";
        return getdata(sql);
    }
    public Sanpham getID(String id){
        String sql = "select * from SANPHAM where masp=?";
        List<Sanpham> list = getdata(sql,id);
        return list.get(0);
    }
}
