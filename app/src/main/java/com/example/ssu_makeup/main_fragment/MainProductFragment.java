package com.example.ssu_makeup.main_fragment;

import static com.bumptech.glide.Glide.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ssu_makeup.Product;
import com.example.ssu_makeup.R;


public class MainProductFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_product, container, false);

        assert getArguments() != null;
        Product selectedProduct = (Product)getArguments().getSerializable("selected_product");
        with(requireContext()).load(selectedProduct.getProductImageURL()).into((ImageView)root.findViewById(R.id.product_item_image));
        root.findViewById(R.id.product_item_image).setClipToOutline(true);
        ((TextView)root.findViewById(R.id.product_item_brand)).setText(selectedProduct.getProductBrand());
        ((TextView)root.findViewById(R.id.product_item_name)).setText(selectedProduct.getProductName());
        ((TextView)root.findViewById(R.id.product_item_ingredient)).setText(selectedProduct.getProductIngredient());
        return root;
    }

}