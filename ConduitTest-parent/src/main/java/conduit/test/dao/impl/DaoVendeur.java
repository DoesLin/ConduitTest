package conduit.test.dao.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
//@DiscriminatorValue("vendeur")
@Table(name = "vendeur")
public class DaoVendeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @ManyToOne
    private DaoChefMagasin chefMagasin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DaoChefMagasin getChefMagasin() {
        return chefMagasin;
    }

    public void setChefMagasin(DaoChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }
}
