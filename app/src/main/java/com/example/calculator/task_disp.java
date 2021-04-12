package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class task_disp extends Activity {

    private EditText et_user,et_passwod;
    private ImageButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(lp);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.task02);



        btn_login = findViewById(R.id.login);
        et_passwod = (EditText) findViewById(R.id.password);
        et_user = (EditText) findViewById(R.id.username);

        et_passwod.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_passwod.setHint("");
                }
                else{
                    et_passwod.setHint("请输入密码");
                }
            }
        });

        et_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    et_user.setHint("");
                }
                else{
                    et_user.setHint("请输入用户名");
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("check");
                String password = et_passwod.getText().toString();
                String username = et_user.getText().toString();
                check(username,password);
            }
        });
    }

    protected void check(String username,String password){
        if (username.equals("陈亮") && password.equals("12138")){
            Intent intent = new Intent(this,Welcome.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}