package nhom7.fpoly.motoworld.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import nhom7.fpoly.motoworld.Database.DbHelper;
import nhom7.fpoly.motoworld.Model.Hangxe;

public class HangxeDao {
    SQLiteDatabase db;
    public HangxeDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db =dbHelper.getWritableDatabase();
    }
    public long insert(Hangxe obj){
        ContentValues values = new ContentValues();
        values.put("tenhang",obj.getTenhang());
        return db.insert("HANGXE",null,values);
    }
    public int update(Hangxe obj){
        ContentValues values = new ContentValues();
        values.put("tenhang",obj.getTenhang());
        return db.update("HANGXE",values,"mahang=?",new String[]{String.valueOf(obj.getTenhang())});
    }
    public int delete(String id){
        return db.delete("HANGXE","mahang=?",new String[]{id});
    }
    @SuppressLint("Range")
    private List<Hangxe> getData(String sql, String...selectionArgs){
        List<Hangxe> list = new ArrayList<>();
        Cursor c= db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            Hangxe obj = new Hangxe();
            obj.setMahang(Integer.parseInt(c.getString(c.getColumnIndex("mahang"))));
            obj.setTenhang(c.getString(c.getColumnIndex("tenhang")));
            list.add(obj);
        }
        return list;

    }
    public List<Hangxe> getAll(){
        String sql = "select * from HANGXE";
        return getData(sql);
    }
    public Hangxe getID(String id){
        String sql = "select * from HANGXE where mahang=?";
        List<Hangxe> list = getData(sql,id);
        return list.get(0);
    }
}
