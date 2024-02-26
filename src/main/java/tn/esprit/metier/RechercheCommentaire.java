package tn.esprit.metier;

import tn.esprit.modeles.Commentaire;
import tn.esprit.service.ServiceCommentaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RechercheCommentaire {
    private Connection connection;
    private ArrayList<Commentaire> commentaires;
    private ServiceCommentaire serviceCommentaire;
    public RechercheCommentaire(ArrayList<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
    public RechercheCommentaire(Connection connection) {
        this.connection = connection;
    }

    public RechercheCommentaire(ServiceCommentaire serviceCommentaire) {
        this.serviceCommentaire = serviceCommentaire;
    }

    public ArrayList<Commentaire> searchByUserId(int userId) {
        ArrayList<Commentaire> result = new ArrayList<>();
        for (Commentaire commentaire : serviceCommentaire.getAll()) {
            if (commentaire.getUserId() == userId) {
                result.add(commentaire);
            }
        }
        return result;
    }




    // Search comments by a specific word in their content
    public List<Commentaire> rechercheByWord(String searchWord) {
        return commentaires.stream()
                .filter(commentaire -> commentaire.getContent().toLowerCase().contains(searchWord.toLowerCase()))
                .collect(Collectors.toList());
    }

    public ArrayList<Commentaire> searchByContent(String keyword) {
        ArrayList<Commentaire> matchingComments = new ArrayList<>();
        String query = "SELECT * FROM `Commentaires` WHERE LOWER(`content`) LIKE LOWER(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Commentaire commentaire = new Commentaire();
                    commentaire.setCommentaireId(resultSet.getInt("Commentaire_id"));
                    commentaire.setPostId(resultSet.getInt("post_id"));
                    commentaire.setContent(resultSet.getString("content"));
                    commentaire.setUserId(resultSet.getInt("user_id"));
                    commentaire.setDate(resultSet.getDate("date"));

                    matchingComments.add(commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingComments;
    }
    // You can add more search methods based on your requirements
}
