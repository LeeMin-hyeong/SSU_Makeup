package com.example.ssu_makeup.main_fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ssu_makeup.R;

public class MainSearchFragment extends Fragment {
    EditText searchBar;
    FragmentManager fragmentManager;
    MainSearchResultFragment mainSearchResultFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_search, container, false);
        fragmentManager = getChildFragmentManager();
        searchBar = root.findViewById(R.id.search_bar);

        searchBar.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_SEARCH){
                if(searchBar.getText().toString().length()>0){
                    hideKeyboard();
                    mainSearchResultFragment = new MainSearchResultFragment();
                    Bundle bundle = new Bundle(1);
                    bundle.putString("searching_item", searchBar.getText().toString());
                    mainSearchResultFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.search_result_container, mainSearchResultFragment).addToBackStack("search").commit();
                }
                else{
                    Toast.makeText(requireContext(), "검색어를 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });

        root.findViewById(R.id.parent_view).setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });

        return root;
    }
    private void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}