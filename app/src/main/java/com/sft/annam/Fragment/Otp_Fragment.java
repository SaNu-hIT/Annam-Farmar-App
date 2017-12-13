package com.sft.annam.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.sft.annam.Model.Otp_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import java.util.HashMap;
import java.util.Map;

import swarajsaaj.smscodereader.interfaces.OTPListener;
import swarajsaaj.smscodereader.receivers.OtpReader;

/**
 * Created by SFT on 29/12/2016.
 */
public class Otp_Fragment extends Fragment implements OTPListener {

    EditText edtOTP;
    Button btnConfirm, btnResendOTP, btnCancel;
    String username;
    boolean enableResendOTP;
    private final String PREFS_LOGIN_STATUS = "LOGIN";
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View otpView = inflater.inflate(R.layout.otp_fragment, container, false);
        SharedPreferences sharedpreferences =  getActivity().getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE);
        username = sharedpreferences.getString("username","0");
        enableResendOTP = sharedpreferences.getBoolean("enableResendOTP",true);
        initViews(otpView);
        initLisenewrs();
        if(!enableResendOTP){
            btnResendOTP.setBackgroundColor(0xFFD3D3D3);
            btnResendOTP.setEnabled(false);
        }
        getActivity().setTitle("Confirm OTP");
        requestQueue = Volley.newRequestQueue(getContext());
        OtpReader.bind(this,"ANNAAM");
        return otpView;
    }

    private void initLisenewrs() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmotp();
            }
        });

        btnResendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setMessage("You are about to resend OTP\nAre you sure ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
                        editor.putBoolean("enableResendOTP", false);
                        editor.commit();

                        btnResendOTP.setBackgroundColor(0xFFD3D3D3);
                        btnResendOTP.setEnabled(false);

                        resendotp();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                String message = "If you cancel your registration, You won't be able to redo registration with " + username + " again\n\nAre you sure ?";
                builder.setMessage(message);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
                        editor.putBoolean("hasOTPConfirm", false);
                        editor.remove("enableResendOTP");
                        editor.commit();

                        Fragment fragment=new MainPage_Login_Signup_Fragment();
                        Utilities.getInstance(getActivity()).changeChildFragment(
                                fragment, "MainPage_Login_Signup_Fragment", getActivity());
                        Toast.makeText(getActivity(), "Registration Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void resendotp() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Please wait...","Fetching...",false,false);
        String url = Otp_Model.RESEND_URL + username;
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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        if (response.equals("Success")){
            Toast.makeText(getActivity(), "OTP Resent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
        }
    }


    private void confirmotp() {
        //Displaying a progressbar
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Authenticating", "Please wait while we check the entered code", false,false);
        //Log.e("Loading","Created");

        //Getting the user entered otp from edittext
        final String otp = edtOTP.getText().toString().trim();

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

                            SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_LOGIN_STATUS, Context.MODE_PRIVATE).edit();
                            editor.putBoolean("hasOTPConfirm", false);
                            editor.remove("enableResendOTP");
                            editor.commit();

                            //Starting a new activity
                            Fragment fragment=new MainPage_Login_Signup_Fragment();
                            Utilities.getInstance(getActivity()).changeChildFragment(
                                    fragment, "MainPage_Login_Signup_Fragment", getActivity());
                            Toast.makeText(getActivity(), "Success: Now You May Login", Toast.LENGTH_SHORT).show();
                        }else{
                            //Displaying a toast if the otp entered is wrong
                            Toast.makeText(getActivity(),"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
                            loading.cancel();
                            loading.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
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

    private void initViews(View otpView) {
        edtOTP = (EditText) otpView.findViewById(R.id.editTextOtp);
        btnConfirm = (Button) otpView.findViewById(R.id.buttonConfirm);
        btnResendOTP = (Button) otpView.findViewById(R.id.buttonResendOtp);
        btnCancel = (Button) otpView.findViewById(R.id.buttonCancel);
    }

    @Override
    public void otpReceived(String messageText) {
        Log.d("OTP MSG",messageText);
        String intValue = messageText.replaceAll("[^0-9]", "");
        Log.d("OTP Code",intValue);
        edtOTP.setText(intValue);
        confirmotp();
    }
}
