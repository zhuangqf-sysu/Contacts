package com.example.zhuangqf.contacts;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhuangqf on 7/7/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Contact>mList;

    public MyRecyclerAdapter(List<Contact>mList){
        this.mList = mList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.name.setText(mList.get(position).name);
        myViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),ShowActivity.class);
                intent.putExtra("mId", mList.get(position).getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout item;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            item = (LinearLayout)itemView.findViewById(R.id.item);
        }
    }

}
