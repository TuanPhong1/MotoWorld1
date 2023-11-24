package nhom7.fpoly.motoworld.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.ItemSanphamBinding;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sanpham> list;

    HangxeDao hangxeDao;
    public void setSearchList(ArrayList<Sanpham> list){
        this.list = list;
        notifyDataSetChanged();
    }



    public SanPhamAdapter(Context context, ArrayList<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemSanphamBinding binding = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
       return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sanpham sp = list.get(position);
        Uri imagesUri =Uri.parse(sp.getImage());
        holder.binding.listImage.setImageURI(imagesUri);

        String imgName = sp.getImage();
        int resId = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.binding.listImage.setImageResource(resId);

        holder.binding.tvTensp.setText("TênSP:"+sp.getTensp());

        hangxeDao = new HangxeDao(context);
        Hangxe hangxe = hangxeDao.getID(String.valueOf(sp.getMahang()));
        holder.binding.tvHangsp.setText("Hãng:" + String.valueOf(hangxe.getTenhang()));

        holder.binding.tvGiasp.setText("Giá:"+String.valueOf(sp.getGia()));

        //Đang lỗi intent chi tiết sản phẩm
//        holder.binding.cardview.setOnClickListener(view -> {
//            Intent intent = new Intent(context, ChiTietSanPhamFragment.class);
//            intent.putExtra("Images",list.get(holder.getAbsoluteAdapterPosition()).getImage());
//            intent.putExtra("Name",list.get(holder.getAbsoluteAdapterPosition()).getTensp());
//            intent.putExtra("Hang",list.get(holder.getAbsoluteAdapterPosition()).getMahang());
//            intent.putExtra("Gia",list.get(holder.getAbsoluteAdapterPosition()).getGia());
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemSanphamBinding binding;
        public ViewHolder(@NonNull ItemSanphamBinding binding) {
            super(binding.getRoot());
           this.binding = binding;
        }
    }

}