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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.Baumann;
import com.example.ssu_makeup.MainActivity;
import com.example.ssu_makeup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        String surveyResult = Baumann.analyzeSurveyResult();
        float q1 = Baumann.getQ1ResultPercent();
        float q2 = Baumann.getQ2ResultPercent();
        float q3 = Baumann.getQ3ResultPercent();
        float q4 = Baumann.getQ4ResultPercent();
        Baumann.clearResult();
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

        FirebaseAuth mfirebase = FirebaseAuth.getInstance();
        String uid = mfirebase.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                surveyResultMessage.setText(getString(R.string.survey_result_message, name));
                surveyResultSkinType.setText(surveyResult);
                surveyResultSkinType.setTextColor(Baumann.getColorByString(requireActivity(), surveyResult));
                databaseReference.child(uid).child("skinType").setValue(surveyResult);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //동적 막대 그래프 구현부
        q1Percent.setText(String.valueOf((int)q1));
        q2Percent.setText(String.valueOf((int)q2));
        q3Percent.setText(String.valueOf((int)q3));
        q4Percent.setText(String.valueOf((int)q4));

        LinearLayout.LayoutParams q1LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, q1);
        ImageView q1PercentBar = new ImageView(requireContext());
        q1PercentBar.setImageResource(R.drawable.round_corners_100dp_red);
        q1PercentBar.setLayoutParams(q1LayoutParams);
        q1PercentBarFrame.addView(q1PercentBar);

        LinearLayout.LayoutParams q2LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, q2);
        ImageView q2PercentBar = new ImageView(requireContext());
        q2PercentBar.setImageResource(R.drawable.round_corners_100dp_yellow);
        q2PercentBar.setLayoutParams(q2LayoutParams);
        q2PercentBarFrame.addView(q2PercentBar);

        LinearLayout.LayoutParams q3LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, q3);
        ImageView q3PercentBar = new ImageView(requireContext());
        q3PercentBar.setImageResource(R.drawable.round_corners_100dp_blue);
        q3PercentBar.setLayoutParams(q3LayoutParams);
        q3PercentBarFrame.addView(q3PercentBar);

        LinearLayout.LayoutParams q4LayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, q4);
        ImageView q4PercentBar = new ImageView(requireContext());
        q4PercentBar.setImageResource(R.drawable.round_corners_100dp_green);
        q4PercentBar.setLayoutParams(q4LayoutParams);
        q4PercentBarFrame.addView(q4PercentBar);

        moveToMain.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
        return root;
    }
}