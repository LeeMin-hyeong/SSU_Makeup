package com.example.ssu_makeup.main_fragment;

import static com.bumptech.glide.Glide.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssu_makeup.activity.LoginActivity;
import com.example.ssu_makeup.activity.SurveyActivity;
import com.example.ssu_makeup.adaptor.ProductReviewAdaptor;
import com.example.ssu_makeup.custom_class.Product;
import com.example.ssu_makeup.R;
import com.example.ssu_makeup.custom_class.Review;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainProductFragment extends Fragment implements View.OnClickListener {
    ImageView starButton1;
    ImageView starButton2;
    ImageView starButton3;
    ImageView starButton4;
    ImageView starButton5;
    Button submitReviewButton;
    EditText review;
    int reviewScore=0;

    Product selectedProduct;
    String productCategory, productIndex;

    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference cosmeticDataRef = mFirebaseDatabase.getReference("cosmeticData");
    private DatabaseReference userDataRef = mFirebaseDatabase.getReference("Users");
    private FirebaseAuth mfirebase = FirebaseAuth.getInstance();
    private String uid = mfirebase.getCurrentUser().getUid();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_product, container, false);

        assert getArguments() != null;
        selectedProduct = (Product)getArguments().getSerializable("selected_product");
        with(requireContext()).load(selectedProduct.getProductImageURL()).into((ImageView)root.findViewById(R.id.product_item_image));
        root.findViewById(R.id.product_item_image).setClipToOutline(true);
        ((TextView)root.findViewById(R.id.product_item_brand)).setText(selectedProduct.getProductBrand());
        ((TextView)root.findViewById(R.id.product_item_name)).setText(selectedProduct.getProductName());
        ((TextView)root.findViewById(R.id.product_item_ingredient)).setText(selectedProduct.getProductIngredient());

        productCategory = selectedProduct.getProductCategory();
        productIndex = selectedProduct.getProductIndex();

        starButton1 = root.findViewById(R.id.review_star_button_1);
        starButton2 = root.findViewById(R.id.review_star_button_2);
        starButton3 = root.findViewById(R.id.review_star_button_3);
        starButton4 = root.findViewById(R.id.review_star_button_4);
        starButton5 = root.findViewById(R.id.review_star_button_5);
        review = root.findViewById(R.id.review_edit_text);
        submitReviewButton = root.findViewById(R.id.review_submit_button);
        starButton1.setOnClickListener(this);
        starButton2.setOnClickListener(this);
        starButton3.setOnClickListener(this);
        starButton4.setOnClickListener(this);
        starButton5.setOnClickListener(this);
        submitReviewButton.setOnClickListener(this);

        ArrayList<Review> reviewArrayList = new ArrayList<>();
        //TODO: 리뷰 불러오기
        reviewArrayList.add(new Review(4,"김숭실","추천해요!"));

        RecyclerView recyclerView = root.findViewById(R.id.product_review_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ProductReviewAdaptor productReviewAdaptor = new ProductReviewAdaptor(reviewArrayList);
        recyclerView.setAdapter(productReviewAdaptor);

        return root;
    }

    @Override
    public void onClick(View view) {
        setStarButton();

        if(view == starButton1){
            starButton1.setImageResource(R.drawable.ic_review_star_red);
            reviewScore=1;
        }
        else if(view == starButton2){
            starButton1.setImageResource(R.drawable.ic_review_star_red);
            starButton2.setImageResource(R.drawable.ic_review_star_red);
            reviewScore=2;
        }
        else if(view == starButton3){
            starButton1.setImageResource(R.drawable.ic_review_star_red);
            starButton2.setImageResource(R.drawable.ic_review_star_red);
            starButton3.setImageResource(R.drawable.ic_review_star_red);
            reviewScore=3;
        }
        else if(view == starButton4) {
            starButton1.setImageResource(R.drawable.ic_review_star_red);
            starButton2.setImageResource(R.drawable.ic_review_star_red);
            starButton3.setImageResource(R.drawable.ic_review_star_red);
            starButton4.setImageResource(R.drawable.ic_review_star_red);
            reviewScore=4;
        }
        else if(view == starButton5) {
            starButton1.setImageResource(R.drawable.ic_review_star_red);
            starButton2.setImageResource(R.drawable.ic_review_star_red);
            starButton3.setImageResource(R.drawable.ic_review_star_red);
            starButton4.setImageResource(R.drawable.ic_review_star_red);
            starButton5.setImageResource(R.drawable.ic_review_star_red);
            reviewScore=5;
        }

        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reviewScore==0)
                    Toast.makeText(requireContext(), "별점을 선택해 주세요", Toast.LENGTH_SHORT).show();
                else{
                    Log.d("ProductCategory", productCategory);
                    Log.d("ProductIndex", productIndex);

                    userDataRef.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String firstName = snapshot.getValue(String.class);
                            Review currentUserReview = new Review(reviewScore, firstName, review.getText().toString());
                            cosmeticDataRef.child(productCategory).child(productIndex).child("reviewList").push().setValue(currentUserReview);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });

                }
            }
        });
    }

    void setStarButton(){
        starButton1.setImageResource(R.drawable.ic_review_star_grey);
        starButton2.setImageResource(R.drawable.ic_review_star_grey);
        starButton3.setImageResource(R.drawable.ic_review_star_grey);
        starButton4.setImageResource(R.drawable.ic_review_star_grey);
        starButton5.setImageResource(R.drawable.ic_review_star_grey);
    }

}