package nhom7.fpoly.motoworld.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Fragment.ShopFragment;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;

public class SanPhamAdapter extends ArrayAdapter<Sanpham> {
  private Context context;
  private ShopFragment frgSP;
  private List<Sanpham> list;

  SanphamDao dao;
  HangxeDao hangxeDao;

  TextView tvmasp,tvmahang,tvtensp,tvgia,tvloaixe,tvmauxe,tvnamsx,tvdongco,tvtrangthai;
  ImageView delete;

    public SanPhamAdapter(@NonNull Context context, ShopFragment frgSP, List<Sanpham> list) {
        super(context, 0,list);
        this.context = context;
        this.frgSP = frgSP;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_sanpham,parent,false);
        }
        Sanpham item = list.get(position);
        if(item!= null){
            HangxeDao hangxeDao = new HangxeDao(getContext());
            Hangxe hangxe = hangxeDao.getID(String.valueOf(item.getMahang()));

            tvtensp =view.findViewById(R.id.tvtensp);
            tvtensp.setText("Tên Xe :"+item.getTensp());

            tvmahang =view.findViewById(R.id.tvhangsp);
            tvmahang.setText("Hãng xe: "+hangxe.getTenhang());

            tvgia =view.findViewById(R.id.tvgiasp);
            tvgia.setText("Giá: "+item.getGia() + " VND");
        }
        return view;
    }
}
