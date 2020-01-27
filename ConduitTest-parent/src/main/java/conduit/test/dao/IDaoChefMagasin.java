package conduit.test.dao;

import conduit.test.model.DaoChefMagasin;
import conduit.test.model.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoChefMagasin extends JpaRepository<DaoChefMagasin, Integer> {

    DaoVendeur findByUsername(String username);
}