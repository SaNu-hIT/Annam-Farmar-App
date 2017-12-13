package com.sft.annam.Connection;

import android.support.v4.app.FragmentActivity;

import com.sft.annam.Interfaces.OnHttpResponceForKarshgasenaBooking;

/**
 * Created by intellyelabs on 29/03/17.
 */

public class HttprequestForKarshgasenaClick {

   public void KarshgasenaClick(final FragmentActivity activity)
    {
        OnHttpResponceForKarshgasenaBooking onHttpResponceForKarshgasenaBooking= (OnHttpResponceForKarshgasenaBooking) activity;
onHttpResponceForKarshgasenaBooking.KarshgasenaSuccess();
    }
}
