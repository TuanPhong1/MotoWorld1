package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.NguoiDungDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Dao.TkNguoiDungDao;
import nhom7.fpoly.motoworld.Dao.XeDaMuaDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.NguoiDung;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.TaiKhoan.XeDaMua;
import nhom7.fpoly.motoworld.databinding.FragmentThanhToanBinding;
public class ThanhToanFragment extends Fragment {

    private FragmentThanhToanBinding binding;
    private View view;
    private Sanpham item;
    private Uri selectUri;
    private NguoiDung nguoiDung;

    private TkNguoiDungDao nddao;
    private NguoiDungDao nguoiDungDao;
    private SanphamDao sanphamDao;
    ArrayList<Sanpham> list;
    private boolean isTrangThai = false;
    ArrayList<NguoiDung> listnguoidung;
    int matknd;
    nhom7.fpoly.motoworld.Model.XeDaMua xeDaMua;
XeDaMuaDao xeDaMuaDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThanhToanBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        if (getArguments() != null) {
            item = (Sanpham) getArguments().getSerializable("ThanhToan");
        }

        sanphamDao = new SanphamDao(getActivity());
        nddao = new TkNguoiDungDao(getActivity());
        nguoiDungDao = new NguoiDungDao(getActivity());
        xeDaMuaDao  =new XeDaMuaDao(getActivity());

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbarthanhtoan);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thanh Toán");

        isTrangThai =PreferenceUtils.getFavoriteState(requireContext(),item);

        if (item != null) {
            selectUri = Uri.parse(item.getImage());
            Log.d("TAG", "onCreateView: " + selectUri);
            binding.imgAnhtt.setImageURI(selectUri);
            Picasso.get().load(item.getImage()).into(binding.imgAnhtt);
            Glide.with(requireContext()).load(item.getImage()).into(binding.imgAnhtt);

            binding.txttenxett.setText(item.getTensp());

//            nddao = new TkNguoiDungDao(getContext());
//            TkNguoiDung tkNguoiDung = nddao.getID(String.valueOf(item.getMatk()));
//            if (tkNguoiDung != null) {
//                binding.txttenpersontt.setText(tkNguoiDung.getTaikhoan());
//            }

            HangxeDao hangxeDao = new HangxeDao(getContext());
            Hangxe hangxe = hangxeDao.getID(String.valueOf(item.getMahang()));
            if (hangxe != null) {
                binding.txthangxett.setText(hangxe.getTenhang());
            }

            binding.txtgiatt.setText(String.valueOf(item.getGia()) + " VNĐ");
            binding.txtloaixett.setText(item.getLoaixe());
            binding.txtsanxuattt.setText(String.valueOf(item.getNamsx()));
            binding.txtdongcott.setText(item.getDongco());
            binding.txtmausactt.setText(item.getMauxe());

            listnguoidung = (ArrayList<NguoiDung>) nguoiDungDao.getAllByMAtknd(matknd);
            if (!listnguoidung.isEmpty()) {
                nguoiDung = listnguoidung.get(0);
                binding.txttenpersontt.setText(nguoiDung.getTennd());
                binding.txtsdttt.setText(nguoiDung.getSdt());
                binding.txtdiachitt.setText(nguoiDung.getDiachi());
            } else {
                binding.txttenpersontt.setText("");
                binding.txtsdttt.setText("");
                binding.txtdiachitt.setText("");
            }

            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String user = pref.getString("USERNAME", "");
            String pass = pref.getString("PASSWORD", "");

            matknd = nddao.getMatkndFromTkNguoiDung(user, pass);

            xeDaMua = new nhom7.fpoly.motoworld.Model.XeDaMua();
            ArrayList<Sanpham> listSPDM = (ArrayList<Sanpham>) xeDaMuaDao.getSanPhamInGioHangByMatkd(matknd);

            binding.btndinhdanhtt.setOnClickListener(view1 -> {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

                MyAccountFragment myAccountFragment = new MyAccountFragment();
                fragmentTransaction.replace(R.id.frmbottom, myAccountFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            });

            binding.btnDatHangtt.setOnClickListener(view1 -> {

                xeDaMua.setMasp(item.getMasp());
                xeDaMua.setMatk(matknd);

                boolean co =false;
                for(Sanpham sp:listSPDM){
                    if(sp.getMasp() == item.getMasp()){
                        co = true;
                        break;
                    }
                }

                if (co) {
                    Toast.makeText(activity, "Sản phẩm này đã được mua từ trước", Toast.LENGTH_SHORT).show();
                } else {
                    listSPDM.add(item);
                    long insert = xeDaMuaDao.insert(xeDaMua);
                    if (insert > 0) {
                        Toast.makeText(getContext(), "Đã mua sản phẩm", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                String ten = binding.txttenpersontt.getText().toString().trim();
                String sdt = binding.txtsdttt.getText().toString().trim();
                String diachi = binding.txtdiachitt.getText().toString().trim();
                if (ten.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
                    Toast.makeText(activity, "Vui lòng định danh tài khoản", Toast.LENGTH_SHORT).show();
                } else if (binding.rdottNhanhang.isChecked()) {
                    hoadonchitiet();
                } else if (binding.rdottNganhang.isChecked()) {
                    quaNganHang();
                } else if (binding.rdottVimomo.isChecked()) {
                    ViMoMo();
                } else {
                    Toast.makeText(activity, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarthanhtoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });
    }

    private void hoadonchitiet() {
        Intent intent = new Intent(getContext(), XeDaMua.class);
        startActivity(intent);
    }

    private void quaNganHang() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        NganHangFragment nganHangFragment = new NganHangFragment();
        fragmentTransaction.replace(R.id.frmbottom, nganHangFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void ViMoMo() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

        ViMoMoFragment moMoFragment = new ViMoMoFragment();
        fragmentTransaction.replace(R.id.frmbottom, moMoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
}