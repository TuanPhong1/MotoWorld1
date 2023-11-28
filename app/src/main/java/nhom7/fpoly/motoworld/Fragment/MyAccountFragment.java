package nhom7.fpoly.motoworld.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import nhom7.fpoly.motoworld.databinding.FragmentMyAccountBinding;


public class MyAccountFragment extends Fragment {
    private FragmentMyAccountBinding binding;
    private SharedPreferences sharedPreferences,shared;
String tenND,namSinh,gioiTinh,sdt,diaChi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        loadCurrentAccountData();

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        binding.edttenttperson.setText(sharedPreferences.getString("tenND", ""));
        binding.edtNamsinhttperson.setText(sharedPreferences.getString("namSinh", ""));
        binding.edtGioitinhttperson.setText(sharedPreferences.getString("gioiTinh", ""));
        binding.edtSdtttperson.setText(sharedPreferences.getString("sdt", ""));
        binding.edtDiaChittperson.setText(sharedPreferences.getString("diaChi", ""));

        //cập nhật tên người dùng ngoài personFragment khi đổi tên trong MyAccountFragment
        // Lưu tên người dùng vào SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tenND", tenND); // tenND là tên mới được người dùng nhập vào
        editor.apply();

//         Đoạn mã trong MyAccountFragment khi người dùng đổi sang tài khoản khác
        String newUserName = "Mafia";
        PersonFragment personFragment = (PersonFragment) getParentFragmentManager().findFragmentByTag("person_fragment_tag");
        if (personFragment != null) {
            personFragment.updateUserName(newUserName); // newUserName là tên người dùng mới
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbarttPerson);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thông tin cá nhân");

        binding.btnSaveTtperson.setOnClickListener(view1 -> {
            saveCurrentAccountData();
            Toast.makeText(getActivity(), "Đã lưu thông tin", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCurrentAccountData();
    }


    private void saveCurrentAccountData() {
        tenND = binding.edttenttperson.getText().toString();
        namSinh = binding.edtNamsinhttperson.getText().toString();
        gioiTinh = binding.edtGioitinhttperson.getText().toString();
        sdt = binding.edtSdtttperson.getText().toString();
        diaChi = binding.edtDiaChittperson.getText().toString();
        if (tenND.isEmpty() || namSinh.isEmpty() || gioiTinh.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tenND", tenND);
            editor.putString("namSinh", namSinh);
            editor.putString("gioiTinh", gioiTinh);
            editor.putString("sdt", sdt);
            editor.putString("diaChi", diaChi);
            editor.apply();

            // Tạo intent broadcast
            Intent intent = new Intent("UPDATE_NAME_ACTION");
            intent.putExtra("newName", tenND); // tenND là tên mới được người dùng nhập vào
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent);

            updateTvtenttperson(tenND); // Cập nhật tvtenttperson

            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.popBackStack();
        }
    }

    private void loadCurrentAccountData() {
        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String tenND = sharedPreferences.getString("tenND", "");
        String namSinh = sharedPreferences.getString("namSinh", "");
        String gioiTinh = sharedPreferences.getString("gioiTinh", "");
        String sdt = sharedPreferences.getString("sdt", "");
        String diaChi = sharedPreferences.getString("diaChi", "");

        binding.edttenttperson.setText(tenND);
        binding.edtNamsinhttperson.setText(namSinh);
        binding.edtGioitinhttperson.setText(gioiTinh);
        binding.edtSdtttperson.setText(sdt);
        binding.edtDiaChittperson.setText(diaChi);

        updateTvtenttperson(tenND); // Cập nhật tvtenttperson
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarttPerson.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
    private void updateTvtenttperson(String tenND) {
        binding.tvtenttperson.setText(tenND);
    }
}