package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;


public class ChiTietSanPhamFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_san_pham, container, false);
        Bundle bundle =getArguments();
        if(bundle!=null){
            Sanpham selectSanPham = (Sanpham) bundle.getSerializable("selectSanPham");
        }
        return view;
    }
}