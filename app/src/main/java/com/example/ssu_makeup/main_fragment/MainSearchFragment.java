package com.example.ssu_makeup.main_fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ssu_makeup.MainActivity;
import com.example.ssu_makeup.R;

public class MainSearchFragment extends Fragment {
    //TODO: 검색결과 NestedFragment가 올라와 있을 때는 EditText 선택 시에만 키보드 올라오기

    EditText searchBar;
    FragmentManager fragmentManager;
    MainSearchResultFragment mainSearchResultFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainSearchResultFragment = new MainSearchResultFragment();
        View root = inflater.inflate(R.layout.fragment_main_search, container, false);
        fragmentManager = getChildFragmentManager();
        searchBar = root.findViewById(R.id.search_bar);

        searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_SEARCH){
                Log.d("test", "test");
                if(searchBar.getText()!=null){
                    Bundle bundle = new Bundle(1);
                    bundle.putString("searchingItem", searchBar.getText().toString());
                    mainSearchResultFragment.setArguments(bundle);
                }
                InputMethodManager inputMethodManager = (InputMethodManager)requireActivity().getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(requireActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                fragmentManager.beginTransaction().add(R.id.search_result_container, mainSearchResultFragment).commit();
            }
            return false;
        });
        return root;
    }

    @Override
    public void onResume(){
        //if(!mainSearchResultFragment.isVisible())
            searchBar.requestFocus();
        super.onResume();
    }
}