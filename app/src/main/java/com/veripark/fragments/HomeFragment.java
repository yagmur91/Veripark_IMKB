package com.veripark.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.SearchView;

import com.veripark.adapters.AllFilesAdapter;
import com.veripark.imkb.MainActivity;
import com.veripark.imkb.R;
import com.veripark.services.Model.Stock;
import com.veripark.util.FixedGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;

    private MainActivity main;

    int scrollX = 0;

    List<Stock> stockList = new ArrayList<>();

    HorizontalScrollView headerScroll;
    SearchView searchView;



    private RecyclerView recList;
    private LinearLayoutManager llm;
    private AllFilesAdapter adapter;

    public HomeFragment(MainActivity mainActivity,FragmentManager fragmentManager) {
        this.main=mainActivity;
        this.fragmentManager=fragmentManager;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_lots, container, false);

        AllFilesAdapter adapter = new AllFilesAdapter(fragmentManager);

        recList = (RecyclerView) view.findViewById(R.id.allfilescardList);
        headerScroll = view.findViewById(R.id.headerScroll);

        recList.setHasFixedSize(true);
        recList.setAdapter(adapter);


        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(1);
        recList.setLayoutManager(manager);
        recList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));



        return view;
    }


}
