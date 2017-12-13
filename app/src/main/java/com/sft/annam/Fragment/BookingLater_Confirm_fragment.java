package com.sft.annam.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.sft.annam.MapsActivity;
import com.sft.annam.Model.Book_Confirm_Constant;
import com.sft.annam.Model.Const_Model;
import com.sft.annam.Model.Constants;
import com.sft.annam.R;
import com.sft.annam.TextView_Lato;
import com.sft.annam.Utilities.Utilities;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by JESNA on 7/13/2016.
 */
public class BookingLater_Confirm_fragment extends Fragment {
    Button back_button;
    String bookingid;

    TextView_Lato machine_name, location, bookingID, arrivalDate, arrivalTime, machine_des;
    ImageView machineImage;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.bookinglater_confirm,container,false);
        initViews(rootView);
        Bundle bundle = this.getArguments();
        if(bundle !=null) {
            bookingid = bundle.getString("booking_id");
        }
        setData();
        initListeners();
        getActivity().setTitle("Confirmation");
       return rootView;
    }

    private void setData() {
        location.setText(Book_Confirm_Constant.location);
        arrivalDate.setText(Constants.getSelectedDate());
        arrivalTime.setText(Constants.getSelectedTime());
        bookingID.setText(bookingid);
        String machineFullName = Book_Confirm_Constant.machineTypeName + " " + Book_Confirm_Constant.machineName;
        machine_name.setText(machineFullName);
        String description = "Description : " + Const_Model.getMachine_details();
        machine_des.setText(description);
        String strMachineImageURL=Book_Confirm_Constant.machineImageURL;



        URI uri = null;
        try {
            uri = new URI(strMachineImageURL.replaceAll(" ", "%20"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String imageURL = String.valueOf(uri);

        Picasso.with(getActivity())
                .load(imageURL)
                .error(R.drawable.tillerharvester)      // optional
                .into(machineImage);
    }

    private void initListeners() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void initViews(View rootView) {
        back_button=(Button)rootView.findViewById(R.id.btn_back_button);
        bookingID=(TextView_Lato) rootView.findViewById(R.id.booking_id);
        machine_name=(TextView_Lato) rootView.findViewById(R.id.machine_name);
        location=(TextView_Lato) rootView.findViewById(R.id.location);
        machineImage=(ImageView)rootView.findViewById(R.id.machine_image);
        arrivalDate=(TextView_Lato)rootView.findViewById(R.id.arrival_date);
        arrivalTime=(TextView_Lato)rootView.findViewById(R.id.arrival_time);
        machine_des=(TextView_Lato)rootView.findViewById(R.id.machine_des);
    }

    public static class PropDialogFragment extends DialogFragment {

        public PropDialogFragment() { /*empty*/ }

        /** creates a new instance of PropDialogFragment */
        public static PropDialogFragment newInstance() {
            return new PropDialogFragment();
        }

        Button b;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //getting proper access to LayoutInflater is the trick. getLayoutInflater is a                   //Function
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.popup, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(view);
            //builder.setTitle(getActivity().getString(R.string.title_activity_maps)).setNeutralButton(getActivity().getString(R.string.okay), null);
            b = (Button)view.findViewById(R.id.popup_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PropDialogFragment.this.dismiss();
                    Fragment  fragment = new BookNow_Fragment();
                    Utilities.getInstance(getActivity()).changeHomeFragment(
                            fragment, "MapsActivity", getActivity());
                }
            });
            return builder.create();
        }
    }

    private void showDialog() {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = PropDialogFragment.newInstance();
        newFragment.setCancelable(false);
        //newFragment.getDialog().setCanceledOnTouchOutside(false);
        newFragment.show(ft, "dialog");
    }
}
