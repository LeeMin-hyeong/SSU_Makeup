package com.example.ssu_makeup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Vector;

public class GridProductAdaptor extends RecyclerView.Adapter<GridProductAdaptor.ViewHolder> {
    Vector<Product> productVector;
    Context context;

    public interface OnItemClickListener{
        void onItemClicked(Product product);
    }
    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    public GridProductAdaptor(Vector<Product> productVector, Context context){
        this.productVector = productVector;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler_grid_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(root);
        root.setOnClickListener(view -> {
            if(viewHolder.getAdapterPosition()!=RecyclerView.NO_POSITION)
                itemClickListener.onItemClicked(productVector.get(viewHolder.getAdapterPosition()));
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productBrand.setText(productVector.get(position).getProductBrand());
        holder.productName.setText(productVector.get(position).getProductName());
        Glide.with(context).load(productVector.get(position).getProductImageURL()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productVector.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productBrand;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productBrand = itemView.findViewById(R.id.product_brand);
            productImage.setClipToOutline(true);
        }
    }
}
