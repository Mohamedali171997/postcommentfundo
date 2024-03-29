package tn.esprit.service;

import tn.esprit.interfaces.IService;
import tn.esprit.modeles.Commentaire;
import tn.esprit.utile.DatabaseConnection;

import java.sql.*;
import java.util.*;

public class ServiceCommentaire implements IService<Commentaire> {
    private Connection connection;

    public ServiceCommentaire() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void add(Commentaire Commentaire) {
        String query = "INSERT INTO `Commentaires`(Commentaire_Id,`post_id`, `content`, `user_id`,date) VALUES (?, ?, ?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,Commentaire.getCommentaireId());
            statement.setInt(2, Commentaire.getPostId());
            statement.setString(3, Commentaire.getContent());
            statement.setInt(4, Commentaire.getUserId());
            statement.setDate(5,  Commentaire.getDate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Commentaire> getAll() {
        ArrayList<Commentaire> Commentaires = new ArrayList<>();
        String query = "SELECT * FROM `Commentaires`";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Commentaire Commentaire = new Commentaire();
                Commentaire.setCommentaireId(resultSet.getInt("Commentaire_id"));
                Commentaire.setPostId(resultSet.getInt("post_id"));
                Commentaire.setContent(resultSet.getString("content"));
                Commentaire.setUserId(resultSet.getInt("user_id"));
                Commentaire.setDate(resultSet.getDate("date"));

                Commentaires.add(Commentaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Commentaires;
    }

    @Override
    public void update(Commentaire Commentaire) {
        String query = "UPDATE `Commentaires` SET `post_id`=?, `content`=?, `user_id`=? WHERE `Commentaire_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Commentaire.getPostId());
            statement.setString(2, Commentaire.getContent());
            statement.setInt(3, Commentaire.getUserId());
            statement.setInt(4, Commentaire.getCommentaireId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Commentaire Commentaire) {
        String query = "DELETE FROM `Commentaires` WHERE `Commentaire_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Commentaire.getCommentaireId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    public void sortByDate(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparing(Commentaire::getDate)); // Assuming there's a getDate() method in Commentaire class

    }
    public void sortByUser(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparingInt(Commentaire::getUserId));}


    public void sortByLength(ArrayList<Commentaire> commentaires) {
        Collections.sort(commentaires, Comparator.comparingInt(commentaire -> commentaire.getContent().length()));
    }














  /*
    public ArrayList<Commentaire> getCommentsByDate(Date targetDate) {
        ArrayList<Commentaire> Commentaires = new ArrayList<>();
        String query = "SELECT * FROM `Commentaires` WHERE `date` = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, targetDate);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Commentaire Commentaire = new Commentaire();
                    Commentaire.setCommentaireId(resultSet.getInt("Commentaire_id"));
                    Commentaire.setPostId(resultSet.getInt("post_id"));
                    Commentaire.setContent(resultSet.getString("content"));
                    Commentaire.setUserId(resultSet.getInt("user_id"));
                    Commentaire.setDate(resultSet.getDate("date"));

                    Commentaires.add(Commentaire);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Commentaires;
    }*/
    public ArrayList<Commentaire> getCommentsByDate() {
    ArrayList<Commentaire> Commentaires = new ArrayList<>();
    String query = "SELECT * FROM `Commentaires`";
        try (PreparedStatement statement = connection.prepareStatement(query);
    ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
            Commentaire Commentaire = new Commentaire();
            Commentaire.setCommentaireId(resultSet.getInt("Commentaire_id"));
            Commentaire.setPostId(resultSet.getInt("post_id"));
            Commentaire.setContent(resultSet.getString("content"));
            Commentaire.setUserId(resultSet.getInt("user_id"));
            Commentaire.setDate(resultSet.getDate("date"));

            Commentaires.add(Commentaire);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return Commentaires;
}












    public int getUserIdByCommentId(int commentId) {
        String query = "SELECT user_id FROM Commentaires WHERE Commentaire_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, commentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // or handle appropriately if not found
    }

    public Map<Integer, Integer> getCommentCountPerUser() {
        Map<Integer, Integer> commentCountPerUser = new HashMap<>();
        String query = "SELECT user_id, COUNT(*) AS comment_count FROM Commentaires GROUP BY user_id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int commentCount = resultSet.getInt("comment_count");
                commentCountPerUser.put(userId, commentCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentCountPerUser;
    }
}
