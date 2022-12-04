package com.example.ssu_makeup.main_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssu_makeup.Product;
import com.example.ssu_makeup.ProductAdaptor;
import com.example.ssu_makeup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainHomeFragment extends Fragment {
    ArrayList<Product> productRecommendArrayList;
    TextView recommendationMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_home, container, false);

        FirebaseAuth mfirebase = FirebaseAuth.getInstance();
        String uid = mfirebase.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");


        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(uid).child("lastName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        databaseReference.child(uid).child("skinType").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot3) {
                                String firstName = snapshot.getValue(String.class);
                                String lastName = snapshot2.getValue(String.class);
                                String skinType = snapshot3.getValue(String.class);
                                recommendationMessage = root.findViewById(R.id.recommendation_message);
                                recommendationMessage.setText(getString(R.string.recommendation_message, skinType, firstName, lastName));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

//        RecyclerView recyclerView = root.findViewById(R.id.main_product_recommend_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new ProductAdaptor(productRecommendArrayList, requireContext()));

        return root;
    }
}