package com.main.tubes;

import com.main.tubes.Database.Pusat;
import com.main.tubes.Menu.Transaksi;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Harian extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(500.0, 200.0);

        String dateFormat = "";
        if(Pusat.fromBulanan.isEmpty()) {
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", new Locale("id", "ID"));
            dateFormat = timeNow.format(formatter);
        }else {
            dateFormat = Pusat.fromBulanan;
            Pusat.fromBulanan = "";
        }

        Label dateLabel = new Label(dateFormat);
        dateLabel.setLayoutX(181.0);
        dateLabel.setLayoutY(26.0);
        dateLabel.setPrefSize(137.0, 35.0);
        dateLabel.setFont(new Font("System Bold", 24.0));
        dateLabel.setAlignment(Pos.CENTER);

        Button nextButton = new Button(">");
        nextButton.setLayoutX(326.0);
        nextButton.setLayoutY(24.0);
        nextButton.setFont(new Font("System Bold", 18.0));

        Button prevButton = new Button("<");
        prevButton.setLayoutX(135.0);
        prevButton.setLayoutY(24.0);
        prevButton.setFont(new Font("System Bold", 18.0));

        HBox summaryBox = new HBox();
        summaryBox.setAlignment(Pos.CENTER);
        summaryBox.setLayoutY(85.0);
        summaryBox.setPrefSize(500.0, 81.0);

        VBox incomeBox = new VBox();
        incomeBox.setAlignment(Pos.CENTER);
        incomeBox.setPrefSize(160.0, 200.0);
        Label incomeLabel = new Label("Pemasukan");
        incomeLabel.setFont(new Font(16.0));
        Label incomeAmountLabel = new Label("20.000");
        incomeAmountLabel.setFont(new Font("System Bold", 18.0));
        incomeAmountLabel.setTextFill(Color.GREEN);
        incomeBox.getChildren().addAll(incomeLabel, incomeAmountLabel);

        VBox expenseBox = new VBox();
        expenseBox.setAlignment(Pos.CENTER);
        expenseBox.setPrefSize(160.0, 200.0);
        expenseBox.setPadding(new Insets(0, 5.0, 0, 5.0));
        Label expenseLabel = new Label("Pengeluaran");
        expenseLabel.setFont(new Font(16.0));
        Label expenseAmountLabel = new Label("10.000");
        expenseAmountLabel.setFont(new Font("System Bold", 18.0));
        expenseAmountLabel.setTextFill(Color.RED);
        expenseBox.getChildren().addAll(expenseLabel, expenseAmountLabel);

        VBox balanceBox = new VBox();
        balanceBox.setAlignment(Pos.CENTER);
        balanceBox.setPrefSize(160.0, 200.0);
        Label balanceLabel = new Label("Saldo");
        balanceLabel.setFont(new Font(16.0));
        Label balanceAmountLabel = new Label("10.000");
        balanceAmountLabel.setFont(new Font("System Bold", 18.0));
        balanceBox.getChildren().addAll(balanceLabel, balanceAmountLabel);

        summaryBox.getChildren().addAll(incomeBox, expenseBox, balanceBox);

        ScrollPane transactionsPane = new ScrollPane();
        transactionsPane.setLayoutY(169.0);
        transactionsPane.setPrefSize(500.0, 331.0);
        transactionsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        transactionsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox transactionsBox = new VBox();
        transactionsBox.setPrefWidth(490.0);

        updateTable(transactionsBox, dateLabel.getText(), incomeAmountLabel, expenseAmountLabel, balanceAmountLabel);
        transactionsBox.setPadding(new Insets(0, 0, 30.0, 0));

        transactionsPane.setContent(transactionsBox);

        Button addButton = new Button("+");
        addButton.setLayoutX(436.0);
        addButton.setLayoutY(18.0);
        addButton.setFont(new Font("System Bold", 24.0));

        rightPane.getChildren().addAll(addButton, dateLabel, nextButton, prevButton, summaryBox, transactionsPane);

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

        nextButton.setOnAction(actionEvent -> {
            String tanggalNow = dateLabel.getText();
            String[] splitted = tanggalNow.split(" ");
            if(tanggalNow.contains("Jan")) {
                dateLabel.setText("Feb " + splitted[1]);
            }else if(tanggalNow.contains("Feb")) {
                dateLabel.setText("Mar " + splitted[1]);
            }else if(tanggalNow.contains("Mar")) {
                dateLabel.setText("Apr " + splitted[1]);
            }else if(tanggalNow.contains("Apr")) {
                dateLabel.setText("Mei " + splitted[1]);
            }else if(tanggalNow.contains("Mei")) {
                dateLabel.setText("Jun " + splitted[1]);
            }else if(tanggalNow.contains("Jun")) {
                dateLabel.setText("Jul " + splitted[1]);
            }else if(tanggalNow.contains("Jul")) {
                dateLabel.setText("Agu " + splitted[1]);
            }else if(tanggalNow.contains("Agu")) {
                dateLabel.setText("Sep " + splitted[1]);
            }else if(tanggalNow.contains("Sep")) {
                dateLabel.setText("Okt " + splitted[1]);
            }else if(tanggalNow.contains("Okt")) {
                dateLabel.setText("Nov " + splitted[1]);
            }else if(tanggalNow.contains("Nov")) {
                dateLabel.setText("Des " + splitted[1]);
            }else if(tanggalNow.contains("Des")) {
                int dateNow = Integer.parseInt(splitted[1]);
                dateNow++;
                dateLabel.setText("Jan " + dateNow);
            }
            updateTable(transactionsBox, dateLabel.getText(), incomeAmountLabel, expenseAmountLabel, balanceAmountLabel);
        });

        prevButton.setOnAction(actionEvent -> {
            String tanggalNow = dateLabel.getText();
            String[] splitted = tanggalNow.split(" ");
            if(tanggalNow.contains("Des")) {
                dateLabel.setText("Nov " + splitted[1]);
            }else if(tanggalNow.contains("Nov")) {
                dateLabel.setText("Okt " + splitted[1]);
            }else if(tanggalNow.contains("Okt")) {
                dateLabel.setText("Sep " + splitted[1]);
            }else if(tanggalNow.contains("Sep")) {
                dateLabel.setText("Agu " + splitted[1]);
            }else if(tanggalNow.contains("Agu")) {
                dateLabel.setText("Jul " + splitted[1]);
            }else if(tanggalNow.contains("Jul")) {
                dateLabel.setText("Jun " + splitted[1]);
            }else if(tanggalNow.contains("Jun")) {
                dateLabel.setText("Mei " + splitted[1]);
            }else if(tanggalNow.contains("Mei")) {
                dateLabel.setText("Apr " + splitted[1]);
            }else if(tanggalNow.contains("Apr")) {
                dateLabel.setText("Mar " + splitted[1]);
            }else if(tanggalNow.contains("Mar")) {
                dateLabel.setText("Feb " + splitted[1]);
            }else if(tanggalNow.contains("Feb")) {
                dateLabel.setText("Jan " + splitted[1]);
            }else if(tanggalNow.contains("Jan")) {
                int dateNow = Integer.parseInt(splitted[1]);
                dateNow--;
                dateLabel.setText("Des " + dateNow);
            }
            updateTable(transactionsBox, dateLabel.getText(), incomeAmountLabel, expenseAmountLabel, balanceAmountLabel);
        });

        addButton.setOnAction(actionEvent -> {
            Transaksi transaksi = new Transaksi();
            transaksi.start(primaryStage);
        });

        monthlyButton.setOnAction(actionEvent -> {
            Bulanan bulanan = new Bulanan();
            bulanan.start(primaryStage);
        });

        yearlyButton.setOnAction(actionEvent -> {
            Tahunan tahunan = new Tahunan();
            tahunan.start(primaryStage);
        });

        leftPane.getChildren().addAll(dailyButton, monthlyButton, yearlyButton);

        // Create the main BorderPane
        BorderPane root = new BorderPane();
        root.setPrefSize(700.0, 500.0);
        root.setRight(rightPane);
        root.setLeft(leftPane);

        // Set the scene and stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Catatan Harian");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateTable(VBox vbox, String tanggal, Label pemasukan, Label pengeluaran, Label saldo) {
        vbox.getChildren().clear();
        String[] splitted = tanggal.split(" ");
        int totalSemuaPemasukan = 0;
        int totalSemuaPengeluaran = 0;
        for(int i = 0; i < Pusat.dataTahun.size(); i++) {
            if(Pusat.dataTahun.get(i).getTahun().equals(splitted[1])) {
                System.out.println("Tahun");
                ArrayList<String> bulans = Pusat.dataTahun.get(i).getBulan();
                Collections.sort(bulans);
                for(String bulan: bulans) {
                    String[] splitBulan = bulan.split(" ");
                    if(splitBulan[0].equals(splitted[0])) {
                        System.out.println("Bulan1");
                        for(int j = 0; j < Pusat.dataBulan.size(); j++) {
                            String bulanss = Pusat.dataBulan.get(j).getBulan();
                            String[] bulanssSplit = bulanss.split(" ");
                            if(bulanssSplit[0].equals(splitBulan[0])) {
                                ArrayList<String> haris = Pusat.dataBulan.get(j).getHari();
                                for(String hari: haris) {
                                    String[] splitHari = hari.split(" ");
                                    if(splitHari[2].equals(splitted[1])) {

                                        // Membuat VBox untuk setiap transaksi harian
                                        VBox transaction = new VBox();
                                        transaction.setBackground(Background.fill(Color.WHITE));
                                        transaction.setPrefWidth(100.0);
                                        VBox.setMargin(transaction, new Insets(0, 0, 15, 0));

                                        // Membuat HBox untuk ringkasan transaksi
                                        HBox transactionSummary = new HBox();
                                        transactionSummary.setAlignment(Pos.CENTER);
                                        transactionSummary.setPrefSize(490.0, 40.0);

                                        Label transactionDateLabel = new Label(splitHari[0]);
                                        transactionDateLabel.setFont(new Font("System Bold", 18.0));
                                        transactionDateLabel.setPadding(new Insets(0, 100.0, 0, 0));

                                        Label transactionIncomeLabel = new Label("20.000");
                                        transactionIncomeLabel.setFont(new Font("System Bold", 14.0));
                                        transactionIncomeLabel.setTextFill(Color.GREEN);
                                        transactionIncomeLabel.setPadding(new Insets(0, 50.0, 0, 10.0));

                                        Label transactionExpenseLabel = new Label("10.000");
                                        transactionExpenseLabel.setFont(new Font("System Bold", 14.0));
                                        transactionExpenseLabel.setTextFill(Color.RED);

                                        transactionSummary.getChildren().addAll(transactionDateLabel, transactionIncomeLabel, transactionExpenseLabel);
                                        transaction.getChildren().add(transactionSummary);

                                        int totalPemasukan = 0;
                                        int totalPengeluaran = 0;
                                        for (int k = 0; k < Pusat.dataHari.size(); k++) {
                                            String hariss = Pusat.dataHari.get(k).getHari();
                                            String[] harissSplit = hariss.split(" ");
                                            if (harissSplit[0].equals(splitHari[0])) {
                                                System.out.println("Bulan2");
                                                ArrayList<String> isis = Pusat.dataHari.get(k).getIsi();
                                                for (String isi : isis) {
                                                    System.out.println("Hari");
                                                    String[] spliting = isi.split("\\|\\|");

                                                    HBox transactionDetail = new HBox();
                                                    transactionDetail.setPrefSize(490.0, 36.0);

                                                    HBox transactionDetailLeft = new HBox();
                                                    transactionDetailLeft.setAlignment(Pos.CENTER_LEFT);
                                                    transactionDetailLeft.setPrefSize(290.0, 100.0);

                                                    Label transactionDetailLabel = new Label(spliting[0]);
                                                    transactionDetailLabel.setFont(new Font(14.0));
                                                    transactionDetailLabel.setPadding(new Insets(0, 0, 0, 10.0));
                                                    transactionDetailLeft.getChildren().add(transactionDetailLabel);

                                                    HBox transactionDetailRight = new HBox();
                                                    transactionDetailRight.setAlignment(Pos.CENTER_RIGHT);
                                                    transactionDetailRight.setPrefSize(190.0, 100.0);

                                                    Label transactionDetailAmountLabel = new Label("Rp. " + spliting[1]);
                                                    transactionDetailAmountLabel.setFont(new Font(14.0));
                                                    if (spliting[2].equals("Pemasukan")) {
                                                        int masuk = Integer.parseInt(spliting[1]);
                                                        totalPemasukan += masuk;
                                                        totalSemuaPemasukan += masuk;
                                                        transactionDetailAmountLabel.setTextFill(Color.GREEN);
                                                    } else {
                                                        int keluar = Integer.parseInt(spliting[1]);
                                                        totalPengeluaran += keluar;
                                                        totalSemuaPengeluaran += keluar;
                                                        transactionDetailAmountLabel.setTextFill(Color.RED);
                                                    }
                                                    transactionDetailAmountLabel.setPadding(new Insets(0, 20.0, 0, 0));
                                                    transactionDetailRight.getChildren().add(transactionDetailAmountLabel);

                                                    transactionDetail.getChildren().addAll(transactionDetailLeft, transactionDetailRight);
                                                    transaction.getChildren().add(transactionDetail);
                                                }
                                            }
                                        }
                                        transactionIncomeLabel.setText("Rp. " + totalPemasukan);
                                        transactionExpenseLabel.setText("Rp. " + totalPengeluaran);
                                        vbox.getChildren().add(transaction);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        pemasukan.setText(String.valueOf(totalSemuaPemasukan));
        pengeluaran.setText(String.valueOf(totalSemuaPengeluaran));
        saldo.setText(String.valueOf(totalSemuaPemasukan-totalSemuaPengeluaran));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
