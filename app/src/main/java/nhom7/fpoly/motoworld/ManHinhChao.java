package nhom7.fpoly.motoworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import nhom7.fpoly.motoworld.Onboarding.Onboarding_Activity;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        ImageView logo = findViewById(R.id.LogoImg);
        Glide.with(this).load(R.mipmap.logo).into(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChao.this, Onboarding_Activity.class);
                startActivity(intent);
            }
        },3000);
    }
}