package com.example.zhuangqf.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.List;

/**
 * Created by zhuangqf on 6/1/16.
 */
public class MyListAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private Integer mCount;
    private List<Contact> contactList;

    public MyListAdapter(Context mContext, List<Contact> contactList) {
        this.mContext = mContext;
        this.mCount = contactList.size();
        this.contactList = contactList;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    //ATTENTION: Never bind listener or fill values in generateView.
    //           You have to do that in fillValues method.
    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);
    }

    @Override
    public void fillValues(final int position, final View convertView) {
        ImageView m = (ImageView)convertView.findViewById(R.id.header);
        m.setImageResource(R.drawable.profle);
        TextView n = (TextView)convertView.findViewById(R.id.name);
        n.setText(contactList.get(position).name);
        LinearLayout l = (LinearLayout)convertView.findViewById(R.id.touch);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AddActivity.class);
                intent.putExtra("contactId",contactList.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        ImageView d = (ImageView)convertView.findViewById(R.id.delete);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact.delete(contactList.get(position));
                contactList.remove(position);
                mCount--;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
