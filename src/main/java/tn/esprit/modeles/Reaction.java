package tn.esprit.modeles;

public class Reaction {
    private int reactionId;
    private ReactionType type;
    private int userId;
    private int targetId; // Either post ID or comment ID
    // Other attributes...

    // Constructors
    public Reaction() {
    }

    public Reaction(int reactionId, ReactionType type, int userId, int targetId) {
        this.reactionId = reactionId;
        this.type = type;
        this.userId = userId;
        this.targetId = targetId;
    }

    // Getters and setters
    public int getReactionId() {
        return reactionId;
    }

    public void setReactionId(int reactionId) {
        this.reactionId = reactionId;
    }

    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    // Other methods, if needed...

    @Override
    public String toString() {
        return "Reaction{" +
                "reactionId=" + reactionId +
                ", type=" + type +
                ", userId=" + userId +
                ", targetId=" + targetId +
                '}';
    }
}



