package tn.esprit.metier;

import tn.esprit.modeles.Commentaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TriCommentaire {

    public void sortByDate(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparing(Commentaire::getDate)); // Assuming there's a getDate() method in Commentaire class

    }
    public void sortByUser(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparingInt(Commentaire::getUserId));}


    public void sortByLength(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparingInt(commentaire -> commentaire.getContent().length()));
    }

    // You can add more sorting methods based on different criteria
}
