package nhom7.fpoly.motoworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nhom7.fpoly.motoworld.Fragment.DangtinFragment;
import nhom7.fpoly.motoworld.Fragment.HangxeFragment;
import nhom7.fpoly.motoworld.Fragment.HomeFragment;
import nhom7.fpoly.motoworld.Fragment.MuaHangFragment;
import nhom7.fpoly.motoworld.Fragment.PersonFragment;
import nhom7.fpoly.motoworld.Fragment.QuanLiAdminFragment;
import nhom7.fpoly.motoworld.Fragment.QuanLiNguoiDungFragment;
import nhom7.fpoly.motoworld.Fragment.QuanLiSanPhamFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomBar, bottombarAdmin;
    Toolbar toolbarAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bottom_nav);
        bottombarAdmin = findViewById(R.id.bottom_navAdmin);
        toolbarAdmin = findViewById(R.id.toolbarAdmin);


        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");

        replace(new HomeFragment());

        if (user.equals("admin") && pass.equals("admin")) {
            setSupportActionBar(toolbarAdmin);
            getSupportActionBar().setTitle("Thông Tin Người Dùng");
            replace(new QuanLiNguoiDungFragment());

            bottomBar.setVisibility(View.GONE);
            bottombarAdmin.setVisibility(View.VISIBLE);
            toolbarAdmin.setVisibility(View.VISIBLE);

            bottombarAdmin.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.qlnd) {
                    QuanLiNguoiDungFragment quanLiNguoiDungFragment = new QuanLiNguoiDungFragment();
                    replace(quanLiNguoiDungFragment);
                    setSupportActionBar(toolbarAdmin);
                    getSupportActionBar().setTitle("Thông Tin Người Dùng");

                } else if (item.getItemId() == R.id.qlsp) {
                    QuanLiSanPhamFragment quanLiSanPhamFragment = new QuanLiSanPhamFragment();
                    replace(quanLiSanPhamFragment);

                    setSupportActionBar(toolbarAdmin);
                    getSupportActionBar().setTitle("Thông Tin Sản Phẩm");

                } else if (item.getItemId() == R.id.qlhang) {
                    HangxeFragment hangxeFragment = new HangxeFragment();
                    replace(hangxeFragment);
                    setSupportActionBar(toolbarAdmin);
                    getSupportActionBar().setTitle("Thông Tin Hãng");

                } else if (item.getItemId() == R.id.Admin) {
                    QuanLiAdminFragment adminFragment = new QuanLiAdminFragment();
                    replace(adminFragment);
                    toolbarAdmin.setVisibility(View.GONE);

                }
                return true;
            });
        }


        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    HomeFragment homeFragment = new HomeFragment();
                    replace(homeFragment);
                } else if (item.getItemId() == R.id.list) {
                    MuaHangFragment muaHangFragment = new MuaHangFragment();
                    replace(muaHangFragment);
                } else if (item.getItemId() == R.id.fab) {
                    DangtinFragment dangtinFragment = new DangtinFragment();
                    replace(dangtinFragment);
                } else if (item.getItemId() == R.id.person) {
                    PersonFragment personFragment = new PersonFragment();
                    replace(personFragment);
                }


                return true;
            }
        });

    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmbottom, fragment);
        transaction.commit();
    }


}