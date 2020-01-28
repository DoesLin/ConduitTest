package conduit.test.dto;

import conduit.test.dao.impl.DaoArticle;

public class DtoArticle {

    private String serial;
    private String name;
    private String categorie;
    private String description;
    private double prix;
    private int quantite;

    //    private DtoVendeur vendeur;
    private String vendeurName;

    public DtoArticle() {}

    /**
     * Id, ..., Vendeur est copie aussi
     * @param article
     */
    public DtoArticle(DaoArticle article) {

        this.setSerial(article.getSerial());
        this.setName(article.getName());
        this.setCategorie(article.getCategorie());
        this.setDescription(article.getDescription());
        this.setPrix(article.getPrix());
        this.setQuantite(article.getQuantite());
        this.setVendeurName(article.getVendeur().getUsername());
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public String getVendeurName() {
        return vendeurName;
    }

    public void setVendeurName(String vendeurName) {
        this.vendeurName = vendeurName;
    }

//    public DtoVendeur getVendeur() {
//        return vendeur;
//    }
//
//    public void setVendeur(DtoVendeur vendeur) {
//        this.vendeur = vendeur;
//    }
}
