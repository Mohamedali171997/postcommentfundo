package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AfficherPostController {
    @FXML
    private Label postTitle;

    @FXML
    private Label postContent;

    // Additional fields and methods as needed

    public void setPostTitle(String title) {
        postTitle.setText(title);
    }

    public void setPostContent(String content) {
        postContent.setText(content);
    }

    // Additional setters and methods
}
