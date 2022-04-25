package com.example.cryptocurrencies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;

    ImageView news_details_img;

    TextView news_details_title,
            news_details_full_description,
            news_details_content,
            news_details_creators,
            news_details_pubDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        news_details_img = findViewById(R.id.news_details_img);
        news_details_title = findViewById(R.id.news_details_title);
        news_details_full_description = findViewById(R.id.news_details_full_description);
        news_details_content = findViewById(R.id.news_details_content);
        news_details_creators = findViewById(R.id.news_details_creators);
        news_details_pubDate = findViewById(R.id.news_details_pubDate);

        headlines = (NewsHeadlines) getIntent().getSerializableExtra("data");


        news_details_title.setText(headlines.getTitle());
        news_details_full_description.setText(headlines.getFull_description());
        news_details_content.setText(headlines.getContent());
        news_details_pubDate.setText(headlines.getPubDate());

        String v = "";
        for (String i : headlines.getCreator()) {
            v = v + i+" ";
        }
        news_details_creators.setText(v);
        if (headlines.getImage_url()!=null){
            news_details_img.setVisibility(View.VISIBLE);
            Picasso.get().load(headlines.getImage_url()).into(news_details_img);
        }
        else {
            news_details_img.setVisibility(View.GONE);
        }
    }
}