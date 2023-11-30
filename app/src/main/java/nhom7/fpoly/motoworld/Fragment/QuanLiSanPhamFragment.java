package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentQuanLiSanPhamBinding;


public class QuanLiSanPhamFragment extends Fragment {

    private FragmentQuanLiSanPhamBinding binding;
    private View view;
    ArrayList<Sanpham> list;
    SanphamDao dao;
    SanPhamAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuanLiSanPhamBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        loaddata();

        return view;
    }
    public void loaddata() {
        //show list product in recyclerview MuaHangFragment
        dao = new SanphamDao(getActivity());
        list = (ArrayList<Sanpham>) dao.getAll();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvSanpham.setLayoutManager(gridLayoutManager);

        adapter = new SanPhamAdapter(getContext(), list, getActivity(),dao);
        binding.rcvSanpham.setAdapter(adapter);
    }
}