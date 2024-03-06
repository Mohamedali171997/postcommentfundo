package tn.esprit.controllers;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.modeles.Commentaire;
import tn.esprit.service.ServiceCommentaire;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjoutCommentaire {





    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField commentaireField;

    @FXML
    private TextField contentField;

    @FXML
    private TextField postField;

    @FXML
    private TextField userField;

    @FXML
    private DatePicker datePicker;

    private final ServiceCommentaire serviceCommentaire = new ServiceCommentaire();

    private Date convertDatePickerToDate(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();
        if (localDate != null) {
            return Date.valueOf(localDate);  // Utilisez Date.valueOf() pour créer un java.sql.Date
        } else {
            return null;
        }
    }
    @FXML
    private void ajouterCommentaire(ActionEvent event) {
        // Validate input before processing
        if (validateInput()) {
            // Récupérer les valeurs des champs
            int CommentaireId = Integer.parseInt(commentaireField.getText()); // You need to decide how to get CommentaireId, maybe generate it or get it from somewhere
            int postId = Integer.parseInt(postField.getText());
            String content = contentField.getText();
            int userId = Integer.parseInt(userField.getText());
            // Assuming you have a method to convert the DatePicker value to a Date
          //  Date date = Date.valueOf(datePicker.getValue().toString());
            Date date = convertDatePickerToDate(datePicker);

            Commentaire commentaireObject = new Commentaire(CommentaireId, postId, content, userId, date);

            serviceCommentaire.add(commentaireObject);

            // Effacer les champs après l'ajout
            clearFields();

            // Afficher un message de confirmation ou une action appropriée
            // You can add an alert or any other action to notify the user of a successful addition
        } else {
            // Show an alert if validation fails
            // You can customize this method to display an appropriate message to the user
            showAlert("Veuillez remplir tous les champs avec des données valides.", Alert.AlertType.ERROR);
        }
    }
    private boolean validateInput() {
        // Perform basic input validation
        return !commentaireField.getText().isEmpty() &&
                !contentField.getText().isEmpty() &&
                !postField.getText().isEmpty() &&
                !userField.getText().isEmpty() &&
                datePicker.getValue() != null;
    }

    private void clearFields() {
        commentaireField.clear();
        contentField.clear();
        postField.clear();
        userField.clear();
        datePicker.setValue(null);
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
    private void handleOuvrirAffichecommentaire(ActionEvent event) {
        ouvrirFenetre("/FXML/AfficheCommentaire.fxml", "Afficher un commentaire");
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
            ShowAlert("Erreur lors de l'ouverture de la fenêtre.", Alert.AlertType.ERROR);
        }
    }

    private void ShowAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }



    //466666665455555555555555555555555555555555




    @FXML
    private void handleSupprimer(ActionEvent event) {
        int commentaireId = Integer.parseInt(commentaireField.getText()); // Use your actual field name
        Commentaire commentaireToDelete = new Commentaire(commentaireId);

        // Call the delete method of serviceCommentaire
        boolean deleted = serviceCommentaire.delete(commentaireToDelete);

        if (deleted) {
            clearFields(); // Clear fields after successful deletion
        } else {
            System.out.println("Erreur lors de la suppression du commentaire.");
        }
    }

    // ... existing code ...

    // Add the showConfirmationDialog method
    private boolean showConfirmationDialog(String title, String content) {

        System.out.println(title + ": " + content);
        return true;
    }
    @FXML
    private void handleMiseAJour(ActionEvent event) {
        int commentaireId = Integer.parseInt(commentaireField.getText());
        Commentaire commentaireToUpdate = new Commentaire(commentaireId);

        // Retrieve the new values for the fields you want to update
        int newPostId = Integer.parseInt(postField.getText()); // Use your actual field name
        String newContent = contentField.getText(); // Use your actual field name
        int newUserId = Integer.parseInt(userField.getText()); // Use your actual field name
        Date newDate = convertDatePickerToDate(datePicker);

        commentaireToUpdate.setPostId(newPostId);
        commentaireToUpdate.setContent(newContent);
        commentaireToUpdate.setUserId(newUserId);
        commentaireToUpdate.setDate(newDate);

        serviceCommentaire.update(commentaireToUpdate);

        clearFields();
    }

}
