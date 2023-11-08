package nhom7.fpoly.motoworld.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhom7.fpoly.motoworld.Adapter.slideshowAdapter;
import nhom7.fpoly.motoworld.R;


public class HomeFragment extends Fragment {

private ViewPager viewPager;
private slideshowAdapter adapter;
private Handler handler;
private int currentPage =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.Viewpager);
        adapter = new slideshowAdapter(requireContext());
        viewPager.setAdapter(adapter);

        handler = new Handler();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                viewPager.setCurrentItem(currentPage,true);
                startslideshow();
            }
        },2000);
    }
}