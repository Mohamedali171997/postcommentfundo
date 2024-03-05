package tn.esprit.controllers;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import tn.esprit.modeles.Commentaire;
import tn.esprit.utile.StatisticsService;

import java.util.List;
import java.util.Map;

public class OverviewController {

    @FXML
    private BarChart<String, Number> commentsBarChart;

    @FXML
    private Label commentsPerDayLabel;

    @FXML
    private Label totalCommentsLabel;

    // Assuming you have access to the list of comments
    private List<Commentaire> commentaire;

    public void initialize() {
        StatisticsService statisticsService = new StatisticsService();

        // Calculate comments per day
        Map<String, Integer> commentsPerDay = statisticsService.calculateCommentsPerDay(commentaire);
        int totalComments = commentaire.stream().mapToInt(c -> 1).sum();

        // Display the result in your UI
        commentsPerDayLabel.setText("Comments per day: " + commentsPerDay);
        totalCommentsLabel.setText("Total Comments: " + totalComments);

        // Update the bar chart with the data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : commentsPerDay.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        commentsBarChart.getData().add(series);
    }
}
