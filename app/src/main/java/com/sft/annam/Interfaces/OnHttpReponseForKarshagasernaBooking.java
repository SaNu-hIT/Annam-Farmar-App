package com.sft.annam.Interfaces;

/**
 * Created by JOSMY K J on 21-Jul-16.
 */
public interface OnHttpReponseForKarshagasernaBooking {


    void onHttpSuccessfulReponseForKarshagasernaBooking(String responseBody,
                                                      String responseResultMsg, String bookingid);


    void onHttpFailedReponseForKarshagasernaBooking(Throwable throwable, String responseResultMessage);
    void onHttpFailedReponseForKarshagasernaBooking(String responseResultMessage);
}

