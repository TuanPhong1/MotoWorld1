package nhom7.fpoly.motoworld.Onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import nhom7.fpoly.motoworld.MainDangNhap;
import nhom7.fpoly.motoworld.R;

public class GetStarted extends AppCompatActivity {
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetStarted.this, MainDangNhap.class);
                startActivity(i);
                finish();
            }
        });
    }
}