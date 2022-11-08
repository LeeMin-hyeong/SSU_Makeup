package com.example.manwithmakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton kakao;
    ImageButton email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kakao = findViewById(R.id.kakao_login);
        email = findViewById(R.id.email_login);
        setContentView(R.layout.activity_login);


    }

    @Override
    public void onClick(View view) {
        if(view == kakao){

        }
        else if(view == email){

        }
    }
}