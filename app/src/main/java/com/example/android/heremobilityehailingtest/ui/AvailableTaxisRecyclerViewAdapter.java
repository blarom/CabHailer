package com.example.android.heremobilityehailingtest.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.heremobilityehailingtest.R;
import com.example.android.heremobilityehailingtest.Taxi;

import java.util.List;

public class AvailableTaxisRecyclerViewAdapter extends RecyclerView.Adapter<AvailableTaxisRecyclerViewAdapter.CabsViewHolder>{

    private List<Taxi> mTaxiList;
    Context mContext;

    //RecyclerView methods
    public AvailableTaxisRecyclerViewAdapter(Context context) {
        mContext = context;
    }
    @NonNull @Override public CabsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_taxi, parent, false);
        view.setFocusable(true);
        return new CabsViewHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull AvailableTaxisRecyclerViewAdapter.CabsViewHolder holder, int position) {
        updateIcon(holder, position);
        updateCabCompany(holder, position);
        updateEta(holder, position);
    }
    @Override public int getItemCount() {
        return mTaxiList.size();
    }
    class CabsViewHolder extends RecyclerView.ViewHolder {

        ImageView taxiImage;
        TextView cabCompany;
        TextView eta;

        CabsViewHolder(View itemView) {
            super(itemView);
            taxiImage = itemView.findViewById(R.id.cab_company_imageIV);
            cabCompany = itemView.findViewById(R.id.cab_companyTV);
            eta = itemView.findViewById(R.id.eta);
        }
    }

    //Functional methods
    private void updateEta(CabsViewHolder holder, int position) {
        holder.eta.setText(mTaxiList.get(position).getConvertedEta());
    }
    private void updateCabCompany(CabsViewHolder holder, int position) {
        holder.cabCompany.setText(mTaxiList.get(position).getStation());
    }
    private void updateIcon(CabsViewHolder holder, int position) {
        holder.taxiImage.setImageResource(mTaxiList.get(position).getIconResource());
    }
    public void setCabsList(List<Taxi> taxiList) {
        if (taxiList != null) mTaxiList = taxiList;
        if (mTaxiList != null) this.notifyDataSetChanged();
    }
}
