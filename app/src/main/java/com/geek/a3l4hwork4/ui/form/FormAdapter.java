package com.geek.a3l4hwork4.ui.form;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.a3l4hwork4.R;
import com.geek.a3l4hwork4.data.RetrofitClient;
import com.geek.a3l4hwork4.databinding.ListItemBinding;
import com.geek.a3l4hwork4.models.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private final List<Post> list = new ArrayList<>();
    private OnItemCLickListener onItemCLickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(ArrayList<Post> body) {
        list.addAll(body);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
     private   ListItemBinding binding;
        public ViewHolder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Post model) {
            binding.textTv.setText(model.getTitle());
            itemView.setOnClickListener(view -> {
                onItemCLickListener.clickItem(model);
            });

            itemView.setOnLongClickListener(view -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext())
                        .setMessage(R.string.dialog_questioin)
                        .setPositiveButton("yes", (dialogInterface, i) -> RetrofitClient.getApi().deleteMockerModel(model.getId()).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {

                                list.remove(getAdapterPosition());
                                notifyItemChanged(getAdapterPosition());
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        }))
                        .setNegativeButton("no",null);
                alert.create().show();
                return true;
            });

        }
    }
    public interface OnItemCLickListener{
        void clickItem(Post model);
    }

}

