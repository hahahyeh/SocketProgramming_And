package com.example.chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        EditText username = findViewById(R.id.text_name);
        Button btn_enter = findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = username.getText().toString();
                String serverIP = getIntent().getStringExtra("serverIP");

                Intent intent = new Intent(getBaseContext(), ChatActivity.class);
                intent.putExtra("nickname", nickname);
                intent.putExtra("serverIP", serverIP);
                startActivity(intent);
            }
        });
    }
}