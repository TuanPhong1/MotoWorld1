package nhom7.fpoly.motoworld.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import nhom7.fpoly.motoworld.Adapter.HangxeAdapter;
import nhom7.fpoly.motoworld.Dao.HangxeDao;
import nhom7.fpoly.motoworld.Model.Hangxe;
import nhom7.fpoly.motoworld.R;


public class HangxeFragment extends Fragment {

ListView listView;
FloatingActionButton fab;
Dialog dialog;
EditText mahang,tenhang;
Button btnsave,btncancel;
ArrayList<Hangxe> list;
static HangxeDao dao;
HangxeAdapter adapter;
Hangxe item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hangxe, container, false);
        listView = view.findViewById(R.id.listview_hangxe);
        fab =view.findViewById(R.id.fabHangXe);
        dao = new HangxeDao(getActivity());


        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);

                return false;
            }
        });
        return view;
    }

    void capNhatLv(){
        list = (ArrayList<Hangxe>) dao.getAll();
        adapter = new HangxeAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }
    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("bạn có chắc muốn xóa không ");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dao.delete(id)>0){
                    Toast.makeText(getActivity(),"Xóa hãng xe thành công ",Toast.LENGTH_SHORT).show();
                    capNhatLv();
                }else{
                    Toast.makeText(getActivity(),"Xóa hãng xe thất bại ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog =builder.create();
        builder.show();
    }

}