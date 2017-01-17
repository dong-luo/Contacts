package com.mhome.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Title :
 * Description :
 * Author : Dong Luo    Data : 2016/12/23 11:06
 * Updater :                  Data : 2016/12/23 11:06
 * Version : 1.0.0
 * Copyright : Copyright(c) 浙江蘑菇加电子商务有限公司 2015 ~ 2016 版权所有
 */

public class InContactsResponse extends Item implements Parcelable{
    public boolean isChecked;
    public long id;
    public String name;
    public String entityType;
    public int gender;//性别
    public String loginName;//工号
    public String orgDepartmentName;//部门
    public String orgPostName;//岗位
    public String orgLevelName;//级别
    public String telNumber;//个人手机
    public Properties properties;
    int simulateId = 1;

    InContactsResponse(String n,int g,String d){
        name = n;
        gender = g;
        orgDepartmentName = d;
    }
    InContactsResponse(){}

    InContactsResponse(Parcel in){
        name = in.readString();
        gender = in.readInt();
        orgDepartmentName = in.readString();
    }



    public static final Parcelable.Creator<InContactsResponse> CREATOR
            = new Parcelable.Creator<InContactsResponse>() {
        public InContactsResponse createFromParcel(Parcel in) {
            return new InContactsResponse(in);
        }

        public InContactsResponse[] newArray(int size) {
            return new InContactsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(gender);
        parcel.writeString(orgDepartmentName);
    }

    public ArrayList<Item> simulateData(String department){
        int size = (int) Math.ceil(Math.random()*10);
        ArrayList<Item> result = new ArrayList<>();
        for(int i=0;i<size;i++){
            result.add(new InContactsResponse("联系人 "+simulateId,(int) (Math.random()*2),department));
            simulateId++;
        }
        return result;
    }

}
