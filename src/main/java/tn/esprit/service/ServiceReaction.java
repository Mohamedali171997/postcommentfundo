package tn.esprit.service;

import tn.esprit.interfaces.IService;
import tn.esprit.modeles.Reaction;
import tn.esprit.modeles.ReactionType;
import tn.esprit.utile.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class ServiceReaction implements IService<Reaction> {
    private Connection connection;

    public ServiceReaction() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void add(Reaction reaction) {
        String query = "INSERT INTO `reactions`(`type`, `user_id`, `target_id`) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, reaction.getType().name());
            statement.setInt(2, reaction.getUserId());
            statement.setInt(3, reaction.getTargetId());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                reaction.setReactionId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Reaction> getAll() {
        ArrayList<Reaction> reactions = new ArrayList<>();
        String query = "SELECT * FROM `reactions`";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Reaction reaction = new Reaction();
                reaction.setReactionId(resultSet.getInt("reaction_id"));
                reaction.setType(ReactionType.valueOf(resultSet.getString("type")));
                reaction.setUserId(resultSet.getInt("user_id"));
                reaction.setTargetId(resultSet.getInt("target_id"));

                reactions.add(reaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reactions;
    }

    @Override
    public void update(Reaction reaction) {
        String query = "UPDATE `reactions` SET `type`=? WHERE `reaction_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reaction.getType().name());
            statement.setInt(2, reaction.getReactionId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Reaction reaction) {
        String query = "DELETE FROM `reactions` WHERE `reaction_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reaction.getReactionId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Reaction> getReactionsByTargetId(int targetId) {
        ArrayList<Reaction> targetReactions = new ArrayList<>();
        String query = "SELECT * FROM `reactions` WHERE `target_id`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, targetId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reaction reaction = new Reaction();
                    reaction.setReactionId(resultSet.getInt("reaction_id"));
                    reaction.setType(ReactionType.valueOf(resultSet.getString("type")));
                    reaction.setUserId(resultSet.getInt("user_id"));
                    reaction.setTargetId(resultSet.getInt("target_id"));

                    targetReactions.add(reaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return targetReactions;
    }
    public int getReactionIdByCriteria(int userId, int targetId, ReactionType type) {
        int reactionId = -1; // Default value if no matching reaction is found
        String query = "SELECT `reaction_id` FROM `reactions` WHERE `user_id`=? AND `target_id`=? AND `type`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, targetId);
            statement.setString(3, type.name());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reactionId = resultSet.getInt("reaction_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reactionId;
    }
    public ArrayList<Integer> getUserIdsByReactionType(ReactionType type) {
        ArrayList<Integer> userIds = new ArrayList<>();
        String query = "SELECT DISTINCT `user_id` FROM `reactions` WHERE `type`=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, type.name());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    userIds.add(userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
    }

    // Other methods for ServiceReaction class
    // getReactionsForPost, getReactionsForComment, etc.
}
