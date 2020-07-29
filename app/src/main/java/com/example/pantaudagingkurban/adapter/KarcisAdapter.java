package com.example.pantaudagingkurban.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pantaudagingkurban.R;
import com.example.pantaudagingkurban.model.KarcisModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class KarcisAdapter extends RecyclerView.Adapter<KarcisAdapter.MyHolderView> {

    private Context mContext;
    private List<KarcisModel>karcisModels;

    public KarcisAdapter(Context mContext, List<KarcisModel> karcisModels) {
        this.mContext = mContext;
        this.karcisModels = karcisModels;
    }

    @NonNull
    @Override
    public KarcisAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_karcis, parent, false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KarcisAdapter.MyHolderView holder, int position) {

        holder.nama.setText(karcisModels.get(position).getNama_pj());
        holder.tempat.setText(karcisModels.get(position).getTempat());
        holder.tanggal.setText(karcisModels.get(position).getTanggal());
        holder.telpon.setText(karcisModels.get(position).getNomor_hp());
        holder.alamat.setText(karcisModels.get(position).getAlamat());
        holder.jam.setText(karcisModels.get(position).getJam()+" - Selesai");
        holder.kode.setText(karcisModels.get(position).getKode_karcis());

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {

            BitMatrix bitMatrix = multiFormatWriter.encode(karcisModels.get(position).getKode_karcis(),
                    BarcodeFormat.QR_CODE,65,65);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            holder.img_kode.setImageBitmap(bitmap);


        } catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return karcisModels.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {

        private TextView nama;
        private TextView tempat;
        private TextView telpon;
        private TextView tanggal;
        private TextView alamat;
        private TextView jam;
        private TextView kode;

        private ImageView img_kode;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tv_namapj);
            tempat = itemView.findViewById(R.id.tv_tempat);
            telpon = itemView.findViewById(R.id.tv_telpon);
            tanggal = itemView.findViewById(R.id.tv_tgl);
            alamat = itemView.findViewById(R.id.tv_alamat);
            jam = itemView.findViewById(R.id.tv_jam);
            kode = itemView.findViewById(R.id.tv_kode);

            img_kode = itemView.findViewById(R.id.img_kode);

        }
    }
}
