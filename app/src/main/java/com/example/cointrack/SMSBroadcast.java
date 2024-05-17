package com.example.cointrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Objects;

public class SMSBroadcast extends BroadcastReceiver {
    private static String SMS = "android.provider.Telephony.SMS_RECEIVED";
    CoinTrackDatabase coinDB;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(SMS)) {
            final Bundle bundle = intent.getExtras();
            RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }

                @Override
                public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                    super.onDestructiveMigration(db);
                }
            };

            coinDB = Room.databaseBuilder(context, CoinTrackDatabase.class, "CoinDB")
                    .addCallback(myCallBack).build();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");

                    String senderNum = "";
                    String message = "";

                    for (int i = 0; i < Objects.requireNonNull(pdusObj).length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], "3gpp");

                        senderNum = currentMessage.getDisplayOriginatingAddress();
                        message = message.concat(currentMessage.getDisplayMessageBody());
                    }

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();

                    String timestamp = "DD-MM-YYYY HH:mm:ss";
                    String bank = "IOB";
                    double amount = 200.0;
                    String type = "Debit";
                    long trPrimaryTagId = 1;

                    Transaction t1 = new Transaction(message, senderNum, timestamp, bank, amount, type, trPrimaryTagId);

                    BackgroundHelpers.addTransaction(context, coinDB, t1);
                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);
            }
        }
    }
}
