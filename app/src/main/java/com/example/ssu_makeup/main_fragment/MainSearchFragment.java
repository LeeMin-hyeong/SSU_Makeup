package com.example.ssu_makeup.main_fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ssu_makeup.R;

public class MainSearchFragment extends Fragment {
    //TODO: 검색결과 NestedFragment가 올라와 있을 때는 EditText 선택 시에만 키보드 올라오기
    EditText searchBar;
    FragmentManager fragmentManager;
    MainSearchResultFragment mainSearchResultFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_search, container, false);
        fragmentManager = getChildFragmentManager();
        searchBar = root.findViewById(R.id.search_bar);

        searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_SEARCH){
                if(searchBar.getText().toString().length()>0){
                    searchBar.clearFocus();
                    mainSearchResultFragment = new MainSearchResultFragment();
                    Bundle bundle = new Bundle(1);
                    bundle.putString("searchingItem", searchBar.getText().toString());
                    mainSearchResultFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.search_result_container, mainSearchResultFragment).addToBackStack(null).commit();
                }
                else{
                    Toast.makeText(requireContext(), "검색어를 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
        return root;
    }

}