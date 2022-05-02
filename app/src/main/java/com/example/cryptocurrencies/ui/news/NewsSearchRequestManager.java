package com.example.cryptocurrencies.ui.news;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cryptocurrencies.Models.NewsApiResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsSearchRequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsdata.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getSearchNewsHeadlines(NewsOnFetchDataListener listener, Integer page, String qInTitle)
    {
        NewsSearchRequestManager.CallNewsApi callNewsApi = retrofit.create(NewsSearchRequestManager.CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callHeadlines(page, qInTitle);
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<NewsApiResponse> call, @NonNull Response<NewsApiResponse> response) {
                    if (!Objects.requireNonNull(response).isSuccessful()){
                        Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                    }

                    if(response.body() != null){
                        listener.onFetchData(response.body().getResults(), response.message());}

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

    public NewsSearchRequestManager(Context context) {
        this.context = context;
    }

    public interface CallNewsApi{
        //        &q=cryptocurrency&country=us&language=en&page=0
        @GET("1/news?apikey=pub_6835e68c783f68ca250522a9b21493ea902a&country=us&language=en&")
        Call<NewsApiResponse> callHeadlines(
                @Query("page") Integer page,
                @Query("qInTitle") String qInTitle
        );

    }
}
