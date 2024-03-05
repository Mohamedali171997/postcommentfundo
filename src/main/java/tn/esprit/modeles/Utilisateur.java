package tn.esprit.modeles;

public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int telephone;
    private String adresse;
    private Roles role;






    public Utilisateur(int id, String nom, String prenom, String email, int telephone, String adresse, Roles role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = Roles.valueOf(String.valueOf(role));

    }



    public Utilisateur(String nom, String prenom, String email, int telephone, String adresse, Roles role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = Roles.valueOf(String.valueOf(role));

    }

    public Utilisateur(String nom, String prenom, String email, int telephone, String adresse, Roles role,String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = Roles.valueOf(String.valueOf(role));
        this.password = password;

    }


    public Utilisateur(int id,String nom,String prenom,String email,Roles role){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.role = Roles.valueOf(String.valueOf(role));

    }
    public Utilisateur(String nom, String prenom, String email,String password, int telephone, String adresse, Roles role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.adresse = adresse;
        this.role = Roles.valueOf(String.valueOf(role));

    }

    public Utilisateur() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", role=" + role +
                '}';
    }
}
