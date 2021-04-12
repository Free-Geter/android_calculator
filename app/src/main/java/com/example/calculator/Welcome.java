package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv_show = findViewById(R.id.show_user);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");

        tv_show.setText(username);
    }
}