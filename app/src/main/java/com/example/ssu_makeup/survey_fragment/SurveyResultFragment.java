package com.example.ssu_makeup.survey_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.SurveyActivity;

//SurveyActivity 결과 화면 Fragment
public class SurveyResultFragment extends Fragment {
    Button moveToMain;
    public static SurveyResultFragment newInstance() {
        return new SurveyResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_result, container, false);
        moveToMain = root.findViewById(R.id.move_to_main);
        moveToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SurveyActivity)getActivity()).moveToMain();
            }
        });
        return root;
    }
}