package com.example.ssu_makeup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.ssu_makeup.survey_fragment.SurveyFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SurveyActivity extends FragmentActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mFirebaseDatabase.getReference("Users");
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, SurveyFragment.newInstance());
        fragmentTransaction.commit();

        /*
        Intent intent = getIntent();
        String currentUserUid = intent.getStringExtra("uid");

        mRef.child(currentUserUid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 name = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });


        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        SelectSkinTypeFragment selectSkinTypeFragment = new SelectSkinTypeFragment();
        selectSkinTypeFragment.setArguments(bundle);*/

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