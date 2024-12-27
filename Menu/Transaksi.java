package com.main.tubes.Menu;

import com.main.tubes.Database.Hari;
import com.main.tubes.Database.Pusat;
import com.main.tubes.Harian;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaksi extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(700, 500);

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setLayoutX(14);
        kembaliButton.setLayoutY(14);
        kembaliButton.setPrefSize(113, 39);
        kembaliButton.setFont(new Font("System Bold", 18));

        Label buatTransaksiLabel = new Label("Buat Transaksi");
        buatTransaksiLabel.setLayoutX(228);
        buatTransaksiLabel.setLayoutY(40);
        buatTransaksiLabel.setFont(new Font("System Bold", 36));

        ToggleGroup jenis = new ToggleGroup();

        RadioButton pengeluaranRadio = new RadioButton("Pengeluaran");
        pengeluaranRadio.setLayoutX(161);
        pengeluaranRadio.setLayoutY(126);
        pengeluaranRadio.setFont(new Font("System Bold", 24));
        pengeluaranRadio.setSelected(true);
        pengeluaranRadio.setToggleGroup(jenis);

        RadioButton pemasukanRadio = new RadioButton("Pemasukan");
        pemasukanRadio.setLayoutX(369);
        pemasukanRadio.setLayoutY(126);
        pemasukanRadio.setFont(new Font("System Bold", 24));
        pemasukanRadio.setToggleGroup(jenis);

        Label tanggalLabel = new Label("Tanggal");
        tanggalLabel.setLayoutX(154);
        tanggalLabel.setLayoutY(198);
        tanggalLabel.setPrefSize(113, 25);
        tanggalLabel.setFont(new Font(20));

        DatePicker tanggalPicker = new DatePicker();
        tanggalPicker.setLayoutX(249);
        tanggalPicker.setLayoutY(193);
        tanggalPicker.setPrefSize(300, 40);
        tanggalPicker.setValue(LocalDate.now());

        Label kategoriLabel = new Label("Kategori");
        kategoriLabel.setLayoutX(154);
        kategoriLabel.setLayoutY(253);
        kategoriLabel.setPrefSize(113, 25);
        kategoriLabel.setFont(new Font(20));

        ComboBox<String> kategoriComboBox = new ComboBox<>();
        kategoriComboBox.setLayoutX(249);
        kategoriComboBox.setLayoutY(248);
        kategoriComboBox.setPrefSize(300, 40);

        setKategori("Pengeluaran", kategoriComboBox);

        Label jumlahLabel = new Label("Jumlah");
        jumlahLabel.setLayoutX(154);
        jumlahLabel.setLayoutY(308);
        jumlahLabel.setPrefSize(113, 25);
        jumlahLabel.setFont(new Font(20));

        TextField jumlahField = new TextField();
        jumlahField.setLayoutX(249);
        jumlahField.setLayoutY(303);
        jumlahField.setPrefSize(300, 40);
        jumlahField.setFont(new Font(16));

        Button simpanButton = new Button("Simpan");
        simpanButton.setLayoutX(266);
        simpanButton.setLayoutY(378);
        simpanButton.setPrefSize(171, 69);
        simpanButton.setFont(new Font("System Bold", 24));

        Label errorLabel = new Label();
        errorLabel.setFont(new Font("System Bold", 16));
        errorLabel.setLayoutX(193);
        errorLabel.setLayoutY(93);
        errorLabel.setTextFill(Color.RED);

        pemasukanRadio.setOnAction(actionEvent -> {
            setKategori("Pemasukan", kategoriComboBox);
        });

        pengeluaranRadio.setOnAction(actionEvent -> {
            setKategori("Pengeluaran", kategoriComboBox);
        });

        kembaliButton.setOnAction(actionEvent -> {
            Harian harian = new Harian();
            harian.start(primaryStage);
        });

        simpanButton.setOnAction(actionEvent -> {
            LocalDate selectedDate = tanggalPicker.getValue();
            LocalDateTime localDateTime = selectedDate.atStartOfDay();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("id", "ID"));

            String dateFormat = localDateTime.format(formatter);
            String[] splitted = dateFormat.split(" ");

            String category = kategoriComboBox.getSelectionModel().getSelectedItem();

            String inputJenis = "";
            if(pengeluaranRadio.isSelected()) {
                inputJenis = "Pengeluaran";
            }else {
                inputJenis = "Pemasukan";
            }

            String jumlah = jumlahField.getText().trim();
            if(jumlah.isEmpty()) {
                errorLabel.setText("Jumlah kosong.");
                return;
            }
            int inputJumlah = 0;
            try {
                inputJumlah = Integer.parseInt(jumlah);
                if(inputJumlah < 1) {
                    errorLabel.setText("Jumlah minimal adalah 1.");
                    return;
                }
            }catch (Exception e) {
                errorLabel.setText("Jumlah harus berupa angka.");
                return;
            }

            Pusat.makeNewTransaction(splitted[2], splitted[1] + " " + splitted[2], splitted[0] + " " + splitted[1] + " " + splitted[2], category, String.valueOf(inputJumlah), inputJenis);
            Harian harian = new Harian();
            harian.start(primaryStage);
        });

        root.getChildren().addAll(kembaliButton, buatTransaksiLabel, pengeluaranRadio, pemasukanRadio,
                tanggalLabel, tanggalPicker, kategoriLabel, kategoriComboBox, jumlahLabel, jumlahField, simpanButton, errorLabel);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Buat Transaksi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setKategori(String jenis, ComboBox<String> comboBox) {
        if(jenis.equals("Pengeluaran")) {
            comboBox.getItems().clear();
            comboBox.getItems().addAll("Makanan", "Mobil", "Motor", "Olahraga", "Pajak", "Pakaian", "Pendidikan", "Pulsa", "Rokok", "Rumah", "Sosial", "Tagihan", "Transportasi");
            comboBox.getSelectionModel().selectFirst();
        }else {
            comboBox.getItems().clear();
            comboBox.getItems().addAll("Deposita", "Dividen", "Gaji", "Hibah", "Investasi", "Kupon", "Lain-lain", "Pengembalian Dana", "Penghargaan", "Penjualan", "Penyewaan", "Tabungan");
            comboBox.getSelectionModel().select(2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
