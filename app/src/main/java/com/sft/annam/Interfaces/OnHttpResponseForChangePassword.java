package com.sft.annam.Interfaces;

/**
 * Created by JESNA on 8/4/2016.
 */
public interface OnHttpResponseForChangePassword {
    void onHttpSuccessfulForChangePassword(String responseBody, boolean responseStatus,
                                           String responseResultMsg, String customerId);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedForChangePassword(Throwable throwable, String responseBody,
                                       boolean resposeStatus, String responseResultMessage);
}
