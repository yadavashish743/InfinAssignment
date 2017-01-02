package com.ashish.infinassignment.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashish.infinassignment.Adapters.TabRecyclerAdapter;
import com.ashish.infinassignment.Adapters.TabRecyclerMapAdapter;
import com.ashish.infinassignment.R;

import java.util.ArrayList;

/**
 * Created by Ashish Yadav on 27-Dec-16.
 */

public class TabLayoutMap extends Fragment {
    public TabRecyclerMapAdapter adapter;
    private RecyclerView recycler;
    private ArrayList<String> fnameList,femailList,phoneList,officePhoneList,latitudeList,longitudeList;

    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflator.inflate(R.layout.tab_recyclerview,container,false);
        recycler = (RecyclerView)view.findViewById(R.id.tab_recycler_view);
        fnameList = getArguments().getStringArrayList("fnameList");
        for(int i =0; i<fnameList.size();i++){
            Log.d("fnameList", fnameList.get(i).toString());
        }

        femailList = getArguments().getStringArrayList("femailList");
        phoneList = getArguments().getStringArrayList("fphoneList");
        officePhoneList = getArguments().getStringArrayList("fofficePhoneList");
        latitudeList = getArguments().getStringArrayList("latitudeList");
        longitudeList = getArguments().getStringArrayList("longitudeList");


        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TabRecyclerMapAdapter(getActivity(),fnameList ,femailList,phoneList,officePhoneList,latitudeList,longitudeList);
        recycler.setAdapter(adapter);


        return view;
    }

}
