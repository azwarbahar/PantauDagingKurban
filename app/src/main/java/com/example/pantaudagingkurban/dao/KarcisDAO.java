package com.example.pantaudagingkurban.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pantaudagingkurban.model.KarcisModel;

import java.util.List;

@Dao
public interface KarcisDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertKarcis(KarcisModel karcisModel);

    @Query("SELECT * FROM tb_karcis")
    List<KarcisModel> readAllKarcis();


}
