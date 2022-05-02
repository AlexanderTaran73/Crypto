package com.example.cryptocurrencies.Models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = FavItem.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavItemDao favItemDao();
}
