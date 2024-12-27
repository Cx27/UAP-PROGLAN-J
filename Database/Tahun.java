package com.main.tubes.Database;

import java.util.ArrayList;

public class Tahun {

    private String tahun;
    private ArrayList<String> bulan;


    public Tahun(String tahun, ArrayList<String> bulan) {
        this.tahun = tahun;
        this.bulan = bulan;
    }

    public ArrayList<String> getBulan() {
        return bulan;
    }

    public void setBulan(ArrayList<String> bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }


    public void addBulan(String bulan) {
        this.bulan.add(bulan);
    }

}
