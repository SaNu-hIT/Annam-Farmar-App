package com.sft.annam;

import android.app.Activity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Fragment.ForgotPassword;
import com.sft.annam.Fragment.MainPage_Login_Signup_Fragment;
import com.sft.annam.Fragment.Otp_Fragment;
import com.sft.annam.Fragment.Signup_Fragment;
import com.sft.annam.Interfaces.OnHttpResponseForForgotPassword;
import com.sft.annam.Interfaces.OnHttpResponseForSignup;
import com.sft.annam.Interfaces.OnHttpResponseListenerForUploadSignUp;
import com.sft.annam.Interfaces.OnHtttpResponseListenerForLogin;
import com.sft.annam.Model.Crops_type_Model;
import com.sft.annam.Model.Farmer_type;
import com.sft.annam.Model.Otp_Model;
import com.sft.annam.Model.Panchayath_Model;
import com.sft.annam.Utilities.Utilities;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JESNA on 7/12/2016.
 */
public class
Login_Signup_Activity extends AppCompatActivity implements
        OnHtttpResponseListenerForLogin,
        OnHttpResponseListenerForUploadSignUp,OnHttpResponseForForgotPassword,
        OnHttpResponseForSignup {
    private final String PREFS_LOGIN_STATUS = "LOGIN";
    private static final String TAG = "Login_Signup_Activity";
    Fragment fragment;

    private AppCompatButton buttonRegister;
    private AppCompatButton buttonConfirm;
    private EditText editTextConfirmOtp;
    private RequestQueue requestQueue;
    private String username;

    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup_main);

        SharedPreferences sharedpreferences =  getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE);
        boolean hasLoggedIn,hasOTPConfirm;
        hasLoggedIn = sharedpreferences.getBoolean("hasLoggedIn",false);
        hasOTPConfirm = sharedpreferences.getBoolean("hasOTPConfirm",false);
        if(hasLoggedIn) {
            startActivity(new Intent(Login_Signup_Activity.this, Home.class));
            Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_SHORT).show();
            finish();
        } else if (hasOTPConfirm){
            Fragment fragment=new Otp_Fragment();
            Utilities.getInstance(this).changeChildFragment(
                    fragment, "Otp_Fragment", this);
            Toast.makeText(Login_Signup_Activity.this, "Please Enter OTP Received", Toast.LENGTH_SHORT).show();
        } else {
            requestQueue = Volley.newRequestQueue(this);

            //setActionBar(toolbar);
            // toolbar.setTitle("Login");
            fragment = new MainPage_Login_Signup_Fragment();
            Utilities.getInstance(Login_Signup_Activity.this).changeChildFragment(
                    fragment, "MainPage_Login_Signup_Fragment", Login_Signup_Activity.this);

        }
    }


    @Override
    public void onBackPressed() {
        Activity currActivity = this;
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_signup_loginpage);
        if (f instanceof Signup_Fragment || f instanceof ForgotPassword) {

            Fragment fragment = new MainPage_Login_Signup_Fragment();
            Utilities.getInstance(Login_Signup_Activity.this).changeChildFragment(
                    fragment, "MainPage_Login_Signup_Fragment", Login_Signup_Activity.this);

        }  else if (f instanceof MainPage_Login_Signup_Fragment) {
            finish();
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void onHttpSuccessfulResponseLogin(String responseBody, boolean responseStatus, String responseResultMsg, String sessionId, int userId) {
        if (responseStatus) {
          //  saveDataToSharedPreference(userId+ " ",sessionId);

            SharedPreferences.Editor editor = getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
            editor.putBoolean("hasLoggedIn", true);
            editor.putInt("user_id",userId);
            //editor.putString("name", login_model.getUsername());
            editor.commit();
            startActivity(new Intent(Login_Signup_Activity.this, Home.class));

            Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), responseResultMsg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onHttpFailedResponseLogin(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {
        Toast.makeText(Login_Signup_Activity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpSuccessfulResponseOnloadSignup(String responseBody, boolean responseStatus,
                                                     String responseResultMsg,
                                                     ArrayList<Farmer_type>farmer_types,
                                                     ArrayList<Crops_type_Model>crops_type_models,
                                                     ArrayList<Panchayath_Model>panchayath_models) {
        if (responseStatus) {
          Log.d("",""+responseStatus);

            Fragment fragment = new Signup_Fragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("farmer_types", farmer_types);
            bundle.putParcelableArrayList("panchayath", panchayath_models);
            bundle.putParcelableArrayList("crops", crops_type_models);
            fragment.setArguments(bundle);
            Utilities.getInstance(this).changeChildFragment(
                    fragment, "Signup_Fragment", this);




        }
        Log.d("",""+responseStatus);

    }

    @Override
    public void onHttpFailedResponseForOnloadLogin(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {
        Toast.makeText(Login_Signup_Activity.this, "check internet connection", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onHttpSuccessfulResponseSignup(String responseBody, boolean responseStatus, String responseResultMsg, String customerId) {
        /*
        if (responseStatus){
            try {
                confirmOtp();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(Login_Signup_Activity.this, "Failed: Username Already Exists", Toast.LENGTH_SHORT).show();
        }
        */
        if (responseStatus){
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
            editor.putBoolean("hasOTPConfirm", true);
            editor.commit();
            Fragment fragment=new Otp_Fragment();
            Utilities.getInstance(this).changeChildFragment(
                    fragment, "Otp_Fragment", this);
            Toast.makeText(Login_Signup_Activity.this, "Please Enter OTP Received", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Login_Signup_Activity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmOtp() throws JSONException {

        //Setting phone number as username
        username = Otp_Model.getPhone();

        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.otp_dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (AppCompatButton) confirmDialog.findViewById(R.id.buttonConfirm);
        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(Login_Signup_Activity.this, "Authenticating", "Please wait while we check the entered code", false,false);
                //Log.e("Loading","Created");

                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Otp_Model.CONFIRM_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if(response.equalsIgnoreCase("success")){
                                    //dismissing the progressbar
                                    loading.cancel();
                                    loading.dismiss();

                                    //Starting a new activity
                                    Fragment fragment=new MainPage_Login_Signup_Fragment();
                                    Utilities.getInstance(Login_Signup_Activity.this).changeChildFragment(
                                            fragment, "MainPage_Login_Signup_Fragment", Login_Signup_Activity.this);
                                    Toast.makeText(Login_Signup_Activity.this, "Success: Now You May Login", Toast.LENGTH_SHORT).show();
                                }else{
                                    //Displaying a toast if the otp entered is wrong
                                    Toast.makeText(Login_Signup_Activity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
                                    loading.cancel();
                                    loading.dismiss();
                                    try {
                                        //Asking user to enter otp again
                                        confirmOtp();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(Login_Signup_Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put(Otp_Model.KEY_OTP, otp);
                        params.put(Otp_Model.KEY_USERNAME, username);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    public void onHttpFailedResponseSignup(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {

        Toast.makeText(Login_Signup_Activity.this, "Try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHttpSuccessfulForForgotPassword(String responseBody, boolean responseStatus, String responseResultMsg, String customerId) {
        if (responseStatus){
            Fragment fragment=new MainPage_Login_Signup_Fragment();
            Utilities.getInstance(this).changeChildFragment(
                    fragment, "MainPage_Login_Signup_Fragment", this);
            Toast.makeText(Login_Signup_Activity.this, "Success,please login with new password", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Login_Signup_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onHttpFailedForForgotPassword(Throwable throwable, String responseBody, boolean resposeStatus, String responseResultMessage) {
        Toast.makeText(Login_Signup_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
    }
}
