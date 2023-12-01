package nhom7.fpoly.motoworld.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Adapter.slideshowAdapter;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private slideshowAdapter adapter;
    private Handler handler;
    private int currentPage = 0;

    ArrayList<Sanpham> list;
    SanPhamAdapter sanPhamAdapter;


    MuaHangFragment muaHangFragment;

    SanphamDao dao;
    private FragmentHomeBinding binding;
    private View view;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        adapter = new slideshowAdapter(requireContext());
        binding.Viewpager.setAdapter(adapter);

        //cho phép truy cập vị trí hiện tại
//        location();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);

        // Thiết lập Toolbar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.myToolbar);
        binding.myToolbar.setTitle("");

        // Lấy vị trí hiện tại và hiển thị lên TextView
//        getCurrentLocation();


        //lấy dữ liêu từ Recyclerview
        loadata();

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

    private void startslideshow() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPage == adapter.getCount() - 1) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                binding.Viewpager.setCurrentItem(currentPage, true);
                startslideshow();
            }
        }, 2000);
    }

    public void loadata() {
        list = new ArrayList<>();
        dao = new SanphamDao(getActivity());
        list = (ArrayList<Sanpham>) dao.getAll();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.rcvDanhsachsp.setLayoutManager(gridLayoutManager);
        sanPhamAdapter = new SanPhamAdapter(getContext(), list, getActivity(), dao);
        binding.rcvDanhsachsp.setAdapter(sanPhamAdapter);
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
            sanPhamAdapter.setSearchList(sanphamList);
        }
    }

//    private void location() {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
//        locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000); // Thời gian cập nhật vị trí (10 giây)
//        locationCallback = new LocationCallback() {
//            @Override
//            public void onLocationResult(@NonNull LocationResult locationResult) {
//                for (Location location : locationResult.getLocations()) {
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//                }
//            }
//        };
//        // Yêu cầu quyền truy cập vị trí từ người dùng
//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
//        } else {
//            startLocationUpdates();
//        }
//    }
//
//    private void startLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startLocationUpdates();
//            } else {
//                // Người dùng từ chối cấp quyền truy cập vị trí, xử lý tương ứng
//                // Ví dụ: Hiển thị thông báo, vô hiệu hóa tính năng liên quan đến vị trí, v.v.
//            }
//        }
//    }
//
//    private void getCurrentLocation() {
//        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            double latitude = location.getLatitude();
//                            double longitude = location.getLongitude();
//
//                            String cityName = getCityName(latitude, longitude);
//                            binding.HomeLocation.setText(cityName);
//                        }
//                    }
//                });
//    }
//
//    private String getCityName(double latitude, double longitude) {
//        Geocoder geocoder = new Geocoder(mContext, new Locale("vi_VN"));
//        List<Address> addresses;
//        String cityName = "";
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            if (addresses.size() > 0) {
//                cityName = addresses.get(0).getLocality();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return cityName;
//    }
}