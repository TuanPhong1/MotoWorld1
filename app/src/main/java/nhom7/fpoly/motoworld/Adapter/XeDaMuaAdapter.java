package nhom7.fpoly.motoworld.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.XeDaMuaDao;
import nhom7.fpoly.motoworld.Fragment.ChiTietSanPhamFragment;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.TaiKhoan.XeDaMua;
import nhom7.fpoly.motoworld.databinding.ItemXedamuaBinding;

public class XeDaMuaAdapter extends RecyclerView.Adapter<XeDaMuaAdapter.ViewHolder> {
    Context context;
    private ArrayList<Sanpham> listsanpham;
    private XeDaMua xeDaMua;
    XeDaMuaDao xeDaMuaDao;
    Activity activity;
    public XeDaMuaAdapter(Context context, ArrayList<Sanpham> listsanpham, XeDaMua xeDaMua) {
        this.context = context;
        this.listsanpham = listsanpham;
        this.xeDaMua = xeDaMua;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemXedamuaBinding binding = ItemXedamuaBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new XeDaMuaAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sanpham sp = listsanpham.get(position);

        //Lấy dữ liệu ảnh lên recyclerview
        Uri imagesUri = Uri.parse(sp.getImage());
        Log.d("tag", "onBindViewHolder: " + imagesUri);
        holder.binding.imgXedamua.setImageURI(imagesUri);

        holder.binding.tvTenxedamua.setText("TênSP:" + sp.getTensp());

        HangxeDao hangxeDao = new HangxeDao(context);
        Hangxe hangxe = hangxeDao.getID(String.valueOf(sp.getMahang()));
        holder.binding.tvHangxedamua.setText("Hãng:" + String.valueOf(hangxe.getTenhang()));

        holder.binding.tvGiaxedamua.setText("Giá:" + String.valueOf(sp.getGia()));

        if(sp.getTrangthai()==1){
            holder.binding.tvTtxedamua.setTextColor(Color.BLUE);
            holder.binding.tvTtxedamua.setText("Đã Bán");
        }else{
            holder.binding.tvTtxedamua.setTextColor(Color.RED);
            holder.binding.tvTtxedamua.setText("Chưa Bán");
        }

        holder.binding.cardviewsp.setOnClickListener(view -> {
            openFragment(sp, holder.itemView.getContext());
        });
    }

    @Override
    public int getItemCount() {
        return listsanpham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemXedamuaBinding binding;
       public ViewHolder(@NonNull  ItemXedamuaBinding binding) {
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
}
