package com.main.tubes.Menu;

import javafx.scene.control.ComboBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransaksiTest {

    private Transaksi transaksi;
    private ComboBox<String> kategoriComboBox;

    @BeforeEach
    void setUp() {
        transaksi = new Transaksi();
        kategoriComboBox = new ComboBox<>();
    }

    @Test
    void testSetKategoriPengeluaran() {
        // Menetapkan jenis transaksi "Pengeluaran"
        transaksi.setKategori("Pengeluaran", kategoriComboBox);

        // Memeriksa apakah ComboBox berisi kategori pengeluaran yang benar
        assertTrue(kategoriComboBox.getItems().contains("Makanan"));
        assertTrue(kategoriComboBox.getItems().contains("Mobil"));
        assertTrue(kategoriComboBox.getItems().contains("Transportasi"));
        assertEquals("Makanan", kategoriComboBox.getSelectionModel().getSelectedItem());
    }

    @Test
    void testSetKategoriPemasukan() {
        // Menetapkan jenis transaksi "Pemasukan"
        transaksi.setKategori("Pemasukan", kategoriComboBox);

        // Memeriksa apakah ComboBox berisi kategori pemasukan yang benar
        assertTrue(kategoriComboBox.getItems().contains("Deposita"));
        assertTrue(kategoriComboBox.getItems().contains("Dividen"));
        assertTrue(kategoriComboBox.getItems().contains("Tabungan"));
        assertEquals("Gaji", kategoriComboBox.getSelectionModel().getSelectedItem());
    }
}
