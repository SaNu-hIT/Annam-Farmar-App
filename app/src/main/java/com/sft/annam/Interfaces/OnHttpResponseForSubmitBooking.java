package com.sft.annam.Interfaces;

/**
 * Created by JESNA on 7/19/2016.
 */
public interface OnHttpResponseForSubmitBooking {

    /**
     * Response while sucess completion of Signup
     *
     */

    void onHttpSuccessfulResponseSubmitbooking(String responseBody, boolean responseStatus,
                                               String responseResultMsg, String bookingid);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseSubmitbooking(Throwable throwable, String responseBody,
                                           boolean resposeStatus, String responseResultMessage);
}
