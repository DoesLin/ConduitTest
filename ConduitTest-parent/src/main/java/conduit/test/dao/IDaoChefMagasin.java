package conduit.test.dao;

import conduit.test.dao.impl.DaoChefMagasin;
import conduit.test.dao.impl.DaoAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoChefMagasin extends JpaRepository<DaoChefMagasin, Integer> {

    DaoChefMagasin findByUsername(String username);
}