package conduit.test.dto;

/*
 * It is responsible for getting values from user and passing it to the DAO layer for inserting in database.
 */
public class DtoVendeur extends DtoAccount {
    private DtoChefMagasin chefMagasin;

    public DtoChefMagasin getChefMagasin() {
        return chefMagasin;
    }

    public void setChefMagasin(DtoChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }
}
