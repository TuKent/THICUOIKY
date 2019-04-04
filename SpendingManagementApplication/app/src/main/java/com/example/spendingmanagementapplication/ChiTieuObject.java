package com.example.spendingmanagementapplication;

import android.content.Context;

import java.util.List;

public class ChiTieuObject
{
    int ID;
    int SoTienChiTieu;
    String LoaiChiTieu;
    String DiaDiemChiTieu;
    String TimeChiTieu;
    String GhiChuChiTieu;

    public ChiTieuObject(int id,int soTienChiTieu, String loaiChiTieu, String diaDiemChiTieu, String timeChiTieu, String ghiChuChiTieu)
    {
        ID = id;
        SoTienChiTieu = soTienChiTieu;
        LoaiChiTieu = loaiChiTieu;
        DiaDiemChiTieu = diaDiemChiTieu;
        TimeChiTieu = timeChiTieu;
        GhiChuChiTieu = ghiChuChiTieu;
    }

    public ChiTieuObject() {

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSoTienChiTieu() {
        return SoTienChiTieu;
    }

    public void setSoTienChiTieu(int soTienChiTieu) {
        SoTienChiTieu = soTienChiTieu;
    }

    public String getLoaiChiTieu() {
        return LoaiChiTieu;
    }

    public void setLoaiChiTieu(String loaiChiTieu) {
        LoaiChiTieu = loaiChiTieu;
    }

    public String getDiaDiemChiTieu() {
        return DiaDiemChiTieu;
    }

    public void setDiaDiemChiTieu(String diaDiemChiTieu) {
        DiaDiemChiTieu = diaDiemChiTieu;
    }

    public String getTimeChiTieu() {
        return TimeChiTieu;
    }

    public void setTimeChiTieu(String timeChiTieu) {
        TimeChiTieu = timeChiTieu;
    }

    public String getGhiChuChiTieu() {
        return GhiChuChiTieu;
    }

    public void setGhiChuChiTieu(String ghiChuChiTieu) {
        GhiChuChiTieu = ghiChuChiTieu;
    }

}
