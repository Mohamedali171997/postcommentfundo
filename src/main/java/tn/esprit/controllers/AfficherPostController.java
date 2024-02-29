package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.modeles.Post;
import tn.esprit.service.ServicePost;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficherPostController {

    @FXML
    private TableView<Post> tableView;

    @FXML
    private TableColumn<Post, Integer> idColumn;

    @FXML
    private TableColumn<Post, String> titleColumn;

    @FXML
    private TableColumn<Post, String> contentColumn;

    @FXML
    private TableColumn<Post, Integer> authorColumn;

    @FXML
    private TextField keywordTextField;

    private final ServicePost servicePost = new ServicePost();

    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("postId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("authorId"));

        List<Post> allPosts = servicePost.getAll();
        tableView.getItems().addAll(allPosts);
    }

    @FXML
    void handleSearchButton() {
        String keyword = keywordTextField.getText();
        ArrayList<Post> matchingPosts = servicePost.searchByTitle(keyword);
        tableView.getItems().setAll(matchingPosts);
    }

    @FXML
    void handleSortByTitle() {
        List<Post> sortedPosts = servicePost.getAll().stream()
                .sorted(Comparator.comparing(Post::getTitle, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        tableView.getItems().setAll(sortedPosts);
    }

    @FXML
    void handleSortByAuthor() {
        List<Post> sortedPosts = servicePost.getAll().stream()
                .sorted(Comparator.comparingInt(Post::getAuthorId))
                .collect(Collectors.toList());
        tableView.getItems().setAll(sortedPosts);
    }

    @FXML
    void handleSortByContentLength() {
        List<Post> sortedPosts = servicePost.getAll().stream()
                .sorted(Comparator.comparingInt(post -> post.getContent().length()))
                .collect(Collectors.toList());
        tableView.getItems().setAll(sortedPosts);
    }
}
