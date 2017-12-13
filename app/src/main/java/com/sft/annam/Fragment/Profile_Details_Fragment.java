package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Profile_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by SFT on 6/9/2016.
 */
public class Profile_Details_Fragment extends Fragment implements View.OnClickListener {
    EditText profile_name, profile_address, profile_phone, profile_mail, profile_location;
    CheckBox cbRice, cbRubber, cbTea, cbVeg, cbBanana, cbCoffee;
    Button profile_save, profile_cancel;
    private ProgressDialog loading;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View profileView = inflater.inflate(R.layout.profile_fragment,container,false);

        profile_name=(EditText)profileView.findViewById(R.id.textProfileName);
        profile_address=(EditText)profileView.findViewById(R.id.textProfileAddress);
        profile_phone=(EditText)profileView.findViewById(R.id.textProfilePhone);
        profile_mail=(EditText)profileView.findViewById(R.id.textProfileMail);
        profile_location=(EditText)profileView.findViewById(R.id.textProfileLocation);

        cbRice = (CheckBox)profileView.findViewById(R.id.checkBox_rice);
        cbRubber = (CheckBox)profileView.findViewById(R.id.checkBox_ruuber);
        cbTea = (CheckBox)profileView.findViewById(R.id.checkBox_tea);
        cbVeg = (CheckBox)profileView.findViewById(R.id.checkBox_veg);
        cbBanana = (CheckBox)profileView.findViewById(R.id.checkBox_banana);
        cbCoffee = (CheckBox)profileView.findViewById(R.id.checkBox_coffee);

        profile_save=(Button)profileView.findViewById(R.id.buttonProfileSave);
        profile_cancel=(Button)profileView.findViewById(R.id.buttonProfileCancel);

        profile_save.setOnClickListener(this);
        profile_cancel.setOnClickListener(this);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        getActivity().setTitle("Profile");
        initListeners();
        getData();
        return profileView;
    }

    private void initListeners() {
        profile_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                return;
            }
        });
    }

    public void getData(){
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        String url = Profile_Model.DATA_URL+fechid;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void setData(){
        loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
        final String fechid = fetchId.getUser_id();

        String tempURL = Profile_Model.DATA_URL_SET +fechid
                +"&name="+profile_name.getText().toString().trim()
                +"&address="+profile_address.getText().toString().trim()
                +"&email="+profile_mail.getText().toString().trim()
                +"&phone="+profile_phone.getText().toString().trim()
                +"&panchayath_loc="+profile_location.getText().toString().trim()
                +"&crop112="+cbRice.isChecked()
                +"&crop118="+cbBanana.isChecked()
                +"&crop116="+cbVeg.isChecked()
                +"&crop110="+cbRubber.isChecked()
                +"&crop113="+cbTea.isChecked()
                +"&crop119="+cbCoffee.isChecked()
                ;

        String url = fixURL(tempURL);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                setJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private String fixURL(String tempURL) {
        URI uri = null;
        try {
            uri = new URI(tempURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tempURL = String.valueOf(uri);
        uri = null;
        try {
            uri = new URI(tempURL.replaceAll("@", "%40"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tempURL = String.valueOf(uri);
        uri = null;
        try {
            uri = new URI(tempURL.replaceAll(",", "%2C"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return String.valueOf(uri);
    }

    private void setJSON(String response){
        String data_avail="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            data_avail = jsonObject.getString("errorCode");
            if (data_avail.equals("Success")){
                Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(),"No Changes",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showJSON(String response){
        String data_avail="";
        String name="";
        String address="";
        String phone = "";
        String email = "";
        String panchayath_loc = "";
        boolean bRice = false;
        boolean bRubber = false;
        boolean bTea = false;
        boolean bVeg = false;
        boolean bBanana = false;
        boolean bCoffee = false;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Profile_Model.JSON_ARRAY);
            JSONObject serverData = result.getJSONObject(0);
            data_avail = serverData.getString(Profile_Model.KEY_DATA_AVAIL);
            if (data_avail.equals("true")){
                name = serverData.getString(Profile_Model.KEY_NAME);
                address = serverData.getString(Profile_Model.KEY_ADDRESS);
                phone = serverData.getString(Profile_Model.KEY_PHONE);
                email = serverData.getString(Profile_Model.KEY_EMAIL);
                panchayath_loc = serverData.getString(Profile_Model.KEY_PANCHAYATH_LOC);
                if (serverData.getString(Profile_Model.KEY_CROP112).equals("true"))
                    bRice = true;
                if (serverData.getString(Profile_Model.KEY_CROP110).equals("true"))
                    bRubber = true;
                if (serverData.getString(Profile_Model.KEY_CROP113).equals("true"))
                    bTea = true;
                if (serverData.getString(Profile_Model.KEY_CROP116).equals("true"))
                    bVeg = true;
                if (serverData.getString(Profile_Model.KEY_CROP118).equals("true"))
                    bBanana = true;
                if (serverData.getString(Profile_Model.KEY_CROP119).equals("true"))
                    bCoffee = true;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (data_avail.equals("true")){
            profile_name.setText(name);
            profile_address.setText(address);
            profile_phone.setText(phone);
            profile_mail.setText(email);
            profile_location.setText(panchayath_loc);
            cbRice.setChecked(bRice);
            cbRubber.setChecked(bRubber);
            cbTea.setChecked(bTea);
            cbVeg.setChecked(bVeg);
            cbBanana.setChecked(bBanana);
            cbCoffee.setChecked(bCoffee);
        } else {
            profile_name.setText("No Data Available");
            profile_address.setText("No Data Available");
            profile_phone.setText("No Data Available");
            profile_mail.setText("No Data Available");
            profile_location.setText("No Data Available");
        }
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(getActivity(), "You Clicked Save Button", Toast.LENGTH_SHORT).show();
        setData();

    }
}
