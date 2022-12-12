package com.example.ssu_makeup.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.custom_class.Review;

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
        holder.ratingBar.setRating(reviewArrayList.get(position).getReviewScore());
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView reviewer, review;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.review_rating_bar);
            reviewer = itemView.findViewById(R.id.review_reviewer_name);
            review = itemView.findViewById(R.id.review_text);
        }
    }
}
