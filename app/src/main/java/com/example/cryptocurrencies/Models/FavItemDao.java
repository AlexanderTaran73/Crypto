package com.example.cryptocurrencies.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavItemDao {
    @Query("SELECT * FROM favitem")
    List<FavItem> getAll();

    @Query("DELETE FROM favitem")
    void deleteAll();

    @Query("DELETE FROM favitem WHERE type = :type AND name = :name")
    void deleteByTypeANDName(String type, String name);

    @Query("SELECT * FROM favitem WHERE id = :id")
    FavItem getById(long id);

    @Query("SELECT name FROM favitem WHERE type = :type")
    List<String> getNameByTipe(String type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavItem favitem);

    @Update
    void update(FavItem favitem);

    @Delete
    void delete(FavItem favitem);
}
