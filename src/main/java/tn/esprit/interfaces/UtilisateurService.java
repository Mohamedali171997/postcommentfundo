package tn.esprit.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface UtilisateurService<U> {
    void ajouter(U u) throws SQLException;

    void modifier(U u) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<U> recuperer() throws SQLException;

}
