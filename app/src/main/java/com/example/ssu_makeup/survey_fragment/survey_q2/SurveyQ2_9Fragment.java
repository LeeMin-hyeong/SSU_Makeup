package com.example.ssu_makeup.survey_fragment.survey_q2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.Baumann;
import com.example.ssu_makeup.SurveyActivity;

public class SurveyQ2_9Fragment extends Fragment implements View.OnClickListener{
    TextView question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    Button answer5;

    public static SurveyQ2_9Fragment newInstance() {
        return new SurveyQ2_9Fragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_5_answers, container, false);
        question = root.findViewById(R.id.question);
        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        answer3 = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);
        answer5 = root.findViewById(R.id.answer5);

        question.setText(R.string.q2_9);
        answer1.setText(R.string.a2_9_1);
        answer2.setText(R.string.a2_9_2);
        answer3.setText(R.string.a2_9_3);
        answer4.setText(R.string.a2_9_4);
        answer5.setText(R.string.a2_9_5);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);
        answer5.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View view) {
        setButtons();
        view.setBackgroundResource(R.drawable.round_corners_selected);
        Baumann.addScore5AnswersIgnore(view,2,9);
        ((SurveyActivity)requireActivity()).replaceFragment(SurveyQ2_10Fragment.newInstance());
    }

    public void setButtons(){
        answer1.setBackgroundResource(R.drawable.round_corners);
        answer2.setBackgroundResource(R.drawable.round_corners);
        answer3.setBackgroundResource(R.drawable.round_corners);
        answer4.setBackgroundResource(R.drawable.round_corners);
        answer5.setBackgroundResource(R.drawable.round_corners);
    }
}