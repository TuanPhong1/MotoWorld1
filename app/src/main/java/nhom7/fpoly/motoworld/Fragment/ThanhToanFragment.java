package nhom7.fpoly.motoworld.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.NguoiDung;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
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
    ArrayList<NguoiDung> listnguoidung;
    int matknd;

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

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbarthanhtoan);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Thanh Toán");

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        matknd = nddao.getMatkndFromTkNguoiDung(user, pass);

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
            }else{
                binding.txttenpersontt.setText("");
                binding.txtsdttt.setText("");
                binding.txtdiachitt.setText("");
            }


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

            });
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}