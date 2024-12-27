package com.main.tubes.Database;

import java.util.ArrayList;

public class Pusat {

    public static ArrayList<Tahun> dataTahun = new ArrayList<>();
    public static ArrayList<Bulan> dataBulan = new ArrayList<>();
    public static ArrayList<Hari> dataHari = new ArrayList<>();

    public static String fromBulanan = "";
    public static String fromTahunan = "";

    public static void makeNewTransaction(String tahun, String bulan, String hari, String kategori, String harga, String jenis) {
        addTahun(tahun, bulan);
        addBulan(bulan, hari);
        addHari(hari, kategori, harga, jenis);
    }

    private static void addTahun(String tahun, String bulan) {
        for (Tahun t : dataTahun) {
            if (t.getTahun().equals(tahun)) {
                if (!t.getBulan().contains(bulan)) {
                    t.addBulan(bulan);
                }
                return;
            }
        }
        ArrayList<String> bulans = new ArrayList<>();
        bulans.add(bulan);
        dataTahun.add(new Tahun(tahun, bulans));
    }

    private static void addBulan(String bulan, String hari) {
        for (Bulan b : dataBulan) {
            if (b.getBulan().equals(bulan)) {
                if (!b.getHari().contains(hari)) {
                    b.addHari(hari);
                }
                return;
            }
        }
        ArrayList<String> haris = new ArrayList<>();
        haris.add(hari);
        dataBulan.add(new Bulan(bulan, haris));
    }

    private static void addHari(String hari, String kategori, String harga, String jenis) {
        String gabungan = kategori + "||" + harga + "||" + jenis;
        for (Hari h : dataHari) {
            if (h.getHari().equals(hari)) {
                h.addIsi(gabungan);
                return;
            }
        }
        ArrayList<String> isis = new ArrayList<>();
        isis.add(gabungan);
        dataHari.add(new Hari(hari, isis));
    }

}
