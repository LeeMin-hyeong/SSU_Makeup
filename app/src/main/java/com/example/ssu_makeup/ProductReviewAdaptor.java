package com.example.ssu_makeup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductReviewAdaptor extends RecyclerView.Adapter<ProductReviewAdaptor.ViewHolder> {
    ArrayList<Review> reviewArrayList;
    public ProductReviewAdaptor(ArrayList<Review> reviewArrayList){
        this.reviewArrayList=reviewArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_recycler_linear_view_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reviewer.setText(reviewArrayList.get(position).getReviewer());
        holder.review.setText(reviewArrayList.get(position).getReview());
        switch(reviewArrayList.get(position).getReviewScore()){
            case 5:
                holder.star5.setImageResource(R.drawable.ic_review_star_red);
            case 4:
                holder.star4.setImageResource(R.drawable.ic_review_star_red);
            case 3:
                holder.star3.setImageResource(R.drawable.ic_review_star_red);
            case 2:
                holder.star2.setImageResource(R.drawable.ic_review_star_red);
            case 1:
                holder.star1.setImageResource(R.drawable.ic_review_star_red);
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView star1, star2, star3, star4, star5;
        TextView reviewer, review;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            star1 = itemView.findViewById(R.id.review_star_1);
            star2 = itemView.findViewById(R.id.review_star_2);
            star3 = itemView.findViewById(R.id.review_star_3);
            star4 = itemView.findViewById(R.id.review_star_4);
            star5 = itemView.findViewById(R.id.review_star_5);
            reviewer = itemView.findViewById(R.id.review_reviewer_name);
            review = itemView.findViewById(R.id.review_text);
        }
    }
}
