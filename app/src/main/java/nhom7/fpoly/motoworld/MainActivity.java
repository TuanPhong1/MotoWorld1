    package nhom7.fpoly.motoworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import nhom7.fpoly.motoworld.Fragment.HomeFragment;
import nhom7.fpoly.motoworld.Fragment.PersonFragment;
import nhom7.fpoly.motoworld.Fragment.SettingFragment;
import nhom7.fpoly.motoworld.Fragment.ShopFragment;
import nhom7.fpoly.motoworld.Fragment.StoryFragment;
import nl.joery.animatedbottombar.AnimatedBottomBar;


    public class MainActivity extends AppCompatActivity {
AnimatedBottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bottom_bar);


        replace(new HomeFragment());

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                if(tab1.getId() == R.id.home){
                    replace(new HomeFragment());
                }else if (tab1.getId() == R.id.shop){
                    replace(new ShopFragment());
                } else if (tab1.getId() == R.id.fab) {
                    replace(new StoryFragment());
                } else if (tab1.getId() == R.id.person) {
                    replace(new PersonFragment());
                }else if (tab1.getId() == R.id.setting){
                    replace(new SettingFragment());
                }

            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });

    }

    private void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}