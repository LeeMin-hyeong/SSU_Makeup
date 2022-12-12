package com.example.ssu_makeup.survey_fragment.survey_q4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.custom_class.Baumann;
import com.example.ssu_makeup.activity.SurveyActivity;
import com.example.ssu_makeup.survey_fragment.SurveyResultFragment;

public class SurveyQ4_21Fragment extends Fragment implements View.OnClickListener{
    TextView question;
    Button answer1;
    Button answer2;

    public static SurveyQ4_21Fragment newInstance() {
        return new SurveyQ4_21Fragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_2_answers, container, false);
        question = root.findViewById(R.id.question);
        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);

        question.setText(R.string.q4_21);
        answer1.setText(R.string.a4_21_1);
        answer2.setText(R.string.a4_21_2);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        setButtons();
        view.setBackgroundResource(R.drawable.round_corners_selected);
        Baumann.addScore2Answers(view,4,21);
        ((SurveyActivity)requireActivity()).replaceFragment(SurveyResultFragment.newInstance());
    }

    public void setButtons(){
        answer1.setBackgroundResource(R.drawable.round_corners);
        answer2.setBackgroundResource(R.drawable.round_corners);
    }
}