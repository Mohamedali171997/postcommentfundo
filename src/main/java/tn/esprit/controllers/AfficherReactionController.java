package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AfficherReactionController {
    @FXML
    private Label reactionType;

    @FXML
    private Label userIdLabel;

    // Additional fields and methods as needed

    public void setReactionType(String type) {
        reactionType.setText(type);
    }

    public void setUserIdLabel(int userId) {
        userIdLabel.setText("User ID: " + userId);
    }

    // Additional setters and methods
}
