package com.example.ssu_makeup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton kakao;
    ImageButton email;
    TextView loginButton;
    TextView registerTransition;
    TextView registerButton;
    TextView loginTransition;
    LinearLayout registerSection;
    LinearLayout loginSection;
    SlidingUpPanelLayout slide;
    EditText checkPasswordInput;
    EditText emailInput;
    EditText passwordInput;
    EditText lastNameInput;
    EditText firstNameInput;

    long initTime;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = mFirebaseDatabase.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kakao = findViewById(R.id.kakao_login);
        email = findViewById(R.id.email_login);
        slide = findViewById(R.id.sliding_layout);
        loginButton = findViewById(R.id.login_button);
        loginTransition = findViewById(R.id.login_transition_button);
        registerButton = findViewById(R.id.register_button);
        registerTransition = findViewById(R.id.register_transition_button);
        registerSection = findViewById(R.id.register_section);
        loginSection = findViewById(R.id.login_section);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        checkPasswordInput = findViewById(R.id.check_password_input);
        lastNameInput = findViewById(R.id.last_name_input);
        firstNameInput = findViewById(R.id.first_name_input);

        kakao.setOnClickListener(this);
        email.setOnClickListener(this);
        loginTransition.setOnClickListener(this);
        registerTransition.setOnClickListener(this);

        slide.setTouchEnabled(false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
/*
        if (mFirebaseAuth.getCurrentUser() != null) {
            databaseReference.child(mFirebaseAuth.getCurrentUser().getUid()).child("skinType").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String skinType = snapshot.getValue(String.class);
                    if (skinType == null){
                        Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }

*/
        registerButton.setOnClickListener(view -> {
            //회원가입 처리 시작
            String strEmail = emailInput.getText().toString().trim();
            String strPwd = passwordInput.getText().toString().trim();
            String strCheckPwd = checkPasswordInput.getText().toString().trim();
            String strLastName = lastNameInput.getText().toString();
            String strFirstName = firstNameInput.getText().toString();
            if(strEmail.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
            else if(strPwd.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"패스워드를 입력하세요.",Toast.LENGTH_SHORT).show();
            else if(strCheckPwd.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"패스워드 확인을 입력하세요.",Toast.LENGTH_SHORT).show();
            else if(strLastName.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"성을 입력하세요.",Toast.LENGTH_SHORT).show();
            else if(strFirstName.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"이름을 입력하세요.",Toast.LENGTH_SHORT).show();
            else
            if (strPwd.equals(strCheckPwd)) {
                //Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()) {
                        Log.d("EmailPassWord", "signInWithEmail:success");
                        Toast.makeText(LoginActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                        //닉네임, 아이디, 비밀번호 파이어베이스 db 저장
                        String email = firebaseUser.getEmail();
                        String uid = firebaseUser.getUid();
                        Info userInfo = new Info(strFirstName, strLastName, email);
                        mRef.child(uid).setValue(userInfo);
                        setToLogin();
                    } else {
                        Log.w("EmailPassWord", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        clearText();
                        slide.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "비밀번호가 서로 같지 않습니다.", Toast.LENGTH_SHORT).show();
                checkPasswordInput.getText().clear();
            }
        });//Firebase Authentication 회원가입

        loginButton.setOnClickListener(view -> {
            String loginEmail = emailInput.getText().toString().trim();
            String loginPassword = passwordInput.getText().toString().trim();
            if(loginEmail.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
            else if(loginPassword.getBytes().length<=0)
                Toast.makeText(getApplicationContext(),"비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
            else
                mFirebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        databaseReference.child(mFirebaseAuth.getCurrentUser().getUid()).child("skinType").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String skinType = snapshot.getValue(String.class);
                                if (skinType == null){
                                    Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    }else {
                        Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                });
        });//firebase 로그인

    }

    void setToLogin() {
        registerSection.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        checkPasswordInput.setVisibility(View.GONE);
        lastNameInput.setVisibility(View.GONE);
        firstNameInput.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);
        loginSection.setVisibility(View.GONE);

        clearText();
    }
    void setToRegister() {
        registerSection.setVisibility(View.GONE);
        loginButton.setVisibility(View.GONE);
        checkPasswordInput.setVisibility(View.VISIBLE);
        lastNameInput.setVisibility(View.VISIBLE);
        firstNameInput.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);
        loginSection.setVisibility(View.VISIBLE);

        clearText();
    }
    void clearText() {
        emailInput.getText().clear();
        passwordInput.getText().clear();
        checkPasswordInput.getText().clear();
        lastNameInput.getText().clear();
        firstNameInput.getText().clear();
    }

    @Override
    public void onClick(View view) {
        if(view == kakao){
            //kakao 로그인 구현부
        }
        else if(view == email){
            slide.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            setToLogin();
        }
        else if(view == registerTransition){
            setToRegister();
        }
        else if(view == loginTransition){
            setToLogin();
        }
    }

    //BACK BUTTON 2회 입력 시 종료
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if (slide.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                slide.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                setToLogin();
                return true;
            } else {
                if (System.currentTimeMillis() - initTime > 3000) {
                    Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show();
                    initTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
        }
        return false;
    }

    @IgnoreExtraProperties //db 저장 회원정보 객체
    public class Info {
        public String firstName; public String lastName; public String userId;
        public Info() {}
        public Info(String firstName, String lastName, String userId) {
            this.firstName = firstName; this.lastName = lastName; this.userId = userId;
        }
    }
}