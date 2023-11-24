package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import nhom7.fpoly.motoworld.databinding.FragmentChiTietSanPhamBinding;


public class ChiTietSanPhamFragment extends Fragment {

private FragmentChiTietSanPhamBinding binding;
private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentChiTietSanPhamBinding.inflate(inflater,container,false);
       view = binding.getRoot();

        Bundle bundle =getArguments();
        if(bundle!=null){
            binding.imgCtsp.setImageResource(bundle.getInt("Images"));
            binding.tvTenspct.setText(bundle.getString("name"));
            binding.tvHangspct.setText(bundle.getInt("hang"));
            binding.tvGiaspct.setText(bundle.getInt("gia"));
            binding.tvLoaixespct.setText(bundle.getString("loaixe"));
            binding.tvNamsxspct.setText(bundle.getInt("Namsx"));
            binding.tvDongcospct.setText(bundle.getString("dongco"));
            binding.tvMausacspct.setText(bundle.getString("mausac"));
            binding.tvTrangthaispct.setText(bundle.getInt("trangthai"));
        }
        return view;
    }
}