package com.mingrisoft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    private static final String action = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action)) {
            Toast.makeText(context, context.getResources().getString(R.string.message), Toast.LENGTH_LONG).show();
        }
    }
}
