package tn.esprit.views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tn.esprit.modeles.Commentaire;

import java.util.Random;

public class CommentaireCardView extends StackPane {
    String randomColor = getRandomColor();
    public CommentaireCardView(Commentaire commentaire) {

       // Label idLabel = new Label("ID: " + commentaire.getCommentaireId());
        Label postIdLabel = new Label("Post ID: " + commentaire.getPostId());
        Label contentLabel = new Label("Content: " + commentaire.getContent());
        Label userIdLabel = new Label("User ID: " + commentaire.getUserId());
        Label dateLabel = new Label("Date: " + commentaire.getDate());

        VBox cardContent = new VBox(/*idLabel*/ postIdLabel, contentLabel, userIdLabel, dateLabel);
        cardContent.setSpacing(5);

        getChildren().add(cardContent);
        setStyle("-fx-background-color:" + randomColor +";\n" +
                "    -fx-border-color: #cccccc;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-spacing: 50;"
        +"-fx-background-radius: 10;");
        setMaxSize(150, 150);
        setPrefSize(100, 100);
    }
    private String getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}
