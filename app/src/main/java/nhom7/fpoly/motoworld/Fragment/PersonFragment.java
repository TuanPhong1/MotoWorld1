package nhom7.fpoly.motoworld.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import nhom7.fpoly.motoworld.MainDangNhap;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.TaiKhoan.SuDung;
import nhom7.fpoly.motoworld.TaiKhoan.XeDaMua;
import nhom7.fpoly.motoworld.TaiKhoan.XeDangBan;
import nhom7.fpoly.motoworld.TaiKhoan.XeYeuThich;


public class PersonFragment extends Fragment {

    CardView cardView;
    Activity activity;
    TextView tennd;
    LinearLayout linearLayoutdangban, linearLayoutdamua, linearLayoutyeuthich, linearLayoutsudung, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        linearLayoutdangban = view.findViewById(R.id.imgdangban);
        linearLayoutdamua = view.findViewById(R.id.imgdamua);
        linearLayoutyeuthich = view.findViewById(R.id.imgyeuthich);
        linearLayoutsudung = view.findViewById(R.id.imgsudung);
        logout = view.findViewById(R.id.btnlogout);
        tennd = view.findViewById(R.id.tvtennguoidung);
        cardView = view.findViewById(R.id.cardviewperson);

        // Truy xuất tên người dùng từ SharedPreferences
        SharedPreferences shared = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String name = shared.getString("tenND", "Mafia"); // "Name" là tên được hiển thị mặc định nếu không có tên trong SharedPreferences

// Đặt tên vào tvtennguoidung
        tennd.setText(name);

// Đăng ký BroadcastReceiver để nhận broadcast từ MyAccountFragment
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("UPDATE_NAME_ACTION")) {
                    String newName = intent.getStringExtra("newName");
                    tennd.setText(newName);
                }
            }
        };
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver, new IntentFilter("UPDATE_NAME_ACTION"));

        cardView.setOnClickListener(v -> {

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //animation từ fragment này sang fragment khác
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

            MyAccountFragment myAccountFragment = new MyAccountFragment();
            fragmentTransaction.replace(R.id.frmbottom, myAccountFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        linearLayoutdangban.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), XeDangBan.class);
            startActivity(intent);
        });
        linearLayoutdamua.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), XeDaMua.class);
            startActivity(intent);
        });
        linearLayoutyeuthich.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), XeYeuThich.class);
            startActivity(intent);
        });
        linearLayoutsudung.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SuDung.class);
            startActivity(intent);
        });
        logout.setOnClickListener(v -> {

            //Xóa thông tin cá nhân khi người dùng chuyển đổi tài khoản
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", getContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear(); // hoặc editor.remove("tenND") nếu bạn chỉ muốn xóa một trường cụ thể
            editor.apply();

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Đăng xuất");
            builder.setMessage("Bạn có muốn đăng xuất không?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(getActivity(), MainDangNhap.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Log Out successful", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            Dialog dialog = builder.create();
            dialog.show();
        });
        return view;
    }
    public void updateUserName(String newName) {
        tennd.setText(newName);
    }
}