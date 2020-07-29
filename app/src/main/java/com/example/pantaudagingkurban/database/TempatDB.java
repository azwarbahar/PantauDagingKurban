package com.example.pantaudagingkurban.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pantaudagingkurban.dao.TempatDAO;
import com.example.pantaudagingkurban.model.KarcisModel;
import com.example.pantaudagingkurban.model.TempatModel;

@Database(entities = {TempatModel.class}, version = 1)
public abstract class TempatDB extends RoomDatabase {

    public abstract TempatDAO tempatDAO();

}
