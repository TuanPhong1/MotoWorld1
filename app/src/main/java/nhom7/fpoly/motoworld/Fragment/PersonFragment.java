package nhom7.fpoly.motoworld.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.TaiKhoan.SuDung;
import nhom7.fpoly.motoworld.TaiKhoan.XeDaMua;
import nhom7.fpoly.motoworld.TaiKhoan.XeDangBan;
import nhom7.fpoly.motoworld.TaiKhoan.XeYeuThich;


public class PersonFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_person, container, false);
        LinearLayout linearLayoutdangban=view.findViewById(R.id.imgdangban);
        LinearLayout linearLayoutdamua=view.findViewById(R.id.imgdamua);
        LinearLayout linearLayoutyeuthich=view.findViewById(R.id.imgyeuthich);
        LinearLayout linearLayoutsudung=view.findViewById(R.id.imgsudung);


        linearLayoutdangban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), XeDangBan.class);
                startActivity(intent);
            }
        });
        linearLayoutdamua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), XeDaMua.class);
                startActivity(intent);
            }
        });
        linearLayoutyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), XeYeuThich.class);
                startActivity(intent);
            }
        });
        linearLayoutsudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SuDung.class);
                startActivity(intent);
            }
        });
        return view;
    }
}