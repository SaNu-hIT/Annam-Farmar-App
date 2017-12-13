package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.AppEULA;
import com.sft.annam.Connection.HttpRequestHelperForSignup;
import com.sft.annam.Model.Crops_type_Model;
import com.sft.annam.Model.Farmer_type;
import com.sft.annam.Model.Otp_Model;
import com.sft.annam.Model.Panchayath_Model;
import com.sft.annam.Model.Signup_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;


/**
 * Created by JESNA on 7/12/2016.
 */
public class Signup_Fragment extends Fragment {

    private final String PREFS_LOGIN_STATUS = "LOGIN";

    EditText signup_phone, signup_name, signup_addresss,
            signup_email,
            signup_password, signup_confirm_password;

    Spinner signup_whoami,signup_panchay;
    String farmer_typye,farmer_id;

    private AppCompatButton buttonConfirm;
    private EditText editTextConfirmOtp;
    private RequestQueue requestQueue;
    private String username;
    private String phone;

    AutoCompleteTextView signup_location;
    Button signupconfirm;
    ArrayList<Farmer_type> service_Fetching_Model_Array = new ArrayList<Farmer_type>();
    ArrayList<Panchayath_Model> panchayth_Fetching_Model_Array = new ArrayList<Panchayath_Model>();
    ArrayList<Crops_type_Model> crops_Fetching_Model_Array = new ArrayList<Crops_type_Model>();

    private ProgressDialog loading;


    String crops="";
    CheckBox checkbox_rice,checkbox_others,checkbox_veg,checkbox_rubber,checkbox_tea,checkbox_coffee,checkbox_banana,checkbox_coconut;
    private String panchayath_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.signup_fragment, container, false);
        checkbox_rice=(CheckBox)rootView.findViewById(R.id.checkBox_rice);
        checkbox_others=(CheckBox)rootView.findViewById(R.id.checkBox_others);
        checkbox_veg=(CheckBox)rootView.findViewById(R.id.checkBox_veg);
        checkbox_coffee=(CheckBox)rootView.findViewById(R.id.checkBox_coffee);
        checkbox_tea=(CheckBox)rootView.findViewById(R.id.checkBox_tea);
        checkbox_rubber=(CheckBox)rootView.findViewById(R.id.checkBox_ruuber);
        checkbox_banana=(CheckBox)rootView.findViewById(R.id.checkBox_banana);
        checkbox_coconut=(CheckBox)rootView.findViewById(R.id.checkBox_coconut);






        initViews(rootView);


        Bundle service_bundle = getArguments();

        if (service_bundle != null) {
            service_Fetching_Model_Array = service_bundle.getParcelableArrayList("farmer_types");
            panchayth_Fetching_Model_Array = service_bundle.getParcelableArrayList("panchayath");


            crops_Fetching_Model_Array = service_bundle.getParcelableArrayList("crops");

            if (!service_Fetching_Model_Array.isEmpty() && !panchayth_Fetching_Model_Array.isEmpty() && !crops_Fetching_Model_Array.isEmpty()) {


                initViews(rootView);

                buildUI(rootView);


                //SelectMultipleServices(crops_Fetching_Model_Array);
            }
        } else {
            Toast.makeText(getActivity(), "gfhdsf", Toast.LENGTH_SHORT).show();
        }
