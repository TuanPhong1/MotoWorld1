package nhom7.fpoly.motoworld.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.HangxeAdapter;
import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.R;
import nhom7.fpoly.motoworld.databinding.FragmentHangxeBinding;


public class HangxeFragment extends Fragment {

    Dialog dialog;
    ArrayList<Hangxe> list;
    static HangxeDao dao;
    HangxeAdapter adapter;
    Hangxe item;
    private FragmentHangxeBinding binding;
    private View view;
    EditText mahang, tenhang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHangxeBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        dao = new HangxeDao(getActivity());

        capNhatLv();
        binding.fabHangXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogHangXe(getActivity(), 0);
            }
        });
        binding.listviewHangxe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                opendialogHangXe(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    void capNhatLv() {
        list = (ArrayList<Hangxe>) dao.getAll();
        adapter = new HangxeAdapter(getActivity(), list, dao);
        binding.listviewHangxe.setAdapter(adapter);
    }

    public void xoa(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dao.delete(id) > 0) {
                    Toast.makeText(getActivity(), "Xóa hãng xe thành công", Toast.LENGTH_SHORT).show();
                    capNhatLv();
                } else {
                    Toast.makeText(getActivity(), "Xóa hãng xe thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    protected void opendialogHangXe(final Context context, final int type) {

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hangxe);
        mahang = dialog.findViewById(R.id.edtmahang);
        tenhang = dialog.findViewById(R.id.edttenhangxe);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);

        // Thêm dòng này để khởi tạo đối tượng item
        if (type != 0) {
            mahang.setEnabled(false);
        }

        if (type != 0) {
            mahang.setText(String.valueOf(item.getMahang()));
            tenhang.setText(item.getTenhang());
        }
        dialog.findViewById(R.id.btnsavehangxe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tenhang.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Không được để trống hãng xe", Toast.LENGTH_SHORT).show();
                } else {
                    item = new Hangxe();
                    item.setTenhang(tenhang.getText().toString());
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            capNhatLv();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMahang(Integer.parseInt(mahang.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công",Toast.LENGTH_SHORT).show();
                            capNhatLv();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });
        dialog.findViewById(R.id.btncancelhangxe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}