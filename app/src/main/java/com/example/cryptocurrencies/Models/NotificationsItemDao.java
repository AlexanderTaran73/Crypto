package com.example.cryptocurrencies.Models;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotificationsItemDao {

    @Query("SELECT * FROM notificationsitem")
    List<NotificationsItem> getAll();

    @Query("DELETE FROM notificationsitem")
    void deleteAll();

    @Query("SELECT * FROM notificationsitem WHERE id = :id")
    NotificationsItem getById(long id);

    @Query("DELETE FROM notificationsitem WHERE id = :id")
    void deleteById(int id);

    @Query("SELECT * FROM notificationsitem WHERE type = :type")
    List<NotificationsItem> getByType(String type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotificationsItem notificationsitem);

    @Update
    void update(NotificationsItem notificationsitem);

    @Delete
    void delete(NotificationsItem notificationsitem);
}
