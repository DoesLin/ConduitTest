package conduit.test.dao.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("chef_magasin")
//@Table(name = "chef_magasin")
public class DaoChefMagasin extends DaoAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chefMagasin")
    private List<DaoVendeur> listeVendeurs;

    public List<DaoVendeur> getListeVendeurs() {
        return listeVendeurs;
    }

    public void setListeVendeurs(List<DaoVendeur> listeVendeurs) {
        this.listeVendeurs = listeVendeurs;
    }
}
