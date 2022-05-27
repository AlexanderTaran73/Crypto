package com.example.cryptocurrencies.ui.notifications;

import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Notifications extends Fragment {

    private NotificationsViewModel mViewModel;

    public static Notifications newInstance() {
        return new Notifications();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    String[] arr = new String[50];

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        // TODO: Use the ViewModel

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                CryptoRequestManager manager = new CryptoRequestManager(getActivity());
                manager.getCryptoHeadlines(crypto_listener, "usd", "", "market_cap_desc", 50, "1h,24h,7d");
            }
        });

        arr[0]="BTC";
        ArrayAdapter<String> cryptoSelectionAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
        cryptoSelectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spCryptoSelection = getView().findViewById(R.id.crypto_selection);
        spCryptoSelection.setAdapter(cryptoSelectionAdapter);

    }


    private final CryptoOnFetchDataListener crypto_listener = new CryptoOnFetchDataListener() {
        @Override
        public void onFetchData(List<CryptoHeadlines> list, String message) {
            int counter = 0;
            if (list.isEmpty()){
                Toast.makeText(getActivity(), "No data found!!!", Toast.LENGTH_SHORT).show();
            }
            else { for (CryptoHeadlines i : list) {
                    arr[counter]=i.getSymbol().toUpperCase(Locale.ROOT);
                    counter++;
                }
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getActivity(), "An Error Occured!!!", Toast.LENGTH_SHORT).show();

        }
    };
}