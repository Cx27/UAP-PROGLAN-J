package com.main.tubes.Database;

import java.util.ArrayList;

public class Bulan {

    private String bulan;
    private ArrayList<String> hari;


    public Bulan(String bulan, ArrayList<String> hari) {
        this.bulan = bulan;
        this.hari = hari;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public ArrayList<String> getHari() {
        return hari;
    }

    public void setHari(ArrayList<String> hari) {
        this.hari = hari;
    }

    public void addHari(String hari) {
        this.hari.add(hari);
    }
}
