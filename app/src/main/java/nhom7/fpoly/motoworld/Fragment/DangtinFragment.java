package nhom7.fpoly.motoworld.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.HangXeSpinnerAdapter;
import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.Model.Sanpham;
import nhom7.fpoly.motoworld.databinding.FragmentDangtinBinding;

public class DangtinFragment extends Fragment {

    private FragmentDangtinBinding binding;
    private View view;
    SanphamDao dao;
    HangxeDao hangxeDao;
    ArrayList<Hangxe> list;
    HangXeSpinnerAdapter hangXeSpinnerAdapter;
    int mahang;
    Sanpham item;
    Uri selectUri;
    int REQUEST_CODE = 2;
    public int PICK_IMAGE_REQUEST = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDangtinBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        dao = new SanphamDao(getActivity());

        hangxeDao = new HangxeDao(getContext());
        list = new ArrayList<>();
        list = (ArrayList<Hangxe>) hangxeDao.getAll();
        hangXeSpinnerAdapter =new HangXeSpinnerAdapter(getContext(),list);
        binding.spnhangxe.setAdapter(hangXeSpinnerAdapter);

        binding.spnhangxe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mahang  =list.get(i).getMahang();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnDangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = binding.edtTensp.getText().toString();
                Integer giasp = Integer.parseInt(binding.edtGiasp.getText().toString());
                String loaixe = binding.edtLoaixe.getText().toString();
                String mausac = binding.edtMausac.getText().toString();
                String dongco = binding.edtDongco.getText().toString();
                Integer namsx = Integer.parseInt(binding.edtNamsx.getText().toString());

                if(tensp.isEmpty()|| loaixe.isEmpty()||mausac.isEmpty()||dongco.isEmpty()){
                    Toast.makeText(getActivity(), "Vui lòng nhập đúng trường dữ liệu!!!", Toast.LENGTH_SHORT).show();
                } else if (giasp>0) {
                    Toast.makeText(getActivity(), "Giá sản phẩm phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                } else if (namsx < 1900 || namsx > 2040) {
                    Toast.makeText(getActivity(), "Năm sản xuất không đúng", Toast.LENGTH_SHORT).show();
                }

                item = new Sanpham();
                item.setMahang(mahang);
                item.setTensp(tensp);
                item.setGia(giasp);
                item.setLoaixe(loaixe);
                item.setMauxe(mausac);
                item.setDongco(dongco);
                item.setNamsx(namsx);
                String imgPath =getPathFromUri(selectUri);
                item.setImage(imgPath);

                long insert =dao.insert(item);
                if(insert >0){
                    Toast.makeText(getActivity(), "Thêm sản phẩn thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imgbtnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Yêu cầu quyền truy cập vào bộ nhớ ngoài
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE);
                } else {
                    openImagePicker();
                }
            }
        });

        return view;
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
    /** @noinspection deprecation*/
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    /** @noinspection deprecation*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            // Kiểm tra quyền ở đây
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, mở picker ảnh
                openImagePicker();
            } else {
                // Quyền không được cấp, thông báo cho người dùng
                Toast.makeText(getContext(), "Bạn cần cấp quyền để chọn ảnh", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /** @noinspection deprecation*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Nhận Uri của ảnh đã chọn
            selectUri = data.getData();
            Log.d("Image", "Image URI: " + selectUri.toString());
            // Tiến hành xử lý ảnh, ví dụ: hiển thị ảnh lên ImageView
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(selectUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (bitmap != null) {
                    binding.imgbtnAnh.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}