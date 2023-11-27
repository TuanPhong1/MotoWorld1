package nhom7.fpoly.motoworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;
import nhom7.fpoly.motoworld.databinding.ActivityMainDangKiBinding;

public class MainDangKi extends AppCompatActivity {
    TkNguoiDungDao ndDao;
TkNguoiDung nd;

private ActivityMainDangKiBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDangKiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ndDao = new TkNguoiDungDao(this);
        binding.signupButton.setOnClickListener(view -> {
            String user = binding.signupUser.getText().toString();
            String pass = binding.signupPassword.getText().toString();
            String apass = binding.signupAgainpassword.getText().toString();
            if(user.isEmpty()||pass.isEmpty()|| apass.isEmpty()){
                Toast.makeText(this, "Không được để trống trường dữ liệu", Toast.LENGTH_SHORT).show();
            }else {
                if(pass.equalsIgnoreCase(apass)){
                    nd = new TkNguoiDung();
                    nd.setTaikhoan(user);
                    nd.setMatkhau(pass);

                    if(ndDao.insert(nd)>0){
                        Toast.makeText(this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainDangKi.this, MainDangNhap.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}