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

public class MainSearchResultFragment extends Fragment {
    String searchingItem;
    ArrayList<Product> productArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_search_result, container, false);

        //TODO: Firebase DB 에서 searchingItem 에 대한 결과 받아서 productArrayList 에 넣기
        assert getArguments() != null;
        searchingItem = getArguments().getString("searchingItem");

        RecyclerView recyclerView = root.findViewById(R.id.search_result_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ProductAdaptor(productArrayList, requireContext()));

        return  root;
    }
}