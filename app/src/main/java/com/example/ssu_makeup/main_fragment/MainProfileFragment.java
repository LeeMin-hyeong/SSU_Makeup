package com.example.ssu_makeup.main_fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssu_makeup.Baumann;
import com.example.ssu_makeup.LoginActivity;
import com.example.ssu_makeup.R;
import com.example.ssu_makeup.SurveyActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainProfileFragment extends Fragment {
    TextView userName;
    TextView userSkinType;
    ImageView editUserInfo;
    Button newSurvey;
    Button logout;
    Button deleteAccount;
    RelativeLayout userInfoFrame;
    GradientDrawable userInfoBackground;
    String firstName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_profile, container, false);
        userName = root.findViewById(R.id.user_name);
        userSkinType = root.findViewById(R.id.user_skin_type);
        editUserInfo = root.findViewById(R.id.edit_user_info_button);
        newSurvey = root.findViewById(R.id.new_survey_button);
        logout = root.findViewById(R.id.logout_button);
        deleteAccount = root.findViewById(R.id.delete_account_button);
        userInfoFrame = root.findViewById(R.id.user_info_frame);
        userInfoBackground = (GradientDrawable)ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_30dp_dynamic_color);


        FirebaseAuth mfirebase = FirebaseAuth.getInstance();
        String uid = mfirebase.getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                firstName = snapshot.getValue(String.class);

                userName.setText(firstName+"님");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.child(uid).child("skinType").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String testResult = snapshot.getValue(String.class);
                userSkinType.setText(testResult);

                //피부 타입에 따라 동적으로 background color 변경
                userInfoBackground.setColor(Baumann.getColorByString(requireActivity(), testResult));
                userInfoFrame.setBackground(userInfoBackground);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        editUserInfo.setOnClickListener(view -> {
            //TODO: 회원정보 수정 fragment 구현 및 연결
        });

        //피부 타입 변경하기 선택 시 SurveyActivity 호출 후 다시 피부 타입 설문 진행
        newSurvey.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SurveyActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            LayoutInflater layoutInflater = (LayoutInflater)requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customDialogView = layoutInflater.inflate(R.layout.fragment_dialog, null);
            builder.setView(customDialogView);
            TextView dialogText = customDialogView.findViewById(R.id.dialog_text);
            dialogText.setText("로그아웃 하시겠습니까?");
            AlertDialog alertDialog = builder.create();
            customDialogView.findViewById(R.id.dialog_yes_button).setOnClickListener(dialog ->{
                alertDialog.dismiss();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });
            customDialogView.findViewById(R.id.dialog_no_button).setOnClickListener(dialog ->{
                alertDialog.dismiss();
            });
            if(alertDialog.getWindow() != null) alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            alertDialog.show();
        });

        deleteAccount.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            LayoutInflater layoutInflater = (LayoutInflater)requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customDialogView = layoutInflater.inflate(R.layout.fragment_dialog, null);
            builder.setView(customDialogView);
            TextView dialogText = customDialogView.findViewById(R.id.dialog_text);
            dialogText.setText("탈퇴 하시겠습니까?");
            AlertDialog alertDialog = builder.create();
            customDialogView.findViewById(R.id.dialog_yes_button).setOnClickListener(dialog ->{
                alertDialog.dismiss();
                mfirebase.getCurrentUser().delete().addOnCompleteListener(getActivity(), task -> {
                    if(task.isSuccessful()){
                        databaseReference.child(uid).removeValue();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), "회원탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

            });
            customDialogView.findViewById(R.id.dialog_no_button).setOnClickListener(dialog ->{
                alertDialog.dismiss();
            });
            if(alertDialog.getWindow() != null) alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            alertDialog.show();
        });

        return root;
    }

}