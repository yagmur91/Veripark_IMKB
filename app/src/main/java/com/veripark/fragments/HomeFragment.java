package com.veripark.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veripark.imkb.MainActivity;
import com.veripark.imkb.R;


public class HomeFragment extends Fragment {

    private MainActivity main;

    public HomeFragment(MainActivity mainActivity) {
        this.main=mainActivity;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_main, container, false);

        return view;
    }


}
