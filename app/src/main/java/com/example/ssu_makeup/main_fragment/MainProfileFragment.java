package com.example.ssu_makeup.main_fragment;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ssu_makeup.Baumann;
import com.example.ssu_makeup.LoginActivity;
import com.example.ssu_makeup.R;
import com.example.ssu_makeup.SurveyActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainProfileFragment extends Fragment {
    TextView userName;
    TextView userSkinType;
    ImageView editUserInfo;
    Button newSurvey;
    Button logout;
    RelativeLayout userInfoFrame;
    GradientDrawable userInfoBackground;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main_profile, container, false);
        userName = root.findViewById(R.id.user_name);
        userSkinType = root.findViewById(R.id.user_skin_type);
        editUserInfo = root.findViewById(R.id.edit_user_info_button);
        newSurvey = root.findViewById(R.id.new_survey_button);
        logout = root.findViewById(R.id.logout_button);
        userInfoFrame = root.findViewById(R.id.user_info_frame);
        userInfoBackground = (GradientDrawable)ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_30dp_dynamic_color);

        //TODO: 유저 이름 정보와 피부 유형 정보 불러오기
        String name = "김숭실";
        String testResult = "DSPT";

        userName.setText(name+"님");
        userSkinType.setText(testResult);

        //피부 타입에 따라 동적으로 background color 변경
        userInfoBackground.setColor(Baumann.getColorByString(requireActivity(), testResult));
        userInfoFrame.setBackground(userInfoBackground);

        editUserInfo.setOnClickListener(view -> {
            //TODO: 회원정보 수정 fragment 구현 및 연결
        });
        //피부 타입 변경하기 선택 시 SurveyActivity 호출 후 다시 피부 타입 설문 진행
        newSurvey.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SurveyActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(view -> {

            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        return root;
    }
}