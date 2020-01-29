package conduit.test.repository;

import conduit.test.repository.dao.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendeurRepo extends JpaRepository<DaoVendeur, Long> {
//public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DaoVendeur findByUsername(String username);

    @Query(value = "SELECT * FROM vendeur v WHERE v.id = :id and v.chef_magasin_id = :chef_magasin_id",
            nativeQuery = true)
    DaoVendeur findByIdAndChefMagasinId(
            @Param("id") Long id, @Param("chef_magasin_id") Long chef_magasin_id);
}