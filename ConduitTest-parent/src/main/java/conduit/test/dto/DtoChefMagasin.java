package conduit.test.dto;

import conduit.test.dao.impl.DaoChefMagasin;

import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoChefMagasin extends DtoAccount {

//    private List<DtoVendeur> listeVendeurs;

    public DtoChefMagasin() {
        super();
    }

    /**
     * Username, ..., ChefMagasin est copie aussi
     * id, listeVendeurs ne sont pas copies
     * @param chefMagasin
     */
    public DtoChefMagasin(DaoChefMagasin chefMagasin) {
        super();
        this.setUsername(chefMagasin.getUsername());
        this.setManagername(null);
        this.setRole("ChefMagasin");
    }

//    public List<DtoVendeur> getListeVendeurs() {
//        return listeVendeurs;
//    }

//    public void setListeVendeurs(List<DtoVendeur> listeVendeurs) {
//        this.listeVendeurs = listeVendeurs;
//    }
}
