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
import nhom7.fpoly.motoworld.Fragment.HangxeFragment;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.R;

public class HangxeAdapter extends ArrayAdapter<Hangxe> {
    private Context context;
    private HangxeFragment hangxeFragment;
    private List<Hangxe> list;

    TextView tvmahang,tvhang;
    ImageView delete;
HangxeDao dao;
    public void setSearchList(ArrayList<Hangxe> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    public HangxeAdapter(@NonNull Context context, List<Hangxe> list, HangxeDao dao) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_hangxe,parent,false);
        }
        Hangxe item = list.get(position);
        if(item!= null){
            tvmahang = view.findViewById(R.id.tvmahangxe);
            tvmahang.setText("Mã hãng: "+item.getMahang());
            tvhang = view.findViewById(R.id.tvtenhangxe);
            tvhang.setText(" Hãng Xe: "+item.getTenhang());
        }
        delete = view.findViewById(R.id.deleteHangXe);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hangxeFragment.xoa(String.valueOf(item.getMahang()));
                }
            });
        return view;
    }
}
