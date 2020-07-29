package com.example.pantaudagingkurban.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pantaudagingkurban.model.TempatModel;

import java.util.List;

@Dao
public interface TempatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insetrTempat(TempatModel tempatModel);

    @Query("SELECT * FROM tb_tempat")
    List<TempatModel> readAllTempat();
}
