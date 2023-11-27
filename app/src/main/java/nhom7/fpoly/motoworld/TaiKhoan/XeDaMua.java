package nhom7.fpoly.motoworld.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import nhom7.fpoly.motoworld.R;

public class XeDaMua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_da_mua);
        ImageButton imgdamua=findViewById(R.id.imgbackdamua);
        imgdamua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}