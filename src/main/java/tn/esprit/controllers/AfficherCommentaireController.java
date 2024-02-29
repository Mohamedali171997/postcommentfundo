package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.modeles.Commentaire;
import tn.esprit.service.ServiceCommentaire;

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
    private TableView<Commentaire> tableView;

    @FXML
    private TableColumn<Commentaire, Integer> idColumn;

    @FXML
    private TableColumn<Commentaire, Integer> postIdColumn;

    @FXML
    private TableColumn<Commentaire, String> contentColumn;

    @FXML
    private TableColumn<Commentaire, Integer> userIdColumn;

    @FXML
    private TableColumn<Commentaire, String> dateColumn;

    @FXML
    private TextField keywordTextField;

    private final ServiceCommentaire serviceCommentaire = new ServiceCommentaire();

    @FXML
    void initialize() {
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert postIdColumn != null : "fx:id=\"postIdColumn\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert contentColumn != null : "fx:id=\"contentColumn\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert userIdColumn != null : "fx:id=\"userIdColumn\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";
        assert keywordTextField != null : "fx:id=\"keywordTextField\" was not injected: check your FXML file 'AfficheCommentaire.fxml'.";

        // Associate table columns with Commentaire properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("commentaireId"));
        postIdColumn.setCellValueFactory(new PropertyValueFactory<>("postId"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load all comments on initialization
        List<Commentaire> allComments = serviceCommentaire.getAll();
        tableView.getItems().addAll(allComments);
    }

    @FXML
    void handleSearchButton() {
        String keyword = keywordTextField.getText();
        ArrayList<Commentaire> matchingComments = serviceCommentaire.searchByContent(keyword);
        tableView.getItems().setAll(matchingComments);
    }


    @FXML
    void handleSortByDate() {
        List<Commentaire> sortedComments = new ArrayList<>(tableView.getItems());
        serviceCommentaire.sortByDate((ArrayList<Commentaire>) sortedComments);
        tableView.getItems().setAll(sortedComments);
    }

    @FXML
    void handleSortByUser() {
        List<Commentaire> sortedComments = new ArrayList<>(tableView.getItems());
        serviceCommentaire.sortByUser((ArrayList<Commentaire>) sortedComments);
        tableView.getItems().setAll(sortedComments);
    }

    @FXML
    void handleSortByLength() {
        List<Commentaire> sortedComments = new ArrayList<>(tableView.getItems());
        serviceCommentaire.sortByLength((ArrayList<Commentaire>) sortedComments);
        tableView.getItems().setAll(sortedComments);
    }
}


