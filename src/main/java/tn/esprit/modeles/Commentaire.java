package tn.esprit.modeles;

import java.sql.Date;

//import java.util.Date;

public class Commentaire {

    private int CommentaireId;
    private int postId;
    private String content;
    private Date date;
    private int userId; // Assuming a user ID associated with the Commentaire


    public Commentaire() {
    }

    public Commentaire(int CommentaireId, int postId, String content, int userId ,Date date) {
        this.CommentaireId = CommentaireId;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.date = date;

    }

    public int getCommentaireId() {
        return CommentaireId;
    }

    public void setCommentaireId(int CommentaireId) {
        this.CommentaireId = CommentaireId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "CommentaireId=" + CommentaireId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                "}\n";
    }
}
