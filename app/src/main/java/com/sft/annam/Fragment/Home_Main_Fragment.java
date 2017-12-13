package com.sft.annam.Fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sft.annam.Connection.HttpRequestHelperForMachine_types;
import com.sft.annam.Connection.HttpRequestHelperForMachine_types_book_later;
import com.sft.annam.R;
import com.sft.annam.Utilities.Utilities;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


/**
 * Created by JESNA on 7/12/2016.
 */
public class Home_Main_Fragment extends Fragment {
    Button home_booknow,home_booklater,home_machineries;
    Context contxt;

    private static final String SHOWCASE_ID = "Home";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView=inflater.inflate(R.layout.home_main_fragment,container,false);
        initViews(rootView);
        initListeners();
        getActivity().setTitle("Home");
        //MaterialShowcaseView.resetSingleUse(getActivity(), SHOWCASE_ID);
        presentShowcaseSequence(); // one second delay
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

        sequence.addSequenceItem(home_booknow, "Use Book Now if you want to book for less than 100 hours", "GOT IT");

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(home_booklater)
                        .setDismissText("GOT IT")
                        .setContentText("Use Book Later if you want to book for more than 100 hours")
                        .withRectangleShape(true)
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(getActivity())
                        .setTarget(home_machineries)
                        .setDismissText("GOT IT")
                        .setContentText("Machineries will list out all available machines")
                        .withRectangleShape()
                        .build()
        );

        sequence.start();
    }

    private void initListeners() {
        home_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utilities.getInstance(getActivity()).isNetAvailable()){
                    HttpRequestHelperForMachine_types machine_types=new HttpRequestHelperForMachine_types();
                    machine_types.machine_types(getActivity());

               /* Fragment fragment = new BookNow_Fragment();
                Utilities.getInstance(getActivity()).changeHomeFragment(
                        fragment, "BookNow_Fragment", getActivity());*/
                }
                else{
                    Toast.makeText(getActivity(),"network is not available",Toast.LENGTH_SHORT).show();
                }
            }
        });

        home_booklater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utilities.getInstance(getActivity()).isNetAvailable()) {

                    HttpRequestHelperForMachine_types_book_later machine_types_later = new HttpRequestHelperForMachine_types_book_later();
                    machine_types_later.machine_types_booklater(getActivity());
                } else {
                    Toast.makeText(getActivity(), "network is not available", Toast.LENGTH_SHORT).show();
                }

            }
        });
        home_machineries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Machineries_Fragment();
                Utilities.getInstance(getActivity()).changeHomeFragment(
                        fragment, "Machineries_Fragment", getActivity());
            }
        });
    }

    private void initViews(View rootView) {
        home_booknow=(Button)rootView.findViewById(R.id.btn_home_booknow);
        home_booklater=(Button)rootView.findViewById(R.id.btn_home_booklater);
        home_machineries=(Button)rootView.findViewById(R.id.btn_home_machineries);

    }
}
