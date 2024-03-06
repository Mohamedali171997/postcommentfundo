package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import tn.esprit.modeles.Commentaire;
import tn.esprit.service.ServiceCommentaire;
import tn.esprit.views.CommentaireCardView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCommentaireController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private FlowPane cardContainer; // Check the type

    @FXML
    private TextField keywordTextField;

    private final ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
    @FXML
    void initialize() {
        assert cardContainer != null : "fx:id=\"cardContainer\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert keywordTextField != null : "fx:id=\"keywordTextField\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        // Set alignment and gap properties for the FlowPane
        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setHgap(10); // Adjust the horizontal gap as needed
        cardContainer.setVgap(10); // Adjust the vertical gap as needed


        // Load all comments on initialization
        List<Commentaire> allComments = serviceCommentaire.getAll();
        displayCommentaireCards(allComments);
    }

    private void displayCommentaireCards(List<Commentaire> commentaires) {
        cardContainer.getChildren().clear();
        for (Commentaire commentaire : commentaires) {
            CommentaireCardView cardView = new CommentaireCardView(commentaire);
            cardContainer.getChildren().add(cardView);
        }
    }

    @FXML
    void handleSearchButton() {
        String keyword = keywordTextField.getText();
        ArrayList<Commentaire> matchingComments = serviceCommentaire.searchByContent(keyword);
        displayCommentaireCards(matchingComments);
    }

    @FXML
    void handleSortByDate() {
        List<Commentaire> sortedComments = new ArrayList<>(serviceCommentaire.getAll());
        serviceCommentaire.sortByDate((ArrayList<Commentaire>) sortedComments);
        displayCommentaireCards(sortedComments);
    }

    @FXML
    void handleSortByUser() {
        List<Commentaire> sortedComments = new ArrayList<>(serviceCommentaire.getAll());
        serviceCommentaire.sortByUser((ArrayList<Commentaire>) sortedComments);
        displayCommentaireCards(sortedComments);
    }

    @FXML
    void handleSortByLength() {
        List<Commentaire> sortedComments = new ArrayList<>(serviceCommentaire.getAll());
        serviceCommentaire.sortByLength((ArrayList<Commentaire>) sortedComments);
        displayCommentaireCards(sortedComments);
    }
}


