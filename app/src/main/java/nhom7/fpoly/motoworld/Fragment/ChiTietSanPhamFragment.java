package nhom7.fpoly.motoworld.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.FragmentChiTietSanPhamBinding;


public class ChiTietSanPhamFragment extends Fragment {

private FragmentChiTietSanPhamBinding binding;
private View view;
SanPhamAdapter adapter;
ArrayList<Sanpham> list;
SanphamDao dao;
Sanpham item;
Uri selectUri;
TkNguoiDungDao ndDao;
SanphamDao sanphamDao;
private boolean isFavorite = false;

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

       sanphamDao = new SanphamDao(getContext());

       requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.GONE);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.Toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thông tin sản phẩm");


        selectUri = Uri.parse(item.getImage());
        Log.d("TAG", "onCreateView: " + selectUri);
        binding.imgCtsp.setImageURI(selectUri);
        Picasso.get().load(item.getImage()).into(binding.imgCtsp);
        Glide.with(requireContext()).load(item.getImage()).into(binding.imgCtsp);

        if(item!=null){
            binding.tvTenspct.setText("Xe: " + item.getTensp());

            ndDao = new TkNguoiDungDao(getContext());
            TkNguoiDung tkNguoiDung = ndDao.getID(String.valueOf(item.getMatk()));
            Log.d("TAG","Tag" +item.getMatk()   );
            binding.tvtentknd.setText(tkNguoiDung.getTaikhoan());

            HangxeDao hangxeDao = new HangxeDao(getContext());
            Hangxe hangxe = hangxeDao.getID(String.valueOf(item.getMahang()));
            binding.tvHangspct.setText(String.valueOf("Hãng: " + hangxe.getTenhang()));

            binding.tvGiaspct.setText(String.valueOf("Giá: " + item.getGia()));
            binding.tvLoaixespct.setText(item.getLoaixe());
            binding.tvNamsxspct.setText(String.valueOf(item.getNamsx()));
            binding.tvDongcospct.setText(item.getDongco());
            binding.tvMausacspct.setText(item.getMauxe());
//            if(item.getTrangthai() == 1){
//                binding.chkTrangthai.setChecked(true);
//            }else{
//                binding.chkTrangthai.setChecked(false);
//            }

        }


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });
    }

}