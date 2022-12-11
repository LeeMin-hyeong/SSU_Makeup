package com.example.ssu_makeup.main_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssu_makeup.custom_class.Baumann;
import com.example.ssu_makeup.adaptor.GridProductAdaptor;
import com.example.ssu_makeup.custom_class.Product;
import com.example.ssu_makeup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class MainHomeFragment extends Fragment {
    Vector<Product> productRecommendVector;
    TextView recommendationMessage;
    public static String userSkinType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_home, container, false);

        FirebaseAuth mfirebase = FirebaseAuth.getInstance();
        String uid = mfirebase.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstName = snapshot.child("firstName").getValue(String.class);
                String lastName = snapshot.child("lastName").getValue(String.class);
                String skinType = snapshot.child("skinType").getValue(String.class);
                userSkinType = skinType;
                recommendationMessage = root.findViewById(R.id.recommendation_message);
                recommendationMessage.setText(getString(R.string.recommendation_message, skinType, lastName, firstName));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        productRecommendVector= new Vector<>();
        DatabaseReference databaseReference2 = firebaseDatabase.getReference();
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String[] kindOfProduct={"allInOne","cream","essenceSerum","lotionEmulsion","skinToner"};
                DataSnapshot snapshot2 = snapshot.child("Users").child(uid).child("skinType");
                String userSkinType = snapshot2.getValue(String.class);
                for(String product : kindOfProduct)
                {
                    int productCount=0;
                    for(int i=0;productCount<3;i++) {
                        DataSnapshot snapshot1 = snapshot.child("cosmeticData").child(product).child(Integer.toString(i));
                        Product p = new Product(snapshot1.child("brandName").getValue(String.class),
                                snapshot1.child("imgURL").getValue(String.class),
                                snapshot1.child("ingredientsAll").getValue(String.class),
                                snapshot1.child("productName").getValue(String.class),
                                product,
                                Integer.toString(i));
                        if (Baumann.checkIngredientsBySkinType(userSkinType, p.getProductIngredient())) {
                            productRecommendVector.add(p);
                            productCount++;
                        }
                    }
                }
                RecyclerView recyclerView = root.findViewById(R.id.main_product_recommend_recycler_view);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                GridProductAdaptor girdProductAdaptor = new GridProductAdaptor(productRecommendVector, requireContext());

                girdProductAdaptor.setOnItemClickListener(product -> {
                    MainProductFragment mainItemFragment = new MainProductFragment();
                    Bundle bundle = new Bundle(1);
                    bundle.putSerializable("selected_product", product);
                    mainItemFragment.setArguments(bundle);
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.home_recommend_item_container, mainItemFragment)
                            .addToBackStack(null)
                            .commit();
                });
                recyclerView.setAdapter(girdProductAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}