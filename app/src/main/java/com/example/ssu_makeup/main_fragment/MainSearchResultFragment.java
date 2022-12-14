package com.example.ssu_makeup.main_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssu_makeup.custom_class.Product;
import com.example.ssu_makeup.adaptor.LinearProductAdaptor;
import com.example.ssu_makeup.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainSearchResultFragment extends Fragment {
    String searchingItem;
    ArrayList<Product> productArrayList;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.popBackStack();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_search_result, container, false);

        assert getArguments() != null;
        searchingItem = getArguments().getString("searching_item");

        productArrayList= new ArrayList<>();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String[] kindOfProduct={"allInOne","cream","essenceSerum","lotionEmulsion","skinToner"};
                for(String product : kindOfProduct)
                {
                    for(int i=0;snapshot.child("cosmeticData").child(product).child(Integer.toString(i)).getValue()!=null;i++) {
                        DataSnapshot snapshot1 = snapshot.child("cosmeticData").child(product).child(Integer.toString(i));
                        Product p = new Product(snapshot1.child("brandName").getValue(String.class),
                                snapshot1.child("imgURL").getValue(String.class),
                                snapshot1.child("ingredientsAll").getValue(String.class),
                                snapshot1.child("productName").getValue(String.class),
                                product,
                                Integer.toString(i));
                        if (p.getProductName().contains(searchingItem)) {
                            productArrayList.add(p);
                        }
                    }
                }
                if(productArrayList.size()>0)
                    ((TextView)root.findViewById(R.id.search_result_count)).setText(getString(R.string.search_result_count, searchingItem, productArrayList.size()));
                else
                    ((TextView)root.findViewById(R.id.search_result_count)).setText(getString(R.string.search_result_none, searchingItem));

                RecyclerView recyclerView = root.findViewById(R.id.search_result_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                LinearProductAdaptor linearProductAdaptor = new LinearProductAdaptor(productArrayList, requireContext());
                linearProductAdaptor.setOnItemClickListener(product -> {
                    MainProductFragment mainItemFragment = new MainProductFragment();
                    Bundle bundle = new Bundle(1);
                    bundle.putSerializable("selected_product", product);
                    mainItemFragment.setArguments(bundle);
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.search_result_container, mainItemFragment)
                            .addToBackStack(null)
                            .commit();
                });
                recyclerView.setAdapter(linearProductAdaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        return  root;
    }
}