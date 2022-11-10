package com.example.manwithmakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText checkPassword;

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
        checkPassword = findViewById(R.id.check_password_input);

        kakao.setOnClickListener(this);
        email.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        loginTransition.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        registerTransition.setOnClickListener(this);
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
            registerSection.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);

            checkPassword.setVisibility(View.VISIBLE);
            registerButton.setVisibility(View.VISIBLE);
            loginSection.setVisibility(View.VISIBLE);
        }
        else if(view == loginTransition){
            registerSection.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);

            checkPassword.setVisibility(View.GONE);
            registerButton.setVisibility(View.GONE);
            loginSection.setVisibility(View.GONE);
        }
    }
}