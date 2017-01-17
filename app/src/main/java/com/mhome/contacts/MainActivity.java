package com.mhome.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int MAX_LEVEL = 6;
    int[] location = new int[MAX_LEVEL];
    int depth = 0;
    ArrayList<Item> data;
    View contactFragmentView;
    FragmentManager fragmentManager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        data = new BuildingContactsResponse(null, null).simulateData(MAX_LEVEL);
        contactFragmentView = findViewById(R.id.fragment_contact_list);
        BuildingContactsFragment fragment = BuildingContactsFragment.newInstance(data);
        ft.replace(R.id.fragment_contact_list, fragment);
        ft.commit();
    }

    void changeView(int position) {
        ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        ArrayList<Item> list = ((BuildingContactsResponse) getList().get(position)).affiliate;
        if (list.get(0) instanceof BuildingContactsResponse) {
            fragment = BuildingContactsFragment.newInstance(list);
            ft.replace(R.id.fragment_contact_list, fragment);
        } else if (list.get(0) instanceof InContactsResponse) {
            fragment = InContactsFragment.newInstance(list);
            ft.replace(R.id.fragment_contact_list, fragment);
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
        location[depth] = position;
        depth++;
        //ft.replace(R.id.fragment_contact_list,BlankFragment.newInstance("","")).commit();
    }

    public ArrayList<Item> getList(){
        ArrayList<Item> result = data;
        for(int i=0;i<depth;i++){
            result = ((BuildingContactsResponse) result.get(location[i])).affiliate;
        }
        return result;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (depth == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit")
                    .setMessage("你确定要退出应用吗?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).show();

        }else{
            depth--;
            fragmentManager = getSupportFragmentManager();
            ft = fragmentManager.beginTransaction();
       /* Fragment fragment = BuildingContactsFragment.newInstance(getList());
        ft.replace(R.id.fragment_contact_list,fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();*/
            fragmentManager.popBackStack();

        if(depth==0){
//            getActionBar().setDisplayHomeAsUpEnabled(false);
        }

            //ft.addToBackStack(null);
            //getFragmentManager().popBackStack();
            // 4 5 8 7
        }
    }
}
