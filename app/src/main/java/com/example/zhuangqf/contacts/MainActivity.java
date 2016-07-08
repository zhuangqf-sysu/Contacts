package com.example.zhuangqf.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<Contact>mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onResume() {
        super.onResume();
        recycler.setAdapter(new MyRecyclerAdapter(initData()));
        registerForContextMenu(recycler);
    }

    private List<Contact> initData(){
        mList = Contact.find(Contact.class,"client = ?", MyApplication.client);
        return  mList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_search:
                break;
            case R.id.user_add:
                Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent1);
                break;
            case R.id.user_logout:
                MyApplication.client = null;
                Intent intent2 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.user_edit:
                Long mId = mList.get(info.position).getId();
                Intent intent = new Intent(this,AddActivity.class);
                intent.putExtra("mId", mId);
                startActivity(intent);
                break;
            case R.id.user_delete:
                mList.get(info.position).delete();
                recycler.setAdapter(new MyRecyclerAdapter(initData()));
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;

    }
}
