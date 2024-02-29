package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Combinedwindowscontroller {

    @FXML
    private void handleOuvrirAjoutPost(ActionEvent event) {
        ouvrirFenetre("/FXML/AjoutPost.fxml", "Ajouter un Post");
    }

    @FXML
    private void handleOuvrirAjoutCommentaire(ActionEvent event) {
        ouvrirFenetre("/FXML/AjoutCommentaire.fxml", "Ajouter un Commentaire");
    }

    private void ouvrirFenetre(String cheminFxml, String titre) {
        try {
            // Chargez le fichier FXML de la fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFxml));
            Parent root = loader.load();

            // Créez une nouvelle scène
            Scene scene = new Scene(root);

            // Créez une nouvelle fenêtre modale
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(scene);

            // Affichez la fenêtre modale
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur lors de l'ouverture de la fenêtre.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
