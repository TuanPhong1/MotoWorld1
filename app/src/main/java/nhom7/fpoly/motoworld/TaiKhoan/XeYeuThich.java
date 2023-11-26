package nhom7.fpoly.motoworld.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import nhom7.fpoly.motoworld.R;

public class XeYeuThich extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xe_yeu_thich);
        ImageButton imgyeuthic=findViewById(R.id.imgbackyeuthich);
        imgyeuthic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}