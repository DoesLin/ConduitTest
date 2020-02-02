package conduit.test.dto;

import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;

import java.util.ArrayList;
import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoChefMagasin extends DtoAccount {

    private List<DtoVendeur> listeVendeurs;

    public DtoChefMagasin() {
        super();
        listeVendeurs = new ArrayList<>();
    }

    /**
     * Username, ..., ChefMagasin, listeVendeurs est copie aussi
     * id n'est' pas copies
     * @param chefMagasin
     */
    public DtoChefMagasin(DaoChefMagasin chefMagasin) {
        super();
        this.setUsername(chefMagasin.getUsername());
        this.setManagername(chefMagasin.getPdg().getUsername());
        this.setRole("ChefMagasin");
        List<DaoVendeur> listDaoVendeurs = chefMagasin.getListeVendeurs();
        listeVendeurs = new ArrayList<>();
        for (DaoVendeur daoVendeur : listDaoVendeurs) {
            DtoVendeur vendeur = new DtoVendeur(daoVendeur);
            listeVendeurs.add(vendeur);
        }
    }

    public List<DtoVendeur> getListeVendeurs() {
        return listeVendeurs;
    }

    public void setListeVendeurs(List<DtoVendeur> listeVendeurs) {
        this.listeVendeurs = listeVendeurs;
    }
}
