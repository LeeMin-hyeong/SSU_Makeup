package com.example.ssu_makeup.survey_fragment.survey_q3;

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

public class SurveyQ3_2Fragment extends Fragment implements View.OnClickListener{
    TextView question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    public static SurveyQ3_2Fragment newInstance() {
        return new SurveyQ3_2Fragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_4_answers, container, false);
        question = root.findViewById(R.id.question);
        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        answer3 = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);

        question.setText(R.string.q3_2);
        answer1.setText(R.string.a3_2_1);
        answer2.setText(R.string.a3_2_2);
        answer3.setText(R.string.a3_2_3);
        answer4.setText(R.string.a3_2_4);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        setButtons();
        view.setBackgroundResource(R.drawable.round_corners_selected);
        Baumann.addScore4Answers(view,3,2);
        ((SurveyActivity)requireActivity()).replaceFragment(SurveyQ3_3Fragment.newInstance());
    }

    public void setButtons(){
        answer1.setBackgroundResource(R.drawable.round_corners);
        answer2.setBackgroundResource(R.drawable.round_corners);
        answer3.setBackgroundResource(R.drawable.round_corners);
        answer4.setBackgroundResource(R.drawable.round_corners);
    }
}