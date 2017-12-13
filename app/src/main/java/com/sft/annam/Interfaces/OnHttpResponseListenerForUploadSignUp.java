package com.sft.annam.Interfaces;


import com.sft.annam.Model.Crops_type_Model;
import com.sft.annam.Model.Farmer_type;
import com.sft.annam.Model.Panchayath_Model;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/15/2016.
 */
public interface OnHttpResponseListenerForUploadSignUp {
    void onHttpSuccessfulResponseOnloadSignup(String responseBody, boolean responseStatus,
                                              String responseResultMsg,
                                              ArrayList<Farmer_type> farmer_type,
                                              ArrayList<Crops_type_Model> crops_types,
                                              ArrayList<Panchayath_Model> panchayath_models);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseForOnloadLogin(Throwable throwable, String responseBody,
                                            boolean resposeStatus, String responseResultMessage);

}
