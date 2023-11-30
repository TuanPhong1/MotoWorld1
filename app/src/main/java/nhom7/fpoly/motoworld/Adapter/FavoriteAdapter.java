package nhom7.fpoly.motoworld.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.YeuThichDao;
import nhom7.fpoly.motoworld.Fragment.ChiTietSanPhamFragment;
import nhom7.fpoly.motoworld.Fragment.FavoriteFragment;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.ItemFavoriteBinding;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    private ArrayList<Sanpham> list;
    private Activity activity;
    private FavoriteFragment fragment;
    YeuThichDao yeuThichDao;
int matknd;
    public FavoriteAdapter(Context context, ArrayList<Sanpham> list, Activity activity) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        Sanpham sp = list.get(position);

        //Lấy dữ liệu ảnh lên recyclerview
        Uri imagesUri = Uri.parse(sp.getImage());
        Log.d("tag", "onBindViewHolder: " + imagesUri);
        holder.binding.imgFavorite.setImageURI(imagesUri);

        holder.binding.tvTenfavorite.setText("TênSP:" + sp.getTensp());

        HangxeDao hangxeDao = new HangxeDao(context);
        Hangxe hangxe = hangxeDao.getID(String.valueOf(sp.getMahang()));
        holder.binding.tvHangfavorite.setText("Hãng:" + String.valueOf(hangxe.getTenhang()));

        holder.binding.tvGiafavorite.setText("Giá:" + String.valueOf(sp.getGia()));

        holder.binding.cardviewsp.setOnClickListener(view -> {
            openFragment(sp,holder.itemView.getContext());
        });
        holder.binding.deleteFavorite.setOnClickListener(view -> {
            xoa(sp.getMasp());
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ItemFavoriteBinding binding;
        public ViewHolder(@NonNull ItemFavoriteBinding binding) {
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
            if (activity instanceof FragmentActivity) {
                fragmentActivity = (FragmentActivity) activity;
                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frmbottom, frg);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }
    }
    public void xoa(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                yeuThichDao.delete(String.valueOf(id));
                list.clear();
                list.addAll(yeuThichDao.getSanPhamInGioHangByMatkd(matknd));
                notifyDataSetChanged();
                dialog.cancel();
                Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
}
