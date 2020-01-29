package conduit.test.repository.dao;

import javax.persistence.*;
import java.util.List;

@Entity
//@DiscriminatorValue("chef_magasin")
@Table(name = "chef_magasin")
public class DaoChefMagasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chefMagasin")
    private List<DaoVendeur> listeVendeurs;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DaoVendeur> getListeVendeurs() {
        return listeVendeurs;
    }

    public void setListeVendeurs(List<DaoVendeur> listeVendeurs) {
        this.listeVendeurs = listeVendeurs;
    }
}