CheckNetwork();
        requestQueue = Volley.newRequestQueue(getContext());
        initLisenewrs();


        getActivity().setTitle("Signup");

        new AppEULA(getActivity()).show();
        return rootView;

    }




    private void buildUI(View rootView) {

        ArrayList<String> farmer_type_array = new ArrayList<String>();
        ArrayList<String> panchayth_array = new ArrayList<String>();
        final ArrayList<String> panchayth_array_id = new ArrayList<String>();
        ArrayList<String> farmer_type_id = new ArrayList<String>();


        if (!service_Fetching_Model_Array.isEmpty() && service_Fetching_Model_Array != null) {
            farmer_type_array.add("Who am I?");

            for (int i = 0; i < service_Fetching_Model_Array.size(); i++) {
                farmer_type_array.add(service_Fetching_Model_Array.get(i).getFarmertype_name());
                farmer_type_id.add(service_Fetching_Model_Array.get(i).getFarmerttype_id());
            }
            signup_whoami.setAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1, farmer_type_array));




        }





        signup_whoami.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    farmer_typye = service_Fetching_Model_Array.get(position - 1).getFarmertype_name().toString();

                    farmer_id = service_Fetching_Model_Array.get(position - 1).getFarmerttype_id().toString();

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (!panchayth_Fetching_Model_Array.isEmpty() && panchayth_Fetching_Model_Array != null) {


            for (int i = 0; i < panchayth_Fetching_Model_Array.size(); i++) {
                panchayth_array.add(panchayth_Fetching_Model_Array.get(i).getPanchayath_name());
                String id=panchayth_Fetching_Model_Array.get(i).getPanchayath_id();
                panchayth_array_id.add(id);
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (getActivity(), android.R.layout.select_dialog_item, panchayth_array);
            signup_location.setThreshold(1);
            signup_location.setAdapter(adapter);

        }
    }

    private void initLisenewrs(){

     signupconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox_rice.isChecked()){
                    crops+="112,";
                }
                if (checkbox_rubber.isChecked()){
                    crops+="110,";
                }
                if (checkbox_tea.isChecked()){
                    crops+="113,";
                }
                if (checkbox_veg.isChecked()){
                    crops+="116,";
                }
                if (checkbox_others.isChecked()){
                    crops+="118,";
                }
                if (checkbox_coffee.isChecked()){
                    crops+="119,";
                }
                if (checkbox_banana.isChecked()){
                    crops+="121,";
                }
                if (checkbox_coconut.isChecked()){
                    crops+="120,";
                }

                if (!validatesignupfielsEntered()) {


                    Assign_Signup();


                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Assign_Signup() {
        String str_signup_crops = crops;

        int length=crops.length()-1;
        if(length>2) {
            str_signup_crops = str_signup_crops.substring(0, str_signup_crops.length() - 1);
        }
        else {
            Toast.makeText(getActivity(),"Please select atleast one crop",Toast.LENGTH_SHORT).show();
        }

        String str_signup_name = signup_name.getText().toString().trim();
        String str_signup_address = signup_addresss.getText().toString().trim();
        String str_signup_phone = signup_phone.getText().toString().trim();
        String str_signup_email = signup_email.getText().toString().trim();
        String str_signup_password = signup_password.getText().toString().trim();
        String str_signup_panjayath = signup_location.getText().toString().trim();
        String device_id= Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.e("Assign_Signup: ",device_id );

        Signup_Model signup_model = new Signup_Model(str_signup_name, str_signup_address, str_signup_phone,
                str_signup_email, str_signup_password, str_signup_panjayath, str_signup_crops, farmer_id);
        Otp_Model.setPhone(str_signup_phone);

        SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
        editor.putString("username", str_signup_phone);
        editor.commit();

        if (Utilities.getInstance(getActivity()).isNetAvailable()) {
            HttpRequestHelperForSignup helperForSignup = new HttpRequestHelperForSignup();
            helperForSignup.signup(getActivity(), signup_model,device_id);
        } else {
           CheckNetwork();
        }

    }



    private boolean validatesignupfielsEntered() {

        String str_signup_phone = signup_phone.getText().toString();
        String str_signup_name = signup_name.getText().toString();
        String str_signup_address = signup_addresss.getText().toString();
        String str_signup_email = signup_email.getText().toString();
        String str_signup_panchayath = signup_location.getText().toString();
        String str_signup_password = signup_password.getText().toString();
        String str_signup_confirmpassword = signup_confirm_password.getText().toString();



        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(str_signup_phone)) {
            signup_phone.setError(getString(R.string.error_field_required));
            focusView = signup_phone;
            cancel = true;
        }
        if (str_signup_phone.length() != 10) {
            signup_phone.setError("Invalid Phone Number");
            focusView = signup_phone;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_signup_name)) {
            signup_name.setError(getString(R.string.error_field_required));
            focusView = signup_name;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_signup_address)) {
            signup_addresss.setError(getString(R.string.error_field_required));
            focusView = signup_addresss;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_signup_email)) {
            signup_email.setError(getString(R.string.error_field_required));
            focusView = signup_email;
            cancel = true;
        }
        if (!isValidEmail(str_signup_email)) {
            signup_email.setError("Invalid Email");
            focusView = signup_email;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_signup_panchayath)) {
            signup_location.setError(getString(R.string.error_field_required));
            focusView = signup_location;
            cancel = true;
        }

        if (TextUtils.isEmpty(str_signup_password)) {

            signup_password.setError(getString(R.string.error_field_required));
            focusView = signup_password;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_signup_confirmpassword)) {
            signup_confirm_password.setError(getString(R.string.error_field_required));
            focusView = signup_confirm_password;
            cancel = true;
        }
        if (!str_signup_password.equals(str_signup_confirmpassword)) {
            signup_confirm_password.setError("Password Mismatch");
            focusView = signup_confirm_password;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();
        } else {

            return cancel;
        }
        return cancel;
    }

    private void initViews(View rootView) {

        signup_phone = (EditText) rootView.findViewById(R.id.signup_phone);
        signup_name = (EditText) rootView.findViewById(R.id.signup_name);
        signup_addresss = (EditText) rootView.findViewById(R.id.signup_address);
        signup_email = (EditText) rootView.findViewById(R.id.signup_email);
        signup_location = (AutoCompleteTextView) rootView.findViewById(R.id.signup_panchayath);
        signup_whoami = (Spinner) rootView.findViewById(R.id.signup_whoami);
        signup_password = (EditText) rootView.findViewById(R.id.signup_password);
        signup_confirm_password = (EditText) rootView.findViewById(R.id.signup_confirm_password);
        signupconfirm = (Button) rootView.findViewById(R.id.btn_signuppage);

    }

    public final static boolean isValidEmail(CharSequence target) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void ShowMessage()
    {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Error")
                .setContentText("No internet connection,please try again latter")
                .setConfirmText("OK")
                .show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void CheckNetwork()
    {
        if(!isNetworkAvailable())

        {
            ShowMessage();
        }
    }
}
