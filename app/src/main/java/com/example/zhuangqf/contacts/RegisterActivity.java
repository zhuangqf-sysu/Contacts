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

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText pw1;
    private EditText pw2;
    private Button registerBn;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.editName);
        pw1 = (EditText)findViewById(R.id.editPw1);
        pw2 = (EditText)findViewById(R.id.editPw2);
        registerBn = (Button)findViewById(R.id.registerBn);

        registerBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                String strPw1 = pw1.getText().toString();
                String strPw2 = pw2.getText().toString();

                sp = getSharedPreferences("contacts", Context.MODE_PRIVATE);

                if(strName.isEmpty()){
                    Toast.makeText(v.getContext(),"请输入用户名",Toast.LENGTH_LONG).show();
                }else if(sp.getString(strName,null)!=null){
                    Toast.makeText(v.getContext(),"用户已注册",Toast.LENGTH_LONG).show();
                }else if(!strPw1.equals(strPw2)){
                    Toast.makeText(v.getContext(),"密码不一致",Toast.LENGTH_LONG).show();
                }else{
                    sp.edit().putString(strName, MyApplication.MD5(strPw1)).commit();
                    Toast.makeText(v.getContext(), "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
