package nhom7.fpoly.motoworld.TaiKhoan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.XeDaMuaAdapter;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Dao.XeDaMuaDao;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;

public class XeDaMua extends AppCompatActivity {
    private RecyclerView rcv;
    private XeDaMuaAdapter adapter;
    private XeDaMua xeDaMua;
    ArrayList<Sanpham> danhSachSanPhamDaMua;
    XeDaMuaDao dao;
    TkNguoiDungDao nddao;
    int matknd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_da_mua);
        ImageButton imgdamua = findViewById(R.id.imgbackdamua);
        rcv = findViewById(R.id.rcv_xedamua);

        dao = new XeDaMuaDao(this);
        nddao = new TkNguoiDungDao(this);

        SharedPreferences pref = this.getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");

        matknd = nddao.getMatkndFromTkNguoiDung(user, pass);

        danhSachSanPhamDaMua = (ArrayList<Sanpham>) dao.getSanPhamInGioHangByMatkd(matknd);// Khởi tạo danh sách sản phẩm yêu thích


        loaddata();

        imgdamua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loaddata() {
        // Thiết lập layout manager cho RecyclerView

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcv.setLayoutManager(gridLayoutManager);

        // Thiết lập adapter cho RecyclerView
        adapter = new XeDaMuaAdapter(this, danhSachSanPhamDaMua, xeDaMua);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}