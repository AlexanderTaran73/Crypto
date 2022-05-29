package com.example.cryptocurrencies.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class NotificationsStart extends AppCompatActivity {



    String[] arr = new String[50];
    String[] type_arr = new String[]{"Time", "Price"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_start);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CryptoRequestManager manager = new CryptoRequestManager(NotificationsStart.this);
                manager.getCryptoHeadlines(crypto_listener, "usd", "", "market_cap_desc", 50, "1h,24h,7d");
            }
        });
        arr[0]="BTC";
        ArrayAdapter<String> cryptoSelectionAdapter = new ArrayAdapter<String>(NotificationsStart.this, android.R.layout.simple_spinner_item, arr);
        cryptoSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spCryptoSelection = findViewById(R.id.crypto_selection);
        spCryptoSelection.setAdapter(cryptoSelectionAdapter);

        ArrayAdapter<String> typeSelectionAdapter = new ArrayAdapter<String>(NotificationsStart.this, android.R.layout.simple_spinner_item, type_arr);
        cryptoSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spTypeSelection = findViewById(R.id.type_selection);
        spTypeSelection.setAdapter(typeSelectionAdapter);
    }


    private final CryptoOnFetchDataListener crypto_listener = new CryptoOnFetchDataListener() {
        @Override
        public void onFetchData(List<CryptoHeadlines> list, String message) {
            int counter = 0;
            if (list.isEmpty()){
                Toast.makeText(NotificationsStart.this, "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else { for (CryptoHeadlines i : list) {
                arr[counter]=i.getSymbol().toUpperCase(Locale.ROOT);
                counter++;
            }
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(NotificationsStart.this, "An Error Occured!!!", Toast.LENGTH_SHORT).show();

        }
    };
}