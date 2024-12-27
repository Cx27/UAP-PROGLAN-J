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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class Tahunan extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the right side of the BorderPane
        AnchorPane rightPane = new AnchorPane();
        rightPane.setPrefSize(500.0, 200.0);

        Label totalLabel = new Label("Total");
        totalLabel.setLayoutX(181.0);
        totalLabel.setLayoutY(26.0);
        totalLabel.setPrefSize(137.0, 35.0);
        totalLabel.setFont(new Font("System Bold", 24.0));
        totalLabel.setAlignment(Pos.CENTER);

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
        incomeAmountLabel.setFont(new Font(18.0));
        incomeAmountLabel.setTextFill(javafx.scene.paint.Color.rgb(13, 199, 0));
        incomeBox.getChildren().addAll(incomeLabel, incomeAmountLabel);

        VBox expenseBox = new VBox();
        expenseBox.setAlignment(Pos.CENTER);
        expenseBox.setPrefSize(160.0, 200.0);
        expenseBox.setPadding(new Insets(0, 5.0, 0, 5.0));
        Label expenseLabel = new Label("Pengeluaran");
        expenseLabel.setFont(new Font(16.0));
        Label expenseAmountLabel = new Label("10.000");
        expenseAmountLabel.setFont(new Font(18.0));
        expenseAmountLabel.setTextFill(javafx.scene.paint.Color.RED);
        expenseBox.getChildren().addAll(expenseLabel, expenseAmountLabel);

        VBox balanceBox = new VBox();
        balanceBox.setAlignment(Pos.CENTER);
        balanceBox.setPrefSize(160.0, 200.0);
        Label balanceLabel = new Label("Saldo");
        balanceLabel.setFont(new Font(16.0));
        Label balanceAmountLabel = new Label("10.000");
        balanceAmountLabel.setFont(new Font(18.0));
        balanceBox.getChildren().addAll(balanceLabel, balanceAmountLabel);

        summaryBox.getChildren().addAll(incomeBox, expenseBox, balanceBox);

        ScrollPane transactionsPane = new ScrollPane();
        transactionsPane.setLayoutY(169.0);
        transactionsPane.setPrefSize(500.0, 331.0);
        transactionsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        transactionsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox transactionsBox = new VBox();
        transactionsBox.setPrefWidth(490.0);

        int totalSemuaPemasukan = 0;
        int totalSemuaPengeluaran = 0;
        for(int i = 0; i < Pusat.dataTahun.size(); i++) {
            int totalPemasukan = 0;
            int totalPengeluaran = 0;
            System.out.println("Tahun");
            ArrayList<String> bulans = Pusat.dataTahun.get(i).getBulan();
            Collections.sort(bulans);
            for (String bulan : bulans) {
                String[] splitBulan = bulan.split(" ");
                System.out.println("Bulan1");
                for (int j = 0; j < Pusat.dataBulan.size(); j++) {
                    String bulanss = Pusat.dataBulan.get(j).getBulan();
                    String[] bulanssSplit = bulanss.split(" ");
                    if (bulanssSplit[0].equals(splitBulan[0])) {
                        ArrayList<String> haris = Pusat.dataBulan.get(j).getHari();
                        for (String hari : haris) {
                            String[] splitHari = hari.split(" ");
                            if (splitHari[2].equals(Pusat.dataTahun.get(i).getTahun())) {
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
                                                totalPemasukan += masuk;
                                            } else {
                                                int keluar = Integer.parseInt(spliting[1]);
                                                totalSemuaPengeluaran += keluar;
                                                totalPengeluaran += keluar;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            incomeAmountLabel.setText(String.valueOf(totalSemuaPemasukan));
            expenseAmountLabel.setText(String.valueOf(totalSemuaPengeluaran));
            balanceAmountLabel.setText(String.valueOf(totalSemuaPemasukan-totalSemuaPengeluaran));

            HBox transaction = new HBox();
            transaction.setPrefSize(490.0, 67.0);

            HBox transactionDateBox = new HBox();
            transactionDateBox.setAlignment(Pos.CENTER_LEFT);
            transactionDateBox.setPrefSize(200.0, 100.0);

            Button transactionDateButton = new Button(Pusat.dataTahun.get(i).getTahun());
            transactionDateButton.setPrefSize(120.0, 50.0);
            transactionDateButton.setFont(new Font("System Bold", 16.0));
            transactionDateButton.setPadding(new Insets(0, 0, 0, 20.0));
            transactionDateBox.getChildren().add(transactionDateButton);

            HBox transactionIncomeBox = new HBox();
            transactionIncomeBox.setAlignment(Pos.CENTER);
            transactionIncomeBox.setPrefSize(200.0, 100.0);

            Label transactionIncomeLabel = new Label(String.valueOf(totalPemasukan));
            transactionIncomeLabel.setFont(new Font(16.0));
            transactionIncomeLabel.setTextFill(Color.GREEN);
            transactionIncomeBox.getChildren().add(transactionIncomeLabel);

            HBox transactionExpenseBox = new HBox();
            transactionExpenseBox.setAlignment(Pos.CENTER_RIGHT);
            transactionExpenseBox.setPrefSize(200.0, 100.0);

            Label transactionExpenseLabel = new Label(String.valueOf(totalPengeluaran));
            transactionExpenseLabel.setFont(new Font(18.0));
            transactionExpenseLabel.setTextFill(Color.RED);
            transactionExpenseLabel.setPadding(new Insets(0, 40.0, 0, 0));
            transactionExpenseBox.getChildren().add(transactionExpenseLabel);

            int indexx = i;
            transactionDateButton.setOnAction(actionEvent -> {
                Pusat.fromTahunan = Pusat.dataTahun.get(indexx).getTahun();
                Bulanan bulanan = new Bulanan();
                bulanan.start(primaryStage);
            });

            transaction.getChildren().addAll(transactionDateBox, transactionIncomeBox, transactionExpenseBox);
            transactionsBox.getChildren().add(transaction);
        }

        transactionsPane.setContent(transactionsBox);

        Button addButton = new Button("+");
        addButton.setLayoutX(436.0);
        addButton.setLayoutY(18.0);
        addButton.setFont(new Font("System Bold", 24.0));

        rightPane.getChildren().addAll(totalLabel, summaryBox, transactionsPane, addButton);

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

        monthlyButton.setOnAction(actionEvent -> {
            Bulanan bulanan = new Bulanan();
            bulanan.start(primaryStage);
        });

        addButton.setOnAction(actionEvent -> {
            Transaksi transaksi = new Transaksi();
            transaksi.start(primaryStage);
        });

        leftPane.getChildren().addAll(dailyButton, monthlyButton, yearlyButton);

        // Create the main BorderPane
        BorderPane root = new BorderPane();
        root.setPrefSize(700.0, 500.0);
        root.setRight(rightPane);
        root.setLeft(leftPane);

        // Set the scene and stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Catatan Tahunan");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
