package com.example.pantaudagingkurban.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pantaudagingkurban.dao.KarcisDAO;
import com.example.pantaudagingkurban.model.KarcisModel;

@Database(entities = {KarcisModel.class}, version = 1)
public abstract class KarcisDB extends RoomDatabase {

    public abstract KarcisDAO karcisDAO();

}
