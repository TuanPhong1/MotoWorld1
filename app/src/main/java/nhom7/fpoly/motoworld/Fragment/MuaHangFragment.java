package nhom7.fpoly.motoworld.Fragment;

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
    Sanpham sp;
    MuaHangFragment frg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMuahangBinding.inflate(inflater, container, false);
        view = binding.getRoot();
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvListsp.setLayoutManager(gridLayoutManager);

        adapter = new SanPhamAdapter(getContext(), list);
        binding.rcvListsp.setAdapter(adapter);
    }
}