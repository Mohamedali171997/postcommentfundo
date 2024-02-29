package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.modeles.Post;
import tn.esprit.service.ServicePost;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjoutPost {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField autor_id;

    @FXML
    private TextField titel;

    @FXML
    private TextField content;

    @FXML
    private TextField post_id;

    private final ServicePost servicePost = new ServicePost();

    @FXML
    private void ajoutPost(ActionEvent event) {
        // Validate input before processing
        if (validateInput()) {
            // Récupérer les valeurs des champs
            int id = Integer.parseInt(post_id.getText());
            String t = titel.getText();
            String postContent = content.getText();
            int authorId = Integer.parseInt(autor_id.getText());
            Post post = new Post(id, t, postContent, authorId);

            servicePost.add(post);

            // Effacer les champs après l'ajout
            post_id.clear();
            autor_id.clear();
            content.clear();
            titel.clear();

            // Afficher un message de confirmation ou une action appropriée
            showAlert("Post ajouté avec succès!", Alert.AlertType.INFORMATION);
        } else {
            // Show an alert if validation fails
            showAlert("Veuillez remplir tous les champs avec des données valides.", Alert.AlertType.ERROR);
        }
    }

    private boolean validateInput() {
        // Perform basic input validation
        return !post_id.getText().isEmpty() &&
                !autor_id.getText().isEmpty() &&
                !titel.getText().isEmpty() &&
                !content.getText().isEmpty();
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        // Initialization logic, if needed
    }














        @FXML
        private void handleOuvrirAffichePost(ActionEvent event) {
            ouvrirFenetre("/FXML/AffichePost.fxml", "Afficher un Post");
        }

        // ... autres méthodes ...

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

       /* private void ShowAlert(String message, Alert.AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setContentText(message);
            alert.showAndWait();
        }*/



    @FXML
    private void handleSupprimer(ActionEvent event) {
        // Validez le champ post_id avant de traiter
        if (validateInput()) {
            // Récupérez l'ID du post à supprimer
            int postId = Integer.parseInt(post_id.getText());

            // Créez un objet Post avec seulement l'ID
            Post postToDelete = new Post(postId, "", "", 0);

            // Appelez la méthode delete du servicePost
            boolean deleted = servicePost.delete(postToDelete);

            if (deleted) {
                showAlert("Post supprimé avec succès!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Échec de la suppression du post.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Veuillez saisir un ID valide pour supprimer le post.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleMiseAJour(ActionEvent event) {
        // Validez les champs avant de traiter
        if (validateInput()) {
            // Récupérez les valeurs des champs
            int id = Integer.parseInt(post_id.getText());
            String t = titel.getText();
            String postContent = content.getText();
            int authorId = Integer.parseInt(autor_id.getText());
            Post updatedPost = new Post(id, t, postContent, authorId);

            // Appelez la méthode update du servicePost
            servicePost.update(updatedPost);

            showAlert("Post mis à jour avec succès!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Veuillez remplir tous les champs avec des données valides.", Alert.AlertType.ERROR);
        }
    }

    // Méthode de validation de l'ID
  /*  private boolean validateId() {
        // Validez que le champ post_id n'est pas vide
        return !post_id.getText().isEmpty();
    }*/


}

