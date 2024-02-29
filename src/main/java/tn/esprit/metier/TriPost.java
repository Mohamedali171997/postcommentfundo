package tn.esprit.metier;

import tn.esprit.modeles.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TriPost {

    public void sortByTitle(ArrayList<Post> posts) {
        Collections.sort(posts, Comparator.comparing(Post::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public void sortByAuthor(ArrayList<Post> posts) {
        Collections.sort(posts, Comparator.comparingInt(Post::getAuthorId));
    }

    public void sortByContentLength(ArrayList<Post> posts) {
        Collections.sort(posts, Comparator.comparingInt(post -> post.getContent().length()));
    }

}

