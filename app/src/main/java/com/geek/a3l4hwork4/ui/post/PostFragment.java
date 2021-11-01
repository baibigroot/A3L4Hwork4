package com.geek.a3l4hwork4.ui.post;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.a3l4hwork4.R;
import com.geek.a3l4hwork4.data.RetrofitClient;
import com.geek.a3l4hwork4.databinding.FragmentPostBinding;
import com.geek.a3l4hwork4.models.Post;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    private FragmentPostBinding binding;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            Post model;
            model = (Post) getArguments().getSerializable("post_fragment_key");
            Log.d("tag", model.getTitle());

            binding.etTitle.setText(model.getTitle());
            binding.etContent.setText(model.getContent());
            binding.etGroup.setText(String.valueOf(model.getGroup()));
            binding.etUser.setText(String.valueOf(model.getUser()));
            id = model.getId();
         ;
        }

        binding.btnUpgrade.setOnClickListener(view1 -> {
            RetrofitClient.getApi().update(id + "", new Post(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    openFormFragment();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });

        binding.btnCreate.setOnClickListener(view1 -> {
            RetrofitClient.getApi().createMocker(new Post(binding.etTitle.getText().toString().trim(),
                    binding.etContent.getText().toString().trim(),
                    Integer.valueOf(binding.etGroup.getText().toString().trim()),
                    Integer.valueOf(binding.etUser.getText().toString().trim()))).enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    openFormFragment();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        });

    }

    private void openFormFragment() {

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }

}