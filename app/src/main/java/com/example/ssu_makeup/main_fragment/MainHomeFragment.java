package com.example.ssu_makeup.main_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssu_makeup.Product;
import com.example.ssu_makeup.ProductAdaptor;
import com.example.ssu_makeup.R;

import java.util.ArrayList;

public class MainHomeFragment extends Fragment {
    ArrayList<Product> productRecommendArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_home, container, false);

//        RecyclerView recyclerView = root.findViewById(R.id.main_product_recommend_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new ProductAdaptor(productRecommendArrayList, requireContext()));

        return root;
    }
}