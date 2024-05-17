package com.example.cointrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cointrack.models.Bank;
import com.example.cointrack.models.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                    ExecutorService executorService = Executors.newSingleThreadExecutor();

                    Handler handler = new Handler(Looper.getMainLooper());

//                    String timestamp = java.time.LocalDateTime.now().toString();
//                    long trBankId = 1;
//                    double amount = 200.0;
//                    String type = "Debit";
//                    long trPrimaryTagId = 1;

                    String finalSenderNum = senderNum;
                    String finalMessage = message;
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            List<Bank> bankList = coinDB.getBankDAO().getAllBank();
                            Bank targetBank = null;
                            for(Bank bank : bankList) {
                                if(Pattern.matches(bank.getMsgSender(), finalSenderNum)) {
                                    targetBank = bank;
                                }
                            }

                            if(targetBank == null) return;
                            double amount = 0.0;

                            Pattern regex = Pattern.compile(targetBank.getRegex());
                            Matcher messageMatcher = regex.matcher(finalMessage);
                            while(messageMatcher.find()) {
                                amount = Double.parseDouble(messageMatcher.group("amt"));
                            }

                            String timestamp = java.time.LocalDateTime.now().toString();
                            long trBankId = targetBank.getBankId();
                            String type = "Debit";
                            long trPrimaryTagId = 1;

                            coinDB.getTransactionDAO().addTransaction(new Transaction(finalMessage, finalSenderNum, timestamp, trBankId, amount, type, trPrimaryTagId));

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Added Bank to Database", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" +e);
            }
        }
    }
}
