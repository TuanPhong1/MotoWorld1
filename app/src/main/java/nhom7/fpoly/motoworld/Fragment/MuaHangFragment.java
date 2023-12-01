package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentMuahangBinding;


public class MuaHangFragment extends Fragment {
    ArrayList<Sanpham> list;
    SanphamDao dao;
    Hangxe hangxe;
    private FragmentMuahangBinding binding;
    private View view;
    SanPhamAdapter adapter;
    Sanpham item;
    MuaHangFragment frg;
    int matk;
    TkNguoiDungDao ndDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMuahangBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        ndDao = new TkNguoiDungDao(getActivity());
        item = new Sanpham();
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");

        matk =ndDao.getMatkndFromTkNguoiDung(user, pass);

        loaddata();

        binding.search.clearFocus();
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        return view;
    }

    private void searchList(String text) {
        ArrayList<Sanpham> sanphamList = new ArrayList<>();
        for (Sanpham sp : list) {
            if (sp.getTensp().toLowerCase().contains(text.toLowerCase())) {
                sanphamList.add(sp);
            }
        }
        if (sanphamList.isEmpty()) {
            Toast.makeText(getContext(), "Không tìm thấy sản phẩm", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(sanphamList);
        }
    }

    public void loaddata() {
        //show list product in recyclerview MuaHangFragment
        dao = new SanphamDao(getActivity());
        list = (ArrayList<Sanpham>) dao.getAll();
        list = (ArrayList<Sanpham>) dao.getAllByMAtknd(matk);;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvListsp.setLayoutManager(gridLayoutManager);

        adapter = new SanPhamAdapter(getContext(), list, getActivity(),dao);
        binding.rcvListsp.setAdapter(adapter);
    }
    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                loaddata();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
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