package com.sft.annam.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.sft.annam.Adapter.Machine_book_List_Adapter;
import com.sft.annam.Model.BookNow_TempData_Model;
import com.sft.annam.Model.MachineListModel_Booking;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/13/2016.
 */
public class Machine_Booking_List_Fragment  extends Fragment{
    private final String PREFS_TEMP_DATA = "TempData";
    BookNow_TempData_Model tempData_model;
    ListView macchine_listforbooking;
    ArrayList<MachineListModel_Booking> machineries_model_array=new ArrayList<MachineListModel_Booking>();
    Machine_book_List_Adapter machineries_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.machine_list_forbooking,container,false);

        macchine_listforbooking=(ListView)rootView.findViewById(R.id.listView_listformachines);

        getDataFromPrefs();

        Bundle machinetype_bundle = getArguments();
        if(machinetype_bundle!=null) {
            machineries_model_array = machinetype_bundle.getParcelableArrayList("machine_types_book");
            buildUi();
        }


        return rootView;
    }

    private void getDataFromPrefs() {
        SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(PREFS_TEMP_DATA, Context.MODE_PRIVATE);
        if(sharedpreferences.getBoolean("hasData",false)){
            String fullLocation = sharedpreferences.getString("fullLocation",null);
            String latitude = sharedpreferences.getString("latitude",null);
            String longitude = sharedpreferences.getString("longitude",null);
            String machine_id = sharedpreferences.getString("machine_id",null);
            String quantity = sharedpreferences.getString("quantity",null);
            String pickupRequired = sharedpreferences.getString("pickupRequired",null);
            tempData_model = new BookNow_TempData_Model(fullLocation,
                    latitude, longitude,machine_id,quantity,pickupRequired);
        } else {
            Toast.makeText(getActivity(),"Error : Insufficient Data",Toast.LENGTH_SHORT).show();
        }
    }


    public void buildUi() {
        if (machineries_model_array != null) {
            if (!machineries_model_array.isEmpty()) {
                machineries_adapter = new Machine_book_List_Adapter(getActivity(),
                        machineries_model_array,tempData_model);
                macchine_listforbooking.setAdapter(machineries_adapter);
            }
        }
    }
}
