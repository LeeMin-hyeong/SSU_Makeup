package com.example.ssu_makeup.survey_fragment;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.Baumann;
import com.example.ssu_makeup.MainActivity;
import com.example.ssu_makeup.R;

//SurveyActivity 결과 화면 Fragment
public class SurveyResultFragment extends Fragment {
    public static SurveyResultFragment newInstance() {
        return new SurveyResultFragment();
    }
    TextView surveyResultMessage;
    TextView surveyResultSkinType;
    TextView q1Percent;
    TextView q2Percent;
    TextView q3Percent;
    TextView q4Percent;
    LinearLayout q1PercentBarFrame;
    LinearLayout q2PercentBarFrame;
    LinearLayout q3PercentBarFrame;
    LinearLayout q4PercentBarFrame;
    Button moveToMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey_result, container, false);

        surveyResultMessage = root.findViewById(R.id.survey_result_message);
        surveyResultSkinType = root.findViewById(R.id.survey_result_skin_type);
        moveToMain = root.findViewById(R.id.move_to_main);
        q1Percent = root.findViewById(R.id.q1_percent);
        q2Percent = root.findViewById(R.id.q2_percent);
        q3Percent = root.findViewById(R.id.q3_percent);
        q4Percent = root.findViewById(R.id.q4_percent);
        q1PercentBarFrame = root.findViewById(R.id.q1_percent_bar_frame);
        q2PercentBarFrame = root.findViewById(R.id.q2_percent_bar_frame);
        q3PercentBarFrame = root.findViewById(R.id.q3_percent_bar_frame);
        q4PercentBarFrame = root.findViewById(R.id.q4_percent_bar_frame);

        //TODO: 현재 사용자 이름 불러오기, 사용자 피부 타입 정보 서버에 업데이트 하기
        String name = "김숭실";

        //TODO: 사용자 피부 타입 정보 서버에 업데이트하기
        String surveyResult = Baumann.analyzeSurveyResult();
        surveyResultMessage.setText(getString(R.string.survey_result_message, name));
        surveyResultSkinType.setText(surveyResult);
        surveyResultSkinType.setTextColor(Baumann.getColorByString(requireActivity(), surveyResult));

        //동적 막대 그래프 구현부
        q1Percent.setText(String.valueOf((int)Baumann.getQ1ResultPercent()));
        q2Percent.setText(String.valueOf((int)Baumann.getQ2ResultPercent()));
        q3Percent.setText(String.valueOf((int)Baumann.getQ3ResultPercent()));
        q4Percent.setText(String.valueOf((int)Baumann.getQ4ResultPercent()));

        LinearLayout.LayoutParams q1LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float)Baumann.getQ1ResultPercent());
        ImageView q1PercentBar = new ImageView(requireContext());
        q1PercentBar.setImageDrawable((GradientDrawable) ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_100dp_red));
        q1PercentBar.setLayoutParams(q1LayoutParams);
        q1PercentBarFrame.addView(q1PercentBar);

        LinearLayout.LayoutParams q2LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float)Baumann.getQ2ResultPercent());
        ImageView q2PercentBar = new ImageView(requireContext());
        q2PercentBar.setImageDrawable((GradientDrawable) ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_100dp_yellow));
        q2PercentBar.setLayoutParams(q2LayoutParams);
        q2PercentBarFrame.addView(q2PercentBar);

        LinearLayout.LayoutParams q3LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float)Baumann.getQ3ResultPercent());
        ImageView q3PercentBar = new ImageView(requireContext());
        q3PercentBar.setImageDrawable((GradientDrawable) ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_100dp_blue));
        q3PercentBar.setLayoutParams(q3LayoutParams);
        q3PercentBarFrame.addView(q3PercentBar);

        LinearLayout.LayoutParams q4LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, (float)Baumann.getQ4ResultPercent());
        ImageView q4PercentBar = new ImageView(requireContext());
        q4PercentBar.setImageDrawable((GradientDrawable) ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_100dp_green));
        q4PercentBar.setLayoutParams(q4LayoutParams);
        q4PercentBarFrame.addView(q4PercentBar);

        moveToMain.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        return root;
    }
}