package nhom7.fpoly.motoworld.Fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.HangXeSpinnerAdapter;
import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.FragmentCapNhatSanPhamBinding;


public class CapNhatSanPhamFragment extends Fragment {
    Sanpham item;
    ArrayList<Sanpham> list;
    ArrayList<Hangxe> listHang;
    HangXeSpinnerAdapter hangSpinerAdapter;
    HangxeDao hangxeDao;
    SanphamDao dao;
    Uri selectUri;

    private FragmentCapNhatSanPhamBinding binding;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (Sanpham) getArguments().getSerializable("Update");
            Log.d("UpdateFragment", "SanPham: " + item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCapNhatSanPhamBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        list = new ArrayList<>();
        dao = new SanphamDao(getContext());

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.toolbarUpdate);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Update Product");

        requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.GONE);

        selectUri = Uri.parse(item.getImage());
        binding.imgAnhUd.setImageURI(selectUri);

        binding.edtTenspUd.setText(item.getTensp());

        hangxeDao = new HangxeDao(getContext());
        listHang = (ArrayList<Hangxe>) hangxeDao.getAll();
        hangSpinerAdapter = new HangXeSpinnerAdapter(getContext(), listHang);
        binding.spnhangxeUd.setAdapter(hangSpinerAdapter);
        binding.spnhangxeUd.setSelection(getHangPosition(item.getMahang()));

        binding.edtGiaspUd.setText(String.valueOf(item.getGia()));

        binding.edtLoaixeUd.setText(item.getLoaixe());

        binding.edtMausacUd.setText(item.getMauxe());

        binding.edtDongcoUd.setText(item.getDongco());

        binding.edtNamsxUd.setText(String.valueOf(item.getNamsx()));

        binding.btnUpdateStory.setOnClickListener(view -> {
            String tensp = binding.edtTenspUd.getText().toString();
            String giaspStr = binding.edtGiaspUd.getText().toString();
            Integer giasp = Integer.parseInt(giaspStr);
            String loaixe = binding.edtLoaixeUd.getText().toString();
            String mausac = binding.edtMausacUd.getText().toString();
            String dongco = binding.edtDongcoUd.getText().toString();
            String namsxStr = binding.edtNamsxUd.getText().toString();
            Integer namsx = Integer.parseInt(namsxStr);
            Hangxe hangxe = (Hangxe) binding.spnhangxeUd.getSelectedItem();

            if (tensp.isEmpty() || loaixe.isEmpty() || mausac.isEmpty() || dongco.isEmpty()) {
                Toast.makeText(getActivity(), "Vui lòng nhập đúng trường dữ liệu!!!", Toast.LENGTH_SHORT).show();
            } else if (giasp < 0) {
                Toast.makeText(getActivity(), "Giá sản phẩm phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            } else if (namsx < 1900 || namsx > 2040) {
                Toast.makeText(getActivity(), "Năm sản xuất không đúng", Toast.LENGTH_SHORT).show();
            }

            item.setMahang(hangxe.getMahang());
            item.setTensp(tensp);
            item.setGia(giasp);
            item.setLoaixe(loaixe);
            item.setMauxe(mausac);
            item.setDongco(dongco);
            item.setNamsx(namsx);

            String imagePath = getPathFromUri(selectUri);
            Log.d("TAG", "onClick: " + imagePath);
            item.setImage(imagePath);


            if (dao.update(item) >0) {
                list.clear();
                list.addAll(dao.getAll());
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frmbottom, new MuaHangFragment())
                        .commit();
                Toast.makeText(getActivity(), "Thêm sản phẩn thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private int getHangPosition(int mahang) {
        for (int i = 0; i < listHang.size(); i++) {
            if (listHang.get(i).getMahang() == mahang) {
                return i;
            }
        }
        return 0;
    }

    private String getPathFromUri(Uri contentUri) {
        String filePath;
        Cursor cursor = getContext().getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbarUpdate.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}