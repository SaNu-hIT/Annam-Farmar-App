package com.sft.annam.Interfaces;




import com.sft.annam.Model.Profile_View_Model;

import java.util.ArrayList;

/**
 * Created by JOSMY K J on 20-Jul-16.
 */
public interface OnHttpResponseForProfile {

    void onHttpSuccessfulResponseProfile(String responseBody, boolean responseStatus,
                                         String responseResultMsg, ArrayList<Profile_View_Model> profile_models);

    /**
     * Response while failure completion of Signup
     *
     */
    void onHttpFailedResponseProfile(Throwable throwable, String responseBody,
                                     boolean resposeStatus, String responseResultMessage);
}
