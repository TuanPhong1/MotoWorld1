    package nhom7.fpoly.motoworld;

    import android.os.Bundle;
    import android.view.MenuItem;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentTransaction;

    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.android.material.navigation.NavigationBarView;

    import nhom7.fpoly.motoworld.Fragment.DangtinFragment;
    import nhom7.fpoly.motoworld.Fragment.HomeFragment;
    import nhom7.fpoly.motoworld.Fragment.MuaHangFragment;
    import nhom7.fpoly.motoworld.Fragment.PersonFragment;


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
            MuaHangFragment muaHangFragment = new MuaHangFragment();
            replace(muaHangFragment);
        }else if (item.getItemId() == R.id.fab) {
            DangtinFragment dangtinFragment = new DangtinFragment();
            replace(dangtinFragment);
        }else if (item.getItemId() == R.id.person) {
            PersonFragment personFragment = new PersonFragment();
            replace(personFragment);
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