package conduit.test.model;

public class Vendeur extends Account {
    private String status;
    private ChefMagasin chefMagasin;

    public Vendeur(String status) {
        super(status);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ChefMagasin getChefMagasin() {
        return chefMagasin;
    }

    public void setChefMagasin(ChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }

}
