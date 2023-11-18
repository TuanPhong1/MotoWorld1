    package nhom7.fpoly.motoworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import nhom7.fpoly.motoworld.Fragment.ChiTietSanPhamFragment;
import nhom7.fpoly.motoworld.Fragment.HomeFragment;
import nhom7.fpoly.motoworld.Fragment.PersonFragment;
import nhom7.fpoly.motoworld.Fragment.SettingFragment;
import nhom7.fpoly.motoworld.Fragment.ShopFragment;
import nhom7.fpoly.motoworld.Fragment.StoryFragment;
import nl.joery.animatedbottombar.AnimatedBottomBar;


    public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomBar = findViewById(R.id.bottom_nav);


        replace(new HomeFragment());

bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home){
            HomeFragment homeFragment =new HomeFragment();
            replace(homeFragment);
        } else if (item.getItemId() == R.id.list) {
            ShopFragment shopFragment = new ShopFragment();
            replace(shopFragment);
        }else if (item.getItemId() == R.id.fab) {
            StoryFragment storyFragment = new StoryFragment();
            replace(storyFragment);
        }else if (item.getItemId() == R.id.person) {
            PersonFragment personFragment = new PersonFragment();
            replace(personFragment);
        }else if (item.getItemId() == R.id.setting) {
            SettingFragment settingFragment = new SettingFragment();
            replace(settingFragment);
        }
        return true;
    }
});

    }

    private void replace(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frmbottom,fragment);
        transaction.commit();
    }
}