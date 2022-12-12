package com.example.ssu_makeup.main_fragment;

import static com.bumptech.glide.Glide.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Objects;

public class MainProductFragment extends Fragment{
    String productCategory;
    String productIndex;
    EditText review;
    RatingBar averageRatingBar;
    RatingBar submitRatingBar;

    private final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference cosmeticDataRef = mFirebaseDatabase.getReference("cosmeticData");
    private final DatabaseReference userDataRef = mFirebaseDatabase.getReference("Users");
    private final FirebaseAuth mFirebase = FirebaseAuth.getInstance();
    private final String uid = Objects.requireNonNull(mFirebase.getCurrentUser()).getUid();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_product, container, false);

        assert getArguments() != null;
        Product selectedProduct = (Product) getArguments().getSerializable("selected_product");

        productCategory = selectedProduct.getProductCategory();
        productIndex = selectedProduct.getProductIndex();
        with(requireContext()).load(selectedProduct.getProductImageURL()).into((ImageView) root.findViewById(R.id.product_item_image));
        root.findViewById(R.id.product_item_image).setClipToOutline(true);

        ((TextView) root.findViewById(R.id.product_item_brand)).setText(selectedProduct.getProductBrand());
        ((TextView) root.findViewById(R.id.product_item_name)).setText(selectedProduct.getProductName());
        ((TextView) root.findViewById(R.id.product_item_ingredient)).setText(selectedProduct.getProductIngredient());

        review = root.findViewById(R.id.review_edit_text);
        averageRatingBar = root.findViewById(R.id.review_average_rating_bar);
        submitRatingBar = root.findViewById(R.id.review_submit_rating_bar);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("cosmeticData").child(productCategory).child(productIndex).child("reviewList");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Review> reviewArrayList = new ArrayList<>();
                float average = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    float reviewScore = Objects.requireNonNull(dataSnapshot.child("reviewScore").getValue(Float.class));
                    Review review = new Review(reviewScore,
                            dataSnapshot.child("reviewer").getValue(String.class),
                            dataSnapshot.child("review").getValue(String.class));
                    reviewArrayList.add(0,review);
                    average += reviewScore;
                }
                if(reviewArrayList.size() != 0)
                    average/=reviewArrayList.size();
                averageRatingBar.setRating(average);
                ((TextView)root.findViewById(R.id.review_average_rating)).setText(getString(R.string.average_rating,average));
                RecyclerView recyclerView = root.findViewById(R.id.product_review_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ProductReviewAdaptor productReviewAdaptor = new ProductReviewAdaptor(reviewArrayList);
                recyclerView.setAdapter(productReviewAdaptor);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        root.findViewById(R.id.review_submit_button).setOnClickListener(view -> {
            if(submitRatingBar.getRating() == 0)
                Toast.makeText(requireContext(), "별점을 선택해 주세요", Toast.LENGTH_SHORT).show();
            else if(review.getText().toString().length() < 10)
                Toast.makeText(requireContext(), "리뷰는 10자 이상 작성해 주세요", Toast.LENGTH_SHORT).show();
            else{
                userDataRef.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String firstName = snapshot.getValue(String.class);
                        Review currentUserReview = new Review(submitRatingBar.getRating(), firstName, review.getText().toString());
                        cosmeticDataRef.child(productCategory).child(productIndex).child("reviewList").push().setValue(currentUserReview);
                        submitRatingBar.setRating(0);
                        review.getText().clear();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
        });

        root.findViewById(R.id.parent_view).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });

        return root;
    }
    private void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}