package nhom7.fpoly.motoworld;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import nhom7.fpoly.motoworld.Dao.NguoiDungDao;
import nhom7.fpoly.motoworld.Model.NguoiDung;

public class ThongTinUserDialog extends DialogFragment {
    private static final String ARG_MATKND = "matknd";

    public static ThongTinUserDialog newInstance(int matknd) {
        ThongTinUserDialog fragment = new ThongTinUserDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_MATKND, matknd);
        fragment.setArguments(args);
        return fragment;
    }
//nnnn
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        NguoiDungDao nguoiDungDAO = new NguoiDungDao(requireContext());

        int matknd = getArguments().getInt(ARG_MATKND);

        List<NguoiDung> nguoiDungList = nguoiDungDAO.getAllByMAtknd(matknd);

        if (!nguoiDungList.isEmpty()) {
            NguoiDung nguoiDung = nguoiDungList.get(0);

            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.item_thongtinuser, null);

            TextView txtmand = dialogView.findViewById(R.id.txt_mand);
            TextView txtTennd = dialogView.findViewById(R.id.txt_Tennd);
            TextView txtNamSinh = dialogView.findViewById(R.id.txt_Namsinh);
            TextView txtGioiTinh = dialogView.findViewById(R.id.txt_GioiTinh);
            TextView txtSdt = dialogView.findViewById(R.id.txt_Sdt);
            TextView txtDiaChi = dialogView.findViewById(R.id.txt_DiaChi);
            Button btnClose = dialogView.findViewById(R.id.btn_Close);

            txtTennd.setText(nguoiDung.getTennd());
            txtNamSinh.setText(nguoiDung.getNamsinh());
            txtGioiTinh.setText(nguoiDung.getGioitinh());
            txtSdt.setText(nguoiDung.getSdt());
            txtDiaChi.setText(nguoiDung.getDiachi());

            builder.setView(dialogView);

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        } else {
            builder.setMessage("Không tìm thấy thông tin người dùng");
        }

        return builder.create();
    }

}
