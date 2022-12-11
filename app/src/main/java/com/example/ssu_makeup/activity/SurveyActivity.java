package com.example.ssu_makeup.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.ssu_makeup.R;
import com.example.ssu_makeup.survey_fragment.SurveyFragment;

public class SurveyActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, SurveyFragment.newInstance());
        fragmentTransaction.commit();
    }

    //fragment 전환 시 fragment에서 호출되는 함수
    public void replaceFragment(@NonNull Fragment fragment){
        //TODO: fragment 전환 시 데이터 유지(뒤로 가기를 통해 이전 선택 정보, 이후 선택 정보 유지
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
    public void startReplaceFragment(@NonNull Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void moveToMain(){
        Intent intent = new Intent(SurveyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}