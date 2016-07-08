package com.example.zhuangqf.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class ShowActivity extends AppCompatActivity {

    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView remark;
    static private Long mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        name = (TextView)findViewById(R.id.name);
        phone = (TextView)findViewById(R.id.phone);
        email = (TextView)findViewById(R.id.email);
        remark = (TextView)findViewById(R.id.remark);

        Intent intent = getIntent();
        mId = intent.getLongExtra("mId",-1);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(mId!=-1){
            Contact mContact = Contact.findById(Contact.class,mId);
            name.setText(mContact.name);
            phone.setText(mContact.telephone);
            email.setText(mContact.email);
            remark.setText(mContact.remark);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.show, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_search:
                break;
            case R.id.user_add:
                Intent intent1 = new Intent(ShowActivity.this, AddActivity.class);
                startActivity(intent1);
                break;
            case R.id.user_logout:
                MyApplication.client = null;
                Intent intent2 = new Intent(ShowActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case  R.id.user_edit:
                Intent intent3 = new Intent(ShowActivity.this,AddActivity.class);
                intent3.putExtra("mId", mId);
                startActivity(intent3);
                break;
            default:
                break;
        }
        return true;
    }
}
