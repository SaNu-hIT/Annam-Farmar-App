package com.sft.annam.Interfaces;

/**
 * Created by JESNA on 7/19/2016.
 */
public interface OnHttpResponseForSignup {

    /**
     * Response while sucess completion of Signup
     *
     */

    void onHttpSuccessfulResponseSignup(String responseBody, boolean responseStatus,
                                        String responseResultMsg, String customerId);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseSignup(Throwable throwable, String responseBody,
                                    boolean resposeStatus, String responseResultMessage);
}
