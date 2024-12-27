package com.main.tubes.Database;

import java.util.ArrayList;

public class Hari {

    private String hari;
    private ArrayList<String> isi;


    public Hari(String hari, ArrayList<String> isi) {
        this.hari = hari;
        this.isi = isi;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public ArrayList<String> getIsi() {
        return isi;
    }

    public void setIsi(ArrayList<String> isi) {
        this.isi = isi;
    }

    public void addIsi(String isi) {
        this.isi.add(isi);
    }
}
