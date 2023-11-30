package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

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

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.NguoiDungDao;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Model.NguoiDung;
import nhom7.fpoly.motoworld.databinding.FragmentMyAccountBinding;


public class MyAccountFragment extends Fragment {
    private FragmentMyAccountBinding binding;
    ArrayList<NguoiDung> list;
    TkNguoiDungDao nddao;
    NguoiDungDao nguoiDungDao;
    NguoiDung item;
    int matk;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        nddao = new TkNguoiDungDao(getActivity());

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbarttPerson);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thông tin cá nhân");


        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
         matk = nddao.getMatkndFromTkNguoiDung(user, pass);

        list = new ArrayList<>();
        item = new NguoiDung();
        nddao = new TkNguoiDungDao(getContext());
        nguoiDungDao = new NguoiDungDao(getContext());
        list = (ArrayList<NguoiDung>) nguoiDungDao.getAllByMAtknd(matk);

        if (!list.isEmpty()) {
            item = list.get(0);
            binding.edttenttperson.setText(item.getTennd());
            binding.edtNamsinhttperson.setText(item.getNamsinh());
            if (item.getGioitinh().equalsIgnoreCase("Nam")) {
                binding.gtNam.setChecked(true);
            } else {
                binding.gtNu.setChecked(true);
            }
            binding.edtSdtttperson.setText(item.getSdt());
            binding.edtDiaChittperson.setText(item.getDiachi());
        }

//         Đoạn mã trong MyAccountFragment khi người dùng đổi sang tài khoản khác
        String newUserName = "Mafia";
        PersonFragment personFragment = (PersonFragment) getParentFragmentManager().findFragmentByTag("person_fragment_tag");
        if (personFragment != null) {
            personFragment.updateUserName(newUserName); // newUserName là tên người dùng mới
        }


        binding.btnSaveTtperson.setOnClickListener(view1 -> {
            saveCurrentAccountData();
        });

        return view;
    }


    private void saveCurrentAccountData() {
       item.setTennd(binding.edttenttperson.getText().toString());
       item.setNamsinh(binding.edtNamsinhttperson.getText().toString());

        if(binding.gtNam.isChecked()){
            item.setGioitinh("Nam");
        }else {
            item.setGioitinh("Nữ");
        }

        item.setSdt(binding.edtSdtttperson.getText().toString());
        item.setDiachi(binding.edtDiaChittperson.getText().toString());
        item.setMatk(matk);
        if (!list.isEmpty()){
            if (nguoiDungDao.update(item)) {
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }else{
            long insert = nguoiDungDao.insert(item);
            if (insert > 0) {
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }

            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.popBackStack();
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

}