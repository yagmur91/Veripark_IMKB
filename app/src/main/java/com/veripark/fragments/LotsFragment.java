package com.veripark.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.veripark.imkb.R;
import com.veripark.services.Model.Stock;


public class LotsFragment extends Fragment {


    private Stock s;

    public LotsFragment( Stock stock) {
        s=stock;
                
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_lots_detail, container, false);

        TextView vStockDetailSymbol = (TextView) view.findViewById(R.id.txtSembolDetay);
        vStockDetailSymbol.setText(s.getSymbol());

      /*  TextView vStockDetailPrice = (TextView) view.findViewById(R.id.txtFiyat);
        vStockDetailPrice.setText(s.getPrice()+"");

        TextView vStockDetailDiffrence = (TextView) view.findViewById(R.id.txtFarkDetay);
        vStockDetailDiffrence.setText(String.valueOf(s.getDifference()));

        TextView vStockDetailVolume = (TextView) view.findViewById(R.id.txtHacimDetay);
        vStockDetailVolume.setText(String.valueOf(s.getVolume()));

        TextView vStockDetailOffer = (TextView) view.findViewById(R.id.txtAlisDetay);
        vStockDetailOffer.setText(String.valueOf(s.getOffer()));

     */
        return view;
    }


}
