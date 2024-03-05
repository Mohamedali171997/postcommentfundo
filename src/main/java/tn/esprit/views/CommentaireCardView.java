package tn.esprit.views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tn.esprit.modeles.Commentaire;

import java.util.Random;

public class CommentaireCardView extends StackPane {
    String randomColor = getRandomColor();

    public CommentaireCardView(Commentaire commentaire) {
        Label postIdLabel = new Label("Post ID: " + commentaire.getPostId());
        Label contentLabel = new Label("Content: " + commentaire.getContent());
        Label userIdLabel = new Label("User ID: " + commentaire.getUserId());
        Label dateLabel = new Label("Date: " + commentaire.getDate());

        VBox cardContent = new VBox(postIdLabel, contentLabel, userIdLabel, dateLabel);
        cardContent.setSpacing(5);

        // Définir la taille préférée en fonction du contenu des libellés
        setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

        getChildren().add(cardContent);
        setStyle("-fx-background-color:" + randomColor +";\n" +
                "    -fx-border-color: #cccccc;\n" +
                "    -fx-padding: 10;\n" +
                "    -fx-spacing: 50;"
                +"-fx-background-radius: 10;");

        // Calculer la hauteur totale en fonction du nombre de libellés
        double height = cardContent.getChildren().size() * 30 + 20;
        setMaxSize(USE_PREF_SIZE, height);
    }

    private String getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}
