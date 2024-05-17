package com.taxiapp.desktop.menupages;

import com.taxiapp.desktop.services.StatisticsService;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class StatisticsPageController implements Initializable {

    public VBox mainPane;
    public DatePicker afterDatePicker;
    public DatePicker beforeDatePicker;
    public PieChart ratioChart;
    public LineChart lineChart;
    public HBox chartsPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeDatePicker(afterDatePicker);
        initializeDatePicker(beforeDatePicker);
        initializeRatioChart();
        initializeXYChart();
    }

    private void initializeXYChart(){
        Map<String,Number> values = StatisticsService.getPeriodStat();
        if(values.isEmpty()) return;

        var x = new CategoryAxis();
        x.setLabel("Date");

        var y = new NumberAxis(0, values.values()
            .stream()
            .mapToInt(v -> (int) v)
            .max().orElseThrow()*2, 1);
        y.setLabel("Total orders");

        var totalOrdersSeries = new XYChart.Series<String, Number>();
        totalOrdersSeries.setName("Total orders per period");
        values.keySet().forEach(k -> totalOrdersSeries.getData().add(
            new XYChart.Data<>(
                k,
                values.get(k)
            )
        ));

        lineChart = new LineChart<>(x, y);
        lineChart.setTitle("Stock Monitoring");
        lineChart.setMinHeight(300);
        lineChart.getData().add(totalOrdersSeries);
        HBox.setHgrow(lineChart, Priority.ALWAYS);
        chartsPane.getChildren().add(lineChart);

    }
    private void initializeRatioChart(){
        double value = StatisticsService.getRatio();

        ObservableList<Data> data = FXCollections.observableArrayList(
            new PieChart.Data("Cancelled", 2),
            new PieChart.Data("Completed", 3)
        );

        ratioChart.getData().addAll(data);
        ratioChart.setMinHeight(300);
        ratioChart.setTitle("Imported Fruits");
    }
    private void initializeDatePicker(DatePicker dp){
        final var today = LocalDate.now(ZoneId.systemDefault());
        final var formatter = DateTimeFormatter.ISO_DATE;

        dp.setValue(today);
        dp.setPromptText("yyyy-MM-dd");
        dp.setEditable(true);
        dp.setPrefWidth(200);
        dp.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return formatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return today;
                }
                try {
                    return LocalDate.parse(dateString, formatter);
                } catch (DateTimeParseException e) {
                    return today;
                }
            }
        });
    }
}
