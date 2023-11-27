package nhom7.fpoly.motoworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.databinding.ActivityMainDangNhapBinding;

public class MainDangNhap extends AppCompatActivity {
    TkNguoiDungDao ndDao;
    int matk;
    String strUser, strPass;

    private ActivityMainDangNhapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ndDao = new TkNguoiDungDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean remem = pref.getBoolean("REMEMBER", false);

        binding.signinUser.setText(user);
        binding.signinPassword.setText(pass);
        binding.chkSave.setChecked(remem);

        binding.logupRedirectText.setOnClickListener(view -> {
            Intent intent = new Intent(MainDangNhap.this, MainDangKi.class);
            startActivity(intent);
        });

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // Xóa trạng thái lưu trước đó
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        // Lưu lại toàn bộ dữ liệu
        edit.apply();
    }

    public void checkLogin() {
        strUser = binding.signinUser.getText().toString();
        strPass = binding.signinPassword.getText().toString();
        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (ndDao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, binding.chkSave.isChecked());
                matk = ndDao.getMatkndFromTkNguoiDung(strUser, strPass);
                Log.d("123", "tkmd = " + matk);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}