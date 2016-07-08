package com.example.zhuangqf.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private Contact mContact;
    private EditText editName;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editRemark;
    private Button saveBn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = (EditText)findViewById(R.id.editName);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editRemark = (EditText)findViewById(R.id.editRemark);
        saveBn = (Button)findViewById(R.id.save);

        Intent intent = getIntent();

        Long mId =  intent.getLongExtra("mId",-1);

        if(mId!=-1){
            mContact = Contact.findById(Contact.class,mId);
            editName.setText(mContact.name);
            editPhone.setText(mContact.telephone);
            editEmail.setText(mContact.email);
            editRemark.setText(mContact.remark);
        }else mContact = new Contact();

        saveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContact.client = MyApplication.client;
                mContact.name = editName.getText().toString();
                mContact.telephone = editPhone.getText().toString();
                mContact.email = editEmail.getText().toString();
                mContact.remark = editRemark.getText().toString();
                mContact.save();
                finish();
            }
        });

    }
}
