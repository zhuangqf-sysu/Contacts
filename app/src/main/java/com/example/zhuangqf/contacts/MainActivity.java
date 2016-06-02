package com.example.zhuangqf.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.orm.SugarContext;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private MyListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mAdd = (Button)findViewById(R.id.add);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        contactList = Contact.listAll(Contact.class);
        mAdapter = new MyListAdapter(this,contactList);
        ListView myListView = (ListView)findViewById(R.id.listView);
        myListView.setAdapter(mAdapter);
    }
}
