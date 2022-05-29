package com.example.cryptocurrencies.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptocurrencies.App;
import com.example.cryptocurrencies.Models.AppDatabase;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.Models.NotificationsItem;
import com.example.cryptocurrencies.Models.NotificationsItemDao;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class NotificationsStart extends AppCompatActivity {

    Button button;
    EditText edit_hours, edit_minutes;
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


        edit_hours = findViewById(R.id.edittext_hour);
        edit_minutes = findViewById(R.id.edittext_minute);



        button = findViewById(R.id.notifications_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symbol = spCryptoSelection.getSelectedItem().toString(),
                        type = spTypeSelection.getSelectedItem().toString();

                try {
                    int hours = Integer.parseInt(edit_hours.getText().toString());
                    int minutes = Integer.parseInt(edit_minutes.getText().toString());

                    if (hours>23 || minutes>59){ Toast.makeText(getApplicationContext(), "Wrong time!", Toast.LENGTH_SHORT).show();}
                    else {
                        String time = hours+":"+minutes;
                        AppDatabase db = App.getInstance().getDatabase();
                        NotificationsItemDao notificationsItemDao = db.notificationsItemDao();

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                notificationsItemDao.insert(new NotificationsItem(symbol, time, null, type));
                            }
                        });

                }}
                catch (Exception e){ Toast.makeText(getApplicationContext(), "Wrong time!", Toast.LENGTH_SHORT).show();}

            }
        });
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