package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Adapter.slideshowAdapter;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

private slideshowAdapter adapter;
private Handler handler;
private int currentPage =0;

ArrayList<Sanpham> list;
SanPhamAdapter sanPhamAdapter;


MuaHangFragment muaHangFragment;

SanphamDao dao;
private FragmentHomeBinding binding;
private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        adapter = new slideshowAdapter(requireContext());
        binding.Viewpager.setAdapter(adapter);

        loadata();

        handler = new Handler();
        binding.Viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
            startslideshow();
        return view;
    }
    private void startslideshow(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentPage == adapter.getCount() -1){
                    currentPage = 0;
                }else{
                    currentPage ++;
                }
                binding.Viewpager.setCurrentItem(currentPage,true);
                startslideshow();
            }
        },2000);
    }
    public void loadata(){
        list = new ArrayList<>();
        dao = new SanphamDao(getActivity());
        list = (ArrayList<Sanpham>) dao.getAll();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvDanhsachsp.setLayoutManager(gridLayoutManager);
        sanPhamAdapter = new SanPhamAdapter(getActivity(),list);
        binding.rcvDanhsachsp.setAdapter(sanPhamAdapter);
    }
}