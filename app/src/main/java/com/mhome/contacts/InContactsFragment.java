package com.mhome.contacts;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InContactsFragment extends BaseFragmentV4 {
    public static final String LIST = "list";
    public static final String CONTACT_DATA = "response";
    ArrayList<InContactsResponse> itemsList;
    String[] names;
    View view;
    private  ListView lv;
    private ArrayAdapter<String> adapter;

    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1,names);
            lv.setAdapter(adapter);
            return false;
        }
    });

    public InContactsFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static InContactsFragment newInstance(ArrayList<Item> itemsList) {
        InContactsFragment fragment = new InContactsFragment();
        Bundle args = new Bundle();
        args.putSerializable(LIST,itemsList);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_in_contacts;
    }

    @Override
    public void initView(View view) {
        lv = (ListView) view.findViewById(R.id.lv_building);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
                intent.putExtra(CONTACT_DATA,itemsList.get(i));
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doBusiness() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                itemsList = (ArrayList<InContactsResponse>) getArguments().getSerializable(LIST);
                names = new String[itemsList.size()];
                for(int i=0;i<itemsList.size();i++){
                    names[i] = itemsList.get(i).name;
                }

                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public boolean isActionBusiness() {
        return true;
    }

    @Override
    public boolean isNeedEventBus() {
        return false;
    }
}
