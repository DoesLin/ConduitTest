package conduit.test.model;


import java.util.List;

public class ChefMagasin extends Account {
    private String status;
    private List<Vendeur> listeVendeurs;

    public ChefMagasin(String status) {
        super(status);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Vendeur> getListeVendeurs() {
        return listeVendeurs;
    }

    public void setListeVendeurs(List<Vendeur> listeVendeurs) {
        this.listeVendeurs = listeVendeurs;
    }

}
