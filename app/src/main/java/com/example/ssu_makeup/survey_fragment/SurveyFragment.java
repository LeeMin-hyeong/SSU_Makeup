package com.example.ssu_makeup.survey_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.SurveyActivity;
import com.example.ssu_makeup.survey_fragment.survey_q1.SurveyQ1_1Fragment;

//SurveyActivity 초기 화면 Fragment
public class SurveyFragment extends Fragment {
    public static SurveyFragment newInstance() {
        return new SurveyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey, container, false);

        Button surveyYes = root.findViewById(R.id.survey_yes);
        Button surveyNo = root.findViewById(R.id.survey_no);
        TextView greeting = root.findViewById(R.id.greeting_message);

        //TODO: 현재 사용자 이름(First Name) 가져와서 출력하기 "name" SharedPreference?
        String name = "name";
        greeting.setText(getString(R.string.greeting_message, name, name));

        surveyYes.setOnClickListener(view -> ((SurveyActivity)getActivity()).startReplaceFragment(SurveyQ1_1Fragment.newInstance()));
        surveyNo.setOnClickListener(view -> ((SurveyActivity)getActivity()).startReplaceFragment(SelectSkinTypeFragment.newInstance()));

        return root;
    }
}