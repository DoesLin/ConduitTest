package conduit.test.repository.dao;

import javax.persistence.*;
import java.util.List;

@Entity
//@DiscriminatorValue("vendeur")
@Table(name = "vendeur")
public class DaoVendeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vendeur")
    private List<DaoArticle> listeArticles;

    @ManyToOne
    private DaoChefMagasin chefMagasin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DaoArticle> getListeArticles() {
        return listeArticles;
    }

    public void setListeArticles(List<DaoArticle> listeArticles) {
        this.listeArticles = listeArticles;
    }

    public DaoChefMagasin getChefMagasin() {
        return chefMagasin;
    }

    public void setChefMagasin(DaoChefMagasin chefMagasin) {
        this.chefMagasin = chefMagasin;
    }
}
