package com.example.cryptocurrencies.ui.cryptocurrencies;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;


import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CryptoRequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getCryptoHeadlines(CryptoOnFetchDataListener listener, String vs_currency, String ids, String order, Integer per_page, String price_change_percentage){

        CallCryptoApi callCryptoApi = retrofit.create(CallCryptoApi.class);
        Call<List<CryptoHeadlines>> call = callCryptoApi.callHeadlines(vs_currency, ids, order, per_page, 1, true, price_change_percentage);

        try {
            call.enqueue(new Callback<List<CryptoHeadlines>>() {
                @Override
                public void onResponse(@NonNull Call<List<CryptoHeadlines>> call, @NonNull Response<List<CryptoHeadlines>> response) {
                    if (!Objects.requireNonNull(response).isSuccessful()){
                        Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
                    }
                    if(response.body() != null){
                        listener.onFetchData(response.body(), response.message());}
                }
                @Override
                public void onFailure(@NonNull Call<List<CryptoHeadlines>> call, @NonNull Throwable t) {
                    listener.onError("Request Failed!");
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public CryptoRequestManager(Context context) {
        this.context = context;
    }

    public interface CallCryptoApi{
        @GET("coins/markets")
        Call<List<CryptoHeadlines>> callHeadlines(
                @Query("vs_currency") String vs_currency,
                @Query("ids") String ids,
                @Query("order") String order,
                @Query("per_page") Integer per_page,
                @Query("page") Integer page,
                @Query("sparkline") Boolean sparkline,
                @Query("price_change_percentage") String price_change_percentage
        );
    }
}
