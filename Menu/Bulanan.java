package com.main.tubes;

import com.main.tubes.Database.Pusat;
import com.main.tubes.Menu.Transaksi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Bulanan extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Bagian kanan
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(500, 200);

        String dateFormat = "";
        if(Pusat.fromTahunan.isEmpty()) {
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy", new Locale("id", "ID"));
            dateFormat = timeNow.format(formatter);
        }else {
            dateFormat = Pusat.fromTahunan;
            Pusat.fromTahunan = "";
        }

        Label yearLabel = new Label(dateFormat);
        yearLabel.setLayoutX(181.0);
        yearLabel.setLayoutY(26.0);
        yearLabel.setPrefHeight(35.0);
        yearLabel.setPrefWidth(137.0);
        yearLabel.setFont(new Font("System Bold", 24.0));
        yearLabel.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(yearLabel, 26.0);
        AnchorPane.setLeftAnchor(yearLabel, 181.0);

        Button nextButton = new Button(">");
        nextButton.setFont(new Font("System Bold", 18));
        AnchorPane.setTopAnchor(nextButton, 24.0);
        AnchorPane.setLeftAnchor(nextButton, 326.0);

        Button prevButton = new Button("<");
        prevButton.setFont(new Font("System Bold", 18));
        AnchorPane.setTopAnchor(prevButton, 24.0);
        AnchorPane.setLeftAnchor(prevButton, 135.0);

        VBox monthsContainer = new VBox();
        monthsContainer.setSpacing(10);
        monthsContainer.setPadding(new Insets(10));

        updateYear(monthsContainer, yearLabel.getText(), primaryStage);

        Button addButton = new Button("+");
        addButton.setLayoutX(436.0);
        addButton.setLayoutY(18.0);
        addButton.setFont(new Font("System Bold", 24.0));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(monthsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(500, 400);
        AnchorPane.setTopAnchor(scrollPane, 85.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        nextButton.setOnAction(actionEvent -> {
            String yearNow = yearLabel.getText();
            int year = Integer.parseInt(yearNow);
            year++;
            yearLabel.setText(String.valueOf(year));
            updateYear(monthsContainer, String.valueOf(year), primaryStage);
        });

        prevButton.setOnAction(actionEvent -> {
            String yearNow = yearLabel.getText();
            int year = Integer.parseInt(yearNow);
            year--;
            yearLabel.setText(String.valueOf(year));
            updateYear(monthsContainer, String.valueOf(year), primaryStage);
        });

        addButton.setOnAction(actionEvent -> {
            Transaksi transaksi = new Transaksi();
            transaksi.start(primaryStage);
        });

        // Create the left side of the BorderPane
        VBox leftPane = new VBox();
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPrefSize(200.0, 200.0);

        Button dailyButton = new Button("Harian");
        dailyButton.setPrefSize(150.0, 60.0);
        dailyButton.setFont(new Font("System Bold", 24.0));

        Button monthlyButton = new Button("Bulanan");
        monthlyButton.setPrefSize(150.0, 60.0);
        monthlyButton.setFont(new Font("System Bold", 24.0));
        VBox.setMargin(monthlyButton, new Insets(40.0, 0, 40.0, 0));

        Button yearlyButton = new Button("Tahunan");
        yearlyButton.setPrefSize(150.0, 60.0);
        yearlyButton.setFont(new Font("System Bold", 24.0));

        dailyButton.setOnAction(actionEvent -> {
            Harian harian = new Harian();
            harian.start(primaryStage);
        });

        yearlyButton.setOnAction(actionEvent -> {
            Tahunan tahunan = new Tahunan();
            tahunan.start(primaryStage);
        });

        leftPane.getChildren().addAll(dailyButton, monthlyButton, yearlyButton);

        rightPane.getChildren().addAll(addButton, yearLabel, nextButton, prevButton, scrollPane);
        BorderPane root = new BorderPane();
        root.setPrefSize(700.0, 500.0);
        root.setRight(rightPane);
        root.setLeft(leftPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Catatan Bulanan");
        primaryStage.show();
    }

    private void updateYear(VBox monthsContainer, String year, Stage stage) {
        monthsContainer.getChildren().clear();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};

        for (String month : months) {
            int totalSemuaPemasukan = 0;
            int totalSemuaPengeluaran = 0;
            for(int i = 0; i < Pusat.dataTahun.size(); i++) {
                if (Pusat.dataTahun.get(i).getTahun().equals(year)) {
                    System.out.println("Tahun");
                    ArrayList<String> bulans = Pusat.dataTahun.get(i).getBulan();
                    Collections.sort(bulans);
                    for (String bulan : bulans) {
                        String[] splitBulan = bulan.split(" ");
                        if (splitBulan[0].equals(month)) {
                            System.out.println("Bulan1");
                            for (int j = 0; j < Pusat.dataBulan.size(); j++) {
                                String bulanss = Pusat.dataBulan.get(j).getBulan();
                                String[] bulanssSplit = bulanss.split(" ");
                                if (bulanssSplit[0].equals(splitBulan[0])) {
                                    ArrayList<String> haris = Pusat.dataBulan.get(j).getHari();
                                    for (String hari : haris) {
                                        String[] splitHari = hari.split(" ");
                                        if (splitHari[2].equals(year)) {
                                            for (int k = 0; k < Pusat.dataHari.size(); k++) {
                                                String hariss = Pusat.dataHari.get(k).getHari();
                                                String[] harissSplit = hariss.split(" ");
                                                if (harissSplit[0].equals(splitHari[0])) {
                                                    System.out.println("Bulan2");
                                                    ArrayList<String> isis = Pusat.dataHari.get(k).getIsi();
                                                    for (String isi : isis) {
                                                        System.out.println("Hari");
                                                        String[] spliting = isi.split("\\|\\|");
                                                        if (spliting[2].equals("Pemasukan")) {
                                                            int masuk = Integer.parseInt(spliting[1]);
                                                            totalSemuaPemasukan += masuk;
                                                        } else {
                                                            int keluar = Integer.parseInt(spliting[1]);
                                                            totalSemuaPengeluaran += keluar;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int total = totalSemuaPemasukan-totalSemuaPengeluaran;

            HBox monthBox = new HBox();
            monthBox.setPrefSize(490, 70);
            monthBox.setAlignment(Pos.CENTER_LEFT);

            // HBox untuk nama bulan
            HBox monthNameBox = new HBox();
            monthNameBox.setPrefSize(200, 100);
            monthNameBox.setAlignment(Pos.CENTER_LEFT);

            Button monthButton = new Button(month);
            monthButton.setFont(new Font("System Bold", 16));
            monthButton.setPrefSize(120, 50);

            monthNameBox.getChildren().add(monthButton);

            // HBox untuk pendapatan dan pengeluaran
            HBox incomeBox = new HBox();
            incomeBox.setPrefSize(200, 100);
            incomeBox.setAlignment(Pos.CENTER);

            Label incomeLabel = new Label(String.valueOf(totalSemuaPemasukan));
            incomeLabel.setFont(new Font(16));
            incomeLabel.setTextFill(Color.web("#009439"));

            HBox expenseBox = new HBox();
            expenseBox.setPrefSize(200, 100);
            expenseBox.setAlignment(Pos.CENTER_RIGHT);

            Label expenseLabel = new Label(String.valueOf(totalSemuaPengeluaran));
            expenseLabel.setFont(new Font(18));
            expenseLabel.setTextFill(Color.RED);

            HBox.setMargin(expenseLabel, new Insets(0, 30, 0, 0));

            monthButton.setOnAction(actionEvent -> {
                Pusat.fromBulanan = month + " " + year;
                Harian harian = new Harian();
                harian.start(stage);
            });

            incomeBox.getChildren().add(incomeLabel);
            expenseBox.getChildren().add(expenseLabel);

            monthBox.getChildren().addAll(monthNameBox, incomeBox, expenseBox);
            monthsContainer.getChildren().add(monthBox);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
