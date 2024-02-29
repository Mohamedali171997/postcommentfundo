package tn.esprit.service;

import tn.esprit.interfaces.IService;
import tn.esprit.modeles.Post;
import tn.esprit.utile.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ServicePost implements IService<Post> {
    private Connection connection;

    public ServicePost() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void add(Post post) {
        String query = "INSERT INTO `posts`(`post_id`, `title`, `content`, `author_id`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, post.getPostId());  // Assuming you have set the post_id in your Post object
            statement.setString(2, post.getTitle());
            statement.setString(3, post.getContent());
            statement.setInt(4, post.getAuthorId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setPostId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }










    @Override
    public ArrayList<Post> getAll() {
        ArrayList<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM `posts`";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Post post = new Post();
                post.setPostId(resultSet.getInt("post_id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setAuthorId(resultSet.getInt("author_id"));

                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public void update(Post post) {
        String query = "UPDATE `posts` SET `title`=?, `content`=?, `author_id`=? WHERE `post_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setInt(3, post.getAuthorId());
            statement.setInt(4, post.getPostId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Post post) {
        String query = "DELETE FROM `posts` WHERE `post_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, post.getPostId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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


}
