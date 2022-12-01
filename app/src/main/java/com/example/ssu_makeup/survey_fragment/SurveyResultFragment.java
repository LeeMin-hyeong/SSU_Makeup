package com.example.ssu_makeup.survey_fragment;

import static com.example.ssu_makeup.Score.total_score;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.Score;
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
        Score.calculateTotal(1);
        Score.calculateTotal(2);
        Score.calculateTotal(3);
        Score.calculateTotal(4);
        String print="Q1: "+ Double.toString(total_score[1]);
        print.concat(" Q2:"+ Double.toString(total_score[2]));
        print.concat("Q3:"+ Double.toString(total_score[3]));
        print.concat("Q4:"+Double.toString(total_score[4]));

        TextView result=root.findViewById(R.id.greeting_message);

        result.setText(print);
        return root;
    }
}