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
    private Button saveBn;
    private Button callBn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editName = (EditText)findViewById(R.id.editName);
        editPhone = (EditText)findViewById(R.id.editPhone);
        saveBn = (Button)findViewById(R.id.save);
        callBn = (Button)findViewById(R.id.call);

        Intent intent = getIntent();

        Long mId =  intent.getLongExtra("contactId",-1);

        if(mId!=-1){
            mContact = Contact.findById(Contact.class,mId);
            editName.setText(mContact.name);
            editPhone.setText(mContact.telephone);
        }else mContact = new Contact();

        saveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContact.name = editName.getText().toString();
                mContact.telephone = editPhone.getText().toString();
                mContact.save();
            }
        });

    }
}
