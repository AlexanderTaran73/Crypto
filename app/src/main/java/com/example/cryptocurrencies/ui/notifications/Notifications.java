package com.example.cryptocurrencies.ui.notifications;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cryptocurrencies.ActivityCalculator;
import com.example.cryptocurrencies.App;
import com.example.cryptocurrencies.Models.AppDatabase;
import com.example.cryptocurrencies.Models.CryptoHeadlines;
import com.example.cryptocurrencies.Models.NotificationsHeadlines;
import com.example.cryptocurrencies.Models.NotificationsItem;
import com.example.cryptocurrencies.Models.NotificationsItemDao;
import com.example.cryptocurrencies.R;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoCustomAdapter;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoOnFetchDataListener;
import com.example.cryptocurrencies.ui.cryptocurrencies.CryptoRequestManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Notifications extends Fragment implements NotificationsSelectListener{

    private NotificationsViewModel mViewModel;

    ImageButton notifications_btn;
    RecyclerView recyclerView;
    NotificationsCustomAdapter adapter;

    public static Notifications newInstance() {
        return new Notifications();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        // TODO: Use the ViewModel
        notifications_btn = (ImageButton) getView().findViewById(R.id.notifications_btn);

        View.OnClickListener listener = view -> startActivity(new Intent(getActivity(), NotificationsStart.class));
        notifications_btn.setOnClickListener(listener);



        AppDatabase db = App.getInstance().getDatabase();
        NotificationsItemDao notificationsItemDao = db.notificationsItemDao();



    }

    private void showNotifications(List<NotificationsHeadlines> list){
        Context context = getActivity();
        if (context!=null){
            recyclerView = getView().findViewById(R.id.recycler_notifications);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            adapter = new NotificationsCustomAdapter(context, list, this);
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void OnNotificationsClicked(NotificationsHeadlines headlines) {

    }
}