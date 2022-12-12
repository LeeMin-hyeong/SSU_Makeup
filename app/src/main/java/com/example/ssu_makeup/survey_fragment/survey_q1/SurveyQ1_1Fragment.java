package com.example.ssu_makeup.survey_fragment.survey_q1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;

import com.example.ssu_makeup.activity.SurveyActivity;
import com.example.ssu_makeup.custom_class.Baumann;

public class SurveyQ1_1Fragment extends Fragment implements View.OnClickListener{
    TextView question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
//    Button answer5;

    public static SurveyQ1_1Fragment newInstance() {
        return new SurveyQ1_1Fragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_4_answers, container, false);
        question = root.findViewById(R.id.question);
        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        answer3 = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);
//        answer5 = root.findViewById(R.id.answer5);

        question.setText(R.string.q1_1);
        answer1.setText(R.string.a1_1_1);
        answer2.setText(R.string.a1_1_2);
        answer3.setText(R.string.a1_1_3);
        answer4.setText(R.string.a1_1_4);
//        answer5.setText(R.string.a1_1_5);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
//        answer5.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        setButtons();
        view.setBackgroundResource(R.drawable.round_corners_selected);
        Baumann.addScore4Answers(view,1,1);
        ((SurveyActivity)requireActivity()).replaceFragment(SurveyQ1_2Fragment.newInstance());
    }

    public void setButtons(){
        answer1.setBackgroundResource(R.drawable.round_corners);
        answer2.setBackgroundResource(R.drawable.round_corners);
        answer3.setBackgroundResource(R.drawable.round_corners);
        answer4.setBackgroundResource(R.drawable.round_corners);
//        answer5.setBackgroundResource(R.drawable.round_corners);
    }
}