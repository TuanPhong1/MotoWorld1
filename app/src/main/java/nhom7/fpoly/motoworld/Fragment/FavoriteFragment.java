package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.FavoriteAdapter;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Dao.YeuThichDao;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private View view;
    private boolean isFavorite = false;
    FavoriteAdapter adapter;
    YeuThichDao yeuThichDao;
    private ArrayList<Sanpham> danhSachSanPhamYeuThich;
    TkNguoiDungDao nddao;
    int matknd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        yeuThichDao = new YeuThichDao(getContext());
        nddao = new TkNguoiDungDao(getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");

        matknd = nddao.getMatkndFromTkNguoiDung(user, pass);

        danhSachSanPhamYeuThich = (ArrayList<Sanpham>) yeuThichDao.getSanPhamInGioHangByMatkd(matknd);// Khởi tạo danh sách sản phẩm yêu thích

        loaddata();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.myToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Sản Phẩm Yêu Thích");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
        loaddata();
    }

    public void loaddata() {
        // Thiết lập layout manager cho RecyclerView

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.rcvFavorite.setLayoutManager(gridLayoutManager);

        // Thiết lập adapter cho RecyclerView
        adapter = new FavoriteAdapter(getContext(),danhSachSanPhamYeuThich,getActivity());
        binding.rcvFavorite.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}