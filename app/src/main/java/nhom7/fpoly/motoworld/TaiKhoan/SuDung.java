package nhom7.fpoly.motoworld.TaiKhoan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import nhom7.fpoly.motoworld.R;

public class SuDung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_su_dung);
        ImageButton imgyeusudung=findViewById(R.id.imgbacksudung);
        imgyeusudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}