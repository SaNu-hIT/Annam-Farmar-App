package com.sft.annam.Interfaces;

/**
 * Created by JESNA on 22-Feb-16.
 */
public interface OnHtttpResponseListenerForLogin {

    /**
     * Response while successful completion of Login
     *
     */
    void onHttpSuccessfulResponseLogin(String responseBody, boolean responseStatus,
                                       String responseResultMsg, String sessionId, int userId);

    /**
     * Response while failure completion of Login
     *
     */
    void onHttpFailedResponseLogin(Throwable throwable, String responseBody,
                                   boolean resposeStatus, String responseResultMessage);
}
