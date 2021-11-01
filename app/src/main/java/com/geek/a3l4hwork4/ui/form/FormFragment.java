package com.geek.a3l4hwork4.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geek.a3l4hwork4.R;
import com.geek.a3l4hwork4.data.RetrofitClient;
import com.geek.a3l4hwork4.databinding.FragmentFormBinding;
import com.geek.a3l4hwork4.models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {
    private FragmentFormBinding ui;
    private FormAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ui = FragmentFormBinding.inflate(inflater, container, false);
        return ui.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FormAdapter();

        RetrofitClient.getApi().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response != null) {
                    adapter.addItems((ArrayList<Post>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }


        });
        ui.rvForm.setAdapter(adapter);
        ui.fabForm.setOnClickListener(view1 -> {
            openPostFragment();
        });

        adapter.setOnItemCLickListener(model -> {
            Bundle b = new Bundle();
            b.putSerializable("post_fragment_key", model);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.postFragment, b);
        });

    }

    private void openPostFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.postFragment);

    }
}