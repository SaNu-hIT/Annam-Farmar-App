package com.sft.annam.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.sft.annam.Adapter.MachineCategoriesAdapter;
import com.sft.annam.Connection.HttpRequestHelperForMachineListForBooking;
import com.sft.annam.DataBaseHelper.DataBaseHelperFor_location;
import com.sft.annam.DataBaseHelper.DatabaseHelperFor_Book_Later;
import com.sft.annam.Model.Book_Confirm_Constant;
import com.sft.annam.Model.Book_Later_Const;
import com.sft.annam.Model.Book_Later_Model;
import com.sft.annam.Model.Const_Model;
import com.sft.annam.Model.FetchId;
import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.MachineCategories;
import com.sft.annam.Model.Machine_types_model;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

/**
 * Created by JESNA on 7/13/2016.
 */
public class BookLater_Fragment extends Fragment implements MachineCategoriesAdapter.ItemClickCallback {

    private static final String SHOWCASE_ID = "Booklater";
    private MachineCategoriesAdapter adapter;
    private List<MachineCategories> machineCategoriesList;
    Spinner krishibhavan_spinner;
    String machine_type,machine_id;
    String krishibhavan_type,krishibhavan_id,location;
    EditText edt_land_area_boklater;
    TextView txtFromDate,txttoDate,myLocation,titleFDate,titleTDate;
    Location_MOdel location_mOdel;
    String  latLongTV,longtude;
    ArrayList<Machine_types_model> machine_types_models_array = new ArrayList<Machine_types_model>();
    ArrayList<Krishibhavan_Model>krishibhvan_models_array = new ArrayList<Krishibhavan_Model>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final RecyclerView recyclerView;
        View rootView=inflater.inflate(R.layout.book_later_fragment,container,false);
        myLocation=(TextView)rootView.findViewById(R.id.myLocation);
        location_mOdel= DataBaseHelperFor_location.getProfileOffline(getActivity());
        String place=location_mOdel.getPlace();
        String state=location_mOdel.getState();
        String contry=location_mOdel.getCountry();
        latLongTV=location_mOdel.getLatitude();
        longtude=location_mOdel.getLongitude();
        location=place+","+state+","+contry;
        myLocation.setText(location);
        Book_Confirm_Constant.location = location;
        Bundle machinype_bundle = getArguments();

        if (machinype_bundle != null) {
            machine_types_models_array = machinype_bundle.getParcelableArrayList("MachineTypeId");
            krishibhvan_models_array = machinype_bundle.getParcelableArrayList("krishibhavan_book_later");

            if (!machine_types_models_array.isEmpty()) {


                initViews(rootView);
                initListeners();

                buildUI(rootView);
            }
        } else {
//            Toast.makeText(getActivity(), "gfhdsf", Toast.LENGTH_SHORT).show();
        }




        initViews(rootView);
        initListeners();
        getActivity().setTitle("Book Later");

        //MaterialShowcaseView.resetSingleUse(getActivity(), SHOWCASE_ID);
        presentShowcaseSequence(); // one second delay

        // -------------- Added ---------------------
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        machineCategoriesList = new ArrayList<>();
        adapter = new MachineCategoriesAdapter(getActivity(), machineCategoriesList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

        prepareMachineCategoryCards();
        // -------------- End ---------------------

        return rootView;
    }

