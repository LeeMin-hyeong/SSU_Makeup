package com.example.ssu_makeup.survey_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.activity.SurveyActivity;
import com.example.ssu_makeup.survey_fragment.survey_q1.SurveyQ1_1Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

//SurveyActivity 초기 화면 Fragment
public class SurveyFragment extends Fragment {
    public static SurveyFragment newInstance() {
        return new SurveyFragment();
    }

    String firstName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_survey, container, false);

        Button surveyYes = root.findViewById(R.id.survey_yes);
        Button surveyNo = root.findViewById(R.id.survey_no);
        TextView greeting = root.findViewById(R.id.greeting_message);

        FirebaseAuth mFirebase = FirebaseAuth.getInstance();
        String uid = Objects.requireNonNull(mFirebase.getCurrentUser()).getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firstName = snapshot.getValue(String.class);
                greeting.setText(getString(R.string.greeting_message, firstName, firstName));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        surveyYes.setOnClickListener(view -> ((SurveyActivity)requireActivity()).startReplaceFragment(SurveyQ1_1Fragment.newInstance()));
        surveyNo.setOnClickListener(view -> ((SurveyActivity)requireActivity()).startReplaceFragment(SelectSkinTypeFragment.newInstance()));

        return root;
    }
}