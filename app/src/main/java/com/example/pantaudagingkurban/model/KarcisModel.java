package com.example.pantaudagingkurban.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_karcis")
public class KarcisModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nama_pj")
    public String nama_pj;

    @ColumnInfo(name = "tempat")
    public String tempat;

    @ColumnInfo(name = "nomor_hp")
    public String nomor_hp;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @ColumnInfo(name = "jam")
    public String jam;

    @ColumnInfo(name = "kode_karcis")
    public String kode_karcis;

    @ColumnInfo(name = "status")
    public String status;

    public KarcisModel(int id, String nama_pj, String tempat, String nomor_hp, String tanggal, String alamat, String jam, String kode_karcis, String status) {
        this.id = id;
        this.nama_pj = nama_pj;
        this.tempat = tempat;
        this.nomor_hp = nomor_hp;
        this.tanggal = tanggal;
        this.alamat = alamat;
        this.jam = jam;
        this.kode_karcis = kode_karcis;
        this.status = status;
    }

    protected KarcisModel(Parcel in) {
        id = in.readInt();
        nama_pj = in.readString();
        tempat = in.readString();
        nomor_hp = in.readString();
        tanggal = in.readString();
        alamat = in.readString();
        jam = in.readString();
        kode_karcis = in.readString();
        status = in.readString();
    }

    public static final Creator<KarcisModel> CREATOR = new Creator<KarcisModel>() {
        @Override
        public KarcisModel createFromParcel(Parcel in) {
            return new KarcisModel(in);
        }

        @Override
        public KarcisModel[] newArray(int size) {
            return new KarcisModel[size];
        }
    };

    public KarcisModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_pj() {
        return nama_pj;
    }

    public void setNama_pj(String nama_pj) {
        this.nama_pj = nama_pj;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getKode_karcis() {
        return kode_karcis;
    }

    public void setKode_karcis(String kode_karcis) {
        this.kode_karcis = kode_karcis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama_pj);
        dest.writeString(tempat);
        dest.writeString(nomor_hp);
        dest.writeString(tanggal);
        dest.writeString(alamat);
        dest.writeString(jam);
        dest.writeString(kode_karcis);
        dest.writeString(status);
    }
}
