package com.sft.annam.Interfaces;

/**
 * Created by JOSMY K J on 21-Jul-16.
 */
public interface OnHttpReponseForSbmit_LAterBooking {


    void onHttpSuccessfulReponseForSbmit_LAterBooking(String responseBody, boolean responseStatus,
                                                      String responseResultMsg, String bookingid);


    void onHttpFailedReponseForSbmit_LAterBooking(Throwable throwable, String responseBody,
                                                  boolean resposeStatus, String responseResultMessage);
}

