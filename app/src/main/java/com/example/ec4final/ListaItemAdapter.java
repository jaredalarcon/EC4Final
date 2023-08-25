package com.example.ec4final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ec4final.models.Item;

import java.util.ArrayList;

public class ListaItemAdapter extends RecyclerView.Adapter<ListaItemAdapter.ViewHolder> {

    //listener

    private ArrayList<Item> dataset;
    private Context context;

    public ListaItemAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Item p = dataset.get(position);
         holder.nombreTextView.setText(p.getName());

         Glide.with(context)
                 .load(p.getImage())
                 .centerCrop()
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaItem(ArrayList<Item> listaItem) {
        dataset.addAll(listaItem);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView, categoryTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.imgItem);
            nombreTextView = (TextView) itemView.findViewById(R.id.tvName);
            categoryTextView = (TextView) itemView.findViewById(R.id.tvCategory);
        }
    }
}
