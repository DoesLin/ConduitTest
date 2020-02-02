package conduit.test.repository.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pdg")
public class DaoPdg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pdg")
    private List<DaoChefMagasin> listeChefMagasins;

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

    public List<DaoChefMagasin> getListeChefMagasins() {
        return listeChefMagasins;
    }

    public void setListeChefMagasins(List<DaoChefMagasin> listeChefMagasins) {
        this.listeChefMagasins = listeChefMagasins;
    }
}
