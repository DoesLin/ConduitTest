package conduit.test.repository.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chef_magasin")
public class DaoChefMagasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "chefMagasin")
    private List<DaoVendeur> listeVendeurs;

    @ManyToOne
    private DaoPdg pdg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public DaoPdg getPdg() {
        return pdg;
    }

    public void setPdg(DaoPdg pdg) {
        this.pdg = pdg;
    }
}
