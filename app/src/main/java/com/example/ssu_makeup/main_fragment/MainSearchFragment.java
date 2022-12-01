package com.example.ssu_makeup.main_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssu_makeup.R;

public class MainSearchFragment extends Fragment {
    //TODO: MainSearchFragment가 화면에 보일 때 키보드 자동 올라오기
    //TODO: 검색결과 NestedFragment가 올라와 있을 때는 EditText 선택 시에만 키보드 올라오기
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_search, container, false);

        return root;
    }
}