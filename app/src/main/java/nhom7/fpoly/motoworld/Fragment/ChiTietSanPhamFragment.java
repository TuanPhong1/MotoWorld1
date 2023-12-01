package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Dao.YeuThichDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;
import nhom7.fpoly.motoworld.Model.YeuThich;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.FragmentChiTietSanPhamBinding;


public class ChiTietSanPhamFragment extends Fragment {

private FragmentChiTietSanPhamBinding binding;
private View view;
Sanpham item;
Uri selectUri;
TkNguoiDungDao ndDao;
SanphamDao sanphamDao;
YeuThichDao yeuThichDao;
private boolean isFavorite = false;
private boolean isLoggedIn = false;
    private SharedPreferences pref,sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            item = (Sanpham) getArguments().getSerializable("Chitietsanpham");
            Log.d("spct","sanphamchitiet" +item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentChiTietSanPhamBinding.inflate(inflater,container,false);
       view = binding.getRoot();

       yeuThichDao = new YeuThichDao(getContext());

       sanphamDao = new SanphamDao(getContext());

//       requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.GONE);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.Toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thông tin sản phẩm");

        //khi khởi tạo fragment ,kiểm tra trạng thái yêu thích được lưu trước đó
        isFavorite = PreferenceUtils.getFavoriteState(requireContext(), item);

        selectUri = Uri.parse(item.getImage());
        Log.d("TAG", "onCreateView: " + selectUri);
        binding.imgCtsp.setImageURI(selectUri);
        Picasso.get().load(item.getImage()).into(binding.imgCtsp);
        Glide.with(requireContext()).load(item.getImage()).into(binding.imgCtsp);

        if(item!=null){
            binding.tvTenspct.setText("Xe: " + item.getTensp());

            ndDao = new TkNguoiDungDao(getContext());
            TkNguoiDung tkNguoiDung = ndDao.getID(String.valueOf(item.getMatk()));
            Log.d("TAG","Tag" +item.getMatk());
            binding.tvtentknd.setText(tkNguoiDung.getTaikhoan());


            HangxeDao hangxeDao = new HangxeDao(getContext());
            Hangxe hangxe = hangxeDao.getID(String.valueOf(item.getMahang()));
            binding.tvHangspct.setText(String.valueOf("Hãng: " + hangxe.getTenhang()));

            binding.tvGiaspct.setText(String.valueOf("Giá: " + item.getGia()));
            binding.tvLoaixespct.setText( "Xe "+ item.getLoaixe());
            binding.tvNamsxspct.setText(String.valueOf(item.getNamsx()));
            binding.tvDongcospct.setText(item.getDongco());
            binding.tvMausacspct.setText(item.getMauxe());
//            if(item.getTrangthai() == 1){
//                binding.chkTrangthai.setChecked(true);
//            }else{
//                binding.chkTrangthai.setChecked(false);
//            }

            binding.ttperson.setOnClickListener(view -> {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);

                MyAccountFragment myAccountFragment = new MyAccountFragment();
                fragmentTransaction.replace(R.id.frmbottom, myAccountFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
            binding.imgttperson.setOnClickListener(view -> {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);

                MyAccountFragment myAccountFragment = new MyAccountFragment();
                fragmentTransaction.replace(R.id.frmbottom, myAccountFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });
            pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            String pass = pref.getString("PASSWORD", "");

            int matknd = ndDao.getMatkndFromTkNguoiDung(user, pass);

// Kiểm tra xem người dùng đã đăng nhập hay chưa


            Log.d("linh", "onCreateView: " + matknd);
            YeuThich yeuThich = new YeuThich();
            ArrayList<Sanpham> listSPGH = (ArrayList<Sanpham>) yeuThichDao.getSanPhamInGioHangByMatkd(matknd);

            //button yêu thích
            binding.ImgButtonFavorite.setOnClickListener(view -> {
                yeuThich.setMasp(item.getMasp());
                yeuThich.setMatk(matknd);

                boolean cos = false;
                for (Sanpham product : listSPGH) {
                    if (product.getMasp() == item.getMasp()) {
                        cos = true;
                        break;
                    }
                }

                if (cos) {
                    Toast.makeText(activity, "Sản phẩm này đã có trong yêu thích", Toast.LENGTH_SHORT).show();
                } else {
                    listSPGH.add(item);
                    long insert = yeuThichDao.insert(yeuThich);
                    if (insert > 0) {
                        Toast.makeText(getContext(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

            });
            binding.btnmuahang.setOnClickListener(view -> {
                thanhtoan(item,getContext());
            });
        }
        return view;
    }

    public static class PreferenceUtils {
        private static final String PREF_NAME = "MyPreferences";
        private static final String KEY_FAVORITE = "Favorite";

        private static SharedPreferences getSharedPreferences(Context context) {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public static   void saveFavoriteState(Context context, Sanpham sanpham, boolean isFavorite) {
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putBoolean(KEY_FAVORITE + sanpham.getMasp(), isFavorite);
            editor.apply();
        }

        public static boolean getFavoriteState(Context context, Sanpham sanpham) {
            return getSharedPreferences(context).getBoolean(KEY_FAVORITE +  sanpham.getMasp(), false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isFavorite = PreferenceUtils.getFavoriteState(requireContext(), item);
        updateFavoriteIcon();
    }

    private void updateFavoriteIcon() {
        if (isFavorite) {
            binding.ImgButtonFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red));
        } else {
            binding.ImgButtonFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });
    }
    public void thanhtoan(final Sanpham sanpham, Context context) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        ThanhToanFragment thanhToanFragment = new ThanhToanFragment();

        // Truyền thông tin đơn hàng và thông tin người mua qua bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("ThanhToan", sanpham);
        thanhToanFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.frmbottom, thanhToanFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}