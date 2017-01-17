package com.mhome.contacts;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Title :
 * Description :
 * Author : Dong Luo    Data : 2016/12/23 11:05
 * Updater :                  Data : 2016/12/23 11:05
 * Version : 1.0.0
 * Copyright : Copyright(c) 浙江蘑菇加电子商务有限公司 2015 ~ 2016 版权所有
 */

public class BuildingContactsResponse extends Item implements Parcelable {
    ArrayList<Item> affiliate;
    static final int DEPARTMENT_SIZE = 36;
    public boolean isChecked;
    public long id;
    public String name;
    public String name_temp;
    public String entityType;
    int simulateId = 0;
    InContactsResponse inContactsResponse = new InContactsResponse();

    BuildingContactsResponse(String name, ArrayList<Item> items){
        this.name = name;
        this.affiliate = items;
    }

    BuildingContactsResponse(Parcel in){
        name = in.readString();
    }


    public ArrayList<Item> simulateData(int height){
        int size = (int) Math.ceil(Math.random()*DEPARTMENT_SIZE);
        ArrayList<Item> result = new ArrayList<>();
        if(height == 1){
            result = inContactsResponse.simulateData(name_temp);
        }else if(height > 1){
            for(int i=0;i<size;i++){
                int heightNode = (int) Math.ceil(Math.random()*(height-1));
                simulateId++;
                name_temp = "部门 "+simulateId;
                result.add(new BuildingContactsResponse("部门 "+simulateId,simulateData(heightNode)));
            }
        }else{
            result = null;
        }
        return result;
    }

    public static final Parcelable.Creator<BuildingContactsResponse> CREATOR
            = new Parcelable.Creator<BuildingContactsResponse>() {
        public BuildingContactsResponse createFromParcel(Parcel in) {
            return new BuildingContactsResponse(in);


        }

        public BuildingContactsResponse[] newArray(int size) {
            return new BuildingContactsResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }





}
