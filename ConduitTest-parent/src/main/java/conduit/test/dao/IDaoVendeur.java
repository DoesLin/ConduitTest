package conduit.test.dao;

import conduit.test.model.DaoVendeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDaoVendeur extends JpaRepository<DaoVendeur, Integer> {
//public interface UserDao extends CrudRepository<DAOUser, Integer> {

    DaoVendeur findByUsername(String username);
}