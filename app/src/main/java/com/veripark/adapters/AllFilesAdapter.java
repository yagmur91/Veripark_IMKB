package com.veripark.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.veripark.fragments.LotsFragment;
import com.veripark.imkb.MainActivity;
import com.veripark.imkb.R;
import com.veripark.services.Model.Stock;
import com.veripark.services.Model.StockListResponseModel;

import java.util.ArrayList;
import java.util.List;

public class AllFilesAdapter extends RecyclerView.Adapter<AllFilesAdapter.AllFilesViewHolder>implements Filterable {

    private static final int TYPE_ROW = 0;
    private static final int TYPE_ROW_COLORFUL = 1;
    private static Context context;
    static FragmentManager f;


    ServiceAdapter adapter = new ServiceAdapter();
   public  List<Stock> stockList;
    List<Stock> filteredStockList;



    public void loaddata()

    {
        try
        {
            StockListResponseModel response = adapter.StocksList("all");
            if(response != null && response.getStatus().getIsSuccess() == true)
                stockList = response.getStocks();
            int asd = 0;

        }
        catch (Exception exception)
        {
        }

    }


        public int getItemCount()
     {
        return   stockList == null ? 0 : stockList.size();
     }


        public AllFilesAdapter(FragmentManager f)
    {
        this.f=f;
       // this.stockList=stockList;
      //  this.filteredStockList=stockList;

        loaddata();
    }

    @Override
         public int getItemViewType(int position)
    {
        if (position % 2 == 0)
        {
            return TYPE_ROW_COLORFUL;
        }

        return TYPE_ROW;
    }


    @Override
    public AllFilesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if(i == TYPE_ROW) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stocklist, viewGroup, false);
            return new AllFilesViewHolder(itemView,stockList);
        } else
        {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stocklist_colorful, viewGroup, false);
            return new AllFilesViewHolder(itemView,stockList);

        }


    }



    @Override
    public void onBindViewHolder(AllFilesAdapter.AllFilesViewHolder allFilesViewHolder, int i)
    {

       // NLQDOSYA_BILGISI wbodosya= TUM_DOSYALAR.get(i);

        allFilesViewHolder.vSembol.setText(stockList.get(i).getSymbol());
        allFilesViewHolder.vFiyat.setText(stockList.get(i).getSymbol());
        allFilesViewHolder.vFark.setText(String.valueOf(stockList.get(i).getDifference()));
        allFilesViewHolder.vHacim.setText(String.valueOf(stockList.get(i).getVolume()));
        allFilesViewHolder.vAlis.setText(String.valueOf(stockList.get(i).getPrice()));
        allFilesViewHolder.vSatis.setText(String.valueOf(stockList.get(i).getOffer()));





    }





    public static class AllFilesViewHolder extends RecyclerView.ViewHolder {

        private  List<Stock> stockDetail;

        protected TextView vSembol;
        protected TextView vFiyat;
        protected TextView vFark;
        protected TextView vHacim;
        protected TextView vAlis;
        protected TextView vSatis;



        Fragment fragment;

        public AllFilesViewHolder(View v, List<Stock> sdetail) {
            super(v);
            this.stockDetail=sdetail;

            vSembol =  (TextView) v.findViewById(R.id.txtSembol);
            vFiyat = (TextView)  v.findViewById(R.id.txtFiyat);
            vFark = (TextView)  v.findViewById(R.id.txtFark);
            vHacim = (TextView) v.findViewById(R.id.txtHacim);
            vAlis=(TextView)v.findViewById(R.id.txtAlis);
            vSatis=(TextView)v.findViewById(R.id.txtSatis);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                    Stock stockdetail= stockDetail.get(getLayoutPosition());

                    fragment = new LotsFragment(stockdetail); //içinde veri id bişey gitcek

                    FragmentTransaction fragmentTransactiondemand = f.beginTransaction();
                    fragmentTransactiondemand.replace(R.id.container, fragment).commit();








                }
            });


        }


    }





    @Override
    public Filter getFilter()
    {
        return new Filter()
        {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence)
            {
                String charString = charSequence.toString();
                if (charString.isEmpty())
                {
                    filteredStockList = stockList;
                } else
                {
                    List<Stock> filteredList = new ArrayList<>();
                    for (Stock s : stockList)
                    {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name
                        if (s.symbol.toLowerCase().contains(charString.toLowerCase()) )
                        {
                            filteredList.add(s);
                        }
                    }

                    filteredStockList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredStockList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults)
            {
                filteredStockList = (ArrayList<Stock>) filterResults.values;

                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
    }


}