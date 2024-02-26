package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AfficherCommentaireController {
    @FXML
    private Label commentaireContent;

    @FXML
    private Label userIdLabel;

    // Additional fields and methods as needed

    public void setCommentaireContent(String content) {
        commentaireContent.setText(content);
    }

    public void setUserIdLabel(int userId) {
        userIdLabel.setText("User ID: " + userId);
    }

    // Additional setters and methods
}
