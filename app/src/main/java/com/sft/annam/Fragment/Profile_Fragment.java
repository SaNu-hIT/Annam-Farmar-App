package com.sft.annam.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sft.annam.Connection.HttpRequestHelperProfile;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Profile_View_Model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;
import java.util.ArrayList;

/**
 * Created by JOSMY K J on 15-Jul-16.
 */
public class Profile_Fragment extends Fragment {
    TextView profile_name,profile_address,profile_phone,profile_mail,profile_location;
    Button profile_save,profile_cancel;
    ArrayList<Profile_View_Model>profile_fetch_models=new ArrayList<Profile_View_Model>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View profileView=inflater.inflate(R.layout.profile_fragment,container,false);
        // initViews(profileView);
        fetchProfileDetails();

        Bundle profileview_bundle = getArguments();
        if (profileview_bundle != null) {
            profile_fetch_models = profileview_bundle.getParcelableArrayList("profileview");


            if (!profile_fetch_models.isEmpty()) {

                /*initializeListnersForToolbar(toolbar);*/
                initViews(profileView);
                initLisenewrs();

                buildUI(profileView);
            }
        } else {
            Toast.makeText(getActivity(), "gfhdsf", Toast.LENGTH_SHORT).show();
        }


         initLisenewrs();
        getActivity().setTitle("Profile");
        return profileView;

    }

    private void buildUI(View profileView) {



        profile_name.setText(profile_fetch_models.get(0).getFarmer_name());
        profile_address.setText(profile_fetch_models.get(0).getFarmer_address());
        profile_mail.setText(profile_fetch_models.get(0).getFarmer_email());
        profile_location.setText(profile_fetch_models.get(0).getFarmer_pachayath_loc());
        profile_address.setText(profile_fetch_models.get(0).getFarmer_address());
        profile_phone.setText(profile_fetch_models.get(0).getFarmer_phone());


    }

    private void fetchProfileDetails() {
        if(Utilities.getInstance(getActivity()).isNetAvailable()){
            FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
            String fechid=fetchId.getUser_id();
            //Log.e(">>>",fechid);
            HttpRequestHelperProfile.profileview(getActivity(),fechid);

    }else{

            Toast.makeText(getActivity(),"No network connection",Toast.LENGTH_LONG).show();


    }
    }

    private void initLisenewrs() {

    }



    private  boolean validateprofilefilesEnterd() {
        String str_profile_name = profile_name.getText().toString();
        String str_profile_address = profile_address.getText().toString();
        String str_profile_phone = profile_phone.getText().toString();
        String str_profile_mail = profile_mail.getText().toString();
        String str_profile_location = profile_location.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(str_profile_name)) {
            profile_name.setError(getString(R.string.error_field_required));
            focusView = profile_name;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_profile_address)) {
            profile_address.setError(getString(R.string.error_field_required));
            focusView = profile_address;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_profile_phone)) {
            profile_phone.setError(getString(R.string.error_field_required));
            focusView = profile_phone;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_profile_mail)) {
            profile_mail.setError(getString(R.string.error_field_required));
            focusView = profile_mail;
            cancel = true;
        }
        if (TextUtils.isEmpty(str_profile_location)) {
            profile_location.setError(getString(R.string.error_field_required));
            focusView = profile_location;
            cancel = true;
        }
        if (cancel) {

            focusView.requestFocus();
        } else {

            return cancel;
        }
        return cancel;
    }



   private void initViews(View profileView){
       profile_name=(TextView)profileView.findViewById(R.id.textProfileName);
       profile_address=(TextView)profileView.findViewById(R.id.textProfileAddress);
       profile_phone=(TextView)profileView.findViewById(R.id.textProfilePhone);
       profile_mail=(TextView)profileView.findViewById(R.id.textProfileMail);
       profile_location=(TextView)profileView.findViewById(R.id.textProfileLocation);
       profile_save=(Button)profileView.findViewById(R.id.buttonProfileSave);
       profile_cancel=(Button)profileView.findViewById(R.id.buttonresetedit);


   }
}




