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
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editpw = (EditText)findViewById(R.id.editpw);
        login = (Button)findViewById(R.id.login);

        sp = getSharedPreferences("client", Context.MODE_PRIVATE);
        final String password = sp.getString("password",null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editpw.getText().toString();
                if(password==null){
                    sp.edit().putString("password",s).commit();
                }else{
                    if(!password.equals(s)){
                        Toast.makeText(LoginActivity.this,"wrong password",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
