package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import nhom7.fpoly.motoworld.Adapter.SanPhamAdapter;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;


public class ShopFragment extends Fragment {
ListView listView;
List<Sanpham> list;
SanphamDao dao;
Hangxe hangxe;

SanPhamAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        listView = v.findViewById(R.id.lv_listsp);
        dao = new SanphamDao(getActivity());
        capnhatLV();


        return v;
    }
    public void capnhatLV(){
        list =(List<Sanpham>) dao.getAll();
        adapter = new SanPhamAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
}