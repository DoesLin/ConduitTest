package conduit.test.repository;

import conduit.test.repository.dao.DaoChefMagasin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefMagasinRepo extends JpaRepository<DaoChefMagasin, Integer> {

    DaoChefMagasin findByUsername(String username);
}