package conduit.test.dto;

import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoChefMagasin extends DtoAccount {

    private List<DtoVendeur> listeVendeurs;

    public List<DtoVendeur> getListeVendeurs() {
        return listeVendeurs;
    }

    public void setListeVendeurs(List<DtoVendeur> listeVendeurs) {
        this.listeVendeurs = listeVendeurs;
    }
}
