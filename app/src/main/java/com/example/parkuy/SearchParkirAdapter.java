package com.example.parkuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkuy.Model.Parkir;

import java.util.List;

public class SearchParkirAdapter extends RecyclerView.Adapter<SearchParkirAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<Parkir> Parkir;

    public SearchParkirAdapter(Context context, List<Parkir> Parkir){
        this.inflater = LayoutInflater.from(context);
        this.Parkir = Parkir;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_parkir, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namaTempatParkir.setText(Parkir.get(position).getNamaTempatParkir());
        holder.alamatTempatParkir.setText(Parkir.get(position).getAlamatTempatParkir());
        holder.hargaTempatParkir.setText(Parkir.get(position).getHargaTempatParkir());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaTempatParkir, alamatTempatParkir, hargaTempatParkir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaTempatParkir = itemView.findViewById(R.id.namatempatparkir);
            alamatTempatParkir = itemView.findViewById(R.id.alamatparkir);
            hargaTempatParkir = itemView.findViewById(R.id.hargaParkir);

        }
    }
}