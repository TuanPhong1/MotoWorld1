package nhom7.fpoly.motoworld.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.R;

public class HangXeSpinnerAdapter extends ArrayAdapter<Hangxe> {
    Context context;
ArrayList<Hangxe> list;
TextView tvmahang,tvtenhang;
    public HangXeSpinnerAdapter(@NonNull  Context context, ArrayList<Hangxe> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_hangxe_spinner,null);
        }
        Hangxe hangxe = list.get(position);
        if(hangxe != null){
            tvmahang = convertView.findViewById(R.id.tvMaHang);
            tvmahang.setText(hangxe.getMahang() + ". ");
            tvtenhang = convertView.findViewById(R.id.tvTenHang);
            tvtenhang.setText(hangxe.getTenhang() );
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_hangxe_spinner,null);
        }
        Hangxe hangxe = list.get(position);
        if(hangxe != null){
            tvmahang = convertView.findViewById(R.id.tvMaHang);
            tvmahang.setText(hangxe.getMahang() + ". ");
            tvtenhang = convertView.findViewById(R.id.tvTenHang);
            tvtenhang.setText(hangxe.getTenhang() );
        }
        return convertView;
    }
}
