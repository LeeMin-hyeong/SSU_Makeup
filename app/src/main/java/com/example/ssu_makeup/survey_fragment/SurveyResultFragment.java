package com.example.ssu_makeup.survey_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.SelectSkinTypeFragment;
import com.example.ssu_makeup.SurveyActivity;
import com.example.ssu_makeup.survey_fragment.survey_q1.SurveyQ1_1Fragment;

//SurveyActivity 결과 화면 Fragment
public class SurveyResultFragment extends Fragment {
    public static SurveyResultFragment newInstance() {
        return new SurveyResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_result, container, false);

        return root;
    }
}