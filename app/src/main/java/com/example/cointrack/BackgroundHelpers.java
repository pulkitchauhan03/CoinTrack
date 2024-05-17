package com.example.cointrack;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.cointrack.models.Bank;
import com.example.cointrack.models.PrimaryTag;
import com.example.cointrack.models.Transaction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundHelpers {
    public static void addPrimaryTag(Context context, CoinTrackDatabase coinDB, PrimaryTag tag) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                coinDB.getPrimaryTagDAO().addPrimaryTag(tag);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Added to Database", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public static void addTransaction(Context context, CoinTrackDatabase coinDB, Transaction transaction) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                coinDB.getTransactionDAO().addTransaction(transaction);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Added Transaction to Database", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public static void addBank(Context context, CoinTrackDatabase coinDB, Bank bank) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                coinDB.getBankDAO().addBank(bank);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Added Bank to Database", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
