package nhom7.fpoly.motoworld.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.myToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle("Sản Phẩm Yêu Thích");


        sanphamDao = new SanphamDao(getContext());


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requireActivity().findViewById(R.id.bottom_nav).setVisibility(View.VISIBLE);
                getFragmentManager().popBackStack();
            }
        });
    }
}