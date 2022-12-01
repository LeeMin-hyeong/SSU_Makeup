package com.example.ssu_makeup.main_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.ssu_makeup.MainActivity;
import com.example.ssu_makeup.R;

public class MainSearchFragment extends Fragment {
    //TODO: 검색결과 NestedFragment가 올라와 있을 때는 EditText 선택 시에만 키보드 올라오기

    EditText searchBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_search, container, false);

        searchBar = root.findViewById(R.id.search_bar);
        return root;
    }

    @Override
    public void onResume(){
        searchBar.requestFocus();
        super.onResume();
    }
}