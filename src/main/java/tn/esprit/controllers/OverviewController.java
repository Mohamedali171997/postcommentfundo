package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import tn.esprit.service.ServiceCommentaire;
import tn.esprit.service.ServicePost;

import java.util.Map;

public class OverviewController {


    @FXML
    private PieChart postsPieChart;

    @FXML
    private BarChart<String, Number> commentsBarChart;

    @FXML
    private Label commentsPerUserLabel;

    @FXML
    private Label totalCommentsLabel;

    private ServiceCommentaire commentService = new ServiceCommentaire();

    @FXML
    public void initialize() {
        updateChart();
    }

    private void updateChart() {
        Map<Integer, Integer> commentCountPerUser = commentService.getCommentCountPerUser();
        int totalComments = commentCountPerUser.values().stream().mapToInt(Integer::intValue).sum();

        // Display the result in your UI
        commentsPerUserLabel.setText("Comments per user: " + commentCountPerUser);
        totalCommentsLabel.setText("Total Comments: " + totalComments);

        // Update the bar chart with the data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<Integer, Integer> entry : commentCountPerUser.entrySet()) {
            series.getData().add(new XYChart.Data<>(String.valueOf(entry.getKey()), entry.getValue()));
        }

        commentsBarChart.getData().clear(); // Clear previous data
        commentsBarChart.getData().add(series);

        // Apply styles
        styleLabels();
        styleChart();
        updatePostStats();
    }

    private void styleLabels() {
        // Add styles to labels
        commentsPerUserLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #0073e6;");
        totalCommentsLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #0073e6;");
    }

    private void styleChart() {
        // Customize chart style
        commentsBarChart.setStyle("-fx-background-color: #f4f4f4;");
        CategoryAxis xAxis = (CategoryAxis) commentsBarChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) commentsBarChart.getYAxis();

        xAxis.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        yAxis.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        // Styles spécifiques pour le PieChart
        postsPieChart.setStyle("-fx-background-color: #f4f4f4;");
    }
    ServicePost ServicePost = new ServicePost();
    private void updatePostStats() {
        Map<Integer, Integer> postCountPerUser = ServicePost.getPostCountPerUser();

        // Update the pie chart with the data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Integer> entry : postCountPerUser.entrySet()) {
            pieChartData.add(new PieChart.Data("User " + entry.getKey(), entry.getValue()));
        }

        postsPieChart.setData(pieChartData);
    }


}
