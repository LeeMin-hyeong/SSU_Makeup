package com.example.ssu_makeup.survey_fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssu_makeup.custom_class.Baumann;
import com.example.ssu_makeup.R;
import com.example.ssu_makeup.activity.SurveyActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

//바우만 스킨 테스트를 하지 않는 경우, 스킨타입 변경하기
public class SelectSkinTypeFragment extends Fragment implements View.OnClickListener {

    TextView announcement;
    Button drpt, drnt, dspt, dsnt, drpw, drnw, dspw, dsnw, orpt, ornt, ospt, osnt, orpw, ornw, ospw, osnw;
    Button moveToMain;
    String selectedSkinType;
    GradientDrawable buttonBackground;
    public static Fragment newInstance() {
        return new SelectSkinTypeFragment();
    }
    FirebaseAuth mFirebase = FirebaseAuth.getInstance();
    String uid = Objects.requireNonNull(mFirebase.getCurrentUser()).getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_select_skin_type, container, false);
        announcement = root.findViewById(R.id.announcement);
        buttonBackground = (GradientDrawable) ContextCompat.getDrawable(requireActivity(), R.drawable.round_corners_dynamic_color);

        databaseReference.child(uid).child("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue(String.class);
                announcement.setText(getString(R.string.select_skin_type, name));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        drpt = root.findViewById(R.id.drpt);
        drnt = root.findViewById(R.id.drnt);
        dspt = root.findViewById(R.id.dspt);
        dsnt = root.findViewById(R.id.dsnt);
        drpw = root.findViewById(R.id.drpw);
        drnw = root.findViewById(R.id.drnw);
        dspw = root.findViewById(R.id.dspw);
        dsnw = root.findViewById(R.id.dsnw);
        orpt = root.findViewById(R.id.orpt);
        ornt = root.findViewById(R.id.ornt);
        ospt = root.findViewById(R.id.ospt);
        osnt = root.findViewById(R.id.osnt);
        orpw = root.findViewById(R.id.orpw);
        ornw = root.findViewById(R.id.ornw);
        ospw = root.findViewById(R.id.ospw);
        osnw = root.findViewById(R.id.osnw);
        moveToMain = root.findViewById(R.id.move_to_main);
        drpt.setOnClickListener(this);
        drnt.setOnClickListener(this);
        dspt.setOnClickListener(this);
        dsnt.setOnClickListener(this);
        drpw.setOnClickListener(this);
        drnw.setOnClickListener(this);
        dspw.setOnClickListener(this);
        dsnw.setOnClickListener(this);
        orpt.setOnClickListener(this);
        ornt.setOnClickListener(this);
        ospt.setOnClickListener(this);
        osnt.setOnClickListener(this);
        orpw.setOnClickListener(this);
        ornw.setOnClickListener(this);
        ospw.setOnClickListener(this);
        osnw.setOnClickListener(this);
        moveToMain.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        if(view != moveToMain) {
            setButtons();
            selectedSkinType = ((TextView)view).getText().toString();
            buttonBackground.setColor(Baumann.getColorByString(requireActivity(), selectedSkinType));
            view.setBackground(buttonBackground);
            ((TextView) view).setTextColor(Color.WHITE);
        } else {
            if(selectedSkinType != null){
                Log.d("Test", "onClick: "+selectedSkinType);
                databaseReference.child(uid).child("skinType").setValue(selectedSkinType);
                ((SurveyActivity)requireActivity()).moveToMain();
            }
            else {
                Toast.makeText(getActivity(), "피부 타입을 선택해 주세요", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void setButtons(){
        drpt.setBackgroundResource(R.drawable.round_corners);
        drnt.setBackgroundResource(R.drawable.round_corners);
        dspt.setBackgroundResource(R.drawable.round_corners);
        dsnt.setBackgroundResource(R.drawable.round_corners);
        drpw.setBackgroundResource(R.drawable.round_corners);
        drnw.setBackgroundResource(R.drawable.round_corners);
        dspw.setBackgroundResource(R.drawable.round_corners);
        dsnw.setBackgroundResource(R.drawable.round_corners);
        orpt.setBackgroundResource(R.drawable.round_corners);
        ornt.setBackgroundResource(R.drawable.round_corners);
        ospt.setBackgroundResource(R.drawable.round_corners);
        osnt.setBackgroundResource(R.drawable.round_corners);
        orpw.setBackgroundResource(R.drawable.round_corners);
        ornw.setBackgroundResource(R.drawable.round_corners);
        ospw.setBackgroundResource(R.drawable.round_corners);
        osnw.setBackgroundResource(R.drawable.round_corners);
        drpt.setTextColor(Color.BLACK);
        drnt.setTextColor(Color.BLACK);
        dspt.setTextColor(Color.BLACK);
        dsnt.setTextColor(Color.BLACK);
        drpw.setTextColor(Color.BLACK);
        drnw.setTextColor(Color.BLACK);
        dspw.setTextColor(Color.BLACK);
        dsnw.setTextColor(Color.BLACK);
        orpt.setTextColor(Color.BLACK);
        ornt.setTextColor(Color.BLACK);
        ospt.setTextColor(Color.BLACK);
        osnt.setTextColor(Color.BLACK);
        orpw.setTextColor(Color.BLACK);
        ornw.setTextColor(Color.BLACK);
        ospw.setTextColor(Color.BLACK);
        osnw.setTextColor(Color.BLACK);
    }
}
