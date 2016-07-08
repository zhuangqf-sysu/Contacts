package com.example.zhuangqf.contacts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText editpw;
    private Button login;
    private Button register;
    private SharedPreferences sp;
    private EditText editclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editpw = (EditText)findViewById(R.id.editpw);
        login = (Button)findViewById(R.id.login);
        editclient = (EditText)findViewById(R.id.editclient);
        register = (Button)findViewById(R.id.register);

        sp = getSharedPreferences("contacts", Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String client = editclient.getText().toString();
                String password  = MyApplication.MD5(editpw.getText().toString());
                String prePassword = sp.getString(client,null);
                if(prePassword==null){
                    Toast.makeText(v.getContext(),"用户未注册",Toast.LENGTH_LONG).show();
                }else if(password.equals(prePassword)){
                    MyApplication.client = client;
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(v.getContext(),"密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
