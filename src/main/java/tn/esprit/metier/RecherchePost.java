package tn.esprit.metier;


import tn.esprit.modeles.Post;
import java.sql.*;
import java.util.ArrayList;

public class RecherchePost {
    private Connection connection;

    public RecherchePost(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Post> searchByTitle(String keyword) {
        ArrayList<Post> matchingPosts = new ArrayList<>();
        String query = "SELECT * FROM `posts` WHERE LOWER(`title`) LIKE LOWER(?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post();
                    post.setPostId(resultSet.getInt("post_id"));
                    post.setTitle(resultSet.getString("title"));
                    post.setContent(resultSet.getString("content"));
                    post.setAuthorId(resultSet.getInt("author_id"));

                    matchingPosts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchingPosts;
    }

    // You can add more search methods based on different criteria
}
