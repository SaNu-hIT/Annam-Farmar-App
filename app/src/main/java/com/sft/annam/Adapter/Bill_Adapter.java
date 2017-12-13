package com.sft.annam.Adapter;

/**
 * Created by SFT on 15/2/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Bill_model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import com.sft.annam.customfonts.MyTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Anjush on 14/2/2017.
 */

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.MyViewholder> {
    private Context context;
    private List<Bill_model> karshakaItemList;

    public Bill_Adapter(Context context, List<Bill_model> karshakaItemList) {
        this.context = context;
        this.karshakaItemList = karshakaItemList;
    }


    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bill_row, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, final int position) {
        Bill_model karshakaItem = karshakaItemList.get(position);
   holder.bill_bookingid.setText(karshakaItem.getBooking_id());
   holder.bill_location.setText(karshakaItem.getFarmer_loc());
   holder.bill_date.setText(karshakaItem.getDate_and_time());
   holder.bill_machinetype.setText(karshakaItem.getMachine_type());
   holder.bill_machinename.setText(karshakaItem.getMachine_name());
   holder.bill_ownername.setText(karshakaItem.getOwner_name());
   holder.bill_ownerphone.setText(karshakaItem.getFarmer_phone());
   holder.bill_day.setText(karshakaItem.getDate_and_time());
   holder.bill_rate.setText(karshakaItem.getTotal_amount());
   holder.bill_exptime.setText(karshakaItem.getTotal_time());
   holder.bill_total_amount.setText(karshakaItem.getTotal_amount());



    }





    @Override
    public int getItemCount() {
        return karshakaItemList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {

MyTextView bill_bookingid;
MyTextView bill_location;
MyTextView bill_date;
MyTextView bill_machinetype;
MyTextView bill_machinename;
MyTextView bill_ownername;
MyTextView bill_ownerphone;
MyTextView bill_day;
MyTextView bill_rate;
MyTextView bill_exptime;
MyTextView bill_total_amount;

        public MyViewholder(View itemView) {
            super(itemView);
            bill_bookingid = (MyTextView) itemView.findViewById(R.id.bill_bookingid);
            bill_location = (MyTextView) itemView.findViewById(R.id.bill_location);
            bill_date = (MyTextView) itemView.findViewById(R.id.bill_date);
            bill_machinetype = (MyTextView)itemView.findViewById(R.id.bill_machinetype);
            bill_machinename = (MyTextView) itemView.findViewById(R.id.bill_machinename);
            bill_ownername = (MyTextView) itemView.findViewById(R.id.bill_ownername);
            bill_ownerphone = (MyTextView) itemView.findViewById(R.id.bill_ownerphone);
            bill_day = (MyTextView) itemView.findViewById(R.id.bill_day);
            bill_rate = (MyTextView) itemView.findViewById(R.id.bill_rate);
            bill_exptime = (MyTextView) itemView.findViewById(R.id.bill_exptime);
            bill_total_amount = (MyTextView) itemView.findViewById(R.id.bill_total_amount);
        }
    }
}
