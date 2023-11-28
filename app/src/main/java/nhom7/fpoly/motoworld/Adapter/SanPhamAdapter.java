package nhom7.fpoly.motoworld.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Fragment.CapNhatSanPhamFragment;
import nhom7.fpoly.motoworld.Fragment.ChiTietSanPhamFragment;
import nhom7.fpoly.motoworld.Fragment.MuaHangFragment;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.ItemSanphamBinding;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sanpham> list;
    private Activity activity;
    HangxeDao hangxeDao;
    MuaHangFragment fragment;

    public void setSearchList(ArrayList<Sanpham> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public SanPhamAdapter(Context context, ArrayList<Sanpham> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamBinding binding = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        Sanpham sp = list.get(position);

        //Lấy dữ liệu ảnh lên recyclerview
        Uri imagesUri = Uri.parse(sp.getImage());
        Log.d("tag", "onBindViewHolder: " + imagesUri);
        holder.binding.listImage1.setImageURI(imagesUri);

        holder.binding.tvTensp.setText("TênSP:" + sp.getTensp());

        hangxeDao = new HangxeDao(context);
        Hangxe hangxe = hangxeDao.getID(String.valueOf(sp.getMahang()));
        holder.binding.tvHangsp.setText("Hãng:" + String.valueOf(hangxe != null ? hangxe.getTenhang() : ""));

        holder.binding.tvGiasp.setText("Giá:" + String.valueOf(sp.getGia()));
//        if(sp.getTrangthai() ==1){
//            holder.binding.tvTrangthai.setTextColor(Color.RED);
//            holder.binding.tvTrangthai.setText("Chưa Bán");
//        }else{
//            holder.binding.tvTrangthai.setTextColor(Color.GREEN);
//            holder.binding.tvTrangthai.setText("Chưa Bán");
//        }
        holder.binding.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment(sp, holder.itemView.getContext());
            }
        });
        holder.binding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sanpham item = list.get(position);
                    fragment.xoa(String.valueOf(item.getMasp()));
                    notifyDataSetChanged();
            }
        });

        holder.binding.cardviewsp.setOnClickListener(view -> {
            openFragment(sp, holder.itemView.getContext());
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSanphamBinding binding;

        public ViewHolder(@NonNull ItemSanphamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void openFragment(final Sanpham sanPham, Context context) {
        if (context instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            // Tạo Bundle và truyền thông tin sản phẩm vào Bundle
            Bundle bundle = new Bundle();
            bundle.putSerializable("Chitietsanpham", sanPham);

            // Tạo Fragment và truyền Bundle vào Fragment
            ChiTietSanPhamFragment frg = new ChiTietSanPhamFragment();
            frg.setArguments(bundle);

            // Gửi sự kiện tới FragmentActivity để thay thế Fragment hiện tại bằng Fragment chỉnh sửa
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

            fragmentTransaction.replace(R.id.frmbottom, frg);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void EditFragment(final Sanpham sanPham, Context context) {
        // Tạo Bundle và truyền thông tin sản phẩm vào Bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable("Update", sanPham);

        // Tạo Fragment và truyền Bundle vào Fragment
        CapNhatSanPhamFragment updateFragment = new CapNhatSanPhamFragment();
        updateFragment.setArguments(bundle);

        // Gửi sự kiện tới FragmentActivity để thay thế Fragment hiện tại bằng Fragment chỉnh sửa
        if (activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);

            fragmentTransaction.replace(R.id.frmbottom, updateFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}