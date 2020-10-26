package com.e.resepjajanankekinian.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.e.resepjajanankekinian.R;
import com.e.resepjajanankekinian.model.ResepData;
import com.e.resepjajanankekinian.resep;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dwiki Sulthon Saputra Marbi on 14/10/2020.
 */
public class SearchResepBahanAdapter extends RecyclerView.Adapter<SearchResepBahanAdapter.CustomViewHolder> {
    private final List<ResepData> dataList;
    private final Context context;

    public SearchResepBahanAdapter(Context context, List<ResepData> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        private final TextView textViewName;
        private final TextView textViewDilihat;
        private final TextView textViewFavorit;
        private final ImageView imageView;
        private final CardView cardView;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            textViewName = mView.findViewById(R.id.textViewNamaResepSearchResep);
            textViewDilihat = mView.findViewById(R.id.dilihatSearchResep);
            textViewFavorit = mView.findViewById(R.id.favoritSearchResep);
            imageView = mView.findViewById(R.id.imageViewSearchResep);
            cardView = mView.findViewById(R.id.cardViewSearchResep);
        }
    }
    @NonNull
    @Override
    public SearchResepBahanAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row_resep, parent, false);
        return new SearchResepBahanAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResepBahanAdapter.CustomViewHolder holder, int position) {
        ResepData ResepData = dataList.get(position);
        holder.textViewName.setText(ResepData.getNama());
        holder.textViewDilihat.setText(String.valueOf(ResepData.getDilihat()));
        holder.textViewFavorit.setText(String.valueOf(ResepData.getFavorit()));
        final Integer idx = ResepData.getId();
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(ResepData.getGambar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, resep.class);
                intent.putExtra("id", idx);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
