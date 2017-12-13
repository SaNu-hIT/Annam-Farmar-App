package com.sft.annam.Interfaces;

/**
 * Created by JESNA on 8/4/2016.
 */
public interface OnHttpResponseForForgotPassword {

    void onHttpSuccessfulForForgotPassword(String responseBody, boolean responseStatus,
                                           String responseResultMsg, String customerId);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedForForgotPassword(Throwable throwable, String responseBody,
                                       boolean resposeStatus, String responseResultMessage);
}
