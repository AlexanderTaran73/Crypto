package com.example.cryptocurrencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class CryptoDetailsActivity extends AppCompatActivity{
    CryptoHeadlines headlines;
    ImageView icon;
    TextView symbol,
            name,
            volume_24h_txt,
            price,
            change,
            max_24h,
            min_24h,
            volume_24h,
            volume_24h_USD;

    LineChart lineChart;
    SwitchCompat switchCompat;
    Button button;

    ImageButton calculator_btn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_details);

        icon = findViewById(R.id.details_cryptocurrencies_img);
        symbol = findViewById(R.id.details_cryptocurrencies_symbol);
        name = findViewById(R.id.details_cryptocurrencies_name);

        volume_24h_txt = findViewById(R.id.volume_24h_txt);
        price = findViewById(R.id.price);
        change = findViewById(R.id.change_24h);
        max_24h = findViewById(R.id.max_24h);
        min_24h = findViewById(R.id.min_24h);
        volume_24h = findViewById(R.id.volume_24h);
        volume_24h_USD = findViewById(R.id.volume_24h_USD);


        lineChart = findViewById(R.id.crypto_linechart);
        switchCompat = findViewById(R.id.crypto_switch);

        button = findViewById(R.id.crypto_add_to_favorites_btn);
        calculator_btn = findViewById(R.id.crypto_calculator_btn);

        headlines = (CryptoHeadlines) getIntent().getSerializableExtra("data");

        symbol.setText(headlines.getSymbol().toUpperCase()+"/USD");
        name.setText(headlines.getName().substring(0, 1).toUpperCase() + headlines.getName().substring(1).toLowerCase());
        if (headlines.getImage()!=null){
            Picasso.get().load(headlines.getImage()).into(icon);
        }

        volume_24h_txt.setText(volume_24h_txt.getText()+"("+headlines.getSymbol().toUpperCase(Locale.ROOT)+")");
        price.setText(headlines.getCurrent_price().toString());
        if (headlines.getPrice_change_percentage_24h()>=0) {
            change.setText(headlines.getPrice_change_24h().toString().substring(0, Math.min(10, headlines.getPrice_change_24h().toString().length()-1))+"  "+("+"+headlines.getPrice_change_percentage_24h().toString()).substring(0, 5));
            change.setTextColor(Color.parseColor("#159800"));
        }
        else {
            change.setText(headlines.getPrice_change_24h().toString().substring(0, Math.min(10, headlines.getPrice_change_24h().toString().length()-1)) + "  " + (headlines.getPrice_change_percentage_24h().toString()).substring(0, 5));
            change.setTextColor(Color.parseColor("#FF0000"));
        }

        max_24h.setText(headlines.getHigh_24h().toString());
        min_24h.setText(headlines.getLow_24h().toString());
        volume_24h_USD.setText(BigDecimal.valueOf(headlines.getTotal_volume()).toString());
        BigDecimal v = BigDecimal.valueOf(headlines.getTotal_volume()/headlines.getCurrent_price());
        int m = Math.min(14, v.toString().length());
        volume_24h.setText(v.toString().substring(0,m-1));


        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Double> sparkline_in_7d = (ArrayList<Double>) headlines.getSparkline_in_7d().getPrice();
        ArrayList<Entry> values = new ArrayList<>();
        int counter = 0;
        for (Double i :sparkline_in_7d) {

            if (counter/6 == 0) values.add(new Entry(counter, Float.parseFloat(i.toString())));
            counter++;
        }

        LineDataSet set = new LineDataSet(values, headlines.getSymbol().toUpperCase());
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);
        LineData data = new LineData(dataSets);

        lineChart.setData(data);


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ArrayList<Double> sparkline_in_7d = (ArrayList<Double>) headlines.getSparkline_in_7d().getPrice();
                    ArrayList<Entry> values = new ArrayList<>();
                    int counter = 0;
                    for (Double i :sparkline_in_7d) {

                        if (counter/24 == 0) values.add(new Entry(counter, Float.parseFloat(i.toString())));
                        counter++;
                    }

                    LineDataSet set = new LineDataSet(values, headlines.getSymbol().toUpperCase());
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);
                    BigDecimal v = BigDecimal.valueOf(headlines.getPrice_change_percentage_7d_in_currency()*headlines.getCurrent_price()/100);
                    if (headlines.getPrice_change_percentage_24h()>=0) {
                        change.setText(v.toString().substring(0, Math.min(10, v.toString().length()-1))+"  "+("+"+headlines.getPrice_change_percentage_7d_in_currency().toString()).substring(0, 5));
                        change.setTextColor(Color.parseColor("#159800"));
                    }
                    else {
                        change.setText(v.toString().substring(0, Math.min(10, v.toString().length()-1))+ "  " + (headlines.getPrice_change_percentage_24h().toString()).substring(0, 5));
                        change.setTextColor(Color.parseColor("#FF0000"));
                    }



                }
                else {
                    ArrayList<Double> sparkline_in_7d = (ArrayList<Double>) headlines.getSparkline_in_7d().getPrice();
                    ArrayList<Entry> values = new ArrayList<>();
                    int counter = 0;
                    for (Double i :sparkline_in_7d) {

                        if (counter>144) values.add(new Entry(counter-144, Float.parseFloat(i.toString())));
                        counter++;
                    }

                    LineDataSet set = new LineDataSet(values, headlines.getSymbol().toUpperCase());
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set);
                    LineData data = new LineData(dataSets);

                    lineChart.setData(data);

                    if (headlines.getPrice_change_percentage_24h()>=0) {
                        change.setText(headlines.getPrice_change_24h().toString().substring(0, Math.min(10, headlines.getPrice_change_24h().toString().length()-1))+"  "+("+"+headlines.getPrice_change_percentage_24h().toString()).substring(0, 5));
                        change.setTextColor(Color.parseColor("#159800"));
                    }
                    else {
                        change.setText(headlines.getPrice_change_24h().toString().substring(0, Math.min(10, headlines.getPrice_change_24h().toString().length()-1)) + "  " + (headlines.getPrice_change_percentage_24h().toString()).substring(0, 5));
                        change.setTextColor(Color.parseColor("#FF0000"));
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ADD TO FAVORITES
            }
        });

        calculator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CryptoDetailsActivity.this, ActivityCalculator.class).putExtra("data", headlines.getCurrent_price()));
            }
        });
    }
}