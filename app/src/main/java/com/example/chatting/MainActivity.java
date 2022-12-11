package com.example.chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.text_server_ip);
        Button button = findViewById(R.id.btn_connect);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverIP = editText.getText().toString();

                Intent intent = new Intent(getBaseContext(), UserActivity.class);
                intent.putExtra("serverIP", serverIP);
                startActivity(intent);
            }
        });

    }
}