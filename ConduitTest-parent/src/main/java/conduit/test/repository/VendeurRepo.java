package conduit.test.repository;

import conduit.test.repository.dao.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendeurRepo extends JpaRepository<DaoVendeur, Integer> {

    DaoVendeur findByUsername(String username);

    @Query(value = "SELECT * FROM vendeur v WHERE v.username = :username and v.chef_magasin_id = :chef_magasin_id",
            nativeQuery = true)
    DaoVendeur findByUsernameAndChefMagasinId(
            @Param("username") String username, @Param("chef_magasin_id") Integer chef_magasin_id);

    @Modifying
    @Query(value = "DELETE FROM vendeur WHERE username = :username",
            nativeQuery = true)
    Integer deleteByUsername(@Param("username") String username);
}