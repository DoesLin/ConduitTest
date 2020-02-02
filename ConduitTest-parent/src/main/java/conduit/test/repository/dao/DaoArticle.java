package conduit.test.repository.dao;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class DaoArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String serial;
    private String name;
    private String categorie;
    private String description;
    private double prix;
    private int quantite;

    @ManyToOne
    private DaoVendeur vendeur;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public DaoVendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(DaoVendeur vendeur) {
        this.vendeur = vendeur;
    }

}
