package tn.esprit.test;

import tn.esprit.metier.RechercheCommentaire;
import tn.esprit.metier.RecherchePost;
import tn.esprit.metier.TriCommentaire;
import tn.esprit.modeles.Commentaire;
import tn.esprit.modeles.Post;
import tn.esprit.modeles.Reaction;
import tn.esprit.service.ServiceCommentaire;
import tn.esprit.service.ServicePost;
import tn.esprit.service.ServiceReaction;
import tn.esprit.utile.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Post p1 = new Post(1000,"ben mohamed ", "mohamed ",20);
        Post p2 = new Post(8945,"ben mohamed ", "mohamed aziz ",35);


      //  ServicePersonne sp = new ServicePersonne();
        //sp.add(p1);
        //sp.add(p2);
        //sp.add(p3);

        Scanner scanner = new Scanner(System.in);

        // Test method to fill Commentaire with user input
        Commentaire newCommentaire = fillCommentaireWithUserInput(scanner);
        System.out.println("New Commentaire details:\n" + newCommentaire);
       // System.out.println(sp.getAll());

      //  System.out.println("Hello world!");

        Connection databaseConnection =  DatabaseConnection.getInstance().getConnection();
                 RecherchePost postSearch = new RecherchePost(databaseConnection);

        // Search posts by title
        ArrayList<Post> matchingPosts = postSearch.searchByTitle("exemple");

        // Process the matching posts as needed
        for (Post post : matchingPosts) {
            System.out.println(post);
          /*  String sdf = "01/11/2022";
            Date date = (Date) sdf.chars();

            Commentaire c = new Commentaire(11,18,"nice work",02, (java.sql.Date) date);*/

        }

        String sdf = "2015-03-31";
        Date date = Date.valueOf(sdf);

       Commentaire c2 = new Commentaire(13,18,"aWSSOM",02,  date);
       /*  Commentaire c= new Commentaire();
        c.setContent("nice");
        c.setCommentaireId(12);
        c.setUserId(45);
        c.setPostId(56);
        c.setDate(date);*/
        ServiceCommentaire s =new ServiceCommentaire();
      //  s.add(c);
        s.add(c2);
        RechercheCommentaire r = new RechercheCommentaire(databaseConnection);
        TriCommentaire tr = new TriCommentaire();
       // System.out.println("/n" +r.searchByUserId(11));
System.out.println(s.getAll());
System.out.println(r.searchByContent("00"));

//System.out.println(tr.sortByDate(r.searchByContent("nice")));



        //


        TriCommentaire triCommentaire = new TriCommentaire();
        ArrayList<Commentaire> commentaires = getCommentaires(); // Assume you have a method to retrieve commentaires

        // Sort by date
        triCommentaire.sortByDate(commentaires);

        // Sort by user
        triCommentaire.sortByUser(commentaires);

        // Sort by content length
        triCommentaire.sortByLength(commentaires);

        // Print sorted commentaires
        for (Commentaire commentaire : commentaires) {
            System.out.println(commentaire);
        }
    }

    // Assume you have a method to retrieve commentaires
    private static ArrayList<Commentaire> getCommentaires() {
        // Implement logic to retrieve commentaires from your data source
        return new ArrayList<>();



    }


   /* private static Scanner scanner = new Scanner(System.in);
    String sdf = "2015-03-31";
    Date date = Date.valueOf(sdf);
    Commentaire c2 = new Commentaire(13,18,"aWSSOM",02,  date);
    private static void runConsoleApp(Post Post, Commentaire Commentaire, Reaction Reaction) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Test Post methods");
            System.out.println("2. Test Commentaire methods");
            System.out.println("3. Test Reaction methods");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ServicePost.add(Post);
                    break;
                case 2:
                    testCommentaireMethods(serviceCommentaire);
                    break;
                case 3:
                    testReactionMethods(serviceReaction);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static void testAddCommentaire(ServiceCommentaire serviceCommentaire, Scanner scanner) {
        System.out.println("Enter Commentaire details:");
        System.out.print("Post ID: ");
        int postId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        System.out.print("Content: ");
        String content = scanner.nextLine();
        System.out.print("User ID: ");
        int userId = scanner.nextInt();

        Commentaire commentaire = new Commentaire(postId, content, userId);
        serviceCommentaire.add(commentaire);
        System.out.println("Commentaire added successfully.");
    }

    private static void testGetAllCommentaires(ServiceCommentaire serviceCommentaire) {
        ArrayList<Commentaire> commentaires = serviceCommentaire.getAll();
        System.out.println("All Commentaires:");
        for (Commentaire commentaire : commentaires) {
            System.out.println(commentaire);
        }
    }

    private static void testUpdateCommentaire(ServiceCommentaire serviceCommentaire, Scanner scanner) {
        System.out.print("Enter Commentaire ID to update: ");
        int commentaireId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Commentaire existingCommentaire = serviceCommentaire.getById(commentaireId);
        if (existingCommentaire != null) {
            System.out.println("Enter new details for Commentaire:");
            System.out.print("New Content: ");
            String newContent = scanner.nextLine();

            existingCommentaire.setContent(newContent);
            serviceCommentaire.update(existingCommentaire);
            System.out.println("Commentaire updated successfully.");
        } else {
            System.out.println("Commentaire with ID " + commentaireId + " not found.");
        }
    }

    private static void testDeleteCommentaire(ServiceCommentaire serviceCommentaire, Scanner scanner) {
        System.out.print("Enter Commentaire ID to delete: ");
        int commentaireId = scanner.nextInt();

        Commentaire existingCommentaire = serviceCommentaire.getById(commentaireId);
        if (existingCommentaire != null) {
            boolean success = serviceCommentaire.delete(existingCommentaire);
            if (success) {
                System.out.println("Commentaire deleted successfully.");
            } else {
                System.out.println("Failed to delete Commentaire.");
            }
        } else {
            System.out.println("Commentaire with ID " + commentaireId + " not found.");
        }
    }*/


    private static Commentaire fillCommentaireWithUserInput(Scanner scanner) {
        System.out.print("Enter Commentaire ID: ");
        int CommentaireId = scanner.nextInt();

        System.out.print("Enter Post ID: ");
        int postId = scanner.nextInt();

        System.out.print("Enter Content: ");
        scanner.nextLine(); // Consume the newline
        String content = scanner.nextLine();

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();

        // Assuming you want to set the current date
        Commentaire commentaire = new Commentaire(CommentaireId, postId, content, userId, new java.sql.Date(System.currentTimeMillis()));
        ServiceCommentaire serviceCommentaire = new ServiceCommentaire();
        serviceCommentaire.add(commentaire);
        return commentaire;

    }
}



