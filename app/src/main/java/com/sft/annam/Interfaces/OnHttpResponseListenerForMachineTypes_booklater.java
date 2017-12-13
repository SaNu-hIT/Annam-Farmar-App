package com.sft.annam.Interfaces;


import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.Machine_types_model;

import java.util.ArrayList;

/**
 * Created by SFT on 8/1/2016.
 */
public interface OnHttpResponseListenerForMachineTypes_booklater {
    void onHttpSuccessfulResponseMachineTypes_booklater(String responseBody, boolean responseStatus,
                                                        String responseResultMsg,
                                                        ArrayList<Machine_types_model> machine_types_model,
                                                        ArrayList<Krishibhavan_Model> krishibhavan_modelsl);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseMachineTypes_booklater(Throwable throwable, String responseBody,
                                                    boolean resposeStatus, String responseResultMessage);
}
