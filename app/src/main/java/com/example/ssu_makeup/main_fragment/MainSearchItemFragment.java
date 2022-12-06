package com.example.ssu_makeup.main_fragment;

import static com.bumptech.glide.Glide.*;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ssu_makeup.R;


public class MainSearchItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_search_item, container, false);

        assert getArguments() != null;
        with(requireContext()).load(getArguments().getString("product image url")).into((ImageView)root.findViewById(R.id.product_item_image));
        ((ImageView)root.findViewById(R.id.product_item_image)).setClipToOutline(true);
        ((TextView)root.findViewById(R.id.product_item_brand)).setText(getArguments().getString("product brand"));
        ((TextView)root.findViewById(R.id.product_item_name)).setText(getArguments().getString("product name"));
        ((TextView)root.findViewById(R.id.product_item_ingredient)).setText(getArguments().getString("product ingredient"));
        //TODO: 자식 fragment에 대한 뒤로가기 구현. 현재 MainActivity의 뒤로가기만 먹혀서 상품 상세 정보를 봤다가 나올 수 없음
        return root;
    }

}