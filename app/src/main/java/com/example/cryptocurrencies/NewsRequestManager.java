package com.example.cryptocurrencies;

import android.content.Context;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.cryptocurrencies.Models.NewsApiResponse;
import com.example.cryptocurrencies.Models.NewsHeadlines;
import com.example.cryptocurrencies.ui.news.News;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsRequestManager {
    Integer counter = 0;
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getNewsHeadlines(NewsOnFetchDataListener listener, Integer page)
    {


            CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
            Call<NewsApiResponse> call = callNewsApi.callHeadlines(page);
            try {
                call.enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<NewsApiResponse> call, @NonNull Response<NewsApiResponse> response) {
                        if (!Objects.requireNonNull(response).isSuccessful()){
                            Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                        }

                        if(response.body() != null){
                            listener.onFetchData(response.body().getResults(), response.message());
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<NewsApiResponse> call, @NonNull Throwable t) {
                        listener.onError("Request Failed!");
                    }
                });


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


    public NewsRequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi{

        @GET("1/news?apikey=pub_6835e68c783f68ca250522a9b21493ea902a&q=cryptocurrency&country=us&language=en&")
        Call<NewsApiResponse> callHeadlines(
                @Query("page") Integer page
        );

    }
}
