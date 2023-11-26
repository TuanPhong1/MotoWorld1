package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import nhom7.fpoly.motoworld.Dao.SanphamDao;
import nhom7.fpoly.motoworld.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

private FragmentFavoriteBinding binding;
private View view;
private boolean isFavorite = false;
SanphamDao sanphamDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        view = binding.getRoot();

         sanphamDao = new SanphamDao(getContext());


        return view;
    }


}