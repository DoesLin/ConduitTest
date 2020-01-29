package conduit.test.dto;

import conduit.test.repository.dao.DaoArticle;
import conduit.test.repository.dao.DaoVendeur;

import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoVendeur extends DtoAccount {

    private List<DtoArticle> listeArticles;

//    private DtoChefMagasin chefMagasin;

    public DtoVendeur() {
        super();
    }

    /**
     * Username, ..., ChefMagasin est copie aussi
     * id, listeArticles ne sont pas copies
     * @param vendeur
     */
    public DtoVendeur(DaoVendeur vendeur) {
        super();
        this.setUsername(vendeur.getUsername());
        this.setManagername(vendeur.getChefMagasin().getUsername());
        this.setRole("Vendeur");
        List<DaoArticle> listDaoArticles = vendeur.getListeArticles();
        for (DaoArticle daoArticle : listDaoArticles) {
            DtoArticle article = new DtoArticle(daoArticle);
            listeArticles.add(article);
        }
//        this.setChefMagasin(new DtoChefMagasin(vendeur.getChefMagasin()));
    }

    public List<DtoArticle> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<DtoArticle> listeArticles) {
        this.listeArticles = listeArticles;
    }

//    public DtoChefMagasin getChefMagasin() {
//        return chefMagasin;
//    }
//
//    public void setChefMagasin(DtoChefMagasin chefMagasin) {
//        this.chefMagasin = chefMagasin;
//    }
}