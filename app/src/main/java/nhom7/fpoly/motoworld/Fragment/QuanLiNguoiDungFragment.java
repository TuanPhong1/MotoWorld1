package nhom7.fpoly.motoworld.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.TaiKhoanAdapter;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;
import nhom7.fpoly.motoworld.databinding.FragmentQuanLiNguoiDungBinding;


public class QuanLiNguoiDungFragment extends Fragment {

    private FragmentQuanLiNguoiDungBinding binding;
    private View view;
    TkNguoiDungDao dao;
    ArrayList<TkNguoiDung> list;
    TaiKhoanAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuanLiNguoiDungBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        dao = new TkNguoiDungDao(getActivity());

        capNhatLv();

        return view;
    }

    void capNhatLv() {
        list = (ArrayList<TkNguoiDung>) dao.getAll();
        adapter = new TaiKhoanAdapter(getActivity(), this, list);
        binding.lvPerson.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
}