package nhom7.fpoly.motoworld.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import nhom7.fpoly.motoworld.Fragment.PersonFragment;
import nhom7.fpoly.motoworld.R;

public class XeDangBan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_dang_ban);
        ImageButton imgdangban=findViewById(R.id.imgbackdangban);
        imgdangban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}