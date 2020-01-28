package conduit.test.dao.impl;

import javax.persistence.*;

@Entity
@DiscriminatorValue("vendeur")
//@Table(name = "vendeur")
public class DaoVendeur extends DaoAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private DaoChefMagasin chefMagasin;

    public DaoChefMagasin getChefMagasin() {
        return chefMagasin;
    }

    public void setChefMagasin(DaoChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }
}
