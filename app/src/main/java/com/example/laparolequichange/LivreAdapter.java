package com.example.laparolequichange;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

public class LivreAdapter extends RecyclerView.Adapter<LivreAdapter.ViewHolder> {

    public static Context context;
    List<Livres> livres;
    public LivreAdapter(Context context, List<Livres> livres){
        this.context = context;
        this.livres = livres;
    }

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LivreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_livres, parent, false);
        LivreAdapter.ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull LivreAdapter.ViewHolder holder, int position) {
        // Get the data at position
        Livres livre = livres.get(position);
        // Bind the tweet with view holder
        holder.bind(livre,holder);
    }

    @Override
    public int getItemCount() {
        return livres.size();
    }

    // Define a viewholder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLivre;
        TextView tvNomLivre;
        TextView tvDescription;
        ImageView ivAvailable;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener clickListener) {
            super(itemView);
            ivLivre = itemView.findViewById(R.id.ivImageLivre);
            tvNomLivre = itemView.findViewById(R.id.tvNomLivre);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivAvailable = itemView.findViewById(R.id.ivAvailable);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(itemView, getAdapterPosition());
                }
            });
        }


        public void bind(Livres livre, ViewHolder holder) {
            tvNomLivre.setText(livre.getBook_name());
            ivLivre.setImageResource(livre.getImageResourceId());
            tvDescription.setText(livre.getDesc_livre());
            if (livre.getAvailable().equals("vrai") ){
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.circle_medium);
                ivAvailable.setImageDrawable(drawable);
            }else {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.circle_medium_red);
                ivAvailable.setImageDrawable(drawable);
            }
        }
    }
}
