package com.mhome.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContactDetailActivity extends Activity {
    public static final String CONTACT_DATA = "response";
    InContactsResponse contact_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Intent intent = getIntent();
        contact_info = intent.getParcelableExtra(CONTACT_DATA);
        initView();
    }

    void initView(){
        TextView tv_name,tv_gender,tv_department;
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_department = (TextView) findViewById(R.id.tv_department);
        tv_name.setText(contact_info.name);
        tv_gender.setText(contact_info.gender==1 ? "男":"女");
        tv_department.setText(contact_info.orgDepartmentName);
    }
}
