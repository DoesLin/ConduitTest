package conduit.test.repository;

import conduit.test.repository.dao.DaoChefMagasin;
import conduit.test.repository.dao.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Chef de magasin can be consulted by all pdg (supposed as only one pdg)
 */
@Repository
public interface ChefMagasinRepo extends JpaRepository<DaoChefMagasin, Integer> {

    DaoChefMagasin findByUsername(String username);

//    @Query(value = "SELECT * FROM chef_magasin c WHERE c.username = :username and c.pdg_id = :pdg_id",
//            nativeQuery = true)
//    DaoChefMagasin findByUsernameAndPdgId(
//            @Param("username") String username, @Param("pdg_id") Integer pdg_id);

    @Modifying
    @Query(value = "DELETE FROM chef_magasin WHERE username = :username",
            nativeQuery = true)
    Integer deleteByUsername(@Param("username") String username);
}