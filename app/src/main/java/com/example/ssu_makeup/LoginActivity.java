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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
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
    private DatabaseReference mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");

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
        loginButton.setOnClickListener(this);
        loginTransition.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        registerTransition.setOnClickListener(this);

        slide.setTouchEnabled(false);

        mFirebaseAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리 시작
                String strEmail = emailInput.getText().toString();
                String strPwd = passwordInput.getText().toString();
                String strCheckPwd = checkPasswordInput.getText().toString();
                String strLastName = lastNameInput.getText().toString();
                String strFirstName = firstNameInput.getText().toString();

                if (strPwd.equals(strCheckPwd)) {
                    //Firebase Auth 진행
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Log.d("EmailPassWord", "signInWithEmail:success");
                                Toast.makeText(LoginActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                //닉네임, 아이디, 비밀번호 파이어베이스 db 저장
                                String email = firebaseUser.getEmail();
                                String uid = firebaseUser.getUid();
                                Info userInfo = new Info(strFirstName, strLastName, email);
                                mFirebaseDatabase.child(uid).setValue(userInfo);
                                clearText();
                                slide.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                            } else {
                                Log.w("EmailPassWord", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                clearText();
                                slide.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "비밀번호가 서로 같지 않습니다.", Toast.LENGTH_SHORT).show();
                    slide.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
            }
        });//Firebase Authentication 회원가입

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmail = emailInput.getText().toString();
                String loginPassword = passwordInput.getText().toString();
                mFirebaseAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                           Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, SurveyActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });//firebase 로그인

    }

    void setToLogin(){
        registerSection.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        checkPasswordInput.setVisibility(View.GONE);
        lastNameInput.setVisibility(View.GONE);
        firstNameInput.setVisibility(View.GONE);
        registerButton.setVisibility(View.GONE);
        loginSection.setVisibility(View.GONE);

        clearText();
    }
    void setToRegister(){
        registerSection.setVisibility(View.GONE);
        loginButton.setVisibility(View.GONE);
        checkPasswordInput.setVisibility(View.VISIBLE);
        lastNameInput.setVisibility(View.VISIBLE);
        firstNameInput.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);
        loginSection.setVisibility(View.VISIBLE);

        clearText();
    }
    void clearText(){
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
        }
        else if(view == loginButton){
            //email 로그인 구현부
        }
        else if(view == registerTransition){
            setToRegister();
        }
        else if(view == loginTransition){
            setToLogin();
        }
    }

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