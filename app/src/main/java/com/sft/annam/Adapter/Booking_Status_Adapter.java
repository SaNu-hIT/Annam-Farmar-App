package com.sft.annam.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sft.annam.Fragment.BookNow_Confirm;
import com.sft.annam.MapsActivity;
import com.sft.annam.Model.Booking_Status_Items_model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by SFT on 21/9/2016.
 */
public class Booking_Status_Adapter extends BaseAdapter {

    FragmentActivity context;
    TextView id,location,owner_name, owner_phone, machine_name, booking_status, booking_date, arrival_time;
    ImageView machineImage;
    Button cancel_button;

    private ProgressDialog loading;

    public static ArrayList<Booking_Status_Items_model> booking_status_items_model = new ArrayList<Booking_Status_Items_model>();

    public Booking_Status_Adapter (FragmentActivity activity, ArrayList<Booking_Status_Items_model> booking_status_items_model_array) {
        context=activity;
        this.booking_status_items_model = booking_status_items_model_array;

    }

    @Override
    public int getCount() {
        return booking_status_items_model.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.test, parent, false);
        }
        initViews(convertView);
        initListeners(position);
        id.setText(booking_status_items_model.get(position).getBooking_id());
        location.setText(booking_status_items_model.get(position).getFarmer_land_loc());
        owner_name.setText(booking_status_items_model.get(position).getName());
        owner_phone.setText(booking_status_items_model.get(position).getPhone());
        machine_name.setText(booking_status_items_model.get(position).getMachine_name());
        booking_status.setText(booking_status_items_model.get(position).getBstatus());
        if (booking_status_items_model.get(position).getBstatus().equals("Canceled")){
            cancel_button.setVisibility(View.GONE);
        } else {
            cancel_button.setVisibility(View.VISIBLE);
        }
//        if (booking_status_items_model.get(position).getBstatus().equals("Booking Acce")){
//            cancel_button.setVisibility(View.GONE);
//        } else {
//            cancel_button.setVisibility(View.VISIBLE);
//        }
        booking_date.setText(booking_status_items_model.get(position).getBooking_date());
        arrival_time.setText(booking_status_items_model.get(position).getArrival_time());

        String image=booking_status_items_model.get(position).getImage();
        URI uri = null;
        try {
            uri = new URI(image.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String finalurl = String.valueOf(uri);
        Picasso.with(context)
                .load(finalurl)
                .error(R.drawable.tillerharvester)      // optional
                .into(machineImage);


        return convertView;
    }

    private void initViews(View convertView) {
        id=(TextView)convertView.findViewById(R.id.booking_status_id);
        location=(TextView)convertView.findViewById(R.id.booking_staus_location);
        owner_name=(TextView)convertView.findViewById(R.id.booking_staus_owner_name);
        owner_phone=(TextView)convertView.findViewById(R.id.booking_staus_owner_phone);
        machine_name=(TextView)convertView.findViewById(R.id.booking_staus_machine_name);
        booking_status=(TextView)convertView.findViewById(R.id.booking_staus_booking_status);
        cancel_button=(Button)convertView.findViewById(R.id.status_cancel_button);
        machineImage=(ImageView)convertView.findViewById(R.id.booking_staus_machine_image);
        booking_date=(TextView)convertView.findViewById(R.id.booking_staus_arrival_date);
        arrival_time=(TextView)convertView.findViewById(R.id.booking_staus_arrival_time);

    }

    private void initListeners(final int position) {
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"ID : " + position,Toast.LENGTH_SHORT).show();

                showAlert(position);
            }
        });
    }

    private void cancelBooking(int position) {
        loading = ProgressDialog.show(context,"Please wait...","Cancelling Booking...",false,false);
        String url = "http://annamagrotech.com/webservice/farmer/cancelbookingfromstatus.php?"
                + "booking_id=" + booking_status_items_model.get(position).getBooking_id();
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
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getString("errorcode").equals("success")){
                Toast.makeText(context,"Booking Cancelled",Toast.LENGTH_SHORT).show();

                /*
                Fragment fragment = new BookNow_Confirm();
                Utilities.getInstance(context).changeHomeFragment(
                        fragment, "BookNow_Confirm", context);
                */

                Fragment frg = null;
                frg = context.getSupportFragmentManager().findFragmentByTag("Booking_Status_Fragment");
                final FragmentTransaction ft = context.getSupportFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();

            } else {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
                Log.e("MyLog",response);
            }

        } catch (Exception e){
            Log.e("MyLog","Exception in showJSON of Booking Status Adaptor");
        }
    }

    public void showAlert(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Do you want to cancel your booking ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                cancelBooking(position);
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
}
