package com.ashish.infinassignment.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashish.infinassignment.Adapters.TabRecyclerAdapter;
import com.ashish.infinassignment.R;

import java.util.ArrayList;

/**
 * Created by Ashish Yadav on 27-Dec-16.
 */

public class TabLayoutAll extends Fragment {
    public TabRecyclerAdapter adapter;
    private RecyclerView recycler;
    private ArrayList<String> nameList,numberList;

    public View onCreateView(LayoutInflater inflator, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflator.inflate(R.layout.tab_recyclerview,container,false);
        recycler = (RecyclerView)view.findViewById(R.id.tab_recycler_view);
        nameList = getArguments().getStringArrayList("nameList");
        numberList = getArguments().getStringArrayList("numberList");

        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TabRecyclerAdapter(getActivity(),nameList ,numberList);
        recycler.setAdapter(adapter);


        return view;
    }

}

