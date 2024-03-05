package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MenuPrincipaleController {

    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane pane1;

    @FXML
    private AnchorPane pane2;

    @FXML
    private AnchorPane pane3;

    @FXML
    private AnchorPane pane4;

    @FXML
    private AnchorPane pane5;

    @FXML
    private void initialize() {
        // Set pane5 (Overview) visible by default
        showPane(pane5);
    }

    @FXML
    private void handleOverviewButton(ActionEvent event) {
        showPane(pane5);
    }

    @FXML
    private void handleGererPosteButton(ActionEvent event) {
        showPane(pane1);
    }

    @FXML
    private void handleGererCommentaireButton(ActionEvent event) {
        showPane(pane2);
    }

    @FXML
    private void handleAfficheCommentaireButton(ActionEvent event) {
        showPane(pane4);
    }

    @FXML
    private void handleAffichePosteButton(ActionEvent event) {
        showPane(pane3);
    }

    private void hideAllPanes() {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
    }

    private void showPane(AnchorPane pane) {
        hideAllPanes();
        pane.setVisible(true);
    }
}
