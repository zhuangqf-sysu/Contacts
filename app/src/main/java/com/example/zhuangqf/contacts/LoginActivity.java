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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private EditText editpw;
    private Button login;
    private SharedPreferences sp;
    private EditText editclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editpw = (EditText)findViewById(R.id.editpw);
        login = (Button)findViewById(R.id.login);
        editclient = (EditText)findViewById(R.id.editclient);

        sp = getSharedPreferences("client", Context.MODE_PRIVATE);
        final String password = sp.getString("password",null);
        final String client = sp.getString("client",null);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spw = editpw.getText().toString();
                String scl = md5(editclient.getText().toString());
                if(client==null){
                    sp.edit().putString("password",spw)
                            .putString("client",scl).commit();
                }else{
                    if(!password.equals(spw)||!client.equals(scl)){
                        Toast.makeText(LoginActivity.this,"wrong password",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

}
