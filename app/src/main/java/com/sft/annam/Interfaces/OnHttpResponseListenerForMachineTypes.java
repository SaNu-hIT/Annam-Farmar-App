package com.sft.annam.Interfaces;





import com.sft.annam.Model.Krishibhavan_Model;
import com.sft.annam.Model.Machine_types_model;

import java.util.ArrayList;

/**
 * Created by JESNA on 7/23/2016.
 */
public interface OnHttpResponseListenerForMachineTypes {
    void onHttpSuccessfulResponseMachineTypes(String responseBody, boolean responseStatus,
                                              String responseResultMsg,
                                              ArrayList<Machine_types_model> machine_types_model,ArrayList<Krishibhavan_Model> krishibhavan_models);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseMachineTypes(Throwable throwable, String responseBody,
                                          boolean resposeStatus, String responseResultMessage);
}
