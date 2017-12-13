package com.sft.annam.Interfaces;




import com.sft.annam.Model.MachineListModel_Booking;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public interface OnHttpResponseForMachineListBooking {
    void onHttpSuccessfulResponseMachineListBooking(String responseBody, boolean responseStatus,
                                                    String responseResultMsg, ArrayList<MachineListModel_Booking> machineries_model);



    void onHttpFailedResponseMachineListBooking(Throwable throwable, String responseBody,
                                                boolean resposeStatus, String responseResultMessage);
}
