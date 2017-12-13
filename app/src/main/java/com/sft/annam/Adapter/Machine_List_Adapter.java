package com.sft.annam.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sft.annam.Model.Machineries_Model;
import com.sft.annam.R;
import com.sft.annam.customfonts.MyTextView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by JESNA on 7/13/2016.
 */
public class Machine_List_Adapter extends BaseAdapter {
    public static ArrayList<Machineries_Model> machineis_model = new ArrayList<Machineries_Model>();
    Context context;
    MyTextView machine_name, mac_type, mac_rateperdy, mac_rateperhour, mac_description, mac_distance, day, hour;
    ImageView machineimages;
    MyTextView spec1, spec2, spec3;
    String[] minDistance;
    String[] numOwners;

    public Machine_List_Adapter(FragmentActivity activity, ArrayList<Machineries_Model> machineries_model_array, String[] minimumDistance, String[] numOwners) {

        context = activity;
        this.machineis_model = machineries_model_array;
        minDistance = minimumDistance;
        this.numOwners = numOwners;
    }

    @Override
    public int getCount() {
        return machineis_model.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String loading = "Loading...";
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.machne_list_item_design, parent, false);
        }
        //else{
        initViews(convertView);
        machine_name.setText(machineis_model.get(position).getMachine_name());

        mac_type.setText(machineis_model.get(position).getMachine_type());
        String machane_type = machineis_model.get(position).getMachine_type();
        Log.e("getView: ", machane_type);
        String rate_per_tree_climb = machineis_model.get(position).getRate_per_tree_climb();
        String rate_per_tree_clean = machineis_model.get(position).getRate_per_tree_clean();
        String rate_per_coconut_kg = machineis_model.get(position).getRate_per_coconut_kg();
        String rate_per_pesticide_kg = machineis_model.get(position).getRate_per_pesticide_kg();

        String strRatePerDay = machineis_model.get(position).getRate_per_day();
        mac_rateperdy.setText(strRatePerDay);
        String strRatePerHour = machineis_model.get(position).getRate_per_hour();
        mac_rateperhour.setText(strRatePerHour);


        if (!strRatePerDay.equals("0")) {
            mac_rateperdy.setText("₹" + strRatePerDay);
            day.setText("Rate Per day");
        }


        if (!strRatePerHour.equals("0")) {
            hour.setText("Rate Per Hour");
            mac_rateperhour.setText("₹" + strRatePerHour);
        }


        if (!rate_per_coconut_kg.equals("0")) {
            mac_rateperdy.setText("₹" + rate_per_coconut_kg);
            mac_rateperhour.setVisibility(View.GONE);
            hour.setVisibility(View.GONE);

        }


        if (!rate_per_tree_climb.equals("0")) {
            day.setText("Rate Per tree climb");

            mac_rateperdy.setText("₹" + rate_per_tree_climb);

        }

        if (!rate_per_tree_clean.equals("0")) {
            hour.setText("Rate per tree clean");
            mac_rateperhour.setText("₹" + rate_per_tree_clean);

        }

        if (!rate_per_pesticide_kg.equals("0")) {
            day.setText("Rate per KG");
            mac_rateperdy.setText("₹" + rate_per_pesticide_kg);
            mac_rateperhour.setVisibility(View.GONE);


        }

        String specification1 = machineis_model.get(position).getSpecification1();
        String specification2 = machineis_model.get(position).getSpecification2();
        String specification3 = machineis_model.get(position).getSpecification3();

        if (!specification1.equals("")) {
            spec1.setText(specification1);
            spec1.setVisibility(View.VISIBLE);
        }
        if (!specification2.equals("")) {
            spec2.setText(specification2);
            spec2.setVisibility(View.VISIBLE);
        }
        if (!specification3.equals("")) {
            spec3.setText(specification3);
            spec3.setVisibility(View.VISIBLE);
        }

        mac_description.setText(machineis_model.get(position).getDescription());
        String distance;
        if (minDistance == null) {
            distance = "Not Available Near You";
        } else if (minDistance[position].equals("0")) {
            distance = "Not Available Near You";
        } else {
            distance = numOwners[position] + " machines @ " + minDistance[position] + " KM away from you";
        }
        mac_distance.setText(distance);

        String imageURL = machineis_model.get(position).getImage();
        URI uri = null;
        try {
            uri = new URI(imageURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        imageURL = String.valueOf(uri);

        //  Picasso.with(context).load(machineis_model.get(position).getImage()).into(machineimages);
        Picasso.with(context)
                .load(imageURL)
                .error(R.drawable.tillerharvester)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .resize(400, 400)
                .into(machineimages);
        //}


        String mac_feat_all = machineis_model.get(position).getMachine_feature();
        Log.e("Text", "getView: " + mac_feat_all);
        String[] mac_feat_array = mac_feat_all.split("`");
        String spec = "• ";
        String strSpec1, strSpec2, strSpec3;
//            switch (mac_feat_array.length-1){
//                case 0:
//                    strSpec1 = spec + mac_feat_array[0];
//
//                    spec1.setText(strSpec1);
//                    spec1.setVisibility(View.VISIBLE);
//                    spec2.setText(spec);
//                    spec3.setText(spec);
//                    break;
//                case 1:
//                    strSpec1 = spec + mac_feat_array[0];
//
//                    spec1.setText(strSpec1);
//                    spec1.setVisibility(View.VISIBLE);
//                    strSpec2 = spec + mac_feat_array[1];
//                    spec2.setVisibility(View.VISIBLE);
//                    spec2.setText(strSpec2);
//                    spec3.setText(spec);
//                    break;
//                case 2:
//                    strSpec1 = spec + mac_feat_array[0];
//                    spec1.setVisibility(View.VISIBLE);
//                    spec1.setText(strSpec1);
//                    strSpec2 = spec + mac_feat_array[1];
//                    spec2.setVisibility(View.VISIBLE);
//                    spec2.setText(strSpec2);
//                    strSpec3 = spec + mac_feat_array[2];
//                    spec3.setVisibility(View.VISIBLE);
//                    spec3.setText(strSpec3);
//                    break;
//                default:
//                    spec1.setText(spec);
//                    spec2.setText(spec);
//                    spec3.setText(spec);
//            }


        return convertView;
    }


    private void initViews(View convertView) {
        machine_name = (MyTextView) convertView.findViewById(R.id.machine_name);
        mac_type = (MyTextView) convertView.findViewById(R.id.mac_type);
        mac_rateperdy = (MyTextView) convertView.findViewById(R.id.mac_rateperdy);
        mac_rateperhour = (MyTextView) convertView.findViewById(R.id.mac_rateperhour);
        mac_description = (MyTextView) convertView.findViewById(R.id.mac_description);
        machineimages = (ImageView) convertView.findViewById(R.id.machines_images_id);
        mac_distance = (MyTextView) convertView.findViewById(R.id.mac_distance);
        spec1 = (MyTextView) convertView.findViewById(R.id.spec1);
        spec2 = (MyTextView) convertView.findViewById(R.id.spec2);
        spec3 = (MyTextView) convertView.findViewById(R.id.spec3);
        day = (MyTextView) convertView.findViewById(R.id.day_rate);
        hour = (MyTextView) convertView.findViewById(R.id.hour_type);
    }
}