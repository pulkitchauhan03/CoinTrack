package com.example.cointrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class SMSBroadcast extends BroadcastReceiver {
    private static String SMS = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(SMS)) {
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    for (int i = 0; i < Objects.requireNonNull(pdusObj).length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], "3gpp");

                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
                        toast.show();

                    }
                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);
            }
        }
    }
}
