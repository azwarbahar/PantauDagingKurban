package com.example.pantaudagingkurban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantaudagingkurban.R;
import com.example.pantaudagingkurban.model.TempatModel;

import java.util.List;

public class TempatAdapter extends RecyclerView.Adapter<TempatAdapter.MyHolderView> {

    int id;

    private Context mContext;
    private List<TempatModel>tempatModels;

    public TempatAdapter(Context mContext, List<TempatModel> tempatModels) {
        this.mContext = mContext;
        this.tempatModels = tempatModels;
    }

    @NonNull
    @Override
    public TempatAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat_list,parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempatAdapter.MyHolderView holder, int position) {

        holder.tv_nama_tempat.setText(tempatModels.get(position).getTempat());
        holder.tv_tanggal_tempat.setText(tempatModels.get(position).getTanggal());
        id = tempatModels.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return tempatModels.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {

        TextView tv_nama_tempat, tv_tanggal_tempat;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            tv_nama_tempat = itemView.findViewById(R.id.tv_nama_tempat);
            tv_tanggal_tempat = itemView.findViewById(R.id.tv_tanggal_tempat);

        }
    }
}
