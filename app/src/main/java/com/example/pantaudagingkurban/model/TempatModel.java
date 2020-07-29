package com.example.pantaudagingkurban.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_tempat")
public class TempatModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "tempat")
    public String tempat;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    public TempatModel(int id, String tempat, String tanggal) {
        this.id = id;
        this.tempat = tempat;
        this.tanggal = tanggal;
    }

    protected TempatModel(Parcel in) {
        id = in.readInt();
        tempat = in.readString();
        tanggal = in.readString();
    }

    public static final Creator<TempatModel> CREATOR = new Creator<TempatModel>() {
        @Override
        public TempatModel createFromParcel(Parcel in) {
            return new TempatModel(in);
        }

        @Override
        public TempatModel[] newArray(int size) {
            return new TempatModel[size];
        }
    };

    public TempatModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tempat);
        dest.writeString(tanggal);
    }
}
