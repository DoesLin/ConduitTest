package conduit.test.dto;

import conduit.test.repository.dao.DaoArticle;
import conduit.test.repository.dao.DaoVendeur;

import java.util.ArrayList;
import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoVendeur extends DtoAccount {

    private List<DtoArticle> listeArticles;

    public DtoVendeur() {
        super();
        listeArticles = new ArrayList<>();
    }

    /**
     * Username, ..., ChefMagasin, listeArticles est copie aussi
     * id n'est pas copies
     * @param vendeur
     */
    public DtoVendeur(DaoVendeur vendeur) {
        super();
        this.setUsername(vendeur.getUsername());
        this.setManagername(vendeur.getChefMagasin().getUsername());
        this.setRole("Vendeur");
        List<DaoArticle> listDaoArticles = vendeur.getListeArticles();
        listeArticles = new ArrayList<>();
        for (DaoArticle daoArticle : listDaoArticles) {
            DtoArticle article = new DtoArticle(daoArticle);
            listeArticles.add(article);
        }
    }

    public List<DtoArticle> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<DtoArticle> listeArticles) {
        this.listeArticles = listeArticles;
    }

}
