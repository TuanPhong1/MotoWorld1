package nhom7.fpoly.motoworld.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import nhom7.fpoly.motoworld.MainDangNhap;
import nhom7.fpoly.motoworld.R;


public class QuanLiAdminFragment extends Fragment {

LinearLayout logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_quan_li_admin, container, false);

        logout = view.findViewById(R.id.lnLogout);
        logout.setOnClickListener(view1 -> {
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
}