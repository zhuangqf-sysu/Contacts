package com.example.zhuangqf.contacts;

import com.orm.SugarRecord;

/**
 * Created by zhuangqf on 6/1/16.
 */
public class Contact extends SugarRecord {
    String name;
    String telephone;

    public  Contact(){}

    public Contact(String name, String telephone){
        this.name = name;
        this.telephone = telephone;
    }

}
