package com.sft.annam.Interfaces;


import com.sft.annam.Model.Machineries_Model;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public interface OnHttpResponseForMachineView {
    void onHttpSuccessfulResponseMachineView(String responseBody, boolean responseStatus,
                                             String responseResultMsg, ArrayList<Machineries_Model> machineries_model);



    void onHttpFailedResponseMachineView(Throwable throwable, String responseBody,
                                         boolean resposeStatus, String responseResultMessage);
}
