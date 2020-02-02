package conduit.test.dto;

import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoPdg;
import conduit.test.repository.dao.DaoVendeur;

import java.util.ArrayList;
import java.util.List;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoPdg extends DtoAccount {

    private List<DtoChefMagasin> listeChefMagasins;

    public DtoPdg() {
        super();
        listeChefMagasins = new ArrayList<>();
    }

    /**
     * Username, ..., ChefMagasin, listeVendeurs est copie aussi
     * id n'est' pas copies
     * @param pdg
     */
    public DtoPdg(DaoPdg pdg) {
        super();
        this.setUsername(pdg.getUsername());
        this.setManagername(null);
        this.setRole("Pdg");
        List<DaoChefMagasin> listDaoChefMagasins = pdg.getListeChefMagasins();
        listeChefMagasins = new ArrayList<>();
        for (DaoChefMagasin daoChefMagasin : listDaoChefMagasins) {
            DtoChefMagasin vendeur = new DtoChefMagasin(daoChefMagasin);
            listeChefMagasins.add(vendeur);
        }
    }

    public List<DtoChefMagasin> getListeChefMagasins() {
        return listeChefMagasins;
    }

    public void setListeChefMagasins(List<DtoChefMagasin> listeChefMagasins) {
        this.listeChefMagasins = listeChefMagasins;
    }
}
