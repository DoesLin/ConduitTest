package conduit.test.repository;

import conduit.test.repository.dao.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendeurRepo extends JpaRepository<DaoVendeur, Integer> {
//public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DaoVendeur findByUsername(String username);
}