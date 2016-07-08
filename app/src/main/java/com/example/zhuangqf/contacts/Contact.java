package com.example.zhuangqf.contacts;

import android.provider.ContactsContract;

import com.orm.SugarRecord;

/**
 * Created by zhuangqf on 6/1/16.
 */
public class Contact extends SugarRecord {
    String client;
    String name;
    String telephone;
    String email;
    String remark;

    public  Contact(){}

    public Contact(String client, String name, String telephone,String email,String remark){
        this.client = client;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.remark = remark;
    }

}