    private void presentShowcaseSequence() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), SHOWCASE_ID);

        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                // Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        sequence.setConfig(config);

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(krishibhavan_spinner)
                        .setContentText("Select your krishibhavan")
                        .setDismissText("GOT IT")
                        .withRectangleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(titleFDate)
                        .setContentText("Select the date from which you require the service")
                        .setDismissText("GOT IT")
                        .withRectangleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(titleTDate)
                        .setContentText("Select the date till which you require the service")
                        .setDismissText("GOT IT")
                        .withRectangleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(edt_land_area_boklater)
                        .setContentText("Enter total land area")
                        .setDismissText("GOT IT")
                        .withRectangleShape(true)
                        .build()
        );

        sequence.start();
    }

    private void buildUI(View rootView) {

        final ArrayList<String> machine_type_array = new ArrayList<String>();
        final ArrayList<String> machine_type_array_id = new ArrayList<String>();
        final ArrayList<String> krishi_type_array = new ArrayList<String>();
        final ArrayList<String> krishi_type_array_id = new ArrayList<String>();




        if (!machine_types_models_array.isEmpty() &&!krishibhvan_models_array.isEmpty() ) {


            if (machine_types_models_array != null && krishibhvan_models_array !=null) {

                machine_type_array.add("Select Machine Type");
                machine_type_array_id.add("0");
                krishi_type_array.add("Select Krishibavan");
                krishi_type_array_id.add("0");




                for (int i = 0; i < machine_types_models_array.size(); i++) {
                    machine_type_array.add(machine_types_models_array.get(i).getMachine_name());

                    machine_type_array_id.add(machine_types_models_array.get(i).getMachine_type_id());
                }
                for (int i = 0; i < krishibhvan_models_array.size(); i++) {
                    krishi_type_array.add(krishibhvan_models_array.get(i).getKrishi_name());

                    krishi_type_array_id.add(krishibhvan_models_array.get(i).getKrishi_id());
                }
                krishibhavan_spinner.setAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_expandable_list_item_1, krishi_type_array));


            }
        }

        krishibhavan_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    krishibhavan_type= krishibhvan_models_array.get(position-1).getKrishi_name().toString();
                    krishibhavan_id=krishibhvan_models_array.get(position-1).getKrishi_id().toString();

                    Log.d("",krishibhavan_id);



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    private void initListeners() {


        txtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");


            }
        });

        txttoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new SelectToDateFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
            }
        });
    }


    private void initViews(View rootView) {

        edt_land_area_boklater=(EditText)rootView.findViewById(R.id.edt_land_area_bk_later) ;
        txtFromDate=(TextView)rootView.findViewById(R.id.txt_fromdate);
        titleFDate=(TextView)rootView.findViewById(R.id.textView4);
        titleTDate=(TextView)rootView.findViewById(R.id.textView9);
        txttoDate=(TextView)rootView.findViewById(R.id.text_todate);
        krishibhavan_spinner=(Spinner)rootView.findViewById(R.id.krishi_spinner);

    }

    private boolean validate() {
        boolean flag = true;
        boolean flagToast = false;
        View focusView = null;
        String area = edt_land_area_boklater.getText().toString().trim();
        String krishibhavan = krishibhavan_id;
        String fromDate = txtFromDate.getText().toString().trim();
        String toDate = txttoDate.getText().toString().trim();

        if (TextUtils.isEmpty(area)) {
            edt_land_area_boklater.setError(getString(R.string.error_field_required));
            focusView = edt_land_area_boklater;
            flag = false;
        }
        if (fromDate.equals("DD/MM/YYYY")) {
            txtFromDate.setError(getString(R.string.error_field_required));
            focusView = txtFromDate;
            flag = false;
        }
        if (toDate.equals("DD/MM/YYYY")) {
            txttoDate.setError(getString(R.string.error_field_required));
            focusView = txttoDate;
            flag = false;
        }
        if (krishibhavan == null) {
            Toast.makeText(getActivity(),"Please select Krishibhavan",Toast.LENGTH_SHORT).show();
            flagToast = true;
        }

        if (!flag) {
            focusView.requestFocus();
        }

        if(!flag || flagToast){
            return false;
        } else {
            return true;
        }
    }


    @SuppressLint("ValidFragment")
    private class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 345600000);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }
        public void populateSetDate(int year, int month, int day) {
            txtFromDate.setText(day+"/"+month+"/"+year);
        }

    }

    @SuppressLint("ValidFragment")
    private class SelectToDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + 345600000);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetToDate(yy, mm+1, dd);
        }
        public void populateSetToDate(int year, int month, int day) {

            txttoDate.setText(day+"/"+month+"/"+year);

        }

    }

    //---------------------Added------------------------
    /**
     * Adding few albums for testing
     */
    private void prepareMachineCategoryCards() {
        int totalMachineTypes = machine_types_models_array.size();
        MachineCategories a;
        for (int i=0;i<totalMachineTypes;i++){
            a = new MachineCategories(machine_types_models_array.get(i).getMachine_name(),machine_types_models_array.get(i).getImage());
            machineCategoriesList.add(a);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMachineClick(View view, int position) {

        machine_type = machine_types_models_array.get(position).getMachine_name();
        machine_id = machine_types_models_array.get(position).getMachine_type_id();

        validateNMovetoMachineSelect();
    }

    private void validateNMovetoMachineSelect() {
        if (validate()) {
            Const_Model.booklaterFlag = true;
            Book_Later_Model model=new Book_Later_Model(myLocation.getText().toString(),
                    latLongTV,longtude,
                    txtFromDate.getText().toString(),txttoDate.getText().toString(),
                    edt_land_area_boklater.getText().toString(),machine_type,krishibhavan_type);
            DatabaseHelperFor_Book_Later.add_booklater(getActivity(),model);

            Book_Later_Const.setConsBookLaterModel(model);

            FetchId fetchId = Utilities.getInstance(getActivity()).fetchId();
            String fechid = fetchId.getUser_id();
            if (Utilities.getInstance(getActivity()).isNetAvailable()) {

                HttpRequestHelperForMachineListForBooking httptomachinelist = new HttpRequestHelperForMachineListForBooking();
                httptomachinelist.machinetypesforlistforBooking(getActivity(), fechid, machine_id);
            }
            else{
                Toast.makeText(getActivity(),"network is not available",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount)
                { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            }
            else
            {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount)
                {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    //---------------------END--------------------------

}
