package nhom7.fpoly.motoworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Fragment.QuanLiNguoiDungFragment;
import nhom7.fpoly.motoworld.Model.TkNguoiDung;
import nhom7.fpoly.motoworld.ThongTinUserDialog;
import nhom7.fpoly.motoworld.databinding.ItemNguoidungBinding;

public class TaiKhoanAdapter extends ArrayAdapter<TkNguoiDung> {
private ItemNguoidungBinding binding;
    private Context context;
    QuanLiNguoiDungFragment fragment;
    private ArrayList<TkNguoiDung> list;

    public TaiKhoanAdapter(@NonNull Context context, QuanLiNguoiDungFragment fragment, ArrayList<TkNguoiDung> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            binding = ItemNguoidungBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            v = binding.getRoot();
           return v;
        }
        final TkNguoiDung item = list.get(position);
        if (item != null) {
            binding.txtMaTaiKhoan.setText( ""+item.getMatk());
            binding.txtTenTaiKhoan.setText( ""+item.getTaikhoan());
            binding.txtMatKhau.setText(""+item.getMatkhau());

        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo đối tượng UserInfoDialog và truyền giá trị matknd
                ThongTinUserDialog dialog = ThongTinUserDialog.newInstance(item.getMatk());
                // Hiển thị hộp thoại
                dialog.show(fragment.getChildFragmentManager(), "userInfoDialog");
            }
        });
        binding.btnDeleteTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMatk()));
            }
        });
        return v;
    }
}
