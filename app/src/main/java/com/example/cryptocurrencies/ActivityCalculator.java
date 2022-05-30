package com.example.cryptocurrencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class ActivityCalculator extends AppCompatActivity {
    Button Calculat_btn;

    EditText Edittext_entry_price,
            Edittext_exit_price,
            Edittext_amount;

    TextView TextView_PNL,
            TextView_ROE;
    Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Edittext_entry_price = findViewById(R.id.edittext_entry_price);
        Edittext_exit_price = findViewById(R.id.edittext_exit_price);
        Edittext_amount = findViewById(R.id.edittext_amount);
        TextView_PNL = findViewById(R.id.textview_PNL);
        TextView_ROE = findViewById(R.id.textview_ROE);

        Calculat_btn = findViewById(R.id.calculat_btn);

        if (getIntent().getSerializableExtra("data")!=null){
            price = (Double) getIntent().getSerializableExtra("data");
        }
        else price = Double.valueOf(0);
        Edittext_entry_price.setText(price.toString());

        View.OnClickListener Calculat_lst = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Edittext_entry_price_str = Edittext_entry_price.getText().toString(),
                        Edittext_exit_price_str = Edittext_exit_price.getText().toString(),
                        Edittext_amount_str = Edittext_amount.getText().toString();



                if (Edittext_entry_price_str.equals("")||
                        Edittext_entry_price_str.equals("0")||
                        Edittext_exit_price_str.equals("")||
                        Edittext_exit_price_str.equals("0")||
                        Edittext_amount_str.equals("")||
                        Edittext_amount_str.equals("0")){

                    Toast.makeText(getApplicationContext(), "Not enough data!", Toast.LENGTH_SHORT).show();
                }
                else{

                double entry_price = Double.parseDouble(Edittext_entry_price_str),
                        exit_price = Double.parseDouble(Edittext_exit_price_str),
                        amount = Double.parseDouble(Edittext_amount_str);

                String PNL = Double.toString((exit_price-entry_price)*amount), ROE = (Double.toString(Math.round((exit_price-entry_price)/entry_price*100)))+"%";
                    TextView_PNL.setText(PNL);
                    TextView_ROE.setText(ROE);
                }
            }
        };
        Calculat_btn.setOnClickListener(Calculat_lst);

    }

    private static void createChanelIfNeedIt(NotificationManager notificationManager){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("1", "1", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}